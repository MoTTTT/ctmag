import java.lang.*;
class MagCurveResultsHandler extends java.lang.Object 
{
    private Ctmag ctmag;
    private MagCurveTest magcurvetest;
    
    protected int mainkneevolt;
    protected int mainkneeamp;
    protected int redkneevolt;
    protected int redkneeamp;
    protected int whitekneevolt;
    protected int bluekneevolt;
    protected int whitekneeamp;
    protected int bluekneeamp;
    
    private Datum d;
    
    public MagCurveResultsHandler(Ctmag f)
    {
        ctmag = f;
    }
    
    public void setObjects(MagCurveTest m)
    {
       magcurvetest = m; 
    }  

    public void setMainKneeVolt(int volt_factor, int conversion_factor)
    {
        mainkneevolt = magcurvetest.maincurve.getKneePoint().getY();
        ctmag.magcurveresults.mainkneevolttextfield.setText("" + mainkneevolt * volt_factor / conversion_factor + "V");
    }

    public void setMainKneeAmp(int amp_factor, int conversion_factor)
    {
        mainkneeamp = magcurvetest.maincurve.getKneePoint().getX();
        ctmag.magcurveresults.mainkneeamptextfield.setText("" + mainkneeamp * amp_factor / conversion_factor + "mA");
    }   
    
    public void showMagCurvePhase(int phase,int volt_factor, int amp_factor, int conversion_factor)
    {
        switch (phase)
        {
            case magcurvetest.RED:
                    setRedKneeVolt(volt_factor, conversion_factor);
                    setRedKneeAmp(amp_factor, conversion_factor);                                            
                    break;
       
            case magcurvetest.WHITE:
                    setWhiteKneeVolt(volt_factor, conversion_factor);
                    setWhiteKneeAmp(amp_factor, conversion_factor);                                            
                    break;
                    
            case magcurvetest.BLUE:
                    setBlueKneeVolt(volt_factor, conversion_factor);
                    setBlueKneeAmp(amp_factor, conversion_factor);                                    
                    break;
                    
            default:
                    break;                        
        }
    }
    
    public void setRedKneeVolt(int volt_factor, int conversion_factor)
    {
        redkneevolt = magcurvetest.vimagcurve[magcurvetest.RED].getKneePoint().getY();
        ctmag.magcurveresults.redkneevolttextfield.setText("" + redkneevolt * volt_factor/conversion_factor + "V");
        ctmag.reportmagcurveresults.redkneevolttextfield.setText("" + redkneevolt * volt_factor/conversion_factor + "V");        
    }

    public void setWhiteKneeVolt(int volt_factor, int conversion_factor)
    {
        whitekneevolt = magcurvetest.vimagcurve[magcurvetest.WHITE].getKneePoint().getY();
        ctmag.magcurveresults.whitekneevolttextfield.setText("" + whitekneevolt * volt_factor/conversion_factor + "V");
        ctmag.reportmagcurveresults.whitekneevolttextfield.setText("" + whitekneevolt * volt_factor/conversion_factor + "V");
    }

    public void setBlueKneeVolt(int volt_factor, int conversion_factor)
    {
        bluekneevolt = magcurvetest.vimagcurve[magcurvetest.BLUE].getKneePoint().getY();
        ctmag.magcurveresults.bluekneevolttextfield.setText("" + bluekneevolt * volt_factor/conversion_factor + "V");
        ctmag.reportmagcurveresults.bluekneevolttextfield.setText("" + bluekneevolt * volt_factor/conversion_factor + "V");
    }
    
    public void setRedKneeAmp(int amp_factor, int conversion_factor)
    {
        redkneeamp = magcurvetest.vimagcurve[magcurvetest.RED].getKneePoint().getX();
        ctmag.magcurveresults.redkneeamptextfield.setText("" + redkneeamp * amp_factor / conversion_factor + "mA");
        ctmag.reportmagcurveresults.redkneeamptextfield.setText("" + redkneeamp * amp_factor / conversion_factor + "mA");
    }    

    public void setWhiteKneeAmp(int amp_factor, int conversion_factor)
    {
        whitekneeamp = magcurvetest.vimagcurve[magcurvetest.WHITE].getKneePoint().getX();
        ctmag.magcurveresults.whitekneeamptextfield.setText("" + whitekneeamp * amp_factor / conversion_factor + "mA");
        ctmag.reportmagcurveresults.whitekneeamptextfield.setText("" + whitekneeamp * amp_factor / conversion_factor + "mA");
    }    

    public void setBlueKneeAmp(int amp_factor, int conversion_factor)
   {    
        bluekneeamp = magcurvetest.vimagcurve[magcurvetest.BLUE].getKneePoint().getX();
        ctmag.magcurveresults.bluekneeamptextfield.setText("" + bluekneeamp * amp_factor / conversion_factor + "mA");
        ctmag.reportmagcurveresults.bluekneeamptextfield.setText("" + bluekneeamp * amp_factor / conversion_factor + "mA");
    }        
}