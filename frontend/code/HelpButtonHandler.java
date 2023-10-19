//***************************************************************
// Determine which button was activated on the HelpDialog and act
// accordingly i.e. step forwards or backwards through the already 
// viewed HelpDialog HTML files.
//***************************************************************    

import java.lang.*;
import java.awt.event.*;
import javabrowser.BrowserInterface;

public class HelpButtonHandler extends java.lang.Object implements java.awt.event.ActionListener
{
    BrowserInterface browserinterface;
    HelpDialog helpdialog;
    
    public HelpButtonHandler(HelpDialog h,BrowserInterface b)
    {
        helpdialog = h;
        browserinterface = b; 
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == helpdialog.forwardbutton)
            browserinterface.goForward();
        else if(e.getSource() == helpdialog.backbutton)
            browserinterface.goBack(); 
    }
}