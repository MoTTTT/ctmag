//**************************************************************
// Handles the convertion and display of the CT Ratio, gets called
// from the TestControlEngine and ImportTest.
//**************************************************************

import java.lang.*;
import java.text.DecimalFormat;
class CurrentRatioResultsHandler extends java.lang.Object  
{
    private Ctmag ctmag;
    private MagCurveTest magcurvetest;
    private String pol_mess[]; 
    
    private int measuredprimaryctwindings;
    private int measuredsecondaryctwindings;
    private int trueprimaryctwindings;    
    private int truesecondaryctwindings; 
    private int convertedprimaryctwindings;
    private int displayprimaryctwindings;    
    private float convertedsecondaryctwindings;
    private float displaysecondaryctwindings;    
    
    private float trueratio[];
    private float measuredratio[];
    private float percentage_error[];    
    private final int phases = 3;
    private final int HUNDRED_PERCENT = 100;
    private DecimalFormat precision2;
    private DecimalFormat precision4;    
    
    public CurrentRatioResultsHandler(Ctmag f)
    {
        ctmag = f;
        pol_mess = new String[2];
        pol_mess[0] = new String("NO");
        pol_mess[1] = new String("OK");
        trueratio = new float[phases];
        measuredratio = new float[phases];
        percentage_error = new float[phases];
        precision2 = new DecimalFormat("#0.00");
        precision4 = new DecimalFormat("#0.0000");        
    }
    
    public void setObjects(MagCurveTest m)
    {
       magcurvetest = m; 
    } 
    
//****************************************************************
// Method to display the measured ratios in the same format as was
// entered as the expected ratio i.e. display the measured ratio 
// in terms of the primary entered ratio.
//****************************************************************

   private void convertMeasuredCTRatios()
   {
        try{
            convertedprimaryctwindings = measuredprimaryctwindings/measuredprimaryctwindings;
            displayprimaryctwindings = convertedprimaryctwindings * ctmag.magcurvetest.getTruePrimCTRatio();//!!!
        }
        catch (ArithmeticException a){
            convertedprimaryctwindings = 0;
        }
        
        try{
            convertedsecondaryctwindings = (float)measuredsecondaryctwindings/measuredprimaryctwindings;
            displaysecondaryctwindings = convertedsecondaryctwindings * ctmag.magcurvetest.getTruePrimCTRatio();//!!!            
        }
        catch (ArithmeticException a){
            convertedsecondaryctwindings = 0;
        }    
   }

//**************************************************************
// Get the entered primary and secondary ratios.
//**************************************************************

    public void getEnteredRatios()
    {
        try
        {
            trueprimaryctwindings = magcurvetest.getTruePrimCTRatio();
        }
        catch(NullPointerException npe)
        {
            ctmag.statustextfield.setText("Integer values must be entered for the primary to secondary CT ratio before starting the test.");
        }

        try
        {
            truesecondaryctwindings = magcurvetest.getTrueSeconCTRatio();
        }
        catch(NullPointerException npe)
        {
            ctmag.statustextfield.setText("Integer values must be entered for the primary to secondary CT ratio before starting the test.");            
        }        
    }

//**************************************************************
// Display the measured polarity for the phase under test.
//**************************************************************

    public void showTestedCurrentPolarity()
    {
       
        if(ctmag.magcurvetest.holding_amppolarity == 0)        
            ctmag.ratiodialog.holdingpolaritytextfield.setText(
                "No");
        else
            ctmag.ratiodialog.holdingpolaritytextfield.setText(
                "Yes");
    }

//**************************************************************
// Display the tested ratio for the phase under test.
//**************************************************************
    
    public void showTestedCurrentRatio()
    {  
        int amp_prim = ctmag.testcontrolengine.channel_max_val[ctmag.testcontrolengine.CP];
        int amp_secon = ctmag.testcontrolengine.channel_max_val[ctmag.testcontrolengine.CS];
        int bitmax = ctmag.testcontrolengine.FULLSCALE;
        
         measuredsecondaryctwindings = magcurvetest.holdingcurrentratio.
            getX()*amp_secon/bitmax;   
        measuredprimaryctwindings = magcurvetest.holdingcurrentratio.
            getY()*amp_prim/bitmax;                
        convertMeasuredCTRatios();            
        ctmag.ratiodialog.holdingratiotextfield.setText(""+displayprimaryctwindings+" : "+precision2.format(displaysecondaryctwindings));                    
   }
    
//**************************************************************
// Show the entered ratio for the specified phase.
//**************************************************************

    public void showTruePhaseRatio(int phase)
    {
        switch(phase)
        {
            case magcurvetest.RED:
                    ctmag.currentratioresults.redamptrueratiotextfield.
                        setText(""+trueprimaryctwindings+":"+truesecondaryctwindings);
                    break;
       
            case magcurvetest.WHITE:
                    ctmag.currentratioresults.whiteamptrueratiotextfield.
                        setText(""+trueprimaryctwindings+":"+truesecondaryctwindings);            
                    break;
                    
            case magcurvetest.BLUE:
                    ctmag.currentratioresults.blueamptrueratiotextfield.
                        setText(""+trueprimaryctwindings+":"+truesecondaryctwindings);            
                    break;
                    
            default:
                    break;                    
        }
    }

//**************************************************************
// Show the percentage error for the measured ratio of a specified
// phase.
//**************************************************************
                        
    public void showMeasError(int phase)
    {
        switch(phase)
        {
            case magcurvetest.RED:
                    ctmag.currentratioresults.redamperrratiotextfield.
                        setText(""+precision2.format(percentage_error[phase])+"%");
                    ctmag.reportcurrentratioresults.redamperrratiotextfield.
                        setText(""+precision2.format(percentage_error[phase])+"%");                    
                    break;       
            case magcurvetest.WHITE:
                    ctmag.currentratioresults.whiteamperrratiotextfield.
                        setText(""+precision2.format(percentage_error[phase])+"%");            
                    ctmag.reportcurrentratioresults.whiteamperrratiotextfield.
                        setText(""+precision2.format(percentage_error[phase])+"%");                                
                    break;                    
            case magcurvetest.BLUE:
                    ctmag.currentratioresults.blueamperrratiotextfield.
                        setText(""+precision2.format(percentage_error[phase])+"%");            
                    ctmag.reportcurrentratioresults.blueamperrratiotextfield.
                        setText(""+precision2.format(percentage_error[phase])+"%");                                
                    break;
                    
            default:
                    break;                                
        }
    }
    
//**************************************************************
// Show the measured polarity for the selected phase.
//**************************************************************    
    
    public void showPhasePolarity(int phase)
    {
        getEnteredRatios();        
        
        switch(phase)
        {
            case magcurvetest.RED:
                ctmag.currentratioresults.redamppolratiotextfield.setText(
                    pol_mess[magcurvetest.phase_amppolarity
                    [magcurvetest.RED]]);   
                ctmag.reportcurrentratioresults.redamppolratiotextfield.setText(
                    pol_mess[magcurvetest.phase_amppolarity
                    [magcurvetest.RED]]);
                break;       
            case magcurvetest.WHITE:
                ctmag.currentratioresults.whiteamppolratiotextfield.setText(
                    pol_mess[magcurvetest.phase_amppolarity
                    [magcurvetest.WHITE]]);
                ctmag.reportcurrentratioresults.whiteamppolratiotextfield.setText(
                    pol_mess[magcurvetest.phase_amppolarity
                    [magcurvetest.WHITE]]);                    
                break;                    
            case magcurvetest.BLUE:
                ctmag.currentratioresults.blueamppolratiotextfield.setText(
                    pol_mess[magcurvetest.phase_amppolarity
                    [magcurvetest.BLUE]]);
                ctmag.reportcurrentratioresults.blueamppolratiotextfield.setText(
                    pol_mess[magcurvetest.phase_amppolarity
                    [magcurvetest.BLUE]]);
                break;
                    
            default:
                    break;            
        }        
    }

//**************************************************************
// Gets called from TestControlEngine when a CT Ratio test gets
// assigned to a phase.
//**************************************************************    
    
    public void showPhaseRatio(int phase)
    {
        getCalculatedRatios(phase);
        
        switch(phase)
        {
            case magcurvetest.RED:
                ctmag.currentratioresults.redampresultratiotextfield.setText(
                    ""+displayprimaryctwindings+":"+precision2.format(displaysecondaryctwindings));
                ctmag.reportcurrentratioresults.redampresultratiotextfield.setText(
                    ""+displayprimaryctwindings+":"+precision2.format(displaysecondaryctwindings));                    
                break;       
            case magcurvetest.WHITE:
                ctmag.currentratioresults.whiteampresultratiotextfield.setText(
                    ""+displayprimaryctwindings+":"+precision2.format(displaysecondaryctwindings));          
                ctmag.reportcurrentratioresults.whiteampresultratiotextfield.setText(
                    ""+displayprimaryctwindings+":"+precision2.format(displaysecondaryctwindings));          
                break;                    
            case magcurvetest.BLUE:
                ctmag.currentratioresults.blueampresultratiotextfield.setText(
                    ""+displayprimaryctwindings+":"+precision2.format(displaysecondaryctwindings));          
                ctmag.reportcurrentratioresults.blueampresultratiotextfield.setText(
                    ""+displayprimaryctwindings+":"+precision2.format(displaysecondaryctwindings));          
                break;
                    
            default:
                    break;
        }
    }    

//**************************************************************
// Display a converted measured ratio for a selected phase.
//**************************************************************        
    
    private void getCalculatedRatios(int phase)
    {
        int bitmax = ctmag.testcontrolengine.FULLSCALE;
        int cs_setting = ctmag.testcontrolengine.CS_SETTING;        
        int amp_prim = ctmag.testcontrolengine.channel_max_val[ctmag.testcontrolengine.CP];
        int amp_secon = ctmag.testcontrolengine.channel_max_val[ctmag.testcontrolengine.CS];

        measuredsecondaryctwindings = magcurvetest.phasecurrentratio[phase].
            getX()*amp_secon/bitmax;                
        measuredprimaryctwindings = magcurvetest.phasecurrentratio[phase].
            getY()*amp_prim/bitmax;    
        convertMeasuredCTRatios();                                    
        measuredratio[phase] = (float)measuredprimaryctwindings/measuredsecondaryctwindings;
        getEnteredRatios();
        trueratio[phase]= (float)trueprimaryctwindings/truesecondaryctwindings;
        percentage_error[phase] = ((measuredratio[phase]/ trueratio[phase])-1)* HUNDRED_PERCENT;
    }

//**************************************************************
// Gets called from ImportTest. Import the measured ratio for the 
// specified phase from a test and display the converted result.
//**************************************************************    
        
    public boolean showTestedPhaseRatios(int phase)
    {
        importCalculatedRatios(phase);
        
        if ((measuredprimaryctwindings == 0) && (measuredsecondaryctwindings == 0))
            return false;
        else
        {
            switch(phase)
            {
                case magcurvetest.RED:
                    ctmag.currentratioresults.redampresultratiotextfield.setText(
                        ""+displayprimaryctwindings+":"+precision2.format(displaysecondaryctwindings));
                    ctmag.reportcurrentratioresults.redampresultratiotextfield.setText(
                        ""+displayprimaryctwindings+":"+precision2.format(displaysecondaryctwindings));                    
                    break;       
                case magcurvetest.WHITE:
                    ctmag.currentratioresults.whiteampresultratiotextfield.setText(
                        ""+displayprimaryctwindings+":"+precision2.format(displaysecondaryctwindings));          
                    ctmag.reportcurrentratioresults.whiteampresultratiotextfield.setText(
                        ""+displayprimaryctwindings+":"+precision2.format(displaysecondaryctwindings));          
                    break;                    
                case magcurvetest.BLUE:
                    ctmag.currentratioresults.blueampresultratiotextfield.setText(
                        ""+displayprimaryctwindings+":"+precision2.format(displaysecondaryctwindings));          
                    ctmag.reportcurrentratioresults.blueampresultratiotextfield.setText(
                        ""+displayprimaryctwindings+":"+precision2.format(displaysecondaryctwindings));          
                    break;
                    
                default:
                        break;
            }
        }
            return true;
    } 

//**************************************************************
// Import a measured ratio for a specified phase. Read in the CS
// setting for the tested phase.
//**************************************************************

    private void importCalculatedRatios(int phase)
    {
        int byte_max = ctmag.magcurvetest.vimagcurve[phase].BYTE_MAX;
        int cs_setting = ctmag.testcontrolengine.CS_SETTING;        
        int amp_prim = ctmag.testcontrolengine.channel_max_val[ctmag.testcontrolengine.CP];
        int bitmax = ctmag.testcontrolengine.FULLSCALE;
        int amp_secon = 0;
        try{
            amp_secon = ctmag.magcurvetest.vimagcurve[phase].data[byte_max + cs_setting];
        }
        catch (ArrayIndexOutOfBoundsException a){
            amp_secon = ctmag.testcontrolengine.channel_max_val[ctmag.testcontrolengine.CS_VAL];
        }
        measuredsecondaryctwindings = magcurvetest.phasecurrentratio[phase].
            getX()*amp_secon/bitmax;                
        measuredprimaryctwindings = magcurvetest.phasecurrentratio[phase].
            getY()*amp_prim/bitmax;    
        convertMeasuredCTRatios();                                    
        measuredratio[phase] = (float)measuredprimaryctwindings/measuredsecondaryctwindings;
        getEnteredRatios();
        trueratio[phase]= (float)trueprimaryctwindings/truesecondaryctwindings;
        percentage_error[phase] = ((measuredratio[phase]/ trueratio[phase])-1)* HUNDRED_PERCENT;
    }        
}