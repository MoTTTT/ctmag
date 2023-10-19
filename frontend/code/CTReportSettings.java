//**************************************************************
// Panel displaying the CT settings in the test report.
//**************************************************************

import java.awt.*;
import java.beans.*;
import symantec.itools.awt.ComboBox;
class CTReportSettings extends java.awt.Panel  
{
	public CTReportSettings()
	{
		super();
		//{{INIT_CONTROLS
		setLayout(new GridLayout(1,1,0,0));
		setSize(430,270);
		ctsettingpanel = new java.awt.Panel();
		ctsettingpanel.setLayout(new GridLayout(9,2,0,0));
		ctsettingpanel.setBounds(0,0,430,270);
		add(ctsettingpanel);
		ctmakelabel = new java.awt.Label("Make");
		ctmakelabel.setBounds(0,0,215,30);
		ctsettingpanel.add(ctmakelabel);
		ctmaketextfield = new java.awt.TextField();
		ctmaketextfield.setEditable(false);
		ctmaketextfield.setBounds(215,0,215,30);
		ctsettingpanel.add(ctmaketextfield);
		cttypelabel = new java.awt.Label("Type");
		cttypelabel.setBounds(0,30,215,30);
		ctsettingpanel.add(cttypelabel);
		cttypetextfield = new java.awt.TextField();
		cttypetextfield.setEditable(false);
		cttypetextfield.setBounds(215,30,215,30);
		ctsettingpanel.add(cttypetextfield);
		ctclasslabel = new java.awt.Label("Class");
		ctclasslabel.setBounds(0,60,215,30);
		ctsettingpanel.add(ctclasslabel);
		ctclasstextfield = new java.awt.TextField();
		ctclasstextfield.setEditable(false);
		ctclasstextfield.setBounds(215,60,215,30);
		ctsettingpanel.add(ctclasstextfield);
		ctapplicationlabel = new java.awt.Label("Application");
		ctapplicationlabel.setBounds(0,90,215,30);
		ctsettingpanel.add(ctapplicationlabel);
		ctapplicationtextfield = new java.awt.TextField();
		ctapplicationtextfield.setEditable(false);
		ctapplicationtextfield.setBounds(215,90,215,30);
		ctsettingpanel.add(ctapplicationtextfield);
		ctratiolabel = new java.awt.Label("Ratio");
		ctratiolabel.setBounds(0,120,215,30);
		ctsettingpanel.add(ctratiolabel);
		ctratiopanel = new java.awt.Panel();
		ctratiopanel.setLayout(new GridLayout(1,2,0,0));
		ctratiopanel.setBounds(215,120,215,30);
		ctsettingpanel.add(ctratiopanel);
		ctratioprimetextfield = new java.awt.TextField();
		ctratioprimetextfield.setEditable(false);
		ctratioprimetextfield.setBounds(0,0,107,30);
		ctratiopanel.add(ctratioprimetextfield);
		ctratiosecondtextfield = new java.awt.TextField();
		ctratiosecondtextfield.setEditable(false);
		ctratiosecondtextfield.setBounds(107,0,107,30);
		ctratiopanel.add(ctratiosecondtextfield);
		ctrednumberlabel = new java.awt.Label("RED serial no.");
		ctrednumberlabel.setBounds(0,150,215,30);
		ctsettingpanel.add(ctrednumberlabel);
		ctrednumbertextfield = new java.awt.TextField();
		ctrednumbertextfield.setEditable(false);
		ctrednumbertextfield.setBounds(215,150,215,30);
		ctrednumbertextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		ctsettingpanel.add(ctrednumbertextfield);
		ctwhitenumberlabel = new java.awt.Label("WHT Serial no.");
		ctwhitenumberlabel.setBounds(0,180,215,30);
		ctsettingpanel.add(ctwhitenumberlabel);
		ctwhitenumbertextfield = new java.awt.TextField();
		ctwhitenumbertextfield.setEditable(false);
		ctwhitenumbertextfield.setBounds(215,180,215,30);
		ctwhitenumbertextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		ctsettingpanel.add(ctwhitenumbertextfield);
		ctbluenumberlabel = new java.awt.Label("BLU Serial no.");
		ctbluenumberlabel.setBounds(0,210,215,30);
		ctsettingpanel.add(ctbluenumberlabel);
		ctbluenumbertextfield = new java.awt.TextField();
		ctbluenumbertextfield.setEditable(false);
		ctbluenumbertextfield.setBounds(215,210,215,30);
		ctbluenumbertextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		ctsettingpanel.add(ctbluenumbertextfield);
		ctvaratinglabel = new java.awt.Label("VA Rating");
		ctvaratinglabel.setBounds(0,240,215,30);
		ctvaratinglabel.setBackground(new Color(16777215));
		ctsettingpanel.add(ctvaratinglabel);
		ctvaratingtextfield = new java.awt.TextField();
		ctvaratingtextfield.setEditable(false);
		ctvaratingtextfield.setBounds(215,240,215,30);
		ctvaratingtextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		ctsettingpanel.add(ctvaratingtextfield);
		//}}

		//{{REGISTER_LISTENERS
		//}}
	}

	//{{DECLARE_CONTROLS
	java.awt.Panel ctsettingpanel;
	java.awt.Label ctmakelabel;
	java.awt.TextField ctmaketextfield;
	java.awt.Label cttypelabel;
	java.awt.TextField cttypetextfield;
	java.awt.Label ctclasslabel;
	java.awt.TextField ctclasstextfield;
	java.awt.Label ctapplicationlabel;
	java.awt.TextField ctapplicationtextfield;
	java.awt.Label ctratiolabel;
	java.awt.Panel ctratiopanel;
	java.awt.TextField ctratioprimetextfield;
	java.awt.TextField ctratiosecondtextfield;
	java.awt.Label ctrednumberlabel;
	java.awt.TextField ctrednumbertextfield;
	java.awt.Label ctwhitenumberlabel;
	java.awt.TextField ctwhitenumbertextfield;
	java.awt.Label ctbluenumberlabel;
	java.awt.TextField ctbluenumbertextfield;
	java.awt.Label ctvaratinglabel;
	java.awt.TextField ctvaratingtextfield;
	//}}

}