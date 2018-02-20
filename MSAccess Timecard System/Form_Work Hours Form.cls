VERSION 1.0 CLASS
BEGIN
  MultiUse = -1  'True
END
Attribute VB_Name = "Form_Work Hours Form"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = True
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Compare Database

' What happens when the add button is clicked
Private Sub addButton_Click()
    'there are two options, adding and editing
    'check caption of addButton to see which we are doing
    
    'if button says add, do add functionality
    If Me.addButton.Caption & "" = "Add" Then
    
        ' check if work description is null, set it to a blank value if null
        ' if you use "", it won't add for some reason
        If IsNull(Me.Work_Description) Then
        
            Me.Work_Description.Value = " "
            
        End If

        'build add query
        Query = "INSERT INTO [Work Hours] ([Employee], [Date Worked], [Hours], [Department], [Work Code], [Company], [Project], [Work Description], [Created]) " & _
            "VALUES ('" & Me.Employee & _
            "','" & Me.Date_Worked & _
            "','" & Me.Hours & _
            "','" & Me.Department & _
            "','" & Me.Work_Code & _
            "','" & Me.Company & _
            "','" & Me.Project & _
            "','" & Replace(Me.Work_Description, "'", "''") & _
            "',#" & Now & "#)"
        
    'button wasn't add, it was update
    Else
        
        'build update query
        Query = "UPDATE [Work Hours] " & _
            "SET [Employee]='" & Me.Employee & _
            "', [Hours]='" & Me.Hours & _
            "', [Date Worked]='" & Me.Date_Worked & _
            "', [Department]='" & Me.Department & _
            "', [Work Code]='" & Me.Work_Code & _
            "', [Company]='" & Me.Company & _
            "', [Project]='" & Me.Project & _
            "', [Work Description]='" & Replace(Me.Work_Description, "'", "''") & _
            "', [Last Modified]=#" & Now & _
            "# WHERE ID=" & Me.ID.Tag
            
    End If
    
    'debug
    'Debug.Print Query
    
    'temporarily suspending annoying confirmation message
    DoCmd.SetWarnings False
    
    'add data to table
    DoCmd.RunSQL Query
    
    'turn warning message back on
    DoCmd.SetWarnings True
    
    'refresh the list form
    Work_Hours_subform.Requery
    
    'reset form
    clearButton_Click
    
End Sub

'What happens when clearButton is clicked
Private Sub clearButton_Click()

    'clear all fields
    Me.ID = ""
    Me.Employee = ""
    Me.Date_Worked = ""
    Me.Work_Code = ""
    Me.Hours = ""
    Me.Department = ""
    Me.Company = ""
    Me.Project = ""
    Me.Work_Description = ""
    
    'focus on employee
    Me.Employee.SetFocus
    
    'set edit button enabled
    Me.editButton.Enabled = True
    
    'change caption of add button back to add
    Me.addButton.Caption = "Add"
    
    'change caption of clear button back to clear
    Me.clearButton.Caption = "Clear"
    
    'clear temp tag
    Me.ID.Tag = ""
    
End Sub

'What happens when closeButton is clicked
Private Sub closeButton_Click()

    'close whole program
    Application.Quit
    
End Sub

' What happens when the company is changed
Private Sub Company_Change()

    ' Sets the row source based on the company selected
    Me.Department.RowSource = "SELECT Departments.ID, Departments.Description FROM Departments WHERE Departments.Active = -1 AND Departments.[" & Me.Company.Column(1) & "] = -1 ORDER BY Departments.Description"

End Sub

' What happens when the department is changed
' Sets the row source based on the department selected
Private Sub Department_Change()
    
    ' use the string in the combo box to find the column and whether it's active or not
    Me.Work_Code.RowSource = "SELECT [Work Codes].ID, [Work Codes].Description FROM [Work Codes] WHERE [Work Codes].Active = -1 AND [Work Codes].[" & Me.Department.Column(1) & "]=-1 ORDER BY [Work Codes].Description"
    
End Sub

'What happens when Date_Worked field gets the focus
'Basically, if it's blank, put today's date in it
'If it's not blank, leave it the way it is
Private Sub Date_Worked_GotFocus()

    'if date worked is blank, set it to today's date, else do nothing
    If Me.Date_Worked & "" = "" Then

        'set the date
        Me.Date_Worked = Format(Now(), "mm/dd/yyyy")

    End If
    
End Sub

' What happens when the form is loaded
' Basically just resets the form
Private Sub Form_Load()

    'change caption add button to add
    Me.addButton.Caption = "Add"
    
    'enable edit button
    Me.editButton.Enabled = True
    
    'change caption clear button to clear
    Me.clearButton.Caption = "Clear"
    
    'get id of currently logged in user and set the employee dropdown
    Dim rst As DAO.Recordset
    Dim eid As Integer
    Dim strSQL As String
    strSQL = "SELECT ID FROM Employees WHERE [Full Name]='" & GetUserFullName() & "'"
    Set rst = CurrentDb.OpenRecordset(strSQL)
    eid = rst!ID
    
    ' set the value of the query
    Me.Employee.Value = eid
    
End Sub

' This function returns the full name of the currently logged in user
' Shamelessly stolen from StackOverflow
Function GetUserFullName() As String
    Dim WSHnet, UserName, UserDomain, objUser
    Set WSHnet = CreateObject("WScript.Network")
    UserName = WSHnet.UserName
    UserDomain = WSHnet.UserDomain
    Set objUser = GetObject("WinNT://" & UserDomain & "/" & UserName & ",user")
    GetUserFullName = objUser.FullName
End Function

'What happens when Hours field gets the focus
'Basically, if it's blank, put 15 minutes (0.25) in it
'If it's not blank, leave it the way it is
Private Sub Hours_GotFocus()

    'if hours is blank, set it to 0.25 (15 minutes), else do nothing
    If Me.Hours & "" = "" Then

        'set the date
        Me.Hours = "0.25"

    End If
    
End Sub

' What happens when deleteButton is clicked
' Has a password system so that user won't be able to delete past records
Private Sub deleteButton_Click()
    
    'with statement that refers to the subform
    With Me.Work_Hours_subform.Form.Recordset
    
        'if date is NOT today, go through a password confirmation, then a confirmation to delete
        If Not CDate(.Fields("Date Worked")) = Date Then
        
            'input password
            Dim pass As Integer
            'pass = InputBox("Cannot delete past records without administrator approval.  Enter password to delete record:", "Enter Password")
            pass = Authenticate()
            
            'if password was entered correctly
            If pass = 1 Then
                
                'check if the record exists
                If Not (Me.Work_Hours_subform.Form.Recordset.EOF And Me.Work_Hours_subform.Form.Recordset.BOF) Then
                
                    'confirm deletion
                    If MsgBox("Are you sure you want to delete record " & .Fields("ID") & "?", vbYesNo) = vbYes Then
                    
                        'build query and delete
                        Query = "DELETE FROM [Work Hours] WHERE ID=" & Me.Work_Hours_subform.Form.Recordset.Fields("ID")
                        
                        'temporarily suspending confirmation message
                        DoCmd.SetWarnings False
                        
                        'adding data to table
                        DoCmd.RunSQL Query
                        
                        'turn warning message back on
                        DoCmd.SetWarnings True
                        
                        'refresh the list form
                        Work_Hours_subform.Requery
                        
                        'reset form
                        clearButton_Click
                        
                    End If 'End confirmation MsgBox
                        
                End If 'End if record exists
                
            'password was entered incorrectly
            Else
            
                'show utter failure
                MsgBox ("You entered an invalid password")

                'leave
                Exit Sub
                
            End If 'End password checking
            
        'date is today, move along like normal
        Else
            
            'check if the record exists
            If Not (Me.Work_Hours_subform.Form.Recordset.EOF And Me.Work_Hours_subform.Form.Recordset.BOF) Then
            
                'confirm deletion
                If MsgBox("Are you sure you want to delete record " & .Fields("ID") & "?", vbYesNo) = vbYes Then
                
                    'build query and delete
                    Query = "DELETE FROM [Work Hours] WHERE ID=" & Me.Work_Hours_subform.Form.Recordset.Fields("ID")
                    
                    'temporarily suspending confirmation message
                    DoCmd.SetWarnings False
                    
                    'adding data to table
                    DoCmd.RunSQL Query
                    
                    'turn warning message back on
                    DoCmd.SetWarnings True
                    
                    'refresh the list form
                    Work_Hours_subform.Requery
                    
                    'reset form
                    clearButton_Click
                    
                End If 'End confirmation MsgBox
                    
            End If 'End if record exists
            
        End If 'End date checking
        
    End With 'End with
    
End Sub

' What happens when editButton is clicked
' Has a password system so that user won't be able to edit past records
Private Sub editButton_Click()

    'with statement that refers to the subform
    With Me.Work_Hours_subform.Form.Recordset
    
        'check if date is today
        If Not CDate(.Fields("Date Worked")) = Date Then
        
            'input password
            Dim pass As Integer
            pass = Authenticate()
            
            'if password was entered correctly
            If pass = 1 Then
            
                'check if data exists in the list
                If Not (Me.Work_Hours_subform.Form.Recordset.EOF And Me.Work_Hours_subform.Form.Recordset.BOF) Then
                
                    'get data to text box controls
                    With Me.Work_Hours_subform.Form.Recordset
                        Me.ID = .Fields("ID")
                        Me.Employee = .Fields("Employee")
                        Me.Date_Worked = CDate(.Fields("Date Worked"))
                        If IsNull(.Fields("Department")) Then
                            Me.Work_Code.RowSource = "SELECT [Work Codes].ID, [Work Codes].Description FROM [Work Codes] WHERE [Work Codes].Active = -1 ORDER BY [Work Codes].Description"
                        Else
                        
                            ' Since .Fields() returns an int, we have to query the DB to get the Description field
                            Dim GetDeptQuery As String
                            GetDeptQuery = "SELECT Description FROM Departments WHERE ID=" & .Fields("Department")
                            Dim myrs As DAO.Recordset
                            Set myrs = CurrentDb.OpenRecordset(GetDeptQuery)
                            Dim desc As String
                            desc = myrs!Description
                        
                            ' set the recordsource for work code based on the changed department (uses the previous block for finding the description
                            Me.Work_Code.RowSource = "SELECT [Work Codes].ID, [Work Codes].Description FROM [Work Codes] WHERE [Work Codes].Active = -1 AND [Work Codes]." & desc & "=-1 ORDER BY [Work Codes].Description;"
                            Me.Department = .Fields("Department")
                        End If
                        If .Fields("Company") = Null Then
                        Else
                            Me.Company = .Fields("Company")
                        End If
                        If .Fields("Project") = Null Then
                        Else
                            Me.Project = .Fields("Project")
                        End If
                        If .Fields("Work Code") = Null Then
                        Else
                            Me.Work_Code.Value = .Fields("Work Code")
                        End If
                        Me.Hours = .Fields("Hours")
                        Me.Work_Description = .Fields("Work Description")
                        
                        'store id in temp tag variable
                        Me.ID.Tag = Me.ID
                        
                        'change caption add button to update
                        Me.addButton.Caption = "Update"
                        
                        'disable edit button
                        Me.editButton.Enabled = False
                        
                        'change caption clear button to cancel
                        Me.clearButton.Caption = "Cancel"
                        
                    End With 'End with referring to subform

                End If 'End if record exists

            'password was entered incorrectly
            Else
            
                'show utter failure
                MsgBox ("You entered an invalid password")
                
                'leave
                Exit Sub
                
            End If 'End password checking

        'date is today, move along like normal
        Else
            
            'check if data exists in the list
            If Not (Me.Work_Hours_subform.Form.Recordset.EOF And Me.Work_Hours_subform.Form.Recordset.BOF) Then
                
                'get data to text box controls
                With Me.Work_Hours_subform.Form.Recordset
                
                    Me.ID = .Fields("ID")
                    Me.Employee = .Fields("Employee")
                    Me.Date_Worked = CDate(.Fields("Date Worked"))
                    If IsNull(.Fields("Department")) Then
                        Me.Work_Code.RowSource = "SELECT [Work Codes].ID, [Work Codes].Description FROM [Work Codes] WHERE [Work Codes].Active = -1 ORDER BY [Work Codes].Description"
                    Else
                        ' Since .Fields() returns an int, we have to query the DB to get the Description field
                        GetDeptQuery = "SELECT Description FROM Departments WHERE ID=" & .Fields("Department")
                        Dim myrs2 As DAO.Recordset
                        Set myrs2 = CurrentDb.OpenRecordset(GetDeptQuery)
                        Dim desc2 As String
                        desc2 = myrs2!Description
                        
                        ' set the recordsource for work code based on the changed department (uses the previous block for finding the description
                        Me.Work_Code.RowSource = "SELECT [Work Codes].ID, [Work Codes].Description FROM [Work Codes] WHERE [Work Codes].Active = -1 AND [Work Codes]." & desc2 & "=-1 ORDER BY [Work Codes].Description;"
                        Me.Department = .Fields("Department")
                    End If
                    If .Fields("Company") = Null Then
                    Else
                        Me.Company = .Fields("Company")
                    End If
                    If .Fields("Project") = Null Then
                    Else
                       Me.Project = .Fields("Project")
                    End If
                    If .Fields("Work Code") = Null Then
                    Else
                        Me.Work_Code = .Fields("Work Code")
                    End If
                    Me.Hours = .Fields("Hours")
                    Me.Work_Description = .Fields("Work Description")
                    
                    'store id in temp tag variable
                    Me.ID.Tag = Me.ID
                    
                    'change caption add button to update
                    Me.addButton.Caption = "Update"
                    
                    'disable edit button
                    Me.editButton.Enabled = False
                    
                    'change caption clear button to cancel
                    Me.clearButton.Caption = "Cancel"
                    
                End With 'End with referring to subform
                
            End If 'End if record exists
            
        End If 'End date checking
        
    End With 'End with referring to subform
    
End Sub

'What happens when enter is pressed while Employee field is in focus
Private Sub Employee_KeyPress(KeyAscii As Integer)

    'if keypressed is enter, do add
    If KeyAscii = vbKeyReturn Then
        addButton_Click
    End If
    
End Sub

'What happens when enter is pressed while Date_Worked field is in focus
Private Sub Date_Worked_KeyPress(KeyAscii As Integer)

    'if keypressed is enter, do add
    If KeyAscii = vbKeyReturn Then
        addButton_Click
    End If
    
End Sub

'What happens when enter is pressed while Hours field is in focus
Private Sub Hours_KeyPress(KeyAscii As Integer)

    'if keypressed is enter, do add
    If KeyAscii = vbKeyReturn Then
        addButton_Click
    End If
    
End Sub

'What happens when enter is pressed while Work_Code field is in focus
Private Sub Work_Code_KeyPress(KeyAscii As Integer)

    'if keypressed is enter, do add
    If KeyAscii = vbKeyReturn Then
        addButton_Click
    End If
    
End Sub

'What happens when enter is pressed while Description field is in focus
Private Sub Work_Description_KeyPress(KeyAscii As Integer)

    'if keypressed is enter, do add
    If KeyAscii = vbKeyReturn Then
        addButton_Click
    End If
    
End Sub

' What happens when the user clicks in the subform
Private Sub Work_Hours_subform_Enter()
    
    ' if the add/update button says update
    If Me.addButton.Caption & "" = "Update" Then
    
        'clear the form
        clearButton_Click
    
        'change caption add button to add
        Me.addButton.Caption = "Add"
        
    Else
    
        'do nothing
    
    End If
                        
    'enable edit button
    Me.editButton.Enabled = True
End Sub





