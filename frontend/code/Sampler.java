//****************************************************************
// Sampler handels bytes received via the SerialConnection. Determine
// the end of a test. 
//****************************************************************

import javax.comm.*;
import java.io.*;
import java.lang.Object.*;
import java.text.DecimalFormat;

public class Sampler implements java.io.Serializable 
{    
    protected SerialConnection serialconnection;
    protected MagCurve samplermagcurve;
    protected Polarity samplerpolarity;
    protected TestControlEngine parent;
    private   RuningThread runingthread;
    private Ratio holdingratio;
        
    private final int RATIO_SAMPLE_THRESHOLD =  10;
    private final int STABLECOUNTMAX = 20;    
    private int ratio_x1[];
    private int ratio_x2[];
    private int ratiototal_x1;
    private int ratiototal_x2;
    private int ratio_counter;
    
    private int vs_value;
    private int vp_value;
    private int cs_value;
    private int cp_value;

    private float channel1_volt;
    private float channel2_volt;
    private float channel1_amp;
    private float channel2_amp;    

    private int voltage_threshold;
    private int current_threshold;
    private int voltage_ratio_threshold;
    private int current_ratio_threshold;
                
    protected final int CONTINUE=              0;
    protected final int TEST_OK=               1;
    protected final int MAX_ERROR_EXCEEDED=    2;
    protected final int TIMEOUT_EXCEEDED=      3;
    protected final int FRAME_SYNC_ERROR=      4;
    protected final int COMMS_ERROR=           5;
    protected final int OVER_RUN_ERROR=        6;
    protected final int TEST_ABORTED=          7;
    protected final int SERIAL_PORT_ACTIVE=    8;
    protected final int MAGCURVE_THRES=        9;
    protected final int UNKNOWN_ERROR=         99;
    
    protected int timeout_threshold;
    protected final int TIMEOUT_THRESHOLD=     10000;    
    
    private final int NULL=                     0;
    private final int READY=                    1;
    private final int GETCHAR1=                 2;    
    private final int GETCHAR2=                 3;    
    private final int GETCHAR3=                 4;
    private final int GETCHAR4=                 5;
    private final int SPACE=                    6;    
    private final int GETCR=                    7;
    private final int GETLF=                    8;
    private final int ERROR=                    9;
    private final int HUNTING=                  10;

    protected final int NO_TEST=                0;
    protected final int KNEE_POINT_TEST=        1;
    protected final int HIGH_CURRENT_TEST=      2;
    protected final int STABLE_VOLTAGE_TEST=    3;
    protected final int CURRENT_RATIO_TEST=     4;
    protected final int VOLTAGE_RATIO_TEST=     5;

    private final int CHANNELS=                 5;  
    private final int VOLTAGE_CHANNEL1=         0;
    private final int VOLTAGE_CHANNEL2=         1;
    private final int CURRENT_CHANNEL1=         2;
    private final int CURRENT_CHANNEL2=         3;
    private final int POLARITY_CHANNEL=         4;
    
    private final int FRAME_SYNC_MAX=           100;
    private final int MAX_ERROR_CNT=            100;
    private final int MAX_HUNT_CNT=(4*CHANNELS+2)* FRAME_SYNC_MAX;
    private final int MAX_DIGITAL =             1023; 
    private final int MIN_VOLT_DIVISOR=         5;
    
    protected int frame_cnt =                   0;
    protected int first_current_reading; 
    protected int test_type= NO_TEST;
    protected int comms_status;
    private int channel_idx;
    private int error_cnt= 0;
    private int hunt_cnt= 0;
    private int channel_data[];
    private int framestate;
    private int magCurveTestState;
    private int testpolarity;
    private int last_current;
    private int channel_value;
    private int mag_amp_channel;
    private int mag_volt_channel;
    
    private boolean kneepointDetected;
    private boolean hiCurrentDetected;
    private boolean first_val_read;
    private int hiCurrent;
    private int voltageStable;
    private int lastVoltage;
    private int minTestEndVolt;

    private String port =                   "COM1";
    
    private DecimalFormat three_digits;
    private DecimalFormat four_digits;
    private DecimalFormat six_digits;
    

    public Sampler( TestControlEngine parent )
    {
        this.parent = parent;
        timeout_threshold = TIMEOUT_THRESHOLD;
        channel_data = new int[CHANNELS];
        ratio_x1 = new int[RATIO_SAMPLE_THRESHOLD];
        ratio_x2 = new int[RATIO_SAMPLE_THRESHOLD];
        holdingratio = new Ratio();
        samplerpolarity = new Polarity();
        samplermagcurve = new MagCurve(this);
        three_digits = new DecimalFormat("000");
        four_digits = new DecimalFormat("0000");
        six_digits = new DecimalFormat("000000");
        serialconnection = new SerialConnection();
        serialconnection.initSerialConnection(this, port);
    }
    
    public void setPort(String s)
    {
        port = s;
        serialconnection.initSerialConnection(this, port);        
    }    
       
    public void resetData()
    {
        for (int i = 0;i < (CHANNELS);i++)
            channel_data[i] = 0;
    }

    public int getFramestate()
    {
        return framestate;
    }

//****************************************************************
// Start the voltage ratio test. Kick off serial comms monitoring
// thread.
//****************************************************************

    public void startVoltageRatioTest()
    {
        comms_status = serialconnection.openConnection();
        if  ( comms_status == serialconnection.NO_ERROR )
        {
            framestate = NULL;
            test_type=  VOLTAGE_RATIO_TEST;
            resetRatioValues();
            samplerpolarity.resetValues();
            setRatioChannelValues();            
            runingthread = new RuningThread();
            runingthread.initRuningThread(this); 
            runingthread.start();
    	}
    	else
            stopSampler ( COMMS_ERROR );       
    }

//****************************************************************
// Start the current ratio test. Kick off serial comms monitoring
// thread.
//****************************************************************
    
    public void startCurrentRatioTest()
    {
        comms_status = serialconnection.openConnection();
        
        if  ( comms_status == serialconnection.NO_ERROR )
        {
            framestate = NULL;
            test_type=  CURRENT_RATIO_TEST;
            resetRatioValues();
            samplerpolarity.resetValues();
            setRatioChannelValues();  
            runingthread = new RuningThread();
            runingthread.initRuningThread(this);            
            runingthread.start(); 
    	}
    	else
            stopSampler ( COMMS_ERROR );
    }

    private void resetRatioValues()
    {
        ratiototal_x1 = 0;
        ratiototal_x2 = 0;        
        ratio_counter = 0;
        for (int r = 0;r < RATIO_SAMPLE_THRESHOLD; r++)
        {
            ratio_x1[r] = 0;
            ratio_x2[r] = 0;
        }
    }

    private void setRatioChannelValues()
    {
        cs_value = parent.channel_max_val[parent.CS];
        cp_value = parent.channel_max_val[parent.CP];
        vs_value = parent.channel_max_val[parent.VS];
        vp_value = parent.channel_max_val[parent.VP];
    }

//****************************************************************
// Start the MagCurve test. Kick off serial comms monitoring
// thread.
//****************************************************************
        
    public void startMagCurveTest()    
    {
        mag_amp_channel = parent.amp_chan_select;
        mag_volt_channel = parent.volt_chan_select;
        samplermagcurve = new MagCurve();
        comms_status = serialconnection.openConnection();
        if  ( comms_status != serialconnection.NO_ERROR )
        {
            stopSampler ( COMMS_ERROR );
            return;
        }
        kneepointDetected= false;
        framestate = NULL;
        test_type=  KNEE_POINT_TEST;
        last_current = 0;
        first_current_reading = 0;
        setMagChannelValues();
        parent.parent.maincanvas.setupMainCurve(parent.getDrawArea());
        runingthread = new RuningThread();
        runingthread.initRuningThread(this);
        runingthread.start();
    }
    
    private void setMagChannelValues()
    {
        cs_value = parent.channel_max_val[parent.CS];
        cp_value = parent.channel_max_val[parent.CP];
        if(parent.volt_chan_select == parent.VS)
        {
            vs_value = MAX_DIGITAL * parent.volt_mul_factor/parent.CONVERSION_FACTOR;
            vp_value = parent.channel_max_val[parent.VP];
        }
        else
        {
            vs_value = parent.channel_max_val[parent.VS];
            vp_value = MAX_DIGITAL * parent.volt_mul_factor/parent.CONVERSION_FACTOR;            
        }
    }
    
    public void stopSampler ( int in )
    {
        framestate = NULL;
        test_type=  NO_TEST;
        parent.testFinished( in );
        serialconnection.closeConnection();        
    }

//****************************************************************
// Extrapolate between the current reading and the previous reading
// during a magnetisation curve test.
//****************************************************************
       
    private void extrapolateData(Curve cleancurve, int x1, int x2)
    {
        int ygap, xgap, vstart, start, stop;
        if ( x1< x2 )
        {
            start= x1;
            stop= x2;
        }
        else if ( x1> x2 )
        {
            start= x2;
            stop= x1;
        }
        else return;
        if  ( ( stop- start)< 2 )
            return;
        xgap=   stop - start;
        vstart= cleancurve.readData(start);
        ygap=   cleancurve.readData(stop)- vstart;
        for (int x = 1; x < xgap; x++)
            cleancurve.setCurvePoint(start+ x,
                vstart+ ((x* ygap)/ xgap));                         
    }

//****************************************************************
// Test whether a kneepoint has been reached during the magnetisation
// curve test.
//****************************************************************

    private int testKneePoint( int current, int voltage )
    {
        if  ( (current== 0)|| (voltage==0) )    // Ignore signal zero
            return  ( CONTINUE );
        if  ( checkThreshold ( voltage, current ))
            return  ( MAGCURVE_THRES );
        samplermagcurve.setCurvePoint(current, voltage );
        if(parent.draw_upsweep)
            parent.parent.maincanvas.addCurvePoint( 0,
                last_current, lastVoltage, current, voltage );
        extrapolateData( samplermagcurve, last_current, current );
        if  ( samplermagcurve.wayPastKneePoint(current, voltage ))
        {
            hiCurrent= current;
            test_type= HIGH_CURRENT_TEST;
            minTestEndVolt= voltage / MIN_VOLT_DIVISOR;
            samplermagcurve= new MagCurve();  
            samplermagcurve.setLastCurrent(current);  
            samplermagcurve.setLastVoltage(voltage);  
            parent.parent.getToolkit().beep();
        }
        lastVoltage= voltage;        
        last_current= current;
        return  ( CONTINUE );
    }

//****************************************************************
// Test whether the current readings are still increasing after the 
// kneepoint has been detected during the magnetisation curve test.
// If the current readings have decreased to a value below 9/10 of 
// the maximum current reading, then start testing for an end of test 
// condition i.e. a stable voltage reading at a constant current reading.
//****************************************************************

    private int testHighCurrent( int current, int voltage )
    {
         samplermagcurve.setCurvePoint(current, voltage );
         extrapolateData( samplermagcurve, last_current, current );
         if  ( current > hiCurrent )
         {
            if(parent.draw_upsweep)
                parent.parent.maincanvas.addCurvePoint( 0,
                    last_current, lastVoltage, current, voltage );
             hiCurrent= current;             
             samplermagcurve.setLastCurrent(current);
             samplermagcurve.setLastVoltage(voltage);
         }
         else
         {
            parent.parent.maincanvas.addCurvePoint( 1,
                last_current, lastVoltage, current, voltage );
            if  ( current < (hiCurrent * 9/10) ) 
             {
                 test_type= STABLE_VOLTAGE_TEST;
                 voltageStable= 0;
             }
         }
         lastVoltage= voltage;
         last_current= current;
         return  ( CONTINUE );
     }

//****************************************************************
// Test whether an end of magnetisation curve test condition has been
// reached i.e. a stable voltage reading at a stable current reading.
//****************************************************************
     
    private int testStableVoltage( int current, int voltage )
    {
        samplermagcurve.setCurvePoint(current, voltage );
        parent.parent.maincanvas.addCurvePoint( 1,
            last_current, lastVoltage, current, voltage );
        extrapolateData( samplermagcurve, last_current, current );
        if  ( voltage <= minTestEndVolt )
        {
            if ( voltage!= lastVoltage )
            {
                lastVoltage= voltage;
                last_current= current;
                voltageStable= 0;
                return  ( CONTINUE );
            }                
            if ( voltageStable++ == STABLECOUNTMAX )
            {
                samplermagcurve.calculateKneePoint( current ); 
                parent.parent.getToolkit().beep();
                parent.wrapMagCurve(samplermagcurve);
                lastVoltage= voltage;
                last_current= current;
                test_type= NO_TEST;
                return  ( TEST_OK );
            }
        }
        lastVoltage= voltage;
        last_current= current;
        return  ( CONTINUE );
    }

//****************************************************************
// Store the ratio readings into an array, after a specified number 
// of readings have been taken calculate the average and store the 
// value.
//****************************************************************

    private boolean setRatioPoint(int x1, int x2)
    {
        ratio_x1[ratio_counter] = x1;
        ratio_x2[ratio_counter] =  x2;
        ratio_counter++;
        if(ratio_counter == RATIO_SAMPLE_THRESHOLD)
        {
            ratiototal_x1 = 0;
            ratiototal_x2 = 0;
            
            for(int q= 0; q < RATIO_SAMPLE_THRESHOLD; q++)
            {
                ratiototal_x1 += ratio_x1[q];
                ratiototal_x2 += ratio_x2[q];                
            }
            ratiototal_x1 = ratiototal_x1/RATIO_SAMPLE_THRESHOLD;
            ratiototal_x2 = ratiototal_x2/RATIO_SAMPLE_THRESHOLD;
            holdingratio.setRatio(ratiototal_x1,ratiototal_x2);
            ratio_counter = 0;
        }
    return true;        
    }    

//****************************************************************
// Read and interperet the stream of bytes received on the serial 
// port. Convert the ASCII characters into integer values and write it 
// into the correct channel array.
//****************************************************************
    
    public void frameHandler(int serial_in)
    {
        switch(framestate)
        {
        case NULL:
                resetData();
                error_cnt = 0;
                framestate = HUNTING;
                break;
        case READY:
                channel_idx = 0;
        case GETCHAR1:  
                if((serial_in < '0') ||(serial_in > '9'))
                    framestate = ERROR;
                else
                {
                    channel_data[channel_idx] = (serial_in - '0') * 1000;
                    framestate = GETCHAR2;
                }
                break;
        case GETCHAR2:
                if((serial_in < '0') ||(serial_in > '9'))
                    framestate = ERROR;
                else
                {
                    channel_data[channel_idx] += (serial_in - '0') * 100;
                    framestate = GETCHAR3;
                }
                break;
        case GETCHAR3:
                if((serial_in < '0') ||(serial_in > '9'))
                    framestate = ERROR;
                else
                {
                    channel_data[channel_idx] += (serial_in - '0') * 10;
                    framestate = GETCHAR4;
                }
                break;
        case GETCHAR4:  
                if((serial_in < '0') ||(serial_in > '9'))
                    framestate = ERROR;
                else
                {
                    channel_data[channel_idx] += (serial_in - '0');
                    framestate = SPACE;
                }
                break;

        case SPACE:
                if(serial_in != ' ' )
                    framestate = ERROR;
                else
                {
                    channel_idx++;
                    if  ( channel_idx > CHANNELS- 1 )
                        framestate = GETCR;
                    else
                        framestate = GETCHAR1;
                }
                break;
        case GETCR:
                if(serial_in != 0x0d)
                    framestate = ERROR;
                else framestate = GETLF;
                break;
        case GETLF:
                if(serial_in != 0x0A)
                    framestate = ERROR;
                else
                {
                    handleFrame( );
                    framestate = READY;
                }
                break;
        case HUNTING:
                if(serial_in == 0x0A)
                    framestate = READY;
                else
                    if  ( hunt_cnt++== MAX_HUNT_CNT )
                        stopSampler ( FRAME_SYNC_ERROR );
                break;
        case ERROR:
        	    try
        	    {
    	            serialconnection.is.skip(serialconnection.is.available());
        	    }
        	    catch (IOException e)
	            {
    	            System.out.println(e.getMessage());    	            	    	        
    	            comms_status = serialconnection.INPUTSTREAM_ERROR;	            
                    stopSampler ( COMMS_ERROR );
        	    }
                framestate = HUNTING;
                if (error_cnt++ > MAX_ERROR_CNT)
                    stopSampler (MAX_ERROR_EXCEEDED);
                break;
        default:
                framestate = ERROR;
                break;
        }
    }

//****************************************************************
// Display the converted voltage and current readings. Call method 
// to process data.
//****************************************************************

    public void handleFrame( )
    {
        int error;
        frame_cnt++;
        channel1_volt = channel_data[0]*vs_value/MAX_DIGITAL;
        channel2_volt = channel_data[1]*vp_value/MAX_DIGITAL;
        channel1_amp = channel_data[2]*cs_value/MAX_DIGITAL;
        channel2_amp = channel_data[3]*cp_value/MAX_DIGITAL;
        parent.parent.statustextfield.setText ("Vs channel:  "+ three_digits.format(channel1_volt) + " V  " +
            "Vp channel:  "+ three_digits.format(channel2_volt) + " V  " +
            "Cs channel:  "+ four_digits.format(channel1_amp) + " mA  " +
            "Cp channel:  "+ six_digits.format(channel2_amp) + " mA");
        error= processData();
        if  ( error!= CONTINUE )
            stopSampler ( error );
    }

//****************************************************************
// Process the received data i.e. test whether thresholds have been
// reached, kneepoints been detected, stable voltages measured etc. 
//****************************************************************
    
    private int processData( )
    {

        int current1, current2, voltage1, voltage2, polarity;
        int mag_current, mag_voltage;
        
        mag_current = channel_data[mag_amp_channel];
        mag_voltage = channel_data[mag_volt_channel];
        current1= channel_data[CURRENT_CHANNEL1];
        current2= channel_data[CURRENT_CHANNEL2];
        voltage1= channel_data[VOLTAGE_CHANNEL1];
        voltage2= channel_data[VOLTAGE_CHANNEL2];
        polarity= channel_data[POLARITY_CHANNEL];
        switch  ( test_type )
        {
        case KNEE_POINT_TEST:
            return  ( testKneePoint( mag_current, mag_voltage ));
        case HIGH_CURRENT_TEST:
            return  ( testHighCurrent ( mag_current, mag_voltage ) );
        case STABLE_VOLTAGE_TEST:
            return  ( testStableVoltage ( mag_current, mag_voltage ) );
        case CURRENT_RATIO_TEST:
            if  ( !setRatioPoint( current1, current2 ) )
                framestate = ERROR;
            if  ( !samplerpolarity.testPolarity( polarity ) )
                framestate = ERROR;
            if  ( checkThreshold ( current1, current2 ) )
            {
                testpolarity= samplerpolarity.fuzzyPhaseTest();  
                parent.setCurrentRatio(holdingratio, testpolarity);
                test_type= NO_TEST;
                return ( TEST_OK );
            }
            break;
        case VOLTAGE_RATIO_TEST: 
            if  ( !setRatioPoint( voltage1, voltage2 ) )
                framestate = ERROR;
            if  ( !samplerpolarity.testPolarity( polarity ) )
                framestate = ERROR;
            if  ( checkThreshold (  voltage1, voltage2 ) )
            {
                testpolarity= samplerpolarity.fuzzyPhaseTest();   
                parent.setVoltageRatio(holdingratio, testpolarity);
                test_type= NO_TEST;
                return ( TEST_OK );
            }
            break;
        case NO_TEST: 
        default:
            test_type= NO_TEST;
            return ( SERIAL_PORT_ACTIVE );
        }
        return  ( CONTINUE );
    }

//****************************************************************
// Test whether the set thresholds have been reached.
//****************************************************************
    
    private boolean checkThreshold(int in1, int in2 )
    {
        switch  ( test_type )
        {
        case CURRENT_RATIO_TEST: 
            if  ( in2 > current_ratio_threshold ) 
                return true;
            break;
        case VOLTAGE_RATIO_TEST: 
            if  ( in2> voltage_ratio_threshold)
                return true;
            break;
        case NO_TEST: 
            return true;
        default:
            if(( in1  > voltage_threshold) || ( in2 > current_threshold))
            {
                return true;
            }
            break;
        }
        return false;
    }    
    
    public void setTimeOutThreshold(int i)
    {
        timeout_threshold = i;
    }

    public int getTimeOutThreshold()
    {
        return timeout_threshold;
    }
    
    public void setMagVoltThreshold(int i)
    {
        voltage_threshold = i;
        samplermagcurve.setVoltageThreshold(voltage_threshold);
    }

    public int getMagVoltThreshold()
    {
        return voltage_threshold;
    }
    
    public void setMagCurrentThreshold(int i)
    {
        current_threshold = i;
        samplermagcurve.setCurrentThreshold(current_threshold);
    }
    
    public int getMagCurrentThreshold()
    {
        return current_threshold;
    }
    
    public void setVoltRatioThreshold(int i)
    {
        voltage_ratio_threshold = i;
    }
    
    public int getVoltRatioThreshold()
    {
        return voltage_ratio_threshold;
    }
    
    public void setCurrentRatioThreshold(int i)
    {
       current_ratio_threshold = i;
    }

    public int getCurrentRatioThreshold()
    {
        return current_ratio_threshold;
    }
    
}