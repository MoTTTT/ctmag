
Install
*******

The CT & VT Calibration Tester is available on 3x 1.44Mb stiffies. Copy the contents of these stiffies to a directory called c:\magtest, maintaining the subdirectory structure. A shortcut to c:\magtest\Ctmag.bat is available on the stiffies and could be copied to the desktop environment. Any changes in the directory structure should be reflected by the directory settings in Ctmag.bat and the properties settings of the shortcut.

If the home directory of the application is to be changed, edit the " %CTHOME% " setting in  Ctmag.bat to the correct home directory of the application. To edit the shortcut, right click with the mouse on the shortcut icon. Select " Properties ", select " Program ", change the " Cmd line: " and " Working: " settings to reflect the directory structure.

The application was succesfully tested on a 486DX66 machine. A minimum screen resolution of 800x600 is advised. 

Menu
****

The Test and Action buttons of the application is available as menu items. Menu options that are greyed out are not implemented in this version. In-line help is available under the item " Help Contents ". The menu item " Options " available under the menu selection " Settings ", allow the serial communications port to be selected (the default is COM1). It also allows the thresholds to be set for the Magcurve, Voltage Ratio and Current Ratio tests. The Magcurve voltage and current thresholds determine at what sensed inputs a magnetisation curve test is to be aborted as a failed test i.e. it functions as safety features.The ratio thesholds determine when a test is completed. The thesholds for the Voltage Ratio and Current Ratio Tests relate to measured values on the primary winding of the tested transformer.

Tests
*****

When doing the Voltage and Current Ratio Tests, first enter the primary and secondary winding values as integers into the Ratios textfields on the CT and VT tabbed panels before running the respective tests.

Results
*******

The Results textfields are marked as editable which means that if the displayed values are not clearly visible due to the size of the screen, the cursor can be placed inside the textfield by clicking on it with the mouse, it can then be scrolled with the left and right arrow keys in order to read the total value. It is thus possible to edit the results textfields, these changed values would however not be stored with the saved test and if the test is imported, the original values would again be displayed. 

Action
******

The selection of the " Print Report " action would result in a preview of the report to be displayed. When selecting " OK " on the Report Dialog Box, the Print Dialog Box would appear. Set the paper size to " A4 " and the orientation to " landscape " in the properties settings to get the best rendering of the report. 

When exiting the application the currently loaded test is not saved automatically.

Text File
*********

A text file output of the magnetisation curve tests can be produced by selecting the "Generate Text File" menu item. The generated text file can be imported into a spreadsheet such as Excel. Import the file as delimited text with the limiters being spaces. 

Date
****

Note that the time displayed on the printed report is GMT time.
