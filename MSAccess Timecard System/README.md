# Access Timecard System
The company uses Access to manage their time keeping and logging.  Since Access always seems to crash at inopportune times, I made a system with the existing Access system that simplifies the process.  
It is primarily run by using VBA and SQL in the background.  VBA/SQL code is located here.

#### Quick breakdown of what vba code is included:
* Form_Work Hours Form
  * The main form that is used for time entry.


* Form_Work Hours subform
  * The smaller detail form on the Work Hours Form.


* Form_Work Code List
  * A collection of Work Codes.


* Form_Custom Query Form
  * A form that lets you fill in parameters for a search, such as name of the employee, date worked, work code, and more.
