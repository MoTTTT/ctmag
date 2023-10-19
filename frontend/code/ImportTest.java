//**************************************************************
// Handles the importing of a test i.e. displaying the data pertaining 
// to the imported test on the front end of the application. Handles
// the Next Test functionality. Gets called by the TestControlEngine
// and ImportSequentialFile.
//**************************************************************

import java.lang.*;

public class ImportTest extends java.lang.Object
{
    private Ctmag ctmag;
    private MagCurveTest magcurvetest;
    
    public ImportTest(Ctmag f)
    {
        ctmag = f;  
    }
   
    public void setObjects(MagCurveTest m)
    {
        magcurvetest = m;  
    }
   
    public void getImportTest()throws java.beans.PropertyVetoException
    {
        importGeneralSettingsPanel();
        importCTSettingsPanel();
        importVTSettingsPanel();
        drawGraphs();
        importMagCurveResults();        
        importResultspanels(); 
        ctmag.kneepointtablehandler.doThreePhaseTable();
    }

    public void nextTest()throws java.beans.PropertyVetoException
    {
        ctmag.generalsettingspanel.testnametextfield.setText(" ");        
        drawGraphs();
        importMagCurveResults();        
        importResultspanels(); 
        ctmag.kneepointtablehandler.doThreePhaseTable();        
    }

    private void importGeneralSettingsPanel()
    {
        ctmag.generalsettingspanel.testnametextfield.setText(magcurvetest.getTestname());
        ctmag.generalsettingspanel.testertextfield.setText(magcurvetest.getTestername());
        ctmag.generalsettingspanel.supplyauthoritytextfield.setText(magcurvetest.getSupplyauthority());      
        ctmag.generalsettingspanel.substationtextfield.setText(magcurvetest.getSubstation());
        ctmag.generalsettingspanel.feedertextfield.setText(magcurvetest.getFeeder());
    }

    private void importCTSettingsPanel() throws java.beans.PropertyVetoException
    {
        ctmag.ctsettingspanel.ctrednumbertextfield.setText(magcurvetest.getRednumber());      
        ctmag.ctsettingspanel.ctwhitenumbertextfield.setText(magcurvetest.getWhitenumber());      
        ctmag.ctsettingspanel.ctbluenumbertextfield.setText(magcurvetest.getBluenumber());      
        ctmag.ctsettingspanel.ctvaratingtextfield.setText(magcurvetest.getCTVarating()); 
        try{
        ctmag.ctsettingspanel.ctmakecombobox.setText(magcurvetest.getCTMake());
        }
        catch(NullPointerException nexcp)
        {
        }
        catch(java.beans.PropertyVetoException pve)
        {
        }    
        
        try{
        ctmag.ctsettingspanel.cttypecombobox.setText(magcurvetest.getCTType());
        }
        catch(NullPointerException nexcp)
        {
        }
        catch(java.beans.PropertyVetoException pve)
        {
        }    

        try{
            ctmag.ctsettingspanel.ctclasscombobox.setText(magcurvetest.getCTClassification());
            }
        catch(NullPointerException nexcp)
        {
        }
        catch(java.beans.PropertyVetoException pve)
        {
        }    

        try{
            ctmag.ctsettingspanel.ctapplicationcombobox.setText(magcurvetest.getCTApplication());
            }
        catch(NullPointerException nexcp)
        {
        }
        catch(java.beans.PropertyVetoException pve)
        {
        }    

        try{
                ctmag.ctsettingspanel.ctratioprimetextfield.setText
                   (""+ magcurvetest.getTruePrimCTRatio());
            }
        catch(NullPointerException nexcp)
        {
        }
        catch(NumberFormatException nfe){
        }

        try{
                ctmag.ctsettingspanel.ctratiosecondtextfield.setText
                   (""+ magcurvetest.getTrueSeconCTRatio());
            }
        catch(NullPointerException nexcp)
        {
        }
        catch(NumberFormatException nfe){
        }        
    }
    
    private void importVTSettingsPanel() throws java.beans.PropertyVetoException
    {
        ctmag.vtsettingspanel.vtserialnumbertextfield.setText(magcurvetest.getVTSerialno());      
        ctmag.vtsettingspanel.vtdescriptiontextfield.setText(magcurvetest.getVTDescription());      
        ctmag.vtsettingspanel.vtiltextfield.setText(magcurvetest.getVTIl());      
        ctmag.vtsettingspanel.vtvaratingtextfield.setText(magcurvetest.getVTVarating()); 

        try{
            ctmag.vtsettingspanel.vtmakecombobox.setText(magcurvetest.getVTMake());
            }
        catch(NullPointerException nexcp)
        {
        }
        catch(java.beans.PropertyVetoException pve)
        {
        }    


        try{
            ctmag.vtsettingspanel.vttypecombobox.setText(magcurvetest.getVTType());
            }
        catch(NullPointerException nexcp)
        {
        }
        catch(java.beans.PropertyVetoException pve)
        {
        }    
                
        try{
            ctmag.vtsettingspanel.vtclasscombobox.setText(magcurvetest.getVTClassification());
            }
        catch(NullPointerException nexcp)
        {
        }
        catch(java.beans.PropertyVetoException pve)
        {
        }    

        try{
            ctmag.vtsettingspanel.vtphasechoice.select(magcurvetest.getVTPhase());           
            }        
        catch(IllegalArgumentException i){
            ctmag.statustextfield.setText("Exception occurred: " + i);
            ctmag.vtsettingspanel.vtphasechoice.insert(magcurvetest.getVTPhase(), 1);
            ctmag.vtsettingspanel.vtphasechoice.select(magcurvetest.getVTPhase());             
            }
        catch ( NullPointerException npe){
            ctmag.vtsettingspanel.vtphasechoice.select(0);
        }
            
            
        try{
                ctmag.vtsettingspanel.vtratioprimetextfield.setText
                   (""+ magcurvetest.getTruePrimVTRatio());
            }
        catch(NullPointerException nexcp)
        {
        }
        catch(NumberFormatException nfe){
        }

        try{
                ctmag.vtsettingspanel.vtratiosecondtextfield.setText
                   (""+ magcurvetest.getTrueSeconVTRatio());
            }
        catch(NullPointerException nexcp)
        {
        }
        catch(NumberFormatException nfe){
        }        
    }
        
    private void drawGraphs()
    {    
        
        if(ctmag.magcurvetest.main_kneepoint_reached)        
            ctmag.maincanvas.setIncrements(ctmag.magcurvetest.maincurve.getLastCurrent(), ctmag.magcurvetest.maincurve.getLastVoltage());                    
        else        
            ctmag.maincanvas.setFullScaleIncrements(ctmag.magcurvetest.maincurve.getFullscale());        
        
        ctmag.maincanvas.drawMainCurve
            (ctmag.magcurvetest.maincurve);
            
        if(ctmag.magcurvetest.kneepoint_reached)
        {
            ctmag.phasecanvas.setIncrements(ctmag.magcurvetest.max_last_current, ctmag.magcurvetest.max_last_voltage);                
            ctmag.reportphasecanvas.setIncrements(ctmag.magcurvetest.max_last_current, ctmag.magcurvetest.max_last_voltage);                                    
        }
        else
        {
            ctmag.phasecanvas.setFullScaleIncrements();
            ctmag.reportphasecanvas.setFullScaleIncrements();
        }    

        for (int i = ctmag.magcurvetest.RED; i <= ctmag.magcurvetest.BLUE; i++)
        {
            ctmag.phasecanvas.readDataIn(
                        ctmag.magcurvetest.vimagcurve[i], i);
        }                       
    }
    
    private void importResultspanels()
    {
        importCurrentRatioResults();
        importVoltageRatioResults();        
    }
    
    private void importCurrentRatioResults()
    {
        ctmag.currentratioresultshandler.getEnteredRatios();

        for (int i = ctmag.magcurvetest.RED; i <= ctmag.magcurvetest.BLUE; i++)
        { 
            if(ctmag.currentratioresultshandler.showTestedPhaseRatios(i))
            {
                ctmag.currentratioresultshandler.showTruePhaseRatio(i);            
                ctmag.currentratioresultshandler.showMeasError(i);        
                ctmag.currentratioresultshandler.showPhasePolarity(i);        
            }
            else
            {   
                if(i == ctmag.magcurvetest.RED)
                {
                    clearRedCTResults();
                }
                
                if(i == ctmag.magcurvetest.WHITE)
                {
                    clearWhiteCTResults();
                }   
                
                if(i == ctmag.magcurvetest.BLUE)
                {
                    clearBlueCTResults();
                }                                
            }
        }
    }        
    
    private void importVoltageRatioResults()
    {
        ctmag.voltageratioresultshandler.getEnteredRatios();

        for (int i = ctmag.magcurvetest.RED; i <= ctmag.magcurvetest.BLUE; i++)
        {        
            if(ctmag.voltageratioresultshandler.showTestedPhaseRatios(i))
            {
                ctmag.voltageratioresultshandler.showMeasError(i);        
                ctmag.voltageratioresultshandler.showTruePhaseRatio(i);
                ctmag.voltageratioresultshandler.showPhasePolarity(i);        
            }
            else
            {   
                if(i == ctmag.magcurvetest.RED)
                {
                    clearRedVTResults();
                }
                
                if(i == ctmag.magcurvetest.WHITE)
                {
                    clearWhiteVTResults();
                }   
                
                if(i == ctmag.magcurvetest.BLUE)
                {
                    clearBlueVTResults();
                }                                
            }            
        }
    }
    
    private void clearRedVTResults()
    {
        ctmag.voltageratioresults.redvoltresultratiotextfield.setText("");       
        ctmag.voltageratioresults.redvolterrratiotextfield.setText("");       
        ctmag.voltageratioresults.redvolttrueratiotextfield.setText("");       
        ctmag.voltageratioresults.redvoltpolratiotextfield.setText("");               
    }
    
    private void clearWhiteVTResults()
    {
        ctmag.voltageratioresults.whitevoltresultratiotextfield.setText("");       
        ctmag.voltageratioresults.whitevolterrratiotextfield.setText("");       
        ctmag.voltageratioresults.whitevolttrueratiotextfield.setText("");       
        ctmag.voltageratioresults.whitevoltpolratiotextfield.setText("");               
    }
    
    private void clearBlueVTResults()
    {
        ctmag.voltageratioresults.bluevoltresultratiotextfield.setText("");       
        ctmag.voltageratioresults.bluevolterrratiotextfield.setText("");       
        ctmag.voltageratioresults.bluevolttrueratiotextfield.setText("");       
        ctmag.voltageratioresults.bluevoltpolratiotextfield.setText("");               
    }
            
    private void clearRedCTResults()
    {
        ctmag.currentratioresults.redampresultratiotextfield.setText("");       
        ctmag.currentratioresults.redamperrratiotextfield.setText("");       
        ctmag.currentratioresults.redamptrueratiotextfield.setText("");       
        ctmag.currentratioresults.redamppolratiotextfield.setText("");               
    }
    
    private void clearWhiteCTResults()
    {
        ctmag.currentratioresults.whiteampresultratiotextfield.setText("");       
        ctmag.currentratioresults.whiteamperrratiotextfield.setText("");       
        ctmag.currentratioresults.whiteamptrueratiotextfield.setText("");       
        ctmag.currentratioresults.whiteamppolratiotextfield.setText("");               
    }
    
    private void clearBlueCTResults()
    {
        ctmag.currentratioresults.blueampresultratiotextfield.setText("");       
        ctmag.currentratioresults.blueamperrratiotextfield.setText("");       
        ctmag.currentratioresults.blueamptrueratiotextfield.setText("");       
        ctmag.currentratioresults.blueamppolratiotextfield.setText("");               
    }
    
    public void importMagCurveResults()
    {
        int volt_mul_factor = ctmag.testcontrolengine.FULLSCALE + 1 + ctmag.testcontrolengine.VOLT_MUL;
        int amp_mul_factor = ctmag.testcontrolengine.FULLSCALE + 1 + ctmag.testcontrolengine.AMP_MUL;
        int conversion_factor = ctmag.testcontrolengine.CONVERSION_FACTOR;

        try{        
            ctmag.magcurveresultshandler.setMainKneeVolt(magcurvetest.maincurve.data[volt_mul_factor], conversion_factor);
            ctmag.magcurveresultshandler.setMainKneeAmp(magcurvetest.maincurve.data[amp_mul_factor], conversion_factor);
        }
        catch (ArrayIndexOutOfBoundsException a)
        {
            ctmag.magcurveresultshandler.setMainKneeVolt(ctmag.testcontrolengine.DEFAULT_V_MUL_FACTOR, conversion_factor); 
            ctmag.magcurveresultshandler.setMainKneeAmp(ctmag.testcontrolengine.DEFAULT_A_MUL_FACTOR, conversion_factor);                    
        }
        
        if((ctmag.magcurveresultshandler.mainkneevolt == 0) && (ctmag.magcurveresultshandler.mainkneeamp == 0))
        {
            ctmag.magcurveresults.mainkneevolttextfield.setText("");
            ctmag.magcurveresults.mainkneeamptextfield.setText("");
        }
        for (int i = ctmag.magcurvetest.RED; i <= ctmag.magcurvetest.BLUE; i++)
        {
            try{                
                ctmag.magcurveresultshandler.showMagCurvePhase(i, ctmag.magcurvetest.vimagcurve[i].data[volt_mul_factor],
                    ctmag.magcurvetest.vimagcurve[i].data[amp_mul_factor], conversion_factor);
            }
            catch (ArrayIndexOutOfBoundsException a)
            {
                ctmag.magcurveresultshandler.showMagCurvePhase(i, ctmag.testcontrolengine.DEFAULT_V_MUL_FACTOR,
                    ctmag.testcontrolengine.DEFAULT_A_MUL_FACTOR, conversion_factor);                
            }
        } 
        
        if((ctmag.magcurveresultshandler.redkneevolt == 0) && (ctmag.magcurveresultshandler.redkneeamp == 0))
        {
            ctmag.magcurveresults.redkneevolttextfield.setText("");
            ctmag.magcurveresults.redkneeamptextfield.setText("");
            ctmag.reportmagcurveresults.redkneevolttextfield.setText("");
            ctmag.reportmagcurveresults.redkneeamptextfield.setText("");
            
        }

        if((ctmag.magcurveresultshandler.whitekneevolt == 0) && (ctmag.magcurveresultshandler.whitekneeamp == 0))
        {
            ctmag.magcurveresults.whitekneevolttextfield.setText("");
            ctmag.magcurveresults.whitekneeamptextfield.setText("");
            ctmag.reportmagcurveresults.whitekneevolttextfield.setText("");
            ctmag.reportmagcurveresults.whitekneeamptextfield.setText("");

        }

        if((ctmag.magcurveresultshandler.bluekneevolt == 0) && (ctmag.magcurveresultshandler.bluekneeamp == 0))
        {
            ctmag.magcurveresults.bluekneevolttextfield.setText("");
            ctmag.magcurveresults.bluekneeamptextfield.setText("");
            ctmag.reportmagcurveresults.bluekneevolttextfield.setText("");
            ctmag.reportmagcurveresults.bluekneeamptextfield.setText("");
            
        }        
    }      
}