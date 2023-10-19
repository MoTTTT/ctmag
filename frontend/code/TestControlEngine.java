//***************************************************************
// Control the running of the application. Function as a finite
// state machine. 
//***************************************************************

import java.awt.*;
import javax.comm.*;
import java.io.*;
import java.awt.event.*;

public class TestControlEngine implements java.io.Serializable, ActionListener 
{
    protected final int CONVERSION_FACTOR = 10000;
    protected final int CS_VAL = 1000; //!!!
    protected final int CP_VAL = 100000; //!!!
    protected final int VS_VAL = 500; //!!!
    protected final int VP_VAL = 500; //!!!
    protected final int VS_CS = 0;
    protected final int VP_CS = 1;
    protected final int VS_CP = 2;
    protected final int VP_CP = 3;

    protected int VS = 0; //!!!
    protected int VP = 1; //!!!
    protected int CS = 2; //!!!
    protected int CP = 3; //!!!
        
    protected int channel_max_val[]= {VS_VAL, VP_VAL, CS_VAL, CP_VAL}; //!!!
    protected int amp_chan_select = CS; //!!!    
    protected int volt_chan_select = VS; //!!! 
   
    protected int volt_mul_factor;
    protected int amp_mul_factor;
    protected int DEFAULT_V_MUL_FACTOR = 500 * 10000/1023;
    protected int DEFAULT_A_MUL_FACTOR = 1000 * 10000/1023;
    protected float volt_multiply;
 
    protected int drawing_area;
    protected final int HUNDRED_PERCENT = 100;    
    protected boolean draw_real_time;
    protected boolean draw_upsweep; 
    
    protected int settings [];
    
    protected final int RELEASE = 0;
    protected final int LEVEL = 1;
    protected final int BRANCH = 2;
    protected final int SEQUENCE = 3;
    protected final int VOLT_MUL = 4;
    protected final int AMP_MUL = 5;
    protected final int CS_SETTING = 6;
    protected final int SETTINGS_LENGTH = 7;

    protected final int RELEASE_NR = 0;
    protected final int LEVEL_NR = 9;
    protected final int BRANCH_NR = 0;
    protected final int SEQUENCE_NR = 0;
   
    protected Sampler sampler;
    private int mode;
    private int previous_mode;
    private int testmode;
    private int fullscale ;
    protected final int FULLSCALE= 1023;
   
    protected final int NORMAL_MODE = 1;
    protected final int STARTMAGCURVETEST = 2;
    protected final int STARTCURRENTRATIOTEST = 3;
    protected final int STARTVOLTAGERATIOTEST = 4;
    protected final int ASSIGNPHASE = 5;    

    final int VOLT_CHANNEL1_SCALE= 500;
    final int VOLT_CHANNEL2_SCALE= 500;
    final int AMP_CHANNEL1_SCALE= 1000;    
    final int AMP_CHANNEL2_SCALE= 100000;
   
    final protected int VOLTAGE_THRESHOLD=     1003;
    final protected int CURRENT_THRESHOLD=     1003;
    final protected int VOLTAGE_RATIO_THRESHOLD= 562;
    final protected int CURRENT_RATIO_THRESHOLD= 1023/5;
   
    protected int timeout_threshold;
    protected int voltage_threshold;
    protected int current_threshold;
    protected int voltage_ratio_threshold;
    protected int current_ratio_threshold;
   
    private String pol_mess[];   
   
    protected Ctmag parent;
   
   
    public TestControlEngine(Ctmag parent)
    {
        pol_mess = new String[2];
        pol_mess[0] = new String("NO");
        pol_mess[1] = new String("OK");
        
        this.parent = parent;
        mode = NORMAL_MODE;
        fullscale = FULLSCALE;            
        sampler = new Sampler(this);
        initThresholds();                                           // Initialise the default thresholds to be used for the ctmag abort test, the voltage ratio end of test, the current ratio end of test
        setMode(NORMAL_MODE);                                       // Set the state of the testcontrolengine to NORMAL which means that the tester is ready to start a test.
        initOptionsArray();                                         // Write release data to the end of the magcurve data array which would be stored at the end of the test. 
    }
        
//**************************************************************
// Convert  the test thresholds to values to be displayed in
// the OptionsDialog. Write the thresholds to the sampler.
//**************************************************************

    private void initThresholds()
    {
        voltage_threshold = (VOLTAGE_THRESHOLD *                    // Convert the digital value for the magcurve test voltage threshold 
            channel_max_val[volt_chan_select])/ FULLSCALE;          //  into a voltage reading to be displayed in the optionsdialog.
        current_threshold = (CURRENT_THRESHOLD*                     // Convert the digital value for the magcurve test current threshold 
            channel_max_val[amp_chan_select])/FULLSCALE;            //  into an ampere reading to be displayed in the optionsdialog.
        voltage_ratio_threshold = (VOLTAGE_RATIO_THRESHOLD*         // Convert the digital value for the voltage ratio end of test threshold  
            channel_max_val[VP])/FULLSCALE;                         //  into a voltage reading to be displayed in the optionsdialog.
        current_ratio_threshold = (CURRENT_RATIO_THRESHOLD*         // Convert the digital value for the current ratio end of test threshold  
            channel_max_val[CP])/FULLSCALE;                         //  into an ampere reading to be displayed in the optionsdialog.
        
        sampler.setMagVoltThreshold(VOLTAGE_THRESHOLD);             // Set the digital magcurve test voltage reading in sampler at which to abort.
        sampler.setMagCurrentThreshold(CURRENT_THRESHOLD);          // Set the digital magcurve test current reading in sampler at which to abort.
        sampler.setVoltRatioThreshold(VOLTAGE_RATIO_THRESHOLD);     // Set the digital voltage ratio reading in sampler at which to end the test. 
        sampler.setCurrentRatioThreshold(CURRENT_RATIO_THRESHOLD);  // Set the digital current ratio reading in sampler at which to end the test.       
    }

//**************************************************************
// Initialise an array which contains the release no, magcurve volt
// and current multiplication factor, CS setting etc. This array
// is to be appended to the data arrays of the magcurve test. Temperory
// solution to storing test settings while mainting compatability 
// with previous releases.
//**************************************************************

    private void initOptionsArray()
    {
        settings = new int [SETTINGS_LENGTH];
        settings[RELEASE] = RELEASE_NR;
        settings[LEVEL] = LEVEL_NR;
        settings[BRANCH] = BRANCH_NR;
        settings[SEQUENCE] = SEQUENCE_NR;
    }
    
    protected void setCs(int value) //!!!
    {
        channel_max_val[CS] = value;
    }
    
    protected void setAmpMultiplyFactor()
    {
        amp_mul_factor = channel_max_val[amp_chan_select] * CONVERSION_FACTOR/FULLSCALE;        
        settings[AMP_MUL] = amp_mul_factor;
    }
     
//***************************************************************
// Select which channels to use for the serial port COMs
//***************************************************************

    protected void setChannelSelector(int selection) //!!!
    {
        switch(selection)
        {
            case VS_CS:
                amp_chan_select = CS;
                volt_chan_select = VS;
                break;
            case VP_CS:
                amp_chan_select = CS;
                volt_chan_select = VP;
                break;
            case VS_CP:
                amp_chan_select = CP;
                volt_chan_select = VS;
                break;
            case VP_CP:
                amp_chan_select = CP;
                volt_chan_select = VP;
                break;
            default:
                break;            
        }
    }

//***************************************************************
// Set the voltage multiplication factor for the magnetisation 
// curve tests. The recorded digital values are multiplied by
// this factor to determine the measured Volt reading.
//***************************************************************
    
    protected void setVoltageMultiplyFactor(float setting)
    {  
       volt_multiply = setting;
       volt_mul_factor = (int)(setting * channel_max_val[volt_chan_select] * CONVERSION_FACTOR/FULLSCALE);
       settings[VOLT_MUL] = volt_mul_factor;
    }

//***************************************************************
// Write the option settings at the end of the data array. 
//***************************************************************
     
    protected void saveOptionsSettings(MagCurve m)
    {
        try{
        for (int x = 1; x <= SETTINGS_LENGTH -1; x++)   //Do not save the Cs setting     
            m.data[m.BYTE_MAX + x] = settings[x - 1];
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
            parent.statustextfield.setText(a.getMessage());
        }
        return;            
    }
    
//***************************************************************
// Read the settings stored in data array of the specified magcurve.
//***************************************************************

    protected void importOptionsSettings(MagCurve m)
    {
        try{
            for (int x = 1; x <= SEQUENCE; x++)        
                settings[x - 1] = m.data[m.BYTE_MAX + x];
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
            parent.statustextfield.setText(a.getMessage());
        }
    }        

//***************************************************************
// Draw a characteristic curve of the CT as current is being injected.
//***************************************************************

    protected void setUpsweepCheckbox(boolean state)
    {
       draw_upsweep = state;
       parent.maincanvas.draw_upsweep = state;
    }
        
//***************************************************************
// Draw characteristic curves for the CT as the tests are being 
// conducted i.e. while the tests are in progress and not only once
// when a test is completed which is the default procedure.
//***************************************************************
        
    protected void setRealTimeCheckbox(boolean state)
    {
        draw_real_time = state;
        parent.maincanvas.draw_real_time = state;        
    }
    
//***************************************************************
// Setup the maincurve drawing area for the real time drawing of
// the characteristic curve.
//***************************************************************
    
    protected void setDrawArea(int percent)
    {
        drawing_area = percent * FULLSCALE / HUNDRED_PERCENT;
    }
    
    protected int getDrawArea()
    {
        return drawing_area;
    }
    
//***************************************************************
// Set the voltage threshold for an abort test during the magnetisation
// curve test.
//***************************************************************

    public void setMagVoltThreshold(int i)
    {
        if(i <= channel_max_val[volt_chan_select])
        {
            int result, temp;
            voltage_threshold = i;
            parent.magcurvetest.setVoltageThreshold(voltage_threshold);            
            result = (i * FULLSCALE)/channel_max_val[volt_chan_select];
            sampler.setMagVoltThreshold(result);
        }
        else
        parent.statustextfield.setText(
                "Enter an integer value not larger than "+
                channel_max_val[volt_chan_select]+" for the Mag Volt Threshold");                       
    }

    public int getMagVoltThreshold()
    {
        return voltage_threshold;
    }
    
//***************************************************************
// Set the current threshold for an abort test during the magnetisation
// curve test.
//***************************************************************
    
    public void setMagCurrentThreshold(int i)
    {
        int result;

        if(i <= channel_max_val[amp_chan_select])
        {
            current_threshold = i;            
            result = (i * FULLSCALE)/channel_max_val[amp_chan_select];        
            sampler.setMagCurrentThreshold(result); 
            parent.magcurvetest.setCurrentThreshold(current_threshold);            
        }
        else        
        parent.statustextfield.setText(
                "Enter an integer value not larger than "+
                channel_max_val[amp_chan_select]+" for the Mag Current Threshold");                      
    }

    public int getMagCurrentThreshold()
    {
        return current_threshold;
    }

//***************************************************************
// Set the current threshold at which to complete a Current Ratio Test.
//***************************************************************    
    
    public void setCurrentRatioThreshold(int i)
    {
        int result;

        if( i <= channel_max_val[CP])
        {
            current_ratio_threshold = i;            
            parent.magcurvetest.setCurrentRatioThreshold(current_ratio_threshold);
            result = (i * FULLSCALE)/channel_max_val[CP];        
            sampler.setCurrentRatioThreshold(result);  
        }
        else 
        {
            parent.statustextfield.setText(
                "Enter an integer value not larger than "+
                channel_max_val[CP]);
            System.out.println("Current ratio value invalid: "+i);        
        }
    }
    
    public int getCurrentRatioThreshold()
    {
        return current_ratio_threshold;
    }

//*********************************************************************
// Set the voltage threshold at which to complete a Voltage Ratio Test.
//*********************************************************************        
    
    public void setVoltRatioThreshold(int i)
    {
            int result1, result2;
        
        if(i <= channel_max_val[VP])
        {
            voltage_ratio_threshold = i;
            parent.magcurvetest.setVoltageRatioThreshold(voltage_ratio_threshold);            
            result2 = (i * FULLSCALE)/ channel_max_val[VP];        
            sampler.setVoltRatioThreshold(result2);
        }
        else
        parent.statustextfield.setText(
                "Enter an integer value not larger than "+
                channel_max_val[VP]);                       
    }
    
    public int getVoltRatioThreshold()
    {
        return voltage_ratio_threshold;
    }
     
//***************************************************************
// Default state when application starts out i.e. the application
// is ready to start a specified test procedure.
//***************************************************************
    
    public void enableTests( )
    {
        parent.redbutton.setEnabled(false);
        parent.whitebutton.setEnabled(false);
        parent.bluebutton.setEnabled(false);
        parent.magcurvebutton.setEnabled(true);
        parent.currentbutton.setEnabled(true);
        parent.voltagebutton.setEnabled(true);
        parent.stopbutton.setEnabled(false); 

        
        parent.miredphase.setEnabled(false);
        parent.miwhitephase.setEnabled(false);
        parent.mibluephase.setEnabled(false);
        parent.mistartmagcurve.setEnabled(true);
        parent.mistartcurrentratio.setEnabled(true);
        parent.mistartvoltageratio.setEnabled(true);
        parent.mistoptest.setEnabled(false); 
        parent.options_menu_item.setEnabled(true);
    }
    
//***************************************************************
// Disable the start test buttons and the assign phase buttons, the
// abort test button remains enabled. This is the state of the test
// buttons while a test is in progress.
//***************************************************************
    
    public void disableTests( )
    {
        parent.redbutton.setEnabled(false);
        parent.whitebutton.setEnabled(false);
        parent.bluebutton.setEnabled(false);
        parent.magcurvebutton.setEnabled(false);
        parent.currentbutton.setEnabled(false);
        parent.voltagebutton.setEnabled(false);
        parent.stopbutton.setEnabled(true);
        
        parent.miredphase.setEnabled(false);
        parent.miwhitephase.setEnabled(false);
        parent.mibluephase.setEnabled(false);
        parent.mistartmagcurve.setEnabled(false);
        parent.mistartcurrentratio.setEnabled(false);
        parent.mistartvoltageratio.setEnabled(false);
        parent.mistoptest.setEnabled(true);        
        parent.options_menu_item.setEnabled(false);        
    }

//***************************************************************
// Assign a test to one of the phases or abandon the test, this is
// the state on the succesfull completion of a test. 
//***************************************************************

    public void assignTests()
    {
        parent.redbutton.setEnabled(true);
        parent.whitebutton.setEnabled(true);
        parent.bluebutton.setEnabled(true);
        parent.magcurvebutton.setEnabled(false);
        parent.currentbutton.setEnabled(false);
        parent.voltagebutton.setEnabled(false);
        parent.stopbutton.setEnabled(true);
        
        parent.miredphase.setEnabled(true);
        parent.miwhitephase.setEnabled(true);
        parent.mibluephase.setEnabled(true);
        parent.mistartmagcurve.setEnabled(false);
        parent.mistartcurrentratio.setEnabled(false);
        parent.mistartvoltageratio.setEnabled(false);
        parent.mistoptest.setEnabled(true);        
        parent.options_menu_item.setEnabled(false);        
    }

//***************************************************************
// Test the current state of the application and set the Test 
// buttons accordingly.
//***************************************************************

    public void setMode(int i)
    {
        previous_mode = mode;
        mode = i;
        switch(mode)
        {
            case NORMAL_MODE:
                enableTests();
                break;
            case STARTMAGCURVETEST:
                disableTests();
                break;
            case STARTCURRENTRATIOTEST:
                disableTests();
                break;
            case STARTVOLTAGERATIOTEST:
                disableTests();
                break;
            case ASSIGNPHASE:   
                assignTests();
                break;
            default: 
                mode = NORMAL_MODE;
                enableTests();
                break;
        }                         
    }
    
    public int getMode()
    {
        return mode;
    }
    
    public int getPreviousMode()
    {
        return previous_mode;
    }

    
    public void startMagCurveTest()
    { 
        parent.maincanvas.clearMainCurve();
        sampler.startMagCurveTest();
        setMode(STARTMAGCURVETEST);
        testmode= STARTMAGCURVETEST;
    }
    
    public void startCurrentRatioTest()
    {
        parent.saveproc.saveCTSettings();
        sampler.startCurrentRatioTest();        
        setMode(STARTCURRENTRATIOTEST);
        testmode= STARTCURRENTRATIOTEST;
    }
    
    public void startVoltageRatioTest()
    {
        parent.saveproc.saveVTSettings();
        sampler.startVoltageRatioTest();
        setMode(STARTVOLTAGERATIOTEST);
        testmode= STARTVOLTAGERATIOTEST;
    }
    
    public void stopTestButton()
    {
        sampler.stopSampler(sampler.TEST_ABORTED);
        parent.statustextfield.setText("Stopped the test"); 
        parent.ratiodialog.setVisible(false);                         
        setMode(NORMAL_MODE);       
    }

//***************************************************************
// Magnetisation Curve Test completed, store the magcurve in MagcurveTest
// with relevant OptionsDialog settings. Draw the characteristic curve
// for the completed test in the maincanvas.
//***************************************************************

    public void wrapMagCurve(MagCurve magcurve)
    {
        parent.magcurvetest.setMainCurve(magcurve);
        saveOptionsSettings(magcurve);
        parent.magcurvetest.setDate();
        
        if(parent.magcurvetest.main_kneepoint_reached)        
            parent.maincanvas.setIncrements(parent.magcurvetest.maincurve.getLastCurrent(), parent.magcurvetest.maincurve.getLastVoltage());
        else
            parent.maincanvas.setFullScaleIncrements(parent.magcurvetest.maincurve.getFullscale());
            
        parent.maincanvas.setThresholds(magcurve);
        parent.maincanvas.real_time = false;
        parent.maincanvas.repaint();
        parent.magcurveresultshandler.setMainKneeVolt(volt_mul_factor, CONVERSION_FACTOR);
        parent.magcurveresultshandler.setMainKneeAmp (amp_mul_factor, CONVERSION_FACTOR);
        parent.statustextfield.setText("MagCurve test completed. Assign to RED, WHITE or BLUE phase. Select STOP to skip."); 
        setMode(ASSIGNPHASE);               
    }

    public void setCurrentRatio(Ratio r,int i)
    {        
        switch(i)
        {
            case 0:
                parent.magcurvetest.holding_amppolarity =0;
                break;
            case 1:
                parent.magcurvetest.holding_amppolarity =1;
                break;
                
            default:
                break;                
        }        

        parent.magcurvetest.setHoldingCurrentRatio(r);
        parent.magcurvetest.setDate();
        
        parent.currentratioresultshandler.showTestedCurrentRatio();
        parent.currentratioresultshandler.showTestedCurrentPolarity();
        
        parent.ratiodialog.setVisible(true); 
        parent.statustextfield.setText("Current Ratio test completed. Assign to RED, WHITE or BLUE phase. Select STOP to skip.");                     
        setMode(ASSIGNPHASE);               
    }
    
    public void setVoltageRatio(Ratio r,int i)
    {
       switch(i)
        {
            case 0:
                parent.magcurvetest.holding_voltpolarity =0;                            
                break;
            case 1:
                parent.magcurvetest.holding_voltpolarity =1;                            
                break;
                
             default:
                break;                
        }

        parent.magcurvetest.setHoldingVoltageRatio(r);              
        parent.magcurvetest.setDate();        
        parent.voltageratioresultshandler.showTestedVoltageRatio();
        parent.voltageratioresultshandler.showTestedVoltagePolarity();        
        parent.ratiodialog.setVisible(true);                         
        parent.statustextfield.setText("Voltage Ratio test completed. Assign to RED, WHITE or BLUE phase. Select STOP to skip."); 
        setMode(ASSIGNPHASE);          
    }

//***************************************************************
// Assign the completed test to one of the phases. Store the test
// and display the measured results of the assigned test.
//***************************************************************
    
    public boolean assignPhase(int phase)
    {
        switch(testmode)
        {
            case STARTMAGCURVETEST:
                if( !parent.magcurvetest.assignCurve( phase ))
                    return  false;                
                if(parent.magcurvetest.kneepoint_reached)
                {
                    parent.phasecanvas.setIncrements(parent.magcurvetest.max_last_current, parent.magcurvetest.max_last_voltage);                
                    parent.reportphasecanvas.setIncrements(parent.magcurvetest.max_last_current, parent.magcurvetest.max_last_voltage);                                    
                }
                else
                {
                    parent.phasecanvas.setFullScaleIncrements();
                    parent.reportphasecanvas.setFullScaleIncrements();
                }    
                parent.phasecanvas.readDataIn(parent.magcurvetest.vimagcurve[phase], phase);                
                parent.magcurveresultshandler.showMagCurvePhase(phase, volt_mul_factor, amp_mul_factor, CONVERSION_FACTOR);                
                if (parent.magcurvetest.main_kneepoint_reached)
                    parent.kneepointtablehandler.doTable(phase, volt_mul_factor, amp_mul_factor, CONVERSION_FACTOR);
                saveOptionsSettings(parent.magcurvetest.vimagcurve[phase]);
                setMode(NORMAL_MODE);
                return true;

             case STARTCURRENTRATIOTEST:
                int byte_max = parent.magcurvetest.maincurve.BYTE_MAX;
             
                if(!parent.magcurvetest.assignCurrentRatio( phase ))
                    return  false;
                try{    
                parent.magcurvetest.vimagcurve[phase].data[byte_max + CS_SETTING] = channel_max_val[CS];
                }
                catch(ArrayIndexOutOfBoundsException a){
                    System.out.println("ArrayIndexOutOfBoundsException occured when trying to save Cs setting.");
                }
                parent.currentratioresultshandler.showPhaseRatio(phase);               
                parent.currentratioresultshandler.showPhasePolarity(phase);
                parent.currentratioresultshandler.showTruePhaseRatio(phase);
                parent.currentratioresultshandler.showMeasError(phase);                
                parent.ratiodialog.setVisible(false); 
                setMode(NORMAL_MODE);
                return true;

             case STARTVOLTAGERATIOTEST:
                if(!parent.magcurvetest.assignVoltageRatio(phase))
                    return  false;                
                parent.voltageratioresultshandler.showPhaseRatio(phase);               
                parent.voltageratioresultshandler.showPhasePolarity(phase);
                parent.voltageratioresultshandler.showTruePhaseRatio(phase);
                parent.voltageratioresultshandler.showMeasError(phase);                
                parent.ratiodialog.setVisible(false);                 
                setMode(NORMAL_MODE);
                return true;

             default:
                return false;
        }
    }

    public void commsErrorMessage()
    {
        parent.statustextfield.setText( "Error in comms ocurred.");               
    }
    
    public void setFullscale(int i)
    {
       fullscale = i;
    }

    public int getFullscale()
    {
        return fullscale;
    }

//***************************************************************
// Test completed. If an error state has been recorded display a
// descriptive message in the statustextfield else set the state 
// of the ControlEngine to the assign to a phase state.
//***************************************************************

    public void testFinished ( int status )
    {   
        parent.maincanvas.real_time = false;
        
        if ( status != sampler.TEST_OK )
        {
            setMode(NORMAL_MODE);
            if (status == sampler.COMMS_ERROR)
            {
                switch(sampler.comms_status)
                {
                    case sampler.serialconnection.NO_ERROR :
                        parent.statustextfield.setText("Serial comms OK." );
                        break;
                    case sampler.serialconnection.ERROR_GET_PORT_ID :
                        parent.statustextfield.setText("Error getting serial communications port identifier.");
                        break;
                    case sampler.serialconnection.ERROR_OPEN_PORT :
                        parent.statustextfield.setText("Error opening serial communications port.");
                        break;
                    case sampler.serialconnection.ERROR_SET_PARAMETERS :
                        parent.statustextfield.setText("Error setting serial communications port parameters.");
                        break;
                    case sampler.serialconnection.ERROR_GET_IO_STREAM :
                        parent.statustextfield.setText( "Error getting serial communications input - and outputstreams.");
                        break;
                    case sampler.serialconnection.ERROR_ADD_LISTENER :
                        parent.statustextfield.setText("Error adding eventlisteners to serial communications port.");
                        break;
                    case sampler.serialconnection.ERROR_CLOSE_IO_STREAM :
                        parent.statustextfield.setText("Error closing serial communications input - and outputstreams.");
                        break;
                    case sampler.serialconnection.ERROR_NOT_OPEN :
                        parent.statustextfield.setText("Error trying to close an already closed serial communications port ");
                        break;
                    case sampler.serialconnection.ERROR_IO_EXCEPTION :
                        parent.statustextfield.setText("Error accessing serial communications inputstream.");
                        break;
                    case sampler.serialconnection.INPUTSTREAM_ERROR :
                        parent.statustextfield.setText("Error accessing serial communications inputstream.");
                        break;
                        
                    default :
                        parent.statustextfield.setText("An undefined serial communications error generated. " );
                        break;
                }
            } 
            
            else
            {
                switch(status)
                {
                    case sampler.MAX_ERROR_EXCEEDED:
                        parent.statustextfield.setText("Error: " + status + " Maximum error threshold exceeded.");
                        break;
                    case  sampler.TIMEOUT_EXCEEDED:
                        parent.statustextfield.setText("Error: " + status + " Tme-out exceeded.");
                        break;
                    case  sampler.FRAME_SYNC_ERROR:
                        parent.statustextfield.setText("Error: " + status + " Frame sync error." );
                        break;
                    case sampler.OVER_RUN_ERROR:
                        parent.statustextfield.setText("Error: " + status + " Overrun error.");
                        break;
                    case sampler.TEST_ABORTED:
                        parent.statustextfield.setText("Error: " + status + " Test aborted.");
                        break;
                    case sampler.SERIAL_PORT_ACTIVE:
                        parent.statustextfield.setText("Error: " + status + " Serial Port active.");
                        break;
                    case sampler.MAGCURVE_THRES:
                        parent.statustextfield.setText("Magcurve Test aborted, set threshold exceeded. " );
                        break;                        
                    case sampler.UNKNOWN_ERROR:
                        parent.statustextfield.setText("Error: " + status + " Unknown error.");
                        break;
                    default:
                        break;
                } 
            }                
        }
        else
            setMode(ASSIGNPHASE);        
    }

//***************************************************************
// Determine which of the Test buttons were activated and service 
// accordingly.
//***************************************************************
    
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == parent.redbutton || source == parent.miredphase)
        {
            parent.statustextfield.setText( "Assign test to the RED phase.");
            assignPhase(parent.magcurvetest.RED);            
        }
        else if(source == parent.whitebutton || source == parent.miwhitephase)
        {
            parent.statustextfield.setText( "Assign test to the WHITE phase.");
            assignPhase(parent.magcurvetest.WHITE);
        }
        else if(source == parent.bluebutton || source == parent.mibluephase)
        {
            parent.statustextfield.setText( "Assign test to the BLUE phase.");
            assignPhase(parent.magcurvetest.BLUE);
        }
        else if(source == parent.mistartmagcurve || source == parent.magcurvebutton)
        {
            parent.statustextfield.setText( "Started Magnetisation Curve Test.");
            startMagCurveTest();
        }
        else if(source == parent.mistartcurrentratio || source == parent.currentbutton)
        {
            parent.statustextfield.setText( "Started Current Ratio Test.");
            startCurrentRatioTest();        
        }
        else if(source == parent.mistartvoltageratio || source == parent.voltagebutton)
        {
            parent.statustextfield.setText( "Started Voltage Ratio Test.");
            startVoltageRatioTest();                
        }
        else if(source == parent.mistoptest || source == parent.stopbutton)
        {
            parent.statustextfield.setText( "Stop Test.");
            stopTestButton();                        
        }
        else if(source ==  parent.minexttest)
        {
            parent.newMagCurveTest();
            try{
                    parent.importtest.nextTest();
                    parent.testhandler.openFileDialog1.setFile(null);
                    parent.statustextfield.setText("Instantiate a new test.");
                    parent.setTitle("CT & VT Calibration Tester");            
                }
            catch(java.beans.PropertyVetoException pve){
                }        
        }
         else if(source ==  parent.miactionnewtest || source == parent.minewtest || source ==  parent.newtestbutton)
         {
            parent.newMagCurveTest();
            try{
                parent.importtest.getImportTest();
                parent.testhandler.openFileDialog1.setFile(null);
                parent.statustextfield.setText("Instantiate a new test.");
                parent.setTitle("CT & VT Calibration Tester");            
            }
            catch(java.beans.PropertyVetoException pve){
            }
        }
    }
}