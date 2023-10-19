//****************************************************************
// Object that contains all the test information relating to the 
// magnetisation curve, voltage and current ratio tests.
//****************************************************************

import java.lang.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class MagCurveTest implements java.io.Serializable  
{
    protected Date date;
    protected MagCurve maincurve;
    protected MagCurve vimagcurve[];
    
    public Ratio holdingcurrentratio;
    public Ratio holdingvoltageratio;
    public Ratio phasevoltageratio[];
    public Ratio phasecurrentratio[];
    
    public boolean assigned_phases[];
    public boolean kneepoint_reached;
    public boolean main_kneepoint_reached;
    
    public int holding_amppolarity;    
    public int holding_voltpolarity;
    public int phase_voltpolarity[];
    public int phase_amppolarity[];
    public int max_last_current;
    public int max_last_voltage;

    private String testname;
    private String testername;
    private String supplyauthority;
    private String substation;
    private String feeder;

    private String ctmake;
    private String cttype;
    private String rednumber;
    private String whitenumber;
    private String bluenumber;
    private String ctapplication;
    private String ctclassification;
    private String ctvarating;

    private String vtmake;
    private String vttype;
    private String vtclassification;
    private String vtphase;
    private String vtdescription;
    private String vtserialno;
    private String vtil;
    private String vtvarating;

    protected int trueprimctratio;
    protected int trueseconctratio;
    protected int trueprimvtratio;
    protected int trueseconvtratio;
    
    private int timeout_threshold;
    private int voltage_threshold;
    private int current_threshold;
    private int voltage_ratio_threshold;
    private int current_ratio_threshold;        
    
    public final int RED = 0;
    public final int WHITE = 1;
    public final int BLUE = 2;

    public MagCurveTest()
    {
        maincurve= new MagCurve();
        vimagcurve = new MagCurve[3];

        vimagcurve[0] = new MagCurve(); 
        vimagcurve[1] = new MagCurve();
        vimagcurve[2] = new MagCurve(); 
        
        holdingcurrentratio = new Ratio();
        holdingvoltageratio = new Ratio();
        
        phasevoltageratio = new Ratio[3];
        phasevoltageratio[0] = new Ratio();
        phasevoltageratio[1] = new Ratio();
        phasevoltageratio[2] = new Ratio();
        
        phasecurrentratio = new Ratio[3];
        phasecurrentratio[0] = new Ratio();
        phasecurrentratio[1] = new Ratio();
        phasecurrentratio[2] = new Ratio();
        
        phase_voltpolarity = new int[3];
        phase_amppolarity = new int[3];
        
        assigned_phases = new boolean [3];
        assigned_phases[RED] = false;
        assigned_phases[WHITE] = false;
        assigned_phases[BLUE] = false;
        
        kneepoint_reached = false;
        main_kneepoint_reached = false;
        
        setDate();
    }

    public void setVoltageThreshold(int i)
    {
        voltage_threshold = i;
    }

    public int getVoltageThreshold()
    {
        return voltage_threshold;
    }
 
    public void setCurrentThreshold(int i)
    {
        current_threshold = i;
    }

    public int getCurrentThreshold()
    {
        return current_threshold;
    }
    
    public void setVoltageRatioThreshold(int i)
    {
        voltage_ratio_threshold = i;
    }

    public int getVoltageRatioThreshold()
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
    
    public void setHoldingCurrentRatio(Ratio in)
    {
        holdingcurrentratio.setRatio(in.getRatio());  
    }

    public void setHoldingVoltageRatio(Ratio in)
    {
        holdingvoltageratio.setRatio(in.getRatio());          
    }    
    
    public void setDate()
    {
        date = new Date();
    }
    
    public void setMainCurve(MagCurve in)
    {
        maincurve = in;
        
        if(maincurve.knee.getX() > 0)
            main_kneepoint_reached = true;
        else if(maincurve.knee.getY() > 0)                
            main_kneepoint_reached = true;
        else
            main_kneepoint_reached = false;
    }
  
    public boolean assignCurve(int phase)
    {
        if  ( (phase > BLUE)||(phase< RED))
            return false;            
        vimagcurve[phase] = maincurve;
        setMaxMeasPhaseValues();             
        assigned_phases[phase] = true;
        return true;
    }
    
    public boolean assignCurrentRatio(int phase)
    {
        if  ( (phase > BLUE)||(phase< RED))
            return false;
        phasecurrentratio[phase].setRatio(
                holdingcurrentratio.getRatio());  
        phase_amppolarity[phase]= holding_amppolarity;
        return true ;
        
    }

    public boolean assignVoltageRatio(int phase)
    {
        if  ( (phase > BLUE)||(phase< RED))
            return  false;
        phasevoltageratio[phase].setRatio(
                holdingvoltageratio.getRatio());                      
        phase_voltpolarity[phase]= holding_voltpolarity;
        return true;        
    }

//****************************************************************
// Determine whether kneepoints have been reached and set the max
// current and voltage readings measured for the magcurve tests.
// The kneepoints reached determine whether the graphs would be 
// drawn, while the maximum voltage and current settintgs determine
// the scaling of the 3 phase graph.
//****************************************************************
        
    private void setMaxMeasPhaseValues()
    {
        int temp_last_current = 0, temp_last_voltage = 0;
        
        max_last_current = 0;
        max_last_voltage = 0;
        
        for (int q = RED; q <= BLUE; q++)
        {
            if(vimagcurve[q].knee.getX() > 0)
            {
                temp_last_current =  vimagcurve[q].getLastCurrent();
                kneepoint_reached = true;
            }
            
            if(vimagcurve[q].knee.getY() > 0)                
            {
                temp_last_voltage =  vimagcurve[q].getLastVoltage();
                kneepoint_reached = true;
            }    

            if(temp_last_current > max_last_current)
                max_last_current = temp_last_current; 

            if(temp_last_voltage > max_last_voltage)
                max_last_voltage = temp_last_voltage;
        }
    }
    
//****************************************************************
// Methods to get General Settings values.
//****************************************************************
            
    public String getTestname()
    {
        return testname;
    }

    public String getTestername()
    {
        return testername;
    }

    public String getSupplyauthority()
    {
        return supplyauthority;
    }

    public String getSubstation()
    {
        return substation;
    }

    public String getFeeder()
    {
        return feeder;
    }
    
//****************************************************************
// Methods to get CT Settings values.
//****************************************************************

    public String getCTMake()
    {
        return ctmake;
    }

    public String getCTType()
    {
        return cttype;
    }

    public String getCTClassification()
    {
        return ctclassification;
    }

    public String getCTApplication()
    {
        return ctapplication;
    }

    public String getRednumber()
    {
        return rednumber;
    }

    public String getWhitenumber()
    {
        return whitenumber;
    }    

    public String getBluenumber()
    {
        return bluenumber;
    }

    public int getTruePrimCTRatio()
    {
        return trueprimctratio; 
    }
    
    public int getTrueSeconCTRatio()
    {
        return trueseconctratio;
    }
    
    public String getCTVarating()
    {
        return ctvarating;
    }
  
//****************************************************************
// Methods to get VT Settings values.
//****************************************************************

    public String getVTMake()
    {
        return vtmake;
    }    

    public String getVTType()
    {
        return vttype;
    }

    public String getVTClassification()
    {
        return vtclassification;
    }        

    public String getVTPhase()
    {
        return vtphase;
    }        

    public String getVTDescription()
    {
        return vtdescription;
    }        

    public String getVTSerialno()
    {
        return vtserialno;
    }        

    public String getVTIl()
    {
        return vtil;
    }        

    public int getTruePrimVTRatio()
    {
        return trueprimvtratio;
    }
    
    public int getTrueSeconVTRatio()
    {
        return trueseconvtratio;
    }
    
    public String getVTVarating()
    {
        return vtvarating;
    }        

//****************************************************************
// Methods to set General Settings.
//****************************************************************

    public void setTestname(String s)
    {
        testname = s;
    }
   
    public void setTestername(String s)
    {
        testername = s;
    }
   
    public void setSupplyauthority(String s)
    {
        supplyauthority = s;
    }
   
    public void setSubstation(String s)
    {
        substation = s;
    }
   
    public void setFeeder(String s)
    {
        feeder = s;
    }
   
//****************************************************************
// Methods to set CT Settings.
//****************************************************************

    public void setCTMake(String s)
    {
        ctmake = s;
    }
   
    public void setCTType(String s)
    {
        cttype = s;
    }
   
    public void setCTClassification(String s)
    {
        ctclassification = s;
    }
      
    public void setCTApplication(String s)
    {
        ctapplication = s;
    }

    public void setRednumber(String s)
    {
        rednumber = s;
    }

    public void setWhitenumber(String s)
    {
        whitenumber = s;
    }
   
    public void setBluenumber(String s)
    {
        bluenumber = s;
    }
   
    public void setTruePrimCTRatio(int i)
    {
        trueprimctratio = i;
    }
    
    public void setTrueSeconCTRatio(int i)
    {
        trueseconctratio = i;
    }

    public void setCTVarating(String s)
    {
        ctvarating = s;
    }

//****************************************************************
// Methods to set VT Settings.
//****************************************************************
  
    public void setVTMake(String s)
    {
        vtmake = s;
    }    

    public void setVTType(String s)
    {
        vttype = s;
    }

    public void setVTClassification(String s)
    {
        vtclassification = s;
    }        

    public void setVTPhase(String s)
    {
        vtphase = s;
    }        

    public void setVTDescription(String s)
    {
        vtdescription = s;
    }        

    public void setVTSerialno(String s)
    {
        vtserialno = s;
    }        

    public void setVTIl(String s)
    {
        vtil = s;
    }        

    public void setTruePrimVTRatio(int i)
    {
        trueprimvtratio = i;
    }
    
    public void setTrueSeconVTRatio(int i)
    {
        trueseconvtratio = i;
    }

    public void setVTVarating(String s)
    {
        vtvarating = s;
    }        
}