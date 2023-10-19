import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.awt.FileDialog;

public class TextFileHandler extends java.lang.Object implements java.awt.event.ActionListener, java.io.FilenameFilter    
{
    Ctmag ctmag;
    FileDialog textfiledialog;
    TextFile textfile;
    
    public TextFileHandler(Ctmag c)
    {
        ctmag = c;
        textfiledialog = new FileDialog(ctmag);
        textfiledialog.setMode(FileDialog.SAVE);
        textfiledialog.setTitle("TextFileDialog");
        textfiledialog.setFilenameFilter(this);
//		System.out.println("FilenameFilter : " + textfiledialog.getFilenameFilter());        
    }  
    
    public void actionPerformed(ActionEvent e)
    {
       textfiledialog.setFilenameFilter(this);
       saveTestFileDialog();
       
    }
    public boolean accept(File dir, String name)
    {
        System.out.println("test the extension");
        return name.endsWith(".txt");
    }

    private void saveTestFileDialog()
    {
		int		defMode			= textfiledialog.getMode();
		String	defTitle		= textfiledialog.getTitle();
		String  defDirectory	= textfiledialog.getDirectory();
		String  defFile			= textfiledialog.getFile();

		textfiledialog = new java.awt.FileDialog(ctmag, defTitle, defMode);
		textfiledialog.setDirectory(defDirectory);
		textfiledialog.setFile(defFile);
		
		textfiledialog.setVisible(true);		
		String newDirectory = textfiledialog.getDirectory();
		String newFile = textfiledialog.getFile();
		
		if(newDirectory != null && newFile != null)
		    saveTextFile(newDirectory,newFile);	
    } 
    
    private void saveTextFile(String dir, String file)
    {
        System.out.println("Save to data file: "+ dir + file);
        textfile = new TextFile(ctmag);
        textfile.createTextFile(dir, file);
        
    }
}