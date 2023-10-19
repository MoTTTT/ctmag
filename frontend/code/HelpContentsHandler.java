//************************************************************
// Instantiate a HelpDialog and BrowserInterface. BrowserInterface
// is thirdparty code allowing for the browsing of HTML help
// files.
//************************************************************

import java.lang.*;
import java.awt.event.*;
import java.net.*;
import javabrowser.BrowserInterface;

public class HelpContentsHandler extends java.lang.Object implements java.awt.event.ActionListener
{
    Ctmag ctmag;
    HelpDialog helpdialog;
    BrowserInterface browserinterface;
    URL url;
    HelpButtonHandler helpbuttonhandler;
    
    public HelpContentsHandler(Ctmag c)
    {
        ctmag = c;
        try {            
        url = new URL("file:///magtest/docs/index.html");        
        }
        catch(MalformedURLException murl)
        {
            System.out.println(murl.getMessage());
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        helpdialog = new HelpDialog(ctmag,false);        
        helpdialog.setVisible(true);
        browserinterface = new BrowserInterface(helpdialog.helpdialogdisplaypanel);
        browserinterface.setMenuMode(browserinterface.MENU_INLINE);
//        browserinterface.fi.setSize(browserinterface.fi.getFont(),1);
        browserinterface.URL_Process(url);
//        browserinterface.URL_Process(url1);
//        browserinterface.URL_Process(url);

        helpbuttonhandler = new HelpButtonHandler(helpdialog, browserinterface);        
        helpdialog.forwardbutton.addActionListener(helpbuttonhandler);
        helpdialog.backbutton.addActionListener(helpbuttonhandler);        
    }    
}