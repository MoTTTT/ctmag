//*****************************************************************
// Provide the window container in which the HTML help files are
// to be displayed.
//*****************************************************************

import java.awt.*;
import java.awt.event.*;
public class HelpDialog extends java.awt.Dialog implements java.awt.event.ActionListener 
{
    public void actionPerformed(ActionEvent e)
    {
    }
	public HelpDialog(Ctmag parent, boolean modal)
	{
		super(parent, modal);
		//{{INIT_CONTROLS
		setLayout(new BorderLayout(0,0));
		setVisible(false);
		setSize(430,270);
		helpdialogdisplaypanel = new java.awt.Panel();
		helpdialogdisplaypanel.setLayout(new GridLayout(1,1,0,0));
		helpdialogdisplaypanel.setBounds(0,0,430,230);
		add("Center", helpdialogdisplaypanel);
		helpdialogbuttonpanel = new java.awt.Panel();
		helpdialogbuttonpanel.setLayout(new GridLayout(1,2,0,0));
		helpdialogbuttonpanel.setBounds(0,0,430,40);
		add("South", helpdialogbuttonpanel);
		backbutton = new java.awt.Button();
		backbutton.setLabel("Back");
		backbutton.setBounds(0,0,60,40);
		backbutton.setBackground(new Color(12632256));
		helpdialogbuttonpanel.add(backbutton);
		forwardbutton = new java.awt.Button();
		forwardbutton.setLabel("Forward");
		forwardbutton.setBounds(0,0,60,40);
		forwardbutton.setBackground(new Color(12632256));
		helpdialogbuttonpanel.add(forwardbutton);
		setTitle("HelpDialog");
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

	public HelpDialog(Ctmag parent, String title, boolean modal)
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
			if (object == HelpDialog.this)
				HelpDialog_WindowClosing(event);
		}
	}

	void HelpDialog_WindowClosing(java.awt.event.WindowEvent event)
	{
		dispose();
	}
	//{{DECLARE_CONTROLS
	java.awt.Panel helpdialogdisplaypanel;
	java.awt.Panel helpdialogbuttonpanel;
	java.awt.Button backbutton;
	java.awt.Button forwardbutton;
	//}}
}