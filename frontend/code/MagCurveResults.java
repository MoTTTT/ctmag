import java.awt.*;
import java.beans.*;
import symantec.itools.awt.BorderPanel;
class MagCurveResults extends java.awt.Panel 
{
	public MagCurveResults()
	{
		super();
		//{{INIT_CONTROLS
		setLayout(new GridLayout(1,1,0,0));
		setSize(430,270);
		magcurveresultsborderpanel = new symantec.itools.awt.BorderPanel();
		try {
			magcurveresultsborderpanel.setIPadBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			magcurveresultsborderpanel.setLabel("Mag Curve Results");
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			magcurveresultsborderpanel.setIPadSides(2);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			magcurveresultsborderpanel.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			magcurveresultsborderpanel.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			magcurveresultsborderpanel.setIPadTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		GridBagLayout gridBagLayout;
		gridBagLayout = new GridBagLayout();
		magcurveresultsborderpanel.setLayout(gridBagLayout);
		magcurveresultsborderpanel.setBounds(0,0,249,72);
		add(magcurveresultsborderpanel);
		mainkneelabel = new java.awt.Label("Main Kneepoint");
		mainkneelabel.setBounds(1,0,130,14);
		GridBagConstraints gbc;
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)magcurveresultsborderpanel.getLayout()).setConstraints(mainkneelabel, gbc);
		magcurveresultsborderpanel.add(mainkneelabel);
		mainkneevolttextfield = new java.awt.TextField();
		mainkneevolttextfield.setBounds(131,0,50,14);
		mainkneevolttextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)magcurveresultsborderpanel.getLayout()).setConstraints(mainkneevolttextfield, gbc);
		magcurveresultsborderpanel.add(mainkneevolttextfield);
		mainkneeamptextfield = new java.awt.TextField();
		mainkneeamptextfield.setBounds(181,0,50,14);
		mainkneeamptextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		gbc = new GridBagConstraints();
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)magcurveresultsborderpanel.getLayout()).setConstraints(mainkneeamptextfield, gbc);
		magcurveresultsborderpanel.add(mainkneeamptextfield);
		redkneelabel = new java.awt.Label("Red Kneepoint");
		redkneelabel.setBounds(1,14,130,14);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)magcurveresultsborderpanel.getLayout()).setConstraints(redkneelabel, gbc);
		magcurveresultsborderpanel.add(redkneelabel);
		redkneevolttextfield = new java.awt.TextField();
		redkneevolttextfield.setBounds(131,14,50,14);
		redkneevolttextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)magcurveresultsborderpanel.getLayout()).setConstraints(redkneevolttextfield, gbc);
		magcurveresultsborderpanel.add(redkneevolttextfield);
		redkneeamptextfield = new java.awt.TextField();
		redkneeamptextfield.setBounds(181,14,50,14);
		redkneeamptextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		gbc = new GridBagConstraints();
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)magcurveresultsborderpanel.getLayout()).setConstraints(redkneeamptextfield, gbc);
		magcurveresultsborderpanel.add(redkneeamptextfield);
		whitekneelabel = new java.awt.Label("White Kneepoint");
		whitekneelabel.setBounds(1,28,130,14);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)magcurveresultsborderpanel.getLayout()).setConstraints(whitekneelabel, gbc);
		magcurveresultsborderpanel.add(whitekneelabel);
		whitekneevolttextfield = new java.awt.TextField();
		whitekneevolttextfield.setBounds(131,28,50,14);
		whitekneevolttextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)magcurveresultsborderpanel.getLayout()).setConstraints(whitekneevolttextfield, gbc);
		magcurveresultsborderpanel.add(whitekneevolttextfield);
		whitekneeamptextfield = new java.awt.TextField();
		whitekneeamptextfield.setBounds(181,28,50,14);
		whitekneeamptextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		gbc = new GridBagConstraints();
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)magcurveresultsborderpanel.getLayout()).setConstraints(whitekneeamptextfield, gbc);
		magcurveresultsborderpanel.add(whitekneeamptextfield);
		bluekneelabel = new java.awt.Label("Blue Kneepoint");
		bluekneelabel.setBounds(1,42,130,14);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)magcurveresultsborderpanel.getLayout()).setConstraints(bluekneelabel, gbc);
		magcurveresultsborderpanel.add(bluekneelabel);
		bluekneevolttextfield = new java.awt.TextField();
		bluekneevolttextfield.setBounds(131,42,50,14);
		bluekneevolttextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		gbc = new GridBagConstraints();
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)magcurveresultsborderpanel.getLayout()).setConstraints(bluekneevolttextfield, gbc);
		magcurveresultsborderpanel.add(bluekneevolttextfield);
		bluekneeamptextfield = new java.awt.TextField();
		bluekneeamptextfield.setBounds(181,42,50,14);
		bluekneeamptextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		gbc = new GridBagConstraints();
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0,0,0,0);
		((GridBagLayout)magcurveresultsborderpanel.getLayout()).setConstraints(bluekneeamptextfield, gbc);
		magcurveresultsborderpanel.add(bluekneeamptextfield);
		//}}

		//{{REGISTER_LISTENERS
		//}}
	}

	//{{DECLARE_CONTROLS
	symantec.itools.awt.BorderPanel magcurveresultsborderpanel;
	java.awt.Label mainkneelabel;
	java.awt.TextField mainkneevolttextfield;
	java.awt.TextField mainkneeamptextfield;
	java.awt.Label redkneelabel;
	java.awt.TextField redkneevolttextfield;
	java.awt.TextField redkneeamptextfield;
	java.awt.Label whitekneelabel;
	java.awt.TextField whitekneevolttextfield;
	java.awt.TextField whitekneeamptextfield;
	java.awt.Label bluekneelabel;
	java.awt.TextField bluekneevolttextfield;
	java.awt.TextField bluekneeamptextfield;
	//}}

}