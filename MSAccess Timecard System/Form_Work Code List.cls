VERSION 1.0 CLASS
BEGIN
  MultiUse = -1  'True
END
Attribute VB_Name = "Form_Work Code List"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = True
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False
Option Compare Database

' What happens when the form loads
Private Sub Form_Load()

    ' set the active filter to True
    ' so it only shows the active Work Codes
    Me.Filter = "Active = -1"
    Me.FilterOn = True
    
End Sub
