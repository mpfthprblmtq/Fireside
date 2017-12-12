VERSION 1.0 CLASS
BEGIN
  MultiUse = -1  'True
END
Attribute VB_Name = "Form_Custom Query Form"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = True
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Compare Database

' What happens when the Date_Worked field is changed
Private Sub Date_Worked_Change()
    ' refresh the form
    Me.Refresh

    ' do the query
    Call Query_Function
End Sub

' What happens when the Employee field is changed
Private Sub Employee_Change()
    ' refresh the form
    Me.Refresh

    ' do the query
    Call Query_Function
End Sub

' What happens when the Property/Company field is changed
Private Sub Property_Company_Change()
    ' refresh the form
    Me.Refresh

    ' do the query
    Call Query_Function
End Sub

' What happens when the Work Code field is changed
Private Sub Work_Code_Change()
    ' refresh the form
    Me.Refresh

    ' do the query
    Call Query_Function
End Sub

' What happens when the Work Description field is changed
' Basically just refreshes the form so it can grab the text, then sets the selection index to the end of the string, since access is weird (see comment below)
Private Sub Work_Description_Change()
    ' refresh the form
    Me.Refresh
    
    ' set the selection to the end of the string since a refresh keeps highlighting the whole damn string
    If Not (Me.Work_Description.Value = "") Then
        Me.Work_Description.SelStart = Len(Me.Work_Description.Value)
    End If

    ' do the query
    Call Query_Function
End Sub

' Function that builds the query based on what fields are filled or not
Function Query_Function()

    ' original query, from here we'll add on WHERE and AND clauses
    Query = "SELECT Employee, [Date Worked], [Property/Company], [Work Code], Hours, [Work Description], ID FROM [Work Hours]"
    
    ' have something to compare against
    Orig_Query = Query
    
    ''' EMPLOYEE ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    ' if employee is not empty, add things to the query
    If Not Me.Employee = "" Then
    
        ' if this is the first and only field to search by
        If Query = Orig_Query Then
            Query = Query & " WHERE Employee = " & Me.Employee.Column(0)
            
        ' else this is an addition, add an AND clause
        Else
            Query = Query & " AND Employee = " & Me.Employee.Column(0)
            
        End If
        
    End If
    
    ''' DATE ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    ' if date is not null, add things to the query
    If Not IsNull(Me.Date_Worked.Value) Then
    
        ' get some variables
        Dim DateSelected As Date
        Dim DateString   As String
    
        ' format them
        DateSelected = DateValue(Me.Date_Worked.Value)
        DateString = Format(DateSelected, "yyyy-mm-dd")
        
        ' if this is the first and only field to search by
        If Query = Orig_Query Then
            Query = Query & " WHERE [Date Worked] = #" & DateString & "#"
            
        ' else this an addition, add an AND clause
        Else
            Query = Query & " AND [Date Worked] = #" & DateString & "#"
            
        End If

    End If
    
    ''' COMPANY/PROPERTY ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    ' if company/property is not empty, add things to the query
    If Not Me.Property_Company = "" Then
    
        ' if this is the first and only field to search by
        If Query = Orig_Query Then
            Query = Query & " WHERE [Property/Company] = " & Me.Property_Company.Column(0)
            
        ' else this is an addition, add an AND clause
        Else
            Query = Query & " AND [Property/Company] = " & Me.Property_Company.Column(0)
            
        End If
        
    End If
    
    ''' WORK CODE '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    ' if work code is not empty, add things to the query
    If Not Me.Work_Code = "" Then
    
        ' if this is the first and only field to search by
        If Query = Orig_Query Then
            Query = Query & " WHERE [Work Code] = " & Me.Work_Code.Column(0)
            
        ' else this is an addition, add an AND clause
        Else
            Query = Query & " AND [Work Code] = " & Me.Work_Code.Column(0)
            
        End If
        
    End If
    
    ''' WORK DESCRIPTION ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    ' if work description is not empty, add things to the query
    If Not Me.Work_Description = "" Then
    
        ' if this is the first and only field to search by
        If Query = Orig_Query Then
            'Query = Query & " WHERE [Work Description] LIKE " & Chr(34) & "%" & Me.Work_Description & "%" & Chr(34)
            Query = Query & "WHERE INSTR([Work Description], '" & Me.Work_Description & "') > 0"
            
        ' else this is an addition, add an AND clause
        Else
            'Query = Query & " AND [Work Description] LIKE " & Chr(34) & "%" & Me.Work_Description & "%" & Chr(34)
            Query = Query & " AND INSTR([Work Description], '" & Me.Work_Description & "') > 0"
            
        End If
        
    End If
    
    ' order newest to oldest
    Query = Query & " ORDER BY [Date Worked] DESC"
    
    ' debug
    Debug.Print Query
    
    ' set the subform query to the main query
    Me.QueryResult.Form.RecordSource = Query

End Function
