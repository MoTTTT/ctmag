import java.awt.*;
import java.awt.event.*;

public class OptionsDialog extends java.awt.Dialog     
{
    public PrintJob getPrintJob()
    {
        return null;
    }
    
	public OptionsDialog(Ctmag parent, boolean modal)
	{
		super(parent, modal);
		//{{INIT_CONTROLS
		setLayout(new GridLayout(1,1,0,0));
		setVisible(false);
		setSize(440,350);
		panel1 = new java.awt.Panel();
		panel1.setLayout(new GridLayout(14,2,0,0));
		panel1.setBounds(0,0,440,350);
		add(panel1);
		serial_label = new java.awt.Label(" Select COM port:");
		serial_label.setBounds(0,0,83,25);
		panel1.add(serial_label);
		serialblanklabel1 = new java.awt.Label("");
		serialblanklabel1.setBounds(83,0,83,25);
		panel1.add(serialblanklabel1);
		Group1 = new CheckboxGroup();
		com1_serialradiobutton = new java.awt.Checkbox("COM1", Group1, true);
		com1_serialradiobutton.setBounds(0,25,83,25);
		panel1.add(com1_serialradiobutton);
		com2_serialradiobutton = new java.awt.Checkbox("COM2", Group1, false);
		com2_serialradiobutton.setBounds(83,25,83,25);
		panel1.add(com2_serialradiobutton);
		magchannellabel = new java.awt.Label(" Select Magcurve channels:");
		magchannellabel.setBounds(0,200,83,25);
		panel1.add(magchannellabel);
		magblanklabel = new java.awt.Label("");
		magblanklabel.setBounds(83,200,83,25);
		panel1.add(magblanklabel);
		panel6 = new java.awt.Panel();
		panel6.setLayout(new GridLayout(1,2,0,0));
		panel6.setBounds(0,225,83,25);
		panel1.add(panel6);
		Group5 = new CheckboxGroup();
		magradiobutton1 = new java.awt.Checkbox("Vs - Cs", Group5, true);
		magradiobutton1.setBounds(0,0,100,40);
		panel6.add(magradiobutton1);
		magradiobutton2 = new java.awt.Checkbox("Vp - Cs", Group5, false);
		magradiobutton2.setBounds(0,0,100,40);
		panel6.add(magradiobutton2);
		panel7 = new java.awt.Panel();
		panel7.setLayout(new GridLayout(1,2,0,0));
		panel7.setBounds(83,225,83,25);
		panel1.add(panel7);
		magradiobutton3 = new java.awt.Checkbox("Vs - Cp", Group5, false);
		magradiobutton3.setBounds(0,0,100,40);
		panel7.add(magradiobutton3);
		magradiobutton4 = new java.awt.Checkbox("Vp - Cp", Group5, false);
		magradiobutton4.setBounds(0,0,100,40);
		panel7.add(magradiobutton4);
		upsweepcheckbox = new java.awt.Checkbox("Draw upsweep");
		upsweepcheckbox.setBounds(0,250,83,25);
		panel1.add(upsweepcheckbox);
		realtimecheckbox = new java.awt.Checkbox("Draw real time");
		realtimecheckbox.setBounds(83,250,83,25);
		panel1.add(realtimecheckbox);
		rtdrawinglabel = new java.awt.Label(" Real Time Draw Area (10% - 100%)");
		rtdrawinglabel.setBounds(0,275,83,25);
		panel1.add(rtdrawinglabel);
		rtdrawingtextfield = new java.awt.TextField();
		rtdrawingtextfield.setText("100");
		rtdrawingtextfield.setBounds(83,275,83,25);
		panel1.add(rtdrawingtextfield);
		cscurrentlabel = new java.awt.Label(" Cs fullscale current (mA)");
		cscurrentlabel.setBounds(0,0,100,40);
		panel1.add(cscurrentlabel);
		cscurrenttextfield = new java.awt.TextField();
		cscurrenttextfield.setText("1000");
		cscurrenttextfield.setBounds(0,0,100,40);
		panel1.add(cscurrenttextfield);
		voltmultiplylabel = new java.awt.Label(" Mag Voltage Multiplication Factor");
		voltmultiplylabel.setBounds(0,0,100,40);
		panel1.add(voltmultiplylabel);
		voltmultiplytextfield = new java.awt.TextField();
		voltmultiplytextfield.setText("1.0");
		voltmultiplytextfield.setBounds(0,0,100,40);
		panel1.add(voltmultiplytextfield);
		timethresholdlabel = new java.awt.Label(" Time Out Threshold (ms)");
		timethresholdlabel.setBounds(0,300,83,25);
		panel1.add(timethresholdlabel);
		timethresholdtextfield = new java.awt.TextField();
		timethresholdtextfield.setBounds(83,300,83,25);
		panel1.add(timethresholdtextfield);
		magvoltthresholdlabel = new java.awt.Label(" Mag Voltage Threshold (V at sensor)");
		magvoltthresholdlabel.setBounds(0,325,83,25);
		panel1.add(magvoltthresholdlabel);
		magvoltthresholdtextfield = new java.awt.TextField();
		magvoltthresholdtextfield.setBounds(83,325,83,25);
		panel1.add(magvoltthresholdtextfield);
		magampthresholdlabel = new java.awt.Label(" Mag Current Threshold (mA at sensor)");
		magampthresholdlabel.setBounds(0,350,83,25);
		panel1.add(magampthresholdlabel);
		magampthresholdtextfield = new java.awt.TextField();
		magampthresholdtextfield.setBounds(83,350,83,25);
		panel1.add(magampthresholdtextfield);
		voltratiothresholdlabel = new java.awt.Label(" Voltage Ratio Threshold (V at Vp)");
		voltratiothresholdlabel.setBounds(0,375,83,25);
		panel1.add(voltratiothresholdlabel);
		voltratiothresholdtextfield = new java.awt.TextField();
		voltratiothresholdtextfield.setBounds(83,375,83,25);
		panel1.add(voltratiothresholdtextfield);
		ampratiothresholdlabel = new java.awt.Label(" Current Ratio Threshold(mA at Cp)");
		ampratiothresholdlabel.setBounds(0,400,83,25);
		panel1.add(ampratiothresholdlabel);
		ampratiothresholdtextfield = new java.awt.TextField();
		ampratiothresholdtextfield.setBounds(83,400,83,25);
		panel1.add(ampratiothresholdtextfield);
		optionsacceptbutton = new java.awt.Button();
		optionsacceptbutton.setLabel("OK");
		optionsacceptbutton.setBounds(0,425,83,25);
		optionsacceptbutton.setBackground(new Color(12632256));
		panel1.add(optionsacceptbutton);
		optionscancelbutton = new java.awt.Button();
		optionscancelbutton.setLabel("Cancel");
		optionscancelbutton.setBounds(83,425,83,25);
		optionscancelbutton.setBackground(new Color(12632256));
		panel1.add(optionscancelbutton);
		setTitle("OptionsDialog");
		//}}

		//{{REGISTER_LISTENERS
		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		//}}
		ctmag = parent;
		optionsbuttonhandler = new OptionsButtonHandler(this, ctmag);
		optionsacceptbutton.addActionListener(optionsbuttonhandler);		
		optionscancelbutton.addActionListener(optionsbuttonhandler);		
	}

//***************************************************************
// Set the values for the thresholds in the Options Dialog as 
// determined by the testcontrolengine. Gets called at start up
// of the application.
//***************************************************************
	
	protected void instantiateFields()
	{
	    timethresholdtextfield.setText(Integer.toString(
	        ctmag.testcontrolengine.sampler.getTimeOutThreshold()));

	    magvoltthresholdtextfield.setText(Integer.toString(
	        ctmag.testcontrolengine.getMagVoltThreshold()));
	        
        magampthresholdtextfield.setText(Integer.toString(
	        ctmag.testcontrolengine.getMagCurrentThreshold()));
	        
        voltratiothresholdtextfield.setText(Integer.toString(
	        ctmag.testcontrolengine.getVoltRatioThreshold()));
	
        ampratiothresholdtextfield.setText(Integer.toString(
	        ctmag.testcontrolengine.getCurrentRatioThreshold()));
	    
	}

	public void addNotify()
	{
		// Record the size of the window prior to calling parents addNotify.
		Dimension d = getSize();

		super.addNotify();

		if (fComponentsAdjusted)
			return;

		// Adjust components according to the insets
		setSize(insets().left + insets().right + d.width, insets().top + insets().bottom + d.height);
		Component components[] = getComponents();
		for (int i = 0; i < components.length; i++)
		{
			Point p = components[i].getLocation();
			p.translate(insets().left, insets().top);
			components[i].setLocation(p);
		}
		fComponentsAdjusted = true;
	}

	// Used for addNotify check.
	boolean fComponentsAdjusted = false;

	public OptionsDialog(Ctmag parent, String title, boolean modal)
	{
		this(parent, modal);
		setTitle(title);
	}

	public synchronized void show()
	{
		Rectangle bounds = getParent().bounds();
		Rectangle abounds = bounds();

		move(bounds.x + (bounds.width - abounds.width)/ 2,
			bounds.y + (bounds.height - abounds.height)/2);
		super.show();
	}

	public void setVisible(boolean b)
	{
		if(b)
		{
			Rectangle bounds = getParent().getBounds();
			Rectangle abounds = getBounds();
			setLocation(bounds.x + (bounds.width - abounds.width) /2 ,
				bounds.y + (bounds.height - abounds.height) / 2);
		}
	super.setVisible(b);
	}

	public class SymWindow extends java.awt.event.WindowAdapter
	{
		public void windowClosing(java.awt.event.WindowEvent event)
		{
			Object object = event.getSource();
			if (object == OptionsDialog.this)
				OptionsDialog_WindowClosing(event);
		}
	}
	public void setSettings(Ctmag f)
	{
	    ctmag = f;
	}
	
	void OptionsDialog_WindowClosing(java.awt.event.WindowEvent event)
	{
		dispose();
	}
	//{{DECLARE_CONTROLS
	java.awt.Panel panel1;
	java.awt.Label serial_label;
	java.awt.Label serialblanklabel1;
	java.awt.Checkbox com1_serialradiobutton;
	CheckboxGroup Group1;
	java.awt.Checkbox com2_serialradiobutton;
	java.awt.Label magchannellabel;
	java.awt.Label magblanklabel;
	java.awt.Panel panel6;
	java.awt.Checkbox magradiobutton1;
	CheckboxGroup Group5;
	java.awt.Checkbox magradiobutton2;
	java.awt.Panel panel7;
	java.awt.Checkbox magradiobutton3;
	java.awt.Checkbox magradiobutton4;
	java.awt.Checkbox upsweepcheckbox;
	java.awt.Checkbox realtimecheckbox;
	java.awt.Label rtdrawinglabel;
	java.awt.TextField rtdrawingtextfield;
	java.awt.Label cscurrentlabel;
	java.awt.TextField cscurrenttextfield;
	java.awt.Label voltmultiplylabel;
	java.awt.TextField voltmultiplytextfield;
	java.awt.Label timethresholdlabel;
	java.awt.TextField timethresholdtextfield;
	java.awt.Label magvoltthresholdlabel;
	java.awt.TextField magvoltthresholdtextfield;
	java.awt.Label magampthresholdlabel;
	java.awt.TextField magampthresholdtextfield;
	java.awt.Label voltratiothresholdlabel;
	java.awt.TextField voltratiothresholdtextfield;
	java.awt.Label ampratiothresholdlabel;
	java.awt.TextField ampratiothresholdtextfield;
	java.awt.Button optionsacceptbutton;
	java.awt.Button optionscancelbutton;
	//}}

    // Declare Objects
    Ctmag ctmag;
    OptionsButtonHandler optionsbuttonhandler;
}