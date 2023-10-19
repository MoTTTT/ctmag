//**************************************************************
//
//**************************************************************

import java.lang.*;
import java.util.Calendar;
import java.util.Date;
import java.io.*;
import java.text.DecimalFormat;

public class TextFile extends java.lang.Object
{
    protected Ctmag ctmag;
    protected Date date;
    
    protected String testname;
    protected String testername;
    protected String supplyauthority;
    protected String substation;
    protected String feeder;

    protected String ctmake;
    protected String cttype;
    protected String rednumber;
    protected String whitenumber;
    protected String bluenumber;
    protected String ctapplication;
    protected String ctclassification;
    protected String ctvarating;
    
    protected int main_phase[][];
    protected int red_phase[][];
    protected int white_phase[][];
    protected int blue_phase[][];
    
    protected int main_knee_volt;
    protected int main_knee_amp;
    protected int red_knee_volt;
    protected int red_knee_amp;
    protected int white_knee_volt;
    protected int white_knee_amp;
    protected int blue_knee_volt;
    protected int blue_knee_amp;

    private int main_phase_length;
    private int red_phase_length;
    private int white_phase_length;
    private int blue_phase_length;
    
    private final int AMP = 0;
    private final int VOLT = 1;    
    private final int RED = 0;
    private final int WHITE = 1;
    private final int BLUE = 2;  
    
    private float volt_scale;
    private float amp_scale;

    private DataOutputStream ds;
    private DecimalFormat four_digits;    
    
    private final String HEADER = new String("PROMET-CT&VT_Calibration_Tester\n");
    private final String TESTNAME = new String("\nTest_Name: ");
    private final String TESTER = new String("\nRecorded_by: ");
    private final String AUTHORITY = new String("\nSupply_Authority: ");
    private final String SUBSTATION = new String("\nSubstation: ");
    private final String FEEDER = new String("\nFeeder: ");
    private final String MAKE = new String("\nCT_Make: ");
    private final String TYPE = new String("\nCT_Type: ");
    private final String CLASS = new String("\nCT_Classification: ");
    private final String APPLICATION = new String("\nCT_Application: ");
    private final String REDNR = new String("\nRED_number: ");
    private final String WHITENR = new String("\nWHITE_number: ");
    private final String BLUENR = new String("\nBLUE_number: ");
    private final String RATING = new String("\nCT_Varating: ");
    private final String DATE = new String("\nDate: ");
    
    public TextFile (Ctmag c)
    {
        ctmag = c;  
        four_digits = new DecimalFormat("0000");        
    }
 
 //**************************************************************
// Update the General and CT settings for the application. Read
// in and convert the current and voltage readings for the 
// completed magnetisation curve tests.
//**************************************************************
    
    private void updateData()
    {
        updateGeneralSettings();
        updateCTSettings();
        updateMagcurveArrays();
    }
    
    private void updateGeneralSettings()
    {
        date = ctmag.magcurvetest.date;
        testname = ctmag.magcurvetest.getTestname();
        testername = ctmag.magcurvetest.getTestername();
        supplyauthority = ctmag.magcurvetest.getSupplyauthority();
        substation = ctmag.magcurvetest.getSubstation();
        feeder = ctmag.magcurvetest.getFeeder();
    }
    
    private void updateCTSettings()
    {
        ctmake = ctmag.magcurvetest.getCTMake();
        cttype = ctmag.magcurvetest.getCTType();        
        ctclassification = ctmag.magcurvetest.getCTClassification();
        ctapplication = ctmag.magcurvetest.getCTApplication();
        rednumber = ctmag.magcurvetest.getRednumber();
        bluenumber = ctmag.magcurvetest.getBluenumber();
        whitenumber = ctmag.magcurvetest.getWhitenumber();
        ctvarating = ctmag.magcurvetest.getCTVarating();
    }
    
//**************************************************************
// Convert the captured magnetisation curve data sets into voltage
//and current representations reflecting multiplication factors.
//**************************************************************
 
    private void updateMagcurveArrays()
    {
        int amp_mul_factor, volt_mul_factor;
        int volt_mul_index = ctmag.testcontrolengine.FULLSCALE + 1 + ctmag.testcontrolengine.VOLT_MUL;
        int amp_mul_index = ctmag.testcontrolengine.FULLSCALE + 1 + ctmag.testcontrolengine.AMP_MUL;
        int conversion_factor = ctmag.testcontrolengine.CONVERSION_FACTOR;
        
        main_phase_length = ctmag.magcurvetest.maincurve.getLastCurrent() + 1;
        main_phase = new int [2][];
        main_phase[AMP] = new int [main_phase_length];
        main_phase[VOLT] = new int [main_phase_length];
        try{
            amp_mul_factor = ctmag.magcurvetest.maincurve.data[amp_mul_index];
            volt_mul_factor = ctmag.magcurvetest.maincurve.data[volt_mul_index];
            
            populateArrays(ctmag.magcurvetest.maincurve.data,main_phase,main_phase_length, 
                volt_mul_factor, amp_mul_factor, conversion_factor );
            main_knee_volt = ctmag.magcurvetest.maincurve.knee.getY() * volt_mul_factor/conversion_factor;
            main_knee_amp = ctmag.magcurvetest.maincurve.knee.getX() * amp_mul_factor/conversion_factor;
        }
        catch(ArrayIndexOutOfBoundsException a){
            amp_mul_factor = ctmag.testcontrolengine.DEFAULT_A_MUL_FACTOR;
            volt_mul_factor = ctmag.testcontrolengine.DEFAULT_V_MUL_FACTOR;
            
            populateArrays(ctmag.magcurvetest.maincurve.data,main_phase,main_phase_length, 
                volt_mul_factor, amp_mul_factor, conversion_factor );
            main_knee_volt = ctmag.magcurvetest.maincurve.knee.getY() * volt_mul_factor/conversion_factor;
            main_knee_amp = ctmag.magcurvetest.maincurve.knee.getX() * amp_mul_factor/conversion_factor;            
        }

        red_phase_length = ctmag.magcurvetest.vimagcurve[RED].getLastCurrent() + 1;
        red_phase = new int [2][];
        red_phase[AMP] = new int [red_phase_length];
        red_phase[VOLT] = new int [red_phase_length];        
        try{
            amp_mul_factor = ctmag.magcurvetest.vimagcurve[RED].data[amp_mul_index];
            volt_mul_factor = ctmag.magcurvetest.vimagcurve[RED].data[volt_mul_index];
                    
            populateArrays(ctmag.magcurvetest.vimagcurve[RED].data,red_phase,red_phase_length,
                volt_mul_factor, amp_mul_factor, conversion_factor );
            red_knee_volt = ctmag.magcurvetest.vimagcurve[RED].knee.getY() * volt_mul_factor/conversion_factor;
            red_knee_amp = ctmag.magcurvetest.vimagcurve[RED].knee.getX() * amp_mul_factor/conversion_factor;
        }
        catch(ArrayIndexOutOfBoundsException a){
            amp_mul_factor = ctmag.testcontrolengine.DEFAULT_A_MUL_FACTOR;
            volt_mul_factor = ctmag.testcontrolengine.DEFAULT_V_MUL_FACTOR;

            populateArrays(ctmag.magcurvetest.vimagcurve[RED].data,red_phase,red_phase_length,
                volt_mul_factor, amp_mul_factor, conversion_factor );
            red_knee_volt = ctmag.magcurvetest.vimagcurve[RED].knee.getY() * volt_mul_factor/conversion_factor;
            red_knee_amp = ctmag.magcurvetest.vimagcurve[RED].knee.getX() * amp_mul_factor/conversion_factor;            
        }
        
        white_phase_length = ctmag.magcurvetest.vimagcurve[WHITE].getLastCurrent() + 1;
        white_phase = new int [2][];
        white_phase[AMP] = new int [white_phase_length];
        white_phase[VOLT] = new int [white_phase_length];
        try{
            amp_mul_factor = ctmag.magcurvetest.vimagcurve[WHITE].data[amp_mul_index];
            volt_mul_factor = ctmag.magcurvetest.vimagcurve[WHITE].data[volt_mul_index];
                    
            populateArrays(ctmag.magcurvetest.vimagcurve[WHITE].data,white_phase,white_phase_length,
                volt_mul_factor, amp_mul_factor, conversion_factor);
            white_knee_volt = ctmag.magcurvetest.vimagcurve[WHITE].knee.getY() * volt_mul_factor/conversion_factor;
            white_knee_amp = ctmag.magcurvetest.vimagcurve[WHITE].knee.getX() * amp_mul_factor/conversion_factor;
        }            
        catch(ArrayIndexOutOfBoundsException a){
            amp_mul_factor = ctmag.testcontrolengine.DEFAULT_A_MUL_FACTOR;
            volt_mul_factor = ctmag.testcontrolengine.DEFAULT_V_MUL_FACTOR;

            populateArrays(ctmag.magcurvetest.vimagcurve[WHITE].data,white_phase,white_phase_length,
                volt_mul_factor, amp_mul_factor, conversion_factor);
            white_knee_volt = ctmag.magcurvetest.vimagcurve[WHITE].knee.getY() * volt_mul_factor/conversion_factor;
            white_knee_amp = ctmag.magcurvetest.vimagcurve[WHITE].knee.getX() * amp_mul_factor/conversion_factor;            
        }

        blue_phase_length = ctmag.magcurvetest.vimagcurve[BLUE].getLastCurrent() + 1;
        blue_phase = new int [2][];
        blue_phase[AMP] = new int [blue_phase_length];
        blue_phase[VOLT] = new int [blue_phase_length];
        try{
            amp_mul_factor = ctmag.magcurvetest.vimagcurve[BLUE].data[amp_mul_index];
            volt_mul_factor = ctmag.magcurvetest.vimagcurve[BLUE].data[volt_mul_index];
                    
            populateArrays(ctmag.magcurvetest.vimagcurve[BLUE].data,blue_phase,blue_phase_length,
                volt_mul_factor, amp_mul_factor, conversion_factor);       
            blue_knee_volt = ctmag.magcurvetest.vimagcurve[BLUE].knee.getY() * volt_mul_factor/conversion_factor;
            blue_knee_amp = ctmag.magcurvetest.vimagcurve[BLUE].knee.getX() * amp_mul_factor/conversion_factor;
        }
        catch(ArrayIndexOutOfBoundsException a){
            amp_mul_factor = ctmag.testcontrolengine.DEFAULT_A_MUL_FACTOR;
            volt_mul_factor = ctmag.testcontrolengine.DEFAULT_V_MUL_FACTOR;

            populateArrays(ctmag.magcurvetest.vimagcurve[BLUE].data,blue_phase,blue_phase_length,
                volt_mul_factor, amp_mul_factor, conversion_factor);       
            blue_knee_volt = ctmag.magcurvetest.vimagcurve[BLUE].knee.getY() * volt_mul_factor/conversion_factor;
            blue_knee_amp = ctmag.magcurvetest.vimagcurve[BLUE].knee.getX() * amp_mul_factor/conversion_factor;                        
        }
    } 
    
//**************************************************************
// Copy data from a source array into a target array.
//**************************************************************
 
    private void populateArrays(int source_array[], int target_array[][], int size, int volt_factor, int amp_factor, int conversion_factor)
    {
        for(int x = 0; x < size; x++)
        {
           target_array[AMP][x] = x * amp_factor/conversion_factor;
           target_array[VOLT][x] = source_array[x] * volt_factor/conversion_factor;            
        }            
    }
    
//**************************************************************
// Write the text file information into the indicated file as a 
// stream of 8-bit bytes. 
//**************************************************************
 
    public void createTextFile(String d, String f)
    {
        
       try{
            ds = new DataOutputStream(
                   new FileOutputStream(d + f));
            updateData();
            writeGeneralSettings();
            writeCTSettings();
            writeArrayConversions();                   
            ds.close();
            ctmag.statustextfield.setText("Writing the current magcurve test to a text file: " + d + f);           
       }
       catch (FileNotFoundException fnfe){
            ctmag.statustextfield.setText("An error occured when trying to save to the file: " + d + f);
       }

       catch (IOException e){
            ctmag.statustextfield.setText(e + " : An error occurred in writing to the file " + d + f);
       }       
    }    

//**************************************************************
// Write the General settings into the text file.
//**************************************************************
     
    private void writeGeneralSettings()
    {
       try{
        ds.writeBytes(HEADER);
        ds.writeBytes(TESTNAME + testname);
        ds.writeBytes(DATE + date);
        ds.writeBytes(TESTER + testername);
        ds.writeBytes(AUTHORITY + supplyauthority);
        ds.writeBytes(SUBSTATION + substation);
        ds.writeBytes(FEEDER + feeder);
        
       }
        catch (java.io.IOException e){
        }        
    }

//**************************************************************
// Write the CT settings into the text file.
//**************************************************************
 
    private void writeCTSettings()
    {
       try{
        ds.writeBytes(MAKE + ctmake);
        ds.writeBytes(TYPE + cttype);
        ds.writeBytes(CLASS + ctclassification);
        ds.writeBytes(APPLICATION + ctapplication);
        ds.writeBytes(REDNR + rednumber);
        ds.writeBytes(WHITENR + whitenumber);
        ds.writeBytes(BLUENR + bluenumber);
        ds.writeBytes(RATING + ctvarating);        
       }
        catch (java.io.IOException e){
        }        
    }
    
//**************************************************************
// Write the main curve and 3 phase curve data sets to the text 
// file. 
//**************************************************************    
    
    private void writeArrayConversions()
    {
        writeMainDataArray();
        writeRedDataArray();
        writeWhiteDataArray();
        writeBlueDataArray();
    }

    private void writeMainDataArray()
    {
        try{
        ds.writeBytes("\n\nMain_Curve \n");
        ds.writeBytes("mA:  "); 
        for(int x = 0; x < main_phase_length;x++)
        {
            ds.writeBytes(" " + four_digits.format(main_phase[AMP][x]));
        }
        ds.writeByte('\n');
        ds.writeBytes("Volt:");
        for(int x = 0; x < main_phase_length;x++)
        {
            ds.writeBytes(" " + four_digits.format(main_phase[VOLT][x]));
        }
        ds.writeBytes("\nMain_kneepoint: " + main_knee_volt+ "V " + main_knee_amp + "mA \n");
        }
        catch (java.io.IOException e){
        }
    }

    private void writeRedDataArray()
    {
        try{
        ds.writeBytes("\nRed_Phase_Curve \n");
        ds.writeBytes("mA:  "); 
        for(int x = 0; x < red_phase_length;x++)
        {
            ds.writeBytes(" " + four_digits.format(red_phase[AMP][x]));
        }
        ds.writeByte('\n');
        ds.writeBytes("Volt:");
        for(int x = 0; x < red_phase_length;x++)
        {
            ds.writeBytes(" " + four_digits.format(red_phase[VOLT][x]));
        }
        ds.writeBytes("\nRed_kneepoint: " + red_knee_volt+ "V " + red_knee_amp + "mA \n");
        }
        catch (java.io.IOException e){
        }
    }

    private void writeWhiteDataArray()
    {
        try{
        ds.writeBytes("\nWhite_Phase_Curve \n");
        ds.writeBytes("mA:  "); 
        for(int x = 0; x < white_phase_length;x++)
        {
            ds.writeBytes(" " + four_digits.format(white_phase[AMP][x]));
        }
        ds.writeByte('\n');
        ds.writeBytes("Volt:");
        for(int x = 0; x < white_phase_length;x++)
        {
            ds.writeBytes(" " + four_digits.format(white_phase[VOLT][x]));
        }
        ds.writeBytes("\nWhite_kneepoint: " + white_knee_volt+ "V " + white_knee_amp + "mA \n");
        }
        catch (java.io.IOException e){
        }
    }

    private void writeBlueDataArray()
    {
        try{
        ds.writeBytes("\nBlue_Phase_Curve \n");
        ds.writeBytes("mA:  "); 
        for(int x = 0; x < blue_phase_length;x++)
        {
            ds.writeBytes(" " + four_digits.format(blue_phase[AMP][x]));
        }
        ds.writeByte('\n');
        ds.writeBytes("Volt:");
        for(int x = 0; x < blue_phase_length;x++)
        {
            ds.writeBytes(" " + four_digits.format(blue_phase[VOLT][x]));
        }
        ds.writeBytes("\nBlue_kneepoint: " + blue_knee_volt+ "V " + blue_knee_amp + "mA \n");
        }
        catch (java.io.IOException e){
        }
    }    
}