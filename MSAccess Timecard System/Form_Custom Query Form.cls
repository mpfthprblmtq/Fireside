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

' what happens when the report button is clicked
Private Sub reportButton_Click()

    ' declare some colors
    Dim lngRed As Long
    Dim lngBlack As Long
    lngRed = RGB(255, 0, 0)
    lngBla = RGB(0, 0, 0)
    
    ' if the pay period start or end is empty, display a box and highlight the fields
    If IsNull(Me.PayPeriodStart) Or IsNull(Me.PayPeriodEnd) Then
    
        MsgBox ("Please fill out the Date Worked fields")
        Me.PayPeriodStart.BorderColor = lngRed
        Me.PayPeriodEnd.BorderColor = lngRed
    
    ' all is well, do the thing
    Else
        
        ' set the highlighting back to normal
        Me.PayPeriodStart.BorderColor = lngBla
        Me.PayPeriodEnd.BorderColor = lngBla
    
        ' Calculate the starts and ends of the work week
        
        ' if the first day of the period is a Sunday
        If Weekday(Me.PayPeriodStart.Value) = 1 Then
            
            Me.dispWorkWeek1Start = DateAdd("d", 0, Me.PayPeriodStart.Value)
            Me.dispWorkWeek1End = DateAdd("d", 6, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2Start = DateAdd("d", 7, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2End = DateAdd("d", 13, Me.PayPeriodStart.Value)
            
        ' if the first day of the period is a Monday
        ElseIf Weekday(Me.PayPeriodStart.Value) = 2 Then
        
            Me.dispWorkWeek1Start = DateAdd("d", -1, Me.PayPeriodStart.Value)
            Me.dispWorkWeek1End = DateAdd("d", 5, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2Start = DateAdd("d", 6, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2End = DateAdd("d", 12, Me.PayPeriodStart.Value)
    
        ' if the first day of the period is a Tuesday
        ElseIf Weekday(Me.PayPeriodStart.Value) = 3 Then
    
            Me.dispWorkWeek1Start = DateAdd("d", -2, Me.PayPeriodStart.Value)
            Me.dispWorkWeek1End = DateAdd("d", 4, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2Start = DateAdd("d", 5, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2End = DateAdd("d", 11, Me.PayPeriodStart.Value)
    
        ' if the first day of the period is a Wednesday
        ElseIf Weekday(Me.PayPeriodStart.Value) = 4 Then
    
            Me.dispWorkWeek1Start = DateAdd("d", -3, Me.PayPeriodStart.Value)
            Me.dispWorkWeek1End = DateAdd("d", 3, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2Start = DateAdd("d", 4, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2End = DateAdd("d", 10, Me.PayPeriodStart.Value)
    
        ' if the first day of the period is a Thursday
        ElseIf Weekday(Me.PayPeriodStart.Value) = 5 Then
    
            Me.dispWorkWeek1Start = DateAdd("d", -4, Me.PayPeriodStart.Value)
            Me.dispWorkWeek1End = DateAdd("d", 2, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2Start = DateAdd("d", 3, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2End = DateAdd("d", 9, Me.PayPeriodStart.Value)
    
        ' if the first day of the period is a Friday
        ElseIf Weekday(Me.PayPeriodStart.Value) = 6 Then
    
            Me.dispWorkWeek1Start = DateAdd("d", -5, Me.PayPeriodStart.Value)
            Me.dispWorkWeek1End = DateAdd("d", 1, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2Start = DateAdd("d", 2, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2End = DateAdd("d", 8, Me.PayPeriodStart.Value)
    
        ' if the first day of the period is a Saturday
        ElseIf Weekday(Me.PayPeriodStart.Value) = 7 Then
    
            Me.dispWorkWeek1Start = DateAdd("d", -6, Me.PayPeriodStart.Value)
            Me.dispWorkWeek1End = DateAdd("d", 0, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2Start = DateAdd("d", 1, Me.PayPeriodStart.Value)
            Me.dispWorkWeek2End = DateAdd("d", 7, Me.PayPeriodStart.Value)
        
        End If
        
        ' Open the report
        DoCmd.OpenReport "Payroll Report", acViewReport
        
        ' set the date header on the report
        [Reports]![Payroll Report].dateHeader = Me.PayPeriodStart.Value & " to " & Me.PayPeriodEnd
        
    End If
    
End Sub

' what happens when the search button is clicked
Private Sub Search_Button_Click()

    Call Query_Function
    
End Sub

' what happens when the form is loaded
Private Sub Form_Load()

    ' reset the form to the basic query and set the subform's recordsource to that query
    Me.QueryResult.Form.RecordSource = "SELECT * FROM [Work Hours] ORDER BY [Date Worked] DESC"
    Me.Payroll_QueryResult.Form.RecordSource = "SELECT * FROM [Work Hours] ORDER BY [Date Worked] DESC"

End Sub

' Function that builds the query based on what fields are filled or not
Function Query_Function()

    ' original query, from here we'll add on WHERE and AND clauses
    Query = "SELECT * FROM [Work Hours]"
    
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
    
    ' if date fields are not null, add things to the query
    Me.Date_Worked.SetFocus
    If IsDate(Me.Date_Worked.Text) Then
        Me.Date_Worked2.SetFocus
        If IsDate(Me.Date_Worked2.Text) Then
    
            ' get some variables
            Dim DateSelected As Date
            Dim DateString   As String
            
            Dim DateSelected2 As Date
            Dim DateString2    As String
        
            ' format them
            DateSelected = DateValue(Me.Date_Worked.Value)
            DateString = Format(DateSelected, "yyyy-mm-dd")
            
            DateSelected2 = DateValue(Me.Date_Worked2.Value)
            DateString2 = Format(DateSelected2, "yyyy-mm-dd")
            
            ' if this is the first and only field to search by
            If Query = Orig_Query Then
                Query = Query & " WHERE [Date Worked] BETWEEN #" & DateString & "# AND #" & DateString2 & "#"
                
            ' else this an addition, add an AND clause
            Else
                Query = Query & " AND [Date Worked] BETWEEN #" & DateString & "# AND #" & DateString2 & "#"
                
            End If
        End If
    End If
    
    ''' WORK DESCRIPTION ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    ' if work description is not empty, add things to the query
    If Not IsNull(Me.Work_Description) Then
    
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
    
    ''' DEPARTMENT ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    ' if department is not empty, add things to the query
    If Not Me.Department = "" Then
    
        ' if this is the first and only field to search by
        If Query = Orig_Query Then
            Query = Query & " WHERE Department = " & Me.Department.Column(0)
            
        ' else this is an addition, add an AND clause
        Else
            Query = Query & " AND Department = " & Me.Department.Column(0)
            
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
    
    ''' COMPANY '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    ' if company is not empty, add things to the query
    If Not Me.Company = "" Then
    
        ' if this is the first and only field to search by
        If Query = Orig_Query Then
            Query = Query & " WHERE Company = " & Me.Company.Column(0)
            
        ' else this is an addition, add an AND clause
        Else
            Query = Query & " AND Company = " & Me.Company.Column(0)
            
        End If
        
    End If
    
    ''' PROJECT '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    ' if project is not empty, add things to the query
    If Not Me.Project = "" Then
    
        ' if this is the first and only field to search by
        If Query = Orig_Query Then
            Query = Query & " WHERE Project = " & Me.Project.Column(0)
            
        ' else this is an addition, add an AND clause
        Else
            Query = Query & " AND Project = " & Me.Project.Column(0)
            
        End If
        
    End If
    
    ' order newest to oldest
    Query = Query & " ORDER BY [Date Worked] DESC"
    
    ' debug
    'Debug.Print Query
    
    ' set the subform query to the main query
    Me.QueryResult.Form.RecordSource = Query
    
    ' requery the subform based on the new record source
    Me.QueryResult.Requery

End Function

' Function that builds the query based on what fields are filled or not
' Specified to Payroll
Function Payroll_Query_Function()

    ' original query, from here we'll add on WHERE and AND clauses
    Query = "SELECT Employee, [Date Worked], Department, [Work Code], Company, Project, Hours, [Property/Company], [Work Description] FROM [Work Hours]"
    
    ' have something to compare against
    Orig_Query = Query
    
    ''' EMPLOYEE ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    ' if employee is not empty, add things to the query
    If Not Me.Payroll_Employee = "" Then
    
        ' if this is the first and only field to search by
        If Query = Orig_Query Then
            Query = Query & " WHERE Employee = " & Me.Payroll_Employee.Column(0)
            
        ' else this is an addition, add an AND clause
        Else
            Query = Query & " AND Employee = " & Me.Payroll_Employee.Column(0)
            
        End If
        
    End If
    
    ''' DATE ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    
    ' if date fields are good, add things to the query
    If IsNull(Me.PayPeriodStart) Or IsNull(Me.PayPeriodEnd) Then
        ' do nothing
    Else
        
        ' get some variables
        Dim DateSelected As Date
        Dim DateString   As String
        
        Dim DateSelected2 As Date
        Dim DateString2    As String
    
        ' format them
        DateSelected = DateValue(Me.PayPeriodStart.Value)
        DateString = Format(DateSelected, "yyyy-mm-dd")
        
        DateSelected2 = DateValue(Me.PayPeriodEnd.Value)
        DateString2 = Format(DateSelected2, "yyyy-mm-dd")
        
        ' if this is the first and only field to search by
        If Query = Orig_Query Then
            Query = Query & " WHERE [Date Worked] BETWEEN #" & DateString & "# AND #" & DateString2 & "#"
            
        ' else this an addition, add an AND clause
        Else
            Query = Query & " AND [Date Worked] BETWEEN #" & DateString & "# AND #" & DateString2 & "#"
            
        End If
    End If
    
    ' order newest to oldest
    Query = Query & " ORDER BY [Date Worked] DESC"
    
    ' debug
    'Debug.Print Query
    
    ' set the subform query to the main query
    Me.Payroll_QueryResult.Form.RecordSource = Query

End Function

' What happens when the generate hours button is clicked
Private Sub generate_hours_button_Click()

    ' Call the function that updates the subform
    Call Payroll_Query_Function

    ' declare some variables
    Dim PTO_Num As Double
    Dim UTO_Num As Double
    Dim Holiday_Num As Double
    Dim Regular_Num As Double
    Dim Total_Num As Double
    Dim Overtime_Num As Double
    
    ' now comes the fun part
    ' calculating overtime!
    
    ' more variables
    Dim StartDate As Date
    Dim EndDate As Date
    StartDate = Me.PayPeriodStart.Value
    EndDate = Me.PayPeriodEnd.Value
    
    ' one more variable, the debug window text
    Dim DebugText As String
    DebugText = "Payroll Information for " & Me.Payroll_Employee.Column(1) & vbCrLf & vbCrLf & "Period:" & vbCrLf & StartDate & " to " & EndDate & vbCrLf & vbCrLf & "Overtime Calculation:" & vbCrLf
    
    ' see if the employee is overtime exempt or not
    Dim rst As DAO.Recordset
    Dim ote As Integer
    Dim strSQL As String
    strSQL = "SELECT [OT Exempt] FROM Employees WHERE ID=" & Me.Payroll_Employee
    Set rst = CurrentDb.OpenRecordset(strSQL)
    
    ' ote = Overtime Exempt
    ote = rst![OT Exempt]
    
    ' if the employee can actually get overtime
    If ote = 0 Then
    
        ' we'll start by listing out all of the possible days
        Dim Period1 As Date
        Dim Period2 As Date
        Dim Period3 As Date
        Dim Period4 As Date
        Dim Period5 As Date
        Dim Period6 As Date
        Dim Period7 As Date
        Dim Period8 As Date
        Dim Period9 As Date
        Dim Period10 As Date
        Dim Period11 As Date
        Dim Period12 As Date
        Dim Period13 As Date
        Dim Period14 As Date
        Dim Period15 As Date
        Dim Period16 As Date
        
        Period_7 = DateAdd("d", -7, StartDate)
        Period_6 = DateAdd("d", -6, StartDate)
        Period_5 = DateAdd("d", -5, StartDate)
        Period_4 = DateAdd("d", -4, StartDate)
        Period_3 = DateAdd("d", -3, StartDate)
        Period_2 = DateAdd("d", -2, StartDate)
        Period_1 = DateAdd("d", -1, StartDate)
        Period1 = DateAdd("d", 1, StartDate)
        Period2 = DateAdd("d", 2, StartDate)
        Period3 = DateAdd("d", 3, StartDate)
        Period4 = DateAdd("d", 4, StartDate)
        Period5 = DateAdd("d", 5, StartDate)
        Period6 = DateAdd("d", 6, StartDate)
        Period7 = DateAdd("d", 7, StartDate)
        Period8 = DateAdd("d", 8, StartDate)
        Period9 = DateAdd("d", 9, StartDate)
        Period10 = DateAdd("d", 10, StartDate)
        Period11 = DateAdd("d", 11, StartDate)
        Period12 = DateAdd("d", 12, StartDate)
        Period13 = DateAdd("d", 13, StartDate)
        Period14 = DateAdd("d", 14, StartDate)
        Period15 = DateAdd("d", 15, StartDate)
        Period16 = DateAdd("d", 16, StartDate)
        
        ' total hours between the two work weeks
        Dim Week1Total As Double
        Dim Week2Total As Double
        
        ' if the first day of the period is a Sunday
        If Weekday(Me.PayPeriodStart.Value) = 1 Then
            
            Week1Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & StartDate & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period1 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period2 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period3 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period4 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period5 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period6 & "#"), 0)
            DebugText = DebugText + "Week 1: " & StartDate & " to " & Period6 & ", Total Hours: " & Week1Total & vbCrLf
            
            Week2Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period7 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period8 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period9 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period10 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period11 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period12 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period13 & "#"), 0)
            DebugText = DebugText + "Week 2: " & Period7 & " to " & Period13 & ", Total Hours: " & Week2Total & vbCrLf
        
        ' if the first day of the period is a Monday
        ElseIf Weekday(Me.PayPeriodStart.Value) = 2 Then
        
            Week1Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period_1 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & StartDate & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period1 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period2 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period3 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period4 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period5 & "#"), 0)
            DebugText = DebugText + "Week 1: " & Period_1 & " to " & Period5 & ", Total Hours: " & Week1Total & vbCrLf
            
            Week2Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period6 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period7 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period8 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period9 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period10 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period11 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period12 & "#"), 0)
            DebugText = DebugText + "Week 2: " & Period6 & " to " & Period12 & ", Total Hours: " & Week2Total & vbCrLf
        
        ' if the first day of the period is a Tuesday
        ElseIf Weekday(Me.PayPeriodStart.Value) = 3 Then
        
            Week1Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period_2 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period_1 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & StartDate & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period1 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period2 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period3 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period4 & "#"), 0)
            DebugText = DebugText + "Week 1: " & Period_2 & " to " & Period4 & ", Total Hours: " & Week1Total & vbCrLf
            
            Week2Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period5 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period6 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period7 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period8 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period9 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period10 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period11 & "#"), 0)
            DebugText = DebugText + "Week 2: " & Period5 & " to " & Period11 & ", Total Hours: " & Week2Total & vbCrLf
        
        ' if the first day of the period is a Wednesday
        ElseIf Weekday(Me.PayPeriodStart.Value) = 4 Then
        
            Week1Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period_3 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period_2 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period_1 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & StartDate & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period1 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period2 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period3 & "#"), 0)
            DebugText = DebugText + "Week 1: " & Period_3 & " to " & Period3 & ", Total Hours: " & Week1Total & vbCrLf
            
            Week2Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period4 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period5 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period6 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period7 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period8 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period9 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period10 & "#"), 0)
            DebugText = DebugText + "Week 2: " & Period4 & " to " & Period10 & ", Total Hours: " & Week2Total & vbCrLf
        
        ' if the first day of the period is a Thursday
        ElseIf Weekday(Me.PayPeriodStart.Value) = 5 Then
        
            Week1Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period_4 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period_3 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period_2 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period_1 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & StartDate & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period1 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period2 & "#"), 0)
            DebugText = DebugText + "Week 1: " & Period_4 & " to " & Period2 & ", Total Hours: " & Week1Total & vbCrLf
            
            Week2Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period3 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period4 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period5 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period6 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period7 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period8 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period9 & "#"), 0)
            DebugText = DebugText + "Week 2: " & Period3 & " to " & Period9 & ", Total Hours: " & Week2Total & vbCrLf
        
        ' if the first day of the period is a Friday
        ElseIf Weekday(Me.PayPeriodStart.Value) = 6 Then
        
            Week1Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period_5 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period_4 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period_3 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period_2 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period_1 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & StartDate & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period1 & "#"), 0)
            DebugText = DebugText + "Week 1: " & Period_5 & " to " & Period1 & ", Total Hours: " & Week1Total & vbCrLf
            
            Week2Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period2 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period3 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period4 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period5 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period6 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period7 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period8 & "#"), 0)
            DebugText = DebugText + "Week 2: " & Period2 & " to " & Period8 & ", Total Hours: " & Week2Total & vbCrLf
        
        ' if the first day of the period is a Saturday
        ElseIf Weekday(Me.PayPeriodStart.Value) = 7 Then
        
            Week1Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period_6 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period_5 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period_4 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period_3 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period_2 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period_1 & "#"), 0)
            Week1Total = Week1Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & StartDate & "#"), 0)
            DebugText = DebugText + "Week 1: " & Period_6 & " to " & StartDate & ", Total Hours: " & Week1Total & vbCrLf
            
            Week2Total = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code] <> 39 AND [Work Code] <> 51 AND [Work Code] <> 52 AND [Date Worked]=#" & Period1 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period2 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period3 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period4 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period5 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period6 & "#"), 0)
            Week2Total = Week2Total + Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Work Code]<>39 AND [Work Code]<>51 AND [Work Code]<>52 AND [Date Worked]=#" & Period7 & "#"), 0)
            DebugText = DebugText + "Week 2: " & Period1 & " to " & Period7 & ", Total Hours: " & Week2Total & vbCrLf
        
        End If
        
        ' this is a confusing block of math, let's break it down
        ' Overtime_Num is the total amount of overtime between the two work weeks in the pay period
        ' Overtime_Num = (Week1Total - 40) + (Week2Total - 40)
        
        ' if the total number of hours in week 1 is over 40, there's some overtime
        If Week1Total - 40 > 0 Then
            ot1 = Week1Total - 40
            DebugText = DebugText + "OVERTIME IN WEEK 1" & vbCrLf
        End If
        
        ' if the total number of hours in week 2 is over 40, there's some overtime
        If Week2Total - 40 > 0 Then
            ot2 = Week2Total - 40
            DebugText = DebugText + "OVERTIME IN WEEK 2" & vbCrLf
        End If
        
        ' the total overtime
        Overtime_Num = ot1 + ot2
        
        ' set the number in the field
        Me.Overtime_field.Value = Overtime_Num
        
    ' wellp, they don't even get overtime
    Else
        DebugText = DebugText + "Employee is OT Exempt" & vbCrLf
        Overtime_Num = 0
        Me.Overtime_field.Value = ""
    End If
    
    ' calculate all the easier ones, like PTO, Holiday, and Total
    PTO_Num = Nz(DSum("Hours", "[Work Hours]", "[Work Code]=39 AND Employee=" & Me.Payroll_Employee & " AND [Date Worked] BETWEEN #" & Me.PayPeriodStart.Value & "# AND #" & Me.PayPeriodEnd & "#"), 0)
    Holiday_Num = Nz(DSum("Hours", "[Work Hours]", "[Work Code]=51 AND Employee=" & Me.Payroll_Employee & " AND [Date Worked] BETWEEN #" & Me.PayPeriodStart.Value & "# AND #" & Me.PayPeriodEnd & "#"), 0)
    Total_Num = Nz(DSum("Hours", "[Work Hours]", "Employee=" & Me.Payroll_Employee & " AND [Date Worked] BETWEEN #" & Me.PayPeriodStart.Value & "# AND #" & Me.PayPeriodEnd & "#"), 0)
    
    ' regular is the PTO, Holiday, and Overtime taken away from the Total Hours
    Regular_Num = Total_Num - Nz((PTO_Num + Holiday_Num + Overtime_Num), 0)
    
    ' set the fields with the numbers
    Me.PTO_Field.Value = PTO_Num
    Me.Holiday_Field.Value = Holiday_Num
    Me.Regular_field.Value = Regular_Num
    Me.Total_field.Value = Total_Num
    
    ' set the debug text box
    Debug_Field.Value = DebugText

End Sub

' What happens when the export button is clicked
Private Sub exportButton_Click()
    
    ' exports the whole darn query to Excel
    Dim qdf As QueryDef
    Set qdf = CurrentDb.CreateQueryDef("Custom Query", Me.QueryResult.Form.RecordSource)
    
    ' create a query db object to export with
    DoCmd.OutputTo acOutputQuery, "Custom Query", acFormatXLSX, "Access Export.xlsx", True
  
    ' then delete it
    DoCmd.DeleteObject acQuery, "Custom Query"

End Sub
