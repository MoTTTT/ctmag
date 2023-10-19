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
    
    
/*    public void createFile()
    {
       try{
           dataoutputstream = new DataOutputStream(
                   new FileOutputStream(magcurvetest.getTestname()));

           ObjectOutputStream p = new ObjectOutputStream(dataoutputstream);
           p.writeObject (magcurvetest);  // Write serializable object to file
           p.flush();
           dataoutputstream.close();
           ctmag.statustextfield.setText("Writing serialized test to \"" + magcurvetest.getTestname() + "\" in the current working directory.");           
       }
       catch (FileNotFoundException fnfe){
            ctmag.statustextfield.setText("An error occured when trying to save to the filename entered in the \"Test Name\" textfield.");
       }

       catch (IOException e){
            ctmag.statustextfield.setText(e + " : An error occurred in writing to the file \"" + magcurvetest.getTestname()+ "\"");
       }
       

    }
*/    
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