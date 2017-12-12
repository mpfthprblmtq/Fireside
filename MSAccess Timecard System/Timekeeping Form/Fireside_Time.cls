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

'What happens when you click the addButton
'Also is called when Enter key is pressed
Private Sub addButton_Click()

    'there are two options, adding and editing
    'check caption of addButton to see which we are doing
    
    'if button says add, do add functionality
    If Me.addButton.Caption & "" = "Add" Then
    
        'check if work description is null
        If IsNull(Me.Work_Description) Then
        
            Me.Work_Description.Value = ""
            
        End If

        'build add query
        Query = "INSERT INTO [Work Hours] ([Employee], [Date Worked], [Property/Company], [Work Code], [Hours], [Work Description]) " & _
        "VALUES ('" & Me.Employee & "','" & Me.Date_Worked & "','" & Me.Property_Company & "','" & Me.Work_Code & "','" & Me.Hours & "','" & Replace(Me.Work_Description, "'", "''") & "')"
        
    'button wasn't add, it was update
    Else
        
        'build update query
        Query = "UPDATE [Work Hours]" & _
            " SET [Employee]='" & Me.Employee & _
            "', [Date Worked]='" & Me.Date_Worked & _
            "', [Property/Company]='" & Me.Property_Company & _
            "', [Work Code]='" & Me.Work_Code & _
            "', [Hours]='" & Me.Hours & _
            "', [Work Description]='" & Replace(Me.Work_Description, "'", "''") & _
            "' WHERE ID=" & Me.ID.Tag
            
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
    Me.Property_Company = ""
    Me.Work_Code = ""
    Me.Hours = ""
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

'What happens when Hours field gets the focus
'Basically, if it's blank, put 15 minutes (0.25) in it
'If it's not blank, leave it the way it is
Private Sub Hours_GotFocus()

    'if date worked is blank, set it to today's date, else do nothing
    If Me.Hours & "" = "" Then

        'set the date
        Me.Hours = "0.25"

    End If
    
End Sub

'What happens when deleteButton is clicked
'Has a password system so that user won't be able to delete past records
Private Sub deleteButton_Click()
    
    'with statement that refers to the subform
    With Me.Work_Hours_subform.Form.Recordset
    
        'if date is NOT today, go through a password confirmation, then a confirmation to delete
        If Not CDate(.Fields("Date Worked")) = Date Then
        
            'input password
            Dim pass As String
            pass = InputBox("Cannot delete past records without administrator approval.  Enter password to delete record:", "Enter Password")
            
            'if password was entered correctly
            If pass = "fire" Then
                
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

'What happens when editButton is clicked
'Has a password system so that user won't be able to edit past records
Private Sub editButton_Click()

    'with statement that refers to the subform
    With Me.Work_Hours_subform.Form.Recordset
    
        'check if date is today
        If Not CDate(.Fields("Date Worked")) = Date Then
        
            'input password
            Dim pass As String
            pass = InputBox("Cannot edit past records without administrator approval." + vbCrLf + "Enter password to edit record:", "Enter Password")
            
            'if password was entered correctly
            If pass = "fire" Then
            
                'check if data exists in the list
                If Not (Me.Work_Hours_subform.Form.Recordset.EOF And Me.Work_Hours_subform.Form.Recordset.BOF) Then
                
                    'get data to text box controls
                    With Me.Work_Hours_subform.Form.Recordset
                        Me.ID = .Fields("ID")
                        Me.Employee = .Fields("Employee")
                        Me.Date_Worked = CDate(.Fields("Date Worked"))
                        If .Fields("Property/Company") = Null Then
                        Else
                            Me.Property_Company = .Fields("Property/Company")
                        End If
                        Me.Work_Code = .Fields("Work Code")
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
                    If .Fields("Property/Company") = Null Then
                    Else
                        Me.Property_Company = .Fields("Property/Company")
                    End If
                    Me.Work_Code = .Fields("Work Code")
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


Private Sub Work_Hours_subform_Enter()
    
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


