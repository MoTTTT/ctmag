import java.lang.*;
import java.text.DecimalFormat;

class VoltageRatioResultsHandler extends java.lang.Object 
{
    private Ctmag ctmag;
    private MagCurveTest magcurvetest;
    private String pol_mess[]; 
    private int trueprimaryvtwindings;    
    private int truesecondaryvtwindings;        
    private int measuredprimaryvtwindings;
    private int measuredsecondaryvtwindings;
    private int convertedprimaryvtwindings;
    private int displayprimaryvtwindings;
    private float convertedsecondaryvtwindings;
    private float displaysecondaryvtwindings;
    private float trueratio[];
    private float measuredratio[];
    private float percentage_error[];    
    private final int phases = 3;
    private final int HUNDRED_PERCENT = 100;
    private DecimalFormat precision2;
    private DecimalFormat precision4;
           
    public VoltageRatioResultsHandler(Ctmag f)
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
    
    public void showTestedVoltagePolarity()
    {
       
        if(ctmag.magcurvetest.holding_voltpolarity == 0)        
            ctmag.ratiodialog.holdingpolaritytextfield.setText(
            "No");
        else
            ctmag.ratiodialog.holdingpolaritytextfield.setText(
                "Yes");
    }
    
    public void showTestedVoltageRatio()
    {
        int volt_prim = ctmag.testcontrolengine.channel_max_val[ctmag.testcontrolengine.VP];
        int volt_secon = ctmag.testcontrolengine.channel_max_val[ctmag.testcontrolengine.VS];
        int bitmax = ctmag.testcontrolengine.FULLSCALE;
        
        measuredsecondaryvtwindings = magcurvetest.holdingvoltageratio.
            getX()*volt_secon/bitmax;            
        measuredprimaryvtwindings = magcurvetest.holdingvoltageratio.
            getY()*volt_prim/bitmax;            
        convertMeasuredVTRatios();
        ctmag.ratiodialog.holdingratiotextfield.setText(""+displayprimaryvtwindings+" : "+precision2.format(displaysecondaryvtwindings));        
    }
    
    public void convertMeasuredVTRatios()
    {
        try{
            convertedprimaryvtwindings = measuredprimaryvtwindings/measuredprimaryvtwindings;
            displayprimaryvtwindings = convertedprimaryvtwindings * ctmag.magcurvetest.getTruePrimVTRatio();//!!!
        }
        catch (ArithmeticException a){
            convertedprimaryvtwindings = 0;
        }
        
        try{
            convertedsecondaryvtwindings = (float)measuredsecondaryvtwindings/measuredprimaryvtwindings;
            displaysecondaryvtwindings = convertedsecondaryvtwindings * ctmag.magcurvetest.getTruePrimVTRatio();//!!!
        }
        catch (ArithmeticException a){
            convertedsecondaryvtwindings = 0;
        }

        
    }
    
    public void showTruePhaseRatio(int phase)
    {
        switch(phase)
        {
            case magcurvetest.RED:
                    ctmag.voltageratioresults.redvolttrueratiotextfield.
                        setText(""+trueprimaryvtwindings+":"+truesecondaryvtwindings);
                    break;
       
            case magcurvetest.WHITE:
                    ctmag.voltageratioresults.whitevolttrueratiotextfield.
                        setText(""+trueprimaryvtwindings+":"+truesecondaryvtwindings);            
                    break;
                    
            case magcurvetest.BLUE:
                    ctmag.voltageratioresults.bluevolttrueratiotextfield.
                        setText(""+trueprimaryvtwindings+":"+truesecondaryvtwindings);            
                    break;
                    
            default:
                    break;                    
        }
    }

    public void showPhasePolarity(int phase)
    {
        switch(phase)
        {
            case magcurvetest.RED:
                ctmag.voltageratioresults.redvoltpolratiotextfield.setText(
                    pol_mess[magcurvetest.phase_voltpolarity
                    [magcurvetest.RED]]);          
                ctmag.reportvoltageratioresults.redvoltpolratiotextfield.setText(
                    pol_mess[magcurvetest.phase_voltpolarity
                    [magcurvetest.RED]]);                              
                break;
       
            case magcurvetest.WHITE:
                ctmag.voltageratioresults.whitevoltpolratiotextfield.setText(
                    pol_mess[magcurvetest.phase_voltpolarity
                    [magcurvetest.WHITE]]);
                ctmag.reportvoltageratioresults.whitevoltpolratiotextfield.setText(
                    pol_mess[magcurvetest.phase_voltpolarity
                    [magcurvetest.WHITE]]);                    
                break;
                    
            case magcurvetest.BLUE:
                ctmag.voltageratioresults.bluevoltpolratiotextfield.setText(
                    pol_mess[magcurvetest.phase_voltpolarity
                    [magcurvetest.BLUE]]);
                ctmag.reportvoltageratioresults.bluevoltpolratiotextfield.setText(
                    pol_mess[magcurvetest.phase_voltpolarity
                    [magcurvetest.BLUE]]);
                break;
                    
            default:
                break;            
        }        
    }
    
    private void calculateRatiosError(int phase)
    {
        trueratio[phase] = trueprimaryvtwindings/truesecondaryvtwindings;
        measuredratio[phase] = measuredprimaryvtwindings/measuredsecondaryvtwindings;
    }
        
    private void getCalculatedRatios(int phase)
    {
        int volt_prim = ctmag.testcontrolengine.channel_max_val[ctmag.testcontrolengine.VP];
        int volt_secon = ctmag.testcontrolengine.channel_max_val[ctmag.testcontrolengine.VS];
        int bitmax = ctmag.testcontrolengine.FULLSCALE;
        
        measuredsecondaryvtwindings = magcurvetest.phasevoltageratio[phase].
            getX()*volt_secon/bitmax;            
        measuredprimaryvtwindings = magcurvetest.phasevoltageratio[phase].
            getY()*volt_prim/bitmax; 
            
        convertMeasuredVTRatios();        
        measuredratio[phase] = (float)measuredprimaryvtwindings/measuredsecondaryvtwindings;
        getEnteredRatios();
        trueratio[phase]= (float)trueprimaryvtwindings/truesecondaryvtwindings;
        percentage_error[phase] = ((measuredratio[phase]/ trueratio[phase])-1)* HUNDRED_PERCENT;
    }

    public void getEnteredRatios()
    {
        try
        {
            trueprimaryvtwindings = magcurvetest.getTruePrimVTRatio();
        }
        catch(NullPointerException npe)
        {
            ctmag.statustextfield.setText("Integer values must be entered for the primary to secondary VT ratio before starting the test.");
        }

        try
        {
            truesecondaryvtwindings = magcurvetest.getTrueSeconVTRatio();
        }
        catch(NullPointerException npe)
        {
            ctmag.statustextfield.setText("Integer values must be entered for the primary to secondary VT ratio before starting the test.");            
        }        
    }    
    
    public void showMeasError(int phase)
    {
        switch(phase)
        {
            case magcurvetest.RED:
                    ctmag.voltageratioresults.redvolterrratiotextfield.
                        setText(""+precision2.format(percentage_error[phase])+"%");
                    ctmag.reportvoltageratioresults.redvolterrratiotextfield.
                        setText(""+precision2.format(percentage_error[phase])+"%");                    
                    break;
       
            case magcurvetest.WHITE:
                    ctmag.voltageratioresults.whitevolterrratiotextfield.
                        setText(""+precision2.format(percentage_error[phase])+"%");            
                    ctmag.reportvoltageratioresults.whitevolterrratiotextfield.
                        setText(""+precision2.format(percentage_error[phase])+"%");                                
                    break;
                    
            case magcurvetest.BLUE:
                    ctmag.voltageratioresults.bluevolterrratiotextfield.
                        setText(""+precision2.format(percentage_error[phase])+"%");            
                    ctmag.reportvoltageratioresults.bluevolterrratiotextfield.
                        setText(""+precision2.format(percentage_error[phase])+"%");                                
                    break;
                    
            default:
                    break;                                
        }
    }
    
    public void showPhaseRatio(int phase)
    {
        getCalculatedRatios(phase);

        switch(phase)
        {
            case magcurvetest.RED:
                    ctmag.voltageratioresults.redvoltresultratiotextfield.setText(
                        ""+displayprimaryvtwindings+":"+precision2.format(displaysecondaryvtwindings));
                    ctmag.reportvoltageratioresults.redvoltresultratiotextfield.setText(
                        ""+displayprimaryvtwindings+":"+precision2.format(displaysecondaryvtwindings));                    
                    break;
       
            case magcurvetest.WHITE:
                    ctmag.voltageratioresults.whitevoltresultratiotextfield.setText(
                        ""+displayprimaryvtwindings+":"+precision2.format(displaysecondaryvtwindings));
                    ctmag.reportvoltageratioresults.whitevoltresultratiotextfield.setText(
                        ""+displayprimaryvtwindings+":"+precision2.format(displaysecondaryvtwindings));                    
                    break;
                    
            case magcurvetest.BLUE:  
                    ctmag.voltageratioresults.bluevoltresultratiotextfield.setText(
                        ""+displayprimaryvtwindings+":"+precision2.format(displaysecondaryvtwindings));
                    ctmag.reportvoltageratioresults.bluevoltresultratiotextfield.setText(
                        ""+displayprimaryvtwindings+":"+precision2.format(displaysecondaryvtwindings));          
                    break;
                    
            default:
                    break;
        }
    } 

    public boolean showTestedPhaseRatios(int phase)
    {
        getCalculatedRatios(phase);
        
        if ((measuredprimaryvtwindings == 0) && (measuredsecondaryvtwindings == 0))
        {
            return false;
        }            
        else
        {
            switch(phase)
            {
                case magcurvetest.RED:
                    ctmag.voltageratioresults.redvoltresultratiotextfield.setText(
                        ""+displayprimaryvtwindings+":"+precision2.format(displaysecondaryvtwindings));
                    ctmag.reportvoltageratioresults.redvoltresultratiotextfield.setText(
                        ""+displayprimaryvtwindings+":"+precision2.format(displaysecondaryvtwindings));          
                    break;
       
                case magcurvetest.WHITE:
                    ctmag.voltageratioresults.whitevoltresultratiotextfield.setText(
                        ""+displayprimaryvtwindings+":"+precision2.format(displaysecondaryvtwindings));
                    ctmag.reportvoltageratioresults.whitevoltresultratiotextfield.setText(
                        ""+displayprimaryvtwindings+":"+precision2.format(displaysecondaryvtwindings));          
                    break;
                    
                case magcurvetest.BLUE:
                    ctmag.voltageratioresults.bluevoltresultratiotextfield.setText(
                        ""+displayprimaryvtwindings+":"+precision2.format(displaysecondaryvtwindings));
                    ctmag.reportvoltageratioresults.bluevoltresultratiotextfield.setText(
                        ""+displayprimaryvtwindings+":"+precision2.format(displaysecondaryvtwindings));          
                    break;
                    
                default:
                        break;
            }
        }
            return true;
    }        
}