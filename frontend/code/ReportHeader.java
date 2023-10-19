//**************************************************************
// Heading of the report. Display the name of the unit, date and 
// time when the test was conducted.
//**************************************************************

import java.awt.*;
import java.beans.*;
class ReportHeader extends java.awt.Panel 
{
	public ReportHeader()
	{
		super();
		//{{INIT_CONTROLS
		setLayout(new GridLayout(2,1,0,0));
		setSize(430,270);
		reportpanel1 = new java.awt.Panel();
		reportpanel1.setLayout(new GridLayout(1,1,0,0));
		reportpanel1.setBounds(0,0,568,157);
		add(reportpanel1);
		reportlabel1 = new java.awt.Label("PROMET - CT & VT Calibration Tester",Label.CENTER);
		reportlabel1.setBounds(0,0,568,157);
		reportlabel1.setFont(new Font("Dialog", Font.BOLD, 12));
		reportpanel1.add(reportlabel1);
		reportpanel2 = new java.awt.Panel();
		reportpanel2.setLayout(new GridLayout(1,6,0,0));
		reportpanel2.setBounds(0,157,568,157);
		add(reportpanel2);
		reportdatelabel = new java.awt.Label("Test Date:");
		reportdatelabel.setBounds(0,0,94,157);
		reportpanel2.add(reportdatelabel);
		reportdatetextfield = new java.awt.TextField();
		reportdatetextfield.setEditable(false);
		reportdatetextfield.setBounds(94,0,94,157);
		reportpanel2.add(reportdatetextfield);
		reportblanklabel1 = new java.awt.Label("");
		reportblanklabel1.setBounds(188,0,94,157);
		reportpanel2.add(reportblanklabel1);
		reportblanklabel2 = new java.awt.Label("");
		reportblanklabel2.setBounds(282,0,94,157);
		reportpanel2.add(reportblanklabel2);
		reporttimelabel = new java.awt.Label("Test Time:");
		reporttimelabel.setBounds(376,0,94,157);
		reportpanel2.add(reporttimelabel);
		reporttimetextfield = new java.awt.TextField();
		reporttimetextfield.setEditable(false);
		reporttimetextfield.setBounds(470,0,94,157);
		reportpanel2.add(reporttimetextfield);
		//}}

		//{{REGISTER_LISTENERS
		//}}
	}

	//{{DECLARE_CONTROLS
	java.awt.Panel reportpanel1;
	java.awt.Label reportlabel1;
	java.awt.Panel reportpanel2;
	java.awt.Label reportdatelabel;
	java.awt.TextField reportdatetextfield;
	java.awt.Label reportblanklabel1;
	java.awt.Label reportblanklabel2;
	java.awt.Label reporttimelabel;
	java.awt.TextField reporttimetextfield;
	//}}

}