//*****************************************************************
// Gets instantiated and called from testhandler. Read in the selected
// serialised MagCurveTest.
//*****************************************************************

import java.lang.*;
import java.io.*;
import java.beans.*;
import java.awt.FileDialog;

class ImportSequentialFile implements java.io.Serializable 
{
    private MagCurveTest magcurvetest;
    private Ctmag ctmag;
    private DataInputStream datainputstream;
    private ObjectInputStream objectinputstream;
    private FileDialog openFileDialog1;
    
    
    public void setObjects(Ctmag f, MagCurveTest m)
    {
        magcurvetest = m;
        ctmag = f;
    }

    public void setObjects(FileDialog f)
    {
       openFileDialog1 = f; 
    }    
     
//*****************************************************************
// Read in the serialised MagCurveTest object. Pass the imported
// test object on to Ctmag and ImportTest.
//*****************************************************************     
            
    public MagCurveTest getFile(String s)
    {
        try {
            datainputstream = new DataInputStream
            (new FileInputStream(s));
            objectinputstream = new ObjectInputStream(datainputstream);
            ctmag.statustextfield.setText("Import the test file " + s );
            magcurvetest = (MagCurveTest)(objectinputstream.readObject()) ;
            datainputstream.close();
            magcurvetest.setTestname(openFileDialog1.getFile());
            ctmag.importMagCurveTest(magcurvetest);
            ctmag.importtest.getImportTest();
            ctmag.setTitle("CT & VT Calibration Tester - " + s);            
        }

        catch(FileNotFoundException fnfe){
            System.out.println("The specified file not found: " + s);
            ctmag.statustextfield.setText("The specified file not found: " + s);
        }
        
        catch(IOException e){
            System.out.println("IOException Exception occurred when importing the test file: " + s);
            ctmag.statustextfield.setText("IOException Exception occurred when importing the test file: " + s);
        }

        catch (ClassNotFoundException c){
            System.out.println("ClassNotFoundException Exception occurred when importing the test file: " + s);
            ctmag.statustextfield.setText("ClassNotFoundException Exception occurred when importing the test file: " + s);

        }
        catch(PropertyVetoException pve){
            System.out.println("PropertyVetoException when importing the test file: " + s);
            ctmag.statustextfield.setText("PropertyVetoException when importing the test file: " + s);
        }

        return magcurvetest;
    }
}