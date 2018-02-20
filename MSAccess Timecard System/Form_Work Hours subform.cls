VERSION 1.0 CLASS
BEGIN
  MultiUse = -1  'True
END
Attribute VB_Name = "Form_Work Hours subform"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = True
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Compare Database

' What happens when the subform is closed
Private Sub Form_Close()

    ' Resets the form to its default state
    Me.RecordSource = "SELECT Employee, [Date Worked], Department, [Work Code], Company, Project, Hours, [Work Description], [Property/Company] FROM [Work Hours]"

End Sub
