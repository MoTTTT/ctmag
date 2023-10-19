import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.awt.*;

class TestHandler implements java.awt.event.ActionListener  
{
    private Ctmag ctmag;
    protected FileDialog openFileDialog1;
    private MagCurveTest magcurvetest;
    private ImportSequentialFile importsequentialfile;
    private ImportTest importtest;
    private CreateSequentialFile createsequentialfile;
    
  
    public TestHandler(Ctmag f)
    {
        ctmag = f; 
		openFileDialog1 = new java.awt.FileDialog(ctmag);
		openFileDialog1.setMode(FileDialog.LOAD);
		openFileDialog1.setTitle("OpenFileDialog");
		openFileDialog1.setVisible(false);		        
    }
    
    public void setObjects(MagCurveTest m)
    {
        magcurvetest = m;
    }
    
    public void setObjects(ImportTest i)
    {
        importtest = i;
    }
        
    public void actionPerformed(ActionEvent e)
    {                              
        if((e.getSource() == ctmag.importtestbutton)||
            (e.getSource()== ctmag.miopentest)||
                (e.getSource() == ctmag.miactionimport))
        {
		    openFileDialog1.setMode(FileDialog.LOAD);            
            importFileDialog();
        } 
        
        if((e.getSource() == ctmag.savetestbutton)||
            (e.getSource() == ctmag.misavetestas)||
                (e.getSource() == ctmag.miactionsave))
        {
		    openFileDialog1.setMode(FileDialog.SAVE);            
            saveFileDialog();
        } 
        
        if(e.getSource() == ctmag.misavetest)
        {
		    openFileDialog1.setMode(FileDialog.SAVE);            
            saveNoFileDialog();
        }                                
    }
    
   public void importFileDialog()
    {
		int		defMode			= openFileDialog1.getMode();
		String	defTitle		= openFileDialog1.getTitle();
		String defDirectory	    = openFileDialog1.getDirectory();
		String defFile			= openFileDialog1.getFile();

		openFileDialog1 = new java.awt.FileDialog(ctmag, defTitle, defMode);
		openFileDialog1.setDirectory(defDirectory);
		openFileDialog1.setFile(defFile);
		openFileDialog1.setVisible(true);
		
		
		String newDirectory = openFileDialog1.getDirectory();
		String newFile = openFileDialog1.getFile();
		
		if(newDirectory != null && newFile != null)
		    importTestFile(newDirectory + newFile);        
    }

    private void importTestFile(String s)
    {
        try{
                importsequentialfile = new ImportSequentialFile();
                importsequentialfile.setObjects(ctmag, magcurvetest);
                importsequentialfile.setObjects(openFileDialog1);                
                magcurvetest = importsequentialfile.getFile(s);
            }
        catch(NullPointerException npe){
            ctmag.statustextfield.setText("Null Pointer Exception occured when trying to import " + s);
        } 
    }
    
    public void saveFileDialog()
    {
		int		defMode			= openFileDialog1.getMode();
		String	defTitle		= openFileDialog1.getTitle();
		String  defDirectory	= openFileDialog1.getDirectory();
		String  defFile			= openFileDialog1.getFile();

		openFileDialog1 = new java.awt.FileDialog(ctmag, defTitle, defMode);
		openFileDialog1.setDirectory(defDirectory);
		openFileDialog1.setFile(defFile);
		openFileDialog1.setVisible(true);
		
		String newDirectory = openFileDialog1.getDirectory();
		String newFile = openFileDialog1.getFile();
		
		if(newDirectory != null && newFile != null)
		    saveTestFile(newDirectory,newFile);		    
    }

    public void saveNoFileDialog()
    {
		int		defMode			= openFileDialog1.getMode();
		String	defTitle		= openFileDialog1.getTitle();
		String  defDirectory	= openFileDialog1.getDirectory();
		String  defFile			= openFileDialog1.getFile();
		
		if(defDirectory != null && defFile != null)
		    saveTestFile(defDirectory,defFile);
		else
		    saveFileDialog();
    }


    public void saveTestFile(String d, String f)
    {
        ctmag.saveproc.saveTestSettings();
        createsequentialfile = new CreateSequentialFile(ctmag);
        createsequentialfile.setObjects(openFileDialog1);
        createsequentialfile.createFile(d,f);        
    }    
}

