//**************************************************************
// Gets instantiated and called by TestHandler.
//**************************************************************

import java.io.*;
import java.awt.FileDialog;

class CreateSequentialFile implements java.io.Serializable  
{
    private MagCurveTest magcurvetest;
    private Ctmag ctmag;
    private DataOutputStream dataoutputstream;
    private FileDialog openFileDialog1;
    
    public CreateSequentialFile(Ctmag c)
    {
        ctmag = c;
    }
    
    public void setObjects(FileDialog f)
    {
       openFileDialog1 = f; 
    }    

//**************************************************************
// Write a serialised instance of the MagCurveTest to the file 
// and directory passed to the method by TestHandler. 
//**************************************************************

    public void createFile(String d, String f)
    {
       try{
            dataoutputstream = new DataOutputStream(
                   new FileOutputStream(d + f));

            ObjectOutputStream p = new ObjectOutputStream(dataoutputstream);
            ctmag.magcurvetest.setTestname(f);           
            p.writeObject (ctmag.magcurvetest);  // Write serializable object to file
            p.flush();
            dataoutputstream.close();
            ctmag.generalsettingspanel.testnametextfield.setText(f);
            ctmag.statustextfield.setText("Writing serialized test to " + d + f);           
            ctmag.setTitle("CT & VT Calibration Tester - " + d + f);            
       }
       catch (FileNotFoundException fnfe){
            ctmag.statustextfield.setText("An error occured when trying to save to the file: " + d + f);
       }

       catch (IOException e){
            ctmag.statustextfield.setText(e + " : An error occurred in writing to the file " + d + f);
       }       
    }
}