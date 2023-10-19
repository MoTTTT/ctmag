//**************************************************************
// Object that saves the General, CT and VT settings to file.
//**************************************************************

import java.lang.*;
public class SaveProc extends java.lang.Object
{
    private Ctmag ctmag;
    private MagCurveTest magcurvetest;
    
    public SaveProc(Ctmag f)
    {
        ctmag = f;
    }
  
    public void setObjects(MagCurveTest m)
    {
        magcurvetest = m;    
    }

    public void saveTestSettings()
    {
        saveGeneralSettings();
        saveCTSettings();
        saveVTSettings();
    }
    
    public void saveGeneralSettings()
    {
      
        try{
            magcurvetest.setTestname(ctmag.generalsettingspanel.
                testnametextfield.getText());
            }
        catch(NullPointerException npe)
        {
        }
        
        try{
            magcurvetest.setTestername(ctmag.generalsettingspanel.
                testertextfield.getText());
            }
        catch(NullPointerException npe)
        {
        }
        
        try{
            magcurvetest.setSupplyauthority(ctmag.generalsettingspanel.
                supplyauthoritytextfield.getText());
            }
        catch(NullPointerException npe)
        {
        }

        try{
            magcurvetest.setSubstation(ctmag.generalsettingspanel.
                substationtextfield.getText());
            }
        catch(NullPointerException npe)
        {
        }

        try{
            magcurvetest.setFeeder(ctmag.generalsettingspanel.
                feedertextfield.getText());
            }
        catch(NullPointerException npe)
        {
        }              
    }
    
    public void saveCTSettings()
    {
        try{
            magcurvetest.setCTMake(ctmag.ctsettingspanel.
                ctmakecombobox.getText());
            }
        catch(NullPointerException npe)
        {
        }

        try{
            magcurvetest.setCTType(ctmag.ctsettingspanel.
                cttypecombobox.getText());
            }
        catch(NullPointerException npe)
        {
        }

        try{
            magcurvetest.setCTClassification(ctmag.ctsettingspanel.
                ctclasscombobox.getText());
            }
        catch(NullPointerException npe)
        {
        }

        try{
            magcurvetest.setCTApplication(ctmag.ctsettingspanel.
                ctapplicationcombobox.getText());
            }
        catch(NullPointerException npe)
        {
        }
        
        try{
            magcurvetest.setTruePrimCTRatio(Integer.parseInt(ctmag.ctsettingspanel.
                ctratioprimetextfield.getText()));
        }
        catch(NullPointerException npe)
        {
        }
        catch(NumberFormatException nfe)
        {
            ctmag.statustextfield.setText("Enter integer values for primary to secondary transformer ratios.");
        }

        try{
            magcurvetest.setTrueSeconCTRatio(Integer.parseInt(ctmag.ctsettingspanel.
                ctratiosecondtextfield.getText()));
        }
        catch(NullPointerException npe)
        {
        }
        catch(NumberFormatException nfe)
        {
            ctmag.statustextfield.setText("Enter integer values for primary to secondary transformer ratios.");
        }

        try{
            magcurvetest.setRednumber(ctmag.ctsettingspanel.
                ctrednumbertextfield.getText());
        }
        catch(NullPointerException npe)
        {
        }
        try{
            magcurvetest.setWhitenumber(ctmag.ctsettingspanel.
                ctwhitenumbertextfield.getText());
        }
        catch(NullPointerException npe)
        {
        }
        try{
            magcurvetest.setBluenumber(ctmag.ctsettingspanel.
                ctbluenumbertextfield.getText());
        }
        catch(NullPointerException npe)
        {
        }
        try{
            magcurvetest.setCTVarating(ctmag.ctsettingspanel.
                ctvaratingtextfield.getText());
        }
        catch(NullPointerException npe)
        {
        }                
    }
    
    
    public void saveVTSettings()
    {
        try{
            magcurvetest.setVTMake(ctmag.vtsettingspanel.
                vtmakecombobox.getText());
            }
        catch(NullPointerException npe)
        {
        }

        try{
            magcurvetest.setVTType(ctmag.vtsettingspanel.
                vttypecombobox.getText());
            }
        catch(NullPointerException npe)
        {
        }

        try{
            magcurvetest.setVTClassification(ctmag.vtsettingspanel.
                vtclasscombobox.getText());
            }
        catch(NullPointerException npe)
        {
        }

        try{
            magcurvetest.setVTPhase(ctmag.vtsettingspanel.
                vtphasechoice.getSelectedItem());
            }
        catch(NullPointerException npe)
        {
        }
        
        try{
            magcurvetest.setTruePrimVTRatio(Integer.parseInt(ctmag.vtsettingspanel.
                vtratioprimetextfield.getText()));
        }
        catch(NullPointerException npe)
        {
        }
        catch(NumberFormatException nfe)
        {
            ctmag.statustextfield.setText("Enter integer values for primary to secondary transformer ratios.");
        }

        try{
            magcurvetest.setTrueSeconVTRatio(Integer.parseInt(ctmag.vtsettingspanel.
                vtratiosecondtextfield.getText()));
        }
        catch(NullPointerException npe)
        {
        }
        catch(NumberFormatException nfe)
        {
            ctmag.statustextfield.setText("Enter integer values for primary to secondary transformer ratios.");
        }

        try{
            magcurvetest.setVTDescription(ctmag.vtsettingspanel.
                vtdescriptiontextfield.getText());
        }
        catch(NullPointerException npe)
        {
        }
        try{
            magcurvetest.setVTSerialno(ctmag.vtsettingspanel.
                vtserialnumbertextfield.getText());
        }
        catch(NullPointerException npe)
        {
        }
        try{
            magcurvetest.setVTIl(ctmag.vtsettingspanel.
                vtiltextfield.getText());
        }
        catch(NullPointerException npe)
        {
        }
        try{
            magcurvetest.setVTVarating(ctmag.vtsettingspanel.
                vtvaratingtextfield.getText());
        }
        catch(NullPointerException npe)
        {
        }                
    }  
}