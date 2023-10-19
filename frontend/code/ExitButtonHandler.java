//**************************************************************
// Exit the application without saving any test documentation.
//**************************************************************

import java.awt.event.*;
import java.io.*;
class ExitButtonHandler implements java.awt.event.ActionListener//, java.io.Serializable 
{
    private Ctmag ctmag;
    
    public ExitButtonHandler(Ctmag f)
    {
        ctmag = f;        
    }

    public void actionPerformed(ActionEvent e)
    {
	     ctmag.setVisible(false);	// hide the Frame
	     ctmag.dispose();	     
	     System.exit(0);		// close the application
    }
}