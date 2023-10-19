//**************************************************************
// Dialog window container in which the test report is displayed
// with buttons to select whether the report is to be printed. 
//**************************************************************

import java.awt.*;
import symantec.itools.awt.BorderPanel;
import java.awt.event.*;
public class TestReportDialog extends java.awt.Dialog implements java.awt.PrintGraphics  
{
	public TestReportDialog(Frame parent, boolean modal)
	{
		super(parent, modal);
		//{{INIT_CONTROLS
		setLayout(new BorderLayout(0,0));
		setVisible(false);
		setSize(640,480);
		printingpanel = new java.awt.Panel();
		printingpanel.setLayout(new GridLayout(1,1,0,0));
		printingpanel.setBounds(0,457,640,23);
		add("South", printingpanel);
		printreportacceptbutton = new java.awt.Button();
		printreportacceptbutton.setLabel("OK");
		printreportacceptbutton.setBounds(0,0,320,23);
		printreportacceptbutton.setBackground(new Color(12632256));
		printingpanel.add(printreportacceptbutton);
		printreportignorebutton = new java.awt.Button();
		printreportignorebutton.setLabel("Cancel");
		printreportignorebutton.setBounds(320,0,320,23);
		printreportignorebutton.setBackground(new Color(12632256));
		printingpanel.add(printreportignorebutton);
		testreportholdingpanel = new java.awt.Panel();
		GridBagLayout gridBagLayout;
		gridBagLayout = new GridBagLayout();
		testreportholdingpanel.setLayout(gridBagLayout);
		testreportholdingpanel.setBounds(0,0,640,457);
		testreportholdingpanel.setBackground(new Color(16777215));
		add("Center", testreportholdingpanel);
		testreportdialogpanel = new java.awt.Panel();
		gridBagLayout = new GridBagLayout();
		testreportdialogpanel.setLayout(gridBagLayout);
		testreportdialogpanel.setBounds(0,0,640,457);
		GridBagConstraints gbc;
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(22,30,22,30);
		((GridBagLayout)testreportholdingpanel.getLayout()).setConstraints(testreportdialogpanel, gbc);
		testreportholdingpanel.add(testreportdialogpanel);
		reportpanel1 = new java.awt.Panel();
		reportpanel1.setLayout(new GridLayout(1,1,0,0));
		reportpanel1.setBounds(0,0,639,77);
		reportpanel1.setBackground(new Color(16777215));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 6;
		gbc.weightx = 1.0;
		gbc.weighty = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)testreportdialogpanel.getLayout()).setConstraints(reportpanel1, gbc);
		testreportdialogpanel.add(reportpanel1);
		borderpanel1 = new symantec.itools.awt.BorderPanel();
		borderpanel1.setLayout(new GridLayout(1,1,0,0));
		borderpanel1.setBounds(0,0,639,77);
		reportpanel1.add(borderpanel1);
		reportpanel2 = new java.awt.Panel();
		reportpanel2.setLayout(new GridLayout(1,1,0,0));
		reportpanel2.setBounds(0,77,213,140);
		reportpanel2.setBackground(new Color(16777215));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 2.7;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)testreportdialogpanel.getLayout()).setConstraints(reportpanel2, gbc);
		testreportdialogpanel.add(reportpanel2);
		borderpanel2 = new symantec.itools.awt.BorderPanel();
		try {
			borderpanel2.setLabel("General Settings");
		}
		catch(java.beans.PropertyVetoException e) { }
		borderpanel2.setLayout(new GridLayout(1,1,0,0));
		borderpanel2.setBounds(0,0,213,140);
		reportpanel2.add(borderpanel2);
		reportpanel3 = new java.awt.Panel();
		reportpanel3.setLayout(new GridLayout(1,1,0,0));
		reportpanel3.setBounds(213,77,213,140);
		reportpanel3.setBackground(new Color(16777215));
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 2.7;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)testreportdialogpanel.getLayout()).setConstraints(reportpanel3, gbc);
		testreportdialogpanel.add(reportpanel3);
		borderpanel3 = new symantec.itools.awt.BorderPanel();
		try {
			borderpanel3.setIPadBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderpanel3.setLabel("CT Settings");
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderpanel3.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderpanel3.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderpanel3.setIPadTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		borderpanel3.setLayout(new GridLayout(1,1,0,0));
		borderpanel3.setBounds(0,0,213,140);
		reportpanel3.add(borderpanel3);
		reportpanel4 = new java.awt.Panel();
		reportpanel4.setLayout(new GridLayout(1,1,0,0));
		reportpanel4.setBounds(426,77,213,140);
		reportpanel4.setBackground(new Color(16777215));
		gbc = new GridBagConstraints();
		gbc.gridx = 4;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 2.7;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)testreportdialogpanel.getLayout()).setConstraints(reportpanel4, gbc);
		testreportdialogpanel.add(reportpanel4);
		borderpanel4 = new symantec.itools.awt.BorderPanel();
		try {
			borderpanel4.setIPadBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderpanel4.setLabel("VT Setings");
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderpanel4.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderpanel4.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderpanel4.setIPadTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		borderpanel4.setLayout(new GridLayout(1,1,0,0));
		borderpanel4.setBounds(0,0,213,140);
		reportpanel4.add(borderpanel4);
		reportpanel5 = new java.awt.Panel();
		reportpanel5.setLayout(new GridLayout(1,3,0,0));
		reportpanel5.setBounds(0,331,639,48);
		reportpanel5.setBackground(new Color(16777215));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 6;
		gbc.gridheight = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)testreportdialogpanel.getLayout()).setConstraints(reportpanel5, gbc);
		testreportdialogpanel.add(reportpanel5);
		reportpanel5a = new java.awt.Panel();
		reportpanel5a.setLayout(new GridLayout(1,1,0,0));
		reportpanel5a.setBounds(0,0,213,48);
		reportpanel5.add(reportpanel5a);
		reportpanel5b = new java.awt.Panel();
		reportpanel5b.setLayout(new GridLayout(1,1,0,0));
		reportpanel5b.setBounds(213,0,213,48);
		reportpanel5b.setBackground(new Color(16777215));
		reportpanel5.add(reportpanel5b);
		reportpanel5c = new java.awt.Panel();
		reportpanel5c.setLayout(new GridLayout(1,1,0,0));
		reportpanel5c.setBounds(426,0,213,48);
		reportpanel5c.setBackground(new Color(16777215));
		reportpanel5.add(reportpanel5c);
		reportpanel7 = new java.awt.Panel();
		reportpanel7.setLayout(new GridLayout(1,1,0,0));
		reportpanel7.setBounds(213,217,426,114);
		reportpanel7.setBackground(new Color(16777215));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.gridheight = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 3.3;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)testreportdialogpanel.getLayout()).setConstraints(reportpanel7, gbc);
		testreportdialogpanel.add(reportpanel7);
		borderpanel7 = new symantec.itools.awt.BorderPanel();
		try {
			borderpanel7.setLabel("3 Phase Curve");
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderpanel7.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		borderpanel7.setLayout(new GridLayout(1,1,0,0));
		borderpanel7.setBounds(0,0,426,114);
		borderpanel7.setBackground(new Color(16777215));
		reportpanel7.add(borderpanel7);
		reportpanel8 = new java.awt.Panel();
		reportpanel8.setLayout(new GridLayout(1,1,0,0));
		reportpanel8.setBounds(0,379,639,77);
		reportpanel8.setBackground(new Color(16777215));
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 6;
		gbc.weightx = 1.0;
		gbc.weighty = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)testreportdialogpanel.getLayout()).setConstraints(reportpanel8, gbc);
		testreportdialogpanel.add(reportpanel8);
		borderpanel8 = new symantec.itools.awt.BorderPanel();
		try {
			borderpanel8.setIPadBottom(2);
		}
		catch(java.beans.PropertyVetoException e) { }
		borderpanel8.setLayout(new GridLayout(1,1,0,0));
		borderpanel8.setBounds(0,0,639,77);
		reportpanel8.add(borderpanel8);
		reportpanel6 = new java.awt.Panel();
		reportpanel6.setLayout(new GridLayout(1,1,0,0));
		reportpanel6.setBounds(0,0,213,114);
		reportpanel6.setBackground(new Color(16777215));
		gbc = new GridBagConstraints();
		gbc.gridx = 4;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 3.3;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)testreportdialogpanel.getLayout()).setConstraints(reportpanel6, gbc);
		testreportdialogpanel.add(reportpanel6);
		borderpanel6 = new symantec.itools.awt.BorderPanel();
		try {
			borderpanel6.setLabel("Knee Point Table");
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			borderpanel6.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		borderpanel6.setLayout(new GridLayout(1,1,0,0));
		borderpanel6.setBounds(0,0,213,114);
		borderpanel6.setBackground(new Color(16777215));
		reportpanel6.add(borderpanel6);
		setTitle("TestReportDialog");
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

	public TestReportDialog(Frame parent, String title, boolean modal)
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
			if (object == TestReportDialog.this)
				TestReportDialog_WindowClosing(event);
		}
	}

	void TestReportDialog_WindowClosing(java.awt.event.WindowEvent event)
	{
		dispose();
	}
	//{{DECLARE_CONTROLS
	java.awt.Panel printingpanel;
	java.awt.Button printreportacceptbutton;
	java.awt.Button printreportignorebutton;
	java.awt.Panel testreportholdingpanel;
	java.awt.Panel testreportdialogpanel;
	java.awt.Panel reportpanel1;
	symantec.itools.awt.BorderPanel borderpanel1;
	java.awt.Panel reportpanel2;
	symantec.itools.awt.BorderPanel borderpanel2;
	java.awt.Panel reportpanel3;
	symantec.itools.awt.BorderPanel borderpanel3;
	java.awt.Panel reportpanel4;
	symantec.itools.awt.BorderPanel borderpanel4;
	java.awt.Panel reportpanel5;
	java.awt.Panel reportpanel5a;
	java.awt.Panel reportpanel5b;
	java.awt.Panel reportpanel5c;
	java.awt.Panel reportpanel7;
	symantec.itools.awt.BorderPanel borderpanel7;
	java.awt.Panel reportpanel8;
	symantec.itools.awt.BorderPanel borderpanel8;
	java.awt.Panel reportpanel6;
	symantec.itools.awt.BorderPanel borderpanel6;
	//}}
	public  PrintJob getPrintJob()
	{
	    return null;
	}
}