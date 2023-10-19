//************************************************************
// Read in the OptionsDialog settings when a new MagCurveTest is
// kicked off, selected by the accept button in the OptionsDialog.
//************************************************************

import java.lang.*;
import java.awt.event.*;
class OptionsButtonHandler extends java.lang.Object implements java.awt.event.ActionListener  
{
    protected final int VS_CS = 0;
    protected final int VP_CS = 1;
    protected final int VS_CP = 2;
    protected final int VP_CP = 3;
    
    protected int cscurrent = 1000;
    protected int rtdrawingarea = 100;
    protected int timethreshold = 10000;
    protected int magvoltthreshold = 490;
    protected int magampthreshold = 980;
    protected int voltratiothreshold = 280;
    protected int ampratiothreshold = 20000;
    protected float volt_mult_factor;
    
    private OptionsDialog optionsdialog;
    private Ctmag ctmag;
    
    public OptionsButtonHandler(OptionsDialog o, Ctmag f)
    {
        optionsdialog = o;
        ctmag = f;    
    } 
        
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == optionsdialog.optionsacceptbutton)
        {
            getSelection();
            optionsdialog.setVisible(false);                
        }
        else
        {
            optionsdialog.setVisible(false);
            ctmag.statustextfield.setText(" ");                    
        }
    }
    
    public void getSelection()
    {
        getRadioButtonSelection();
        getDrawingOptions();
        getTextfields(); 
        ctmag.testcontrolengine.setAmpMultiplyFactor();        
    }

//************************************************************
// Read in the textfield entries made in the OptionsDialog.
//************************************************************
    
    private void getTextfields()
    {        
        try{
            cscurrent = (int)Integer.parseInt(optionsdialog.cscurrenttextfield.getText());
            ctmag.testcontrolengine.setCs(cscurrent);                     
            ctmag.statustextfield.setText(" ");
            System.out.println("Cs current = " + cscurrent + "mA");
            }
        catch(NullPointerException npe){
        }
        catch(NumberFormatException nfe){
            optionsdialog.cscurrenttextfield.setText("" + cscurrent);
            ctmag.statustextfield.setText("An Integer value has to be "
                + "entered for the Cs fullscale current.");
        }
        try{  
            Float temp;
            temp = Float.valueOf(optionsdialog.voltmultiplytextfield.getText());
            volt_mult_factor = temp.floatValue();
            ctmag.testcontrolengine.setVoltageMultiplyFactor(volt_mult_factor);        
            ctmag.statustextfield.setText(" ");
            System.out.println("Magcurve voltage multiplication factor = "  + volt_mult_factor);
            }
        catch(NullPointerException npe){
        }
        catch(NumberFormatException nfe){
            optionsdialog.voltmultiplytextfield.setText("" + volt_mult_factor);
            ctmag.statustextfield.setText(
            "A Floating point value has to be entered for the Cs fullscale current.");
        }        
        try{
            timethreshold = (int)Integer.parseInt(optionsdialog.timethresholdtextfield.
                getText());
            ctmag.testcontrolengine.sampler.setTimeOutThreshold(timethreshold);
            ctmag.statustextfield.setText(" "); 
            System.out.println("Time out threshold = " + timethreshold + "ms");
            }
        catch(NullPointerException npe){
        }
        catch(NumberFormatException nfe){
            optionsdialog.timethresholdtextfield.setText("" + timethreshold);
            ctmag.statustextfield.setText(
            "An Integer value has to be entered for the timeout threshold.");
        }        
        try{
            magvoltthreshold = (int)Integer.parseInt(optionsdialog.magvoltthresholdtextfield.
                getText());
            ctmag.testcontrolengine.setMagVoltThreshold(magvoltthreshold);
            ctmag.statustextfield.setText(" ");
            System.out.println("Magcurve voltage threshold = " + magvoltthreshold + "V");
            }
        catch(NullPointerException npe){
        }
        catch(NumberFormatException nfe){
            optionsdialog.magvoltthresholdtextfield.setText("" + magvoltthreshold);
            ctmag.statustextfield.setText("An Integer value has to be " +
                "entered for the magnetizing curve voltage threshold.");
        }
        try{
            magampthreshold = (int)Integer.parseInt(optionsdialog.magampthresholdtextfield.
                getText());
            ctmag.testcontrolengine.setMagCurrentThreshold(magampthreshold);
            ctmag.statustextfield.setText(" ");
            System.out.println("Magcurve current threshold = " + magampthreshold +"mA");
            }
        catch(NullPointerException npe){
        }
        catch(NumberFormatException nfe){
            optionsdialog.magampthresholdtextfield.setText("" + magampthreshold);                
            ctmag.statustextfield.setText("An Integer value has to be entered for the magnetizing "+            
                "curve current threshold.");
        }        
        try{
            ampratiothreshold = (int)Integer.parseInt(optionsdialog.ampratiothresholdtextfield.
                getText());
            ctmag.testcontrolengine.setCurrentRatioThreshold(ampratiothreshold);
            ctmag.statustextfield.setText(" ");
            System.out.println("Current Ratio end of test = " + ampratiothreshold +"mA");
            }
        catch(NullPointerException npe){
        }
        catch(NumberFormatException nfe){
            optionsdialog.ampratiothresholdtextfield.setText("" + ampratiothreshold);                
            ctmag.statustextfield.setText("An Integer value has to be entered for the magnetizing "+            
                "curve current threshold.");
        }                        
        try{
            voltratiothreshold = (int)Integer.parseInt(optionsdialog.voltratiothresholdtextfield.
                getText());
            ctmag.testcontrolengine.setVoltRatioThreshold(voltratiothreshold);
            ctmag.statustextfield.setText(" ");
            System.out.println("Voltage Ratio end of test = " + voltratiothreshold +"V");
            }
        catch(NullPointerException npe){
        }
        catch(NumberFormatException nfe){
            optionsdialog.voltratiothresholdtextfield.setText("" + voltratiothreshold);                
            ctmag.statustextfield.setText("An Integer value has to be entered for the magnetizing "+            
                "curve current threshold.");
        }
    } 
    
//************************************************************
// Get the Radio Button selections made in the OptionsDialog.
//************************************************************
    
     private void getRadioButtonSelection()
     {
        if(optionsdialog.com1_serialradiobutton.getState())
        {
            System.out.println("COM1 selected");
            ctmag.testcontrolengine.sampler.setPort("COM1");
        }
        else
        {        
            System.out.println("COM2 selected");
            ctmag.testcontrolengine.sampler.setPort("COM2");
        } 
 
        if(optionsdialog.magradiobutton1.getState()) //!!!
        {
            System.out.println("Vs - Cs selected");
            ctmag.testcontrolengine.setChannelSelector(VS_CS);
        }
        else if(optionsdialog.magradiobutton2.getState())
        {        
            System.out.println("Vp - Cs selected");
            ctmag.testcontrolengine.setChannelSelector(VP_CS);            
        }               
        else if(optionsdialog.magradiobutton3.getState())
        {        
            System.out.println("Vs - Cp selected");
            ctmag.testcontrolengine.setChannelSelector(VS_CP);            
        }               
        else if(optionsdialog.magradiobutton4.getState())
        {        
            System.out.println("Vp - Cp selected");
            ctmag.testcontrolengine.setChannelSelector(VP_CP);            
        }                                
     }
     
//************************************************************
// Read in the drawing options made in the Options Dialog. Draw 
// the down curve, draw real time, scale of maincanvas drawing.
//************************************************************
     
     private void getDrawingOptions()
     {
        int draw_area;
        ctmag.testcontrolengine.setUpsweepCheckbox(optionsdialog.upsweepcheckbox.getState());
        ctmag.testcontrolengine.setRealTimeCheckbox(optionsdialog.realtimecheckbox.getState());
        draw_area = Integer.parseInt(optionsdialog.rtdrawingtextfield.getText());
        if(draw_area<= 100 && draw_area >= 10)
            ctmag.testcontrolengine.setDrawArea(draw_area);
        else
        {
            ctmag.statustextfield.setText("The drawing area must constitute an integer "+
                "value between 10 and a 100 percent.");
            optionsdialog.rtdrawingtextfield.setText("100");
            ctmag.testcontrolengine.setDrawArea(draw_area = 100);
        }
     }
}