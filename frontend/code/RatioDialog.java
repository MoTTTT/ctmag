//************************************************************
// Dialog box to display the measured ratio and polarity at an 
// end of test. 
//************************************************************

import java.awt.*;
import java.awt.event.*;
class RatioDialog extends java.awt.Dialog 
{
	public RatioDialog(Ctmag parent, boolean modal)
	{
		super(parent, modal);
		//{{INIT_CONTROLS
		setLayout(new GridLayout(4,2,0,0));
		setVisible(false);
		setSize(220,66);
		ratiodialogemptylabel1 = new java.awt.Label("");
		ratiodialogemptylabel1.setBounds(0,0,179,78);
		add(ratiodialogemptylabel1);
		ratiodialogemptylabel2 = new java.awt.Label("");
		ratiodialogemptylabel2.setBounds(179,0,179,78);
		add(ratiodialogemptylabel2);
		ratiodialogratiolabel = new java.awt.Label("Ratio :");
		ratiodialogratiolabel.setBounds(0,78,179,78);
		add(ratiodialogratiolabel);
		holdingratiotextfield = new java.awt.TextField();
		holdingratiotextfield.setEditable(false);
		holdingratiotextfield.setBounds(179,78,179,78);
		holdingratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 11));
		add(holdingratiotextfield);
		ratiodialogpolaritylabel = new java.awt.Label("Polarity :");
		ratiodialogpolaritylabel.setBounds(0,156,179,78);
		add(ratiodialogpolaritylabel);
		holdingpolaritytextfield = new java.awt.TextField();
		holdingpolaritytextfield.setEditable(false);
		holdingpolaritytextfield.setBounds(179,156,179,78);
		holdingpolaritytextfield.setFont(new Font("Dialog", Font.PLAIN, 11));
		add(holdingpolaritytextfield);
		ratiodialogemptylabel4 = new java.awt.Label("");
		ratiodialogemptylabel4.setBounds(0,234,179,78);
		add(ratiodialogemptylabel4);
		ratiodialogemptylabel5 = new java.awt.Label("");
		ratiodialogemptylabel5.setBounds(179,234,179,78);
		add(ratiodialogemptylabel5);
		setTitle("RatioDialog");
		//}}

		//{{REGISTER_LISTENERS
		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		//}}
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

	public RatioDialog(Ctmag parent, String title, boolean modal)
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

	class SymWindow extends java.awt.event.WindowAdapter 
	{
		public void windowClosing(java.awt.event.WindowEvent event)
		{
			Object object = event.getSource();
			if (object == RatioDialog.this)
				RatioDialog_WindowClosing(event);
		}
	}

	void RatioDialog_WindowClosing(java.awt.event.WindowEvent event)
	{
		dispose();
	}
	//{{DECLARE_CONTROLS
	java.awt.Label ratiodialogemptylabel1;
	java.awt.Label ratiodialogemptylabel2;
	java.awt.Label ratiodialogratiolabel;
	java.awt.TextField holdingratiotextfield;
	java.awt.Label ratiodialogpolaritylabel;
	java.awt.TextField holdingpolaritytextfield;
	java.awt.Label ratiodialogemptylabel4;
	java.awt.Label ratiodialogemptylabel5;
	//}}

}