//************************************************************
// Panel containing the Current Transformer settings.
//************************************************************

import java.awt.*;
import java.beans.*;
import symantec.itools.awt.ComboBox;
class CTSettingsPanel extends java.awt.Panel  
{
	public CTSettingsPanel()
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
		ctmakecombobox = new symantec.itools.awt.ComboBox();
		try {
			java.lang.String[] tempString = new java.lang.String[6];
			tempString[0] = new java.lang.String("AEG");
			tempString[1] = new java.lang.String("A.T.W. Electrical");
			tempString[2] = new java.lang.String("Current  Electric");
			tempString[3] = new java.lang.String("GEC");
			tempString[4] = new java.lang.String("I.C.E.");
			tempString[5] = new java.lang.String("Reyrolle");
			ctmakecombobox.setListItems(tempString);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			ctmakecombobox.setEditable(true);
		}
		catch(java.beans.PropertyVetoException e) { }
		ctmakecombobox.setBounds(215,0,215,30);
		ctmakecombobox.setFont(new Font("Dialog", Font.PLAIN, 11));
		ctsettingpanel.add(ctmakecombobox);
		cttypelabel = new java.awt.Label("Type");
		cttypelabel.setBounds(0,30,215,30);
		ctsettingpanel.add(cttypelabel);
		cttypecombobox = new symantec.itools.awt.ComboBox();
		try {
			java.lang.String[] tempString = new java.lang.String[3];
			tempString[0] = new java.lang.String("3H/CT/937");
			tempString[1] = new java.lang.String("3H/CT/938");
			tempString[2] = new java.lang.String("3H/CT/996");
			cttypecombobox.setListItems(tempString);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			cttypecombobox.setEditable(true);
		}
		catch(java.beans.PropertyVetoException e) { }
		cttypecombobox.setBounds(215,30,215,30);
		cttypecombobox.setFont(new Font("Dialog", Font.PLAIN, 11));
		ctsettingpanel.add(cttypecombobox);
		ctclasslabel = new java.awt.Label("Class");
		ctclasslabel.setBounds(0,60,215,30);
		ctsettingpanel.add(ctclasslabel);
		ctclasscombobox = new symantec.itools.awt.ComboBox();
		try {
			java.lang.String[] tempString = new java.lang.String[11];
			tempString[0] = new java.lang.String("0.1");
			tempString[1] = new java.lang.String("0.5");
			tempString[2] = new java.lang.String("1.0");
			tempString[3] = new java.lang.String("1.5");
			tempString[4] = new java.lang.String("2.0");
			tempString[5] = new java.lang.String("2.5");
			tempString[6] = new java.lang.String("3.0");
			tempString[7] = new java.lang.String("10 P 10");
			tempString[8] = new java.lang.String("10 P 20");
			tempString[9] = new java.lang.String("20 P 20");
			tempString[10] = new java.lang.String("CLX");
			ctclasscombobox.setListItems(tempString);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			ctclasscombobox.setEditable(true);
		}
		catch(java.beans.PropertyVetoException e) { }
		ctclasscombobox.setBounds(215,60,215,30);
		ctclasscombobox.setFont(new Font("Dialog", Font.PLAIN, 11));
		ctsettingpanel.add(ctclasscombobox);
		ctapplicationlabel = new java.awt.Label("Application");
		ctapplicationlabel.setBounds(0,90,215,30);
		ctsettingpanel.add(ctapplicationlabel);
		ctapplicationcombobox = new symantec.itools.awt.ComboBox();
		try {
			java.lang.String[] tempString = new java.lang.String[10];
			tempString[0] = new java.lang.String("Bz Check");
			tempString[1] = new java.lang.String("Bz Main");
			tempString[2] = new java.lang.String("Diff");
			tempString[3] = new java.lang.String("Dist");
			tempString[4] = new java.lang.String("Ef");
			tempString[5] = new java.lang.String("Metering");
			tempString[6] = new java.lang.String("Oc");
			tempString[7] = new java.lang.String("Ocef");
			tempString[8] = new java.lang.String("Solkor");
			tempString[9] = new java.lang.String("Ref");
			ctapplicationcombobox.setListItems(tempString);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			ctapplicationcombobox.setEditable(true);
		}
		catch(java.beans.PropertyVetoException e) { }
		ctapplicationcombobox.setBounds(0,0,215,30);
		ctsettingpanel.add(ctapplicationcombobox);
		ctrednumberlabel = new java.awt.Label("RED serial no.");
		ctrednumberlabel.setBounds(0,150,215,30);
		ctsettingpanel.add(ctrednumberlabel);
		ctrednumbertextfield = new java.awt.TextField();
		ctrednumbertextfield.setBounds(215,150,215,30);
		ctrednumbertextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		ctsettingpanel.add(ctrednumbertextfield);
		ctwhitenumberlabel = new java.awt.Label("WHT Serial no.");
		ctwhitenumberlabel.setBounds(0,180,215,30);
		ctsettingpanel.add(ctwhitenumberlabel);
		ctwhitenumbertextfield = new java.awt.TextField();
		ctwhitenumbertextfield.setBounds(215,180,215,30);
		ctwhitenumbertextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		ctsettingpanel.add(ctwhitenumbertextfield);
		ctbluenumberlabel = new java.awt.Label("BLU Serial no.");
		ctbluenumberlabel.setBounds(0,210,215,30);
		ctsettingpanel.add(ctbluenumberlabel);
		ctbluenumbertextfield = new java.awt.TextField();
		ctbluenumbertextfield.setBounds(215,210,215,30);
		ctbluenumbertextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		ctsettingpanel.add(ctbluenumbertextfield);
		ctratiolabel = new java.awt.Label("Ratio");
		ctratiolabel.setBounds(0,120,215,30);
		ctsettingpanel.add(ctratiolabel);
		ctratiopanel = new java.awt.Panel();
		ctratiopanel.setLayout(new GridLayout(1,2,0,0));
		ctratiopanel.setBounds(215,120,215,30);
		ctsettingpanel.add(ctratiopanel);
		ctratioprimetextfield = new java.awt.TextField();
		ctratioprimetextfield.setBounds(0,0,107,30);
		ctratiopanel.add(ctratioprimetextfield);
		ctratiosecondtextfield = new java.awt.TextField();
		ctratiosecondtextfield.setBounds(107,0,107,30);
		ctratiopanel.add(ctratiosecondtextfield);
		ctvaratinglabel = new java.awt.Label("VA Rating");
		ctvaratinglabel.setBounds(0,240,215,30);
		ctsettingpanel.add(ctvaratinglabel);
		ctvaratingtextfield = new java.awt.TextField();
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
	symantec.itools.awt.ComboBox ctmakecombobox;
	java.awt.Label cttypelabel;
	symantec.itools.awt.ComboBox cttypecombobox;
	java.awt.Label ctclasslabel;
	symantec.itools.awt.ComboBox ctclasscombobox;
	java.awt.Label ctapplicationlabel;
	symantec.itools.awt.ComboBox ctapplicationcombobox;
	java.awt.Label ctrednumberlabel;
	java.awt.TextField ctrednumbertextfield;
	java.awt.Label ctwhitenumberlabel;
	java.awt.TextField ctwhitenumbertextfield;
	java.awt.Label ctbluenumberlabel;
	java.awt.TextField ctbluenumbertextfield;
	java.awt.Label ctratiolabel;
	java.awt.Panel ctratiopanel;
	java.awt.TextField ctratioprimetextfield;
	java.awt.TextField ctratiosecondtextfield;
	java.awt.Label ctvaratinglabel;
	java.awt.TextField ctvaratingtextfield;
	//}}

}