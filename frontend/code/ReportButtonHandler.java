//**************************************************************
// Report Button Handler determining whether a report is to be 
// printed or aborted. 
//**************************************************************

import java.lang.*;
import java.awt.event.*;
public class ReportButtonHandler extends java.lang.Object implements java.awt.event.ActionListener
{
    TestReportDialog testreportdialog;
    Ctmag ctmag;
    
    public ReportButtonHandler(TestReportDialog t, Ctmag f)
    {
       testreportdialog = t;
       ctmag = f;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        
        if(source == testreportdialog.printreportacceptbutton)
            ctmag.printreportbuttonhandler.printReport();
        ctmag.statustextfield.setText(" ");        
        testreportdialog.setVisible(false);
    }    
}