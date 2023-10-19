import java.awt.*;
import java.beans.*;
import symantec.itools.awt.ComboBox;
class VTSettingsPanel extends java.awt.Panel 
{
	public VTSettingsPanel()
	{
		super();
		//{{INIT_CONTROLS
		setLayout(new GridLayout(1,1,0,0));
		setSize(430,270);
		vtsettingpanel = new java.awt.Panel();
		vtsettingpanel.setLayout(new GridLayout(9,2,0,0));
		vtsettingpanel.setBounds(0,0,430,270);
		add(vtsettingpanel);
		vtmakelabel = new java.awt.Label("Make");
		vtmakelabel.setBounds(0,0,215,30);
		vtsettingpanel.add(vtmakelabel);
		vtmakecombobox = new symantec.itools.awt.ComboBox();
		try {
			java.lang.String[] tempString = new java.lang.String[5];
			tempString[0] = new java.lang.String("Aeg");
			tempString[1] = new java.lang.String("Ajax");
			tempString[2] = new java.lang.String("ETC");
			tempString[3] = new java.lang.String("Gec");
			tempString[4] = new java.lang.String("Reyrolle");
			vtmakecombobox.setListItems(tempString);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			vtmakecombobox.setEditable(true);
		}
		catch(java.beans.PropertyVetoException e) { }
		vtmakecombobox.setBounds(215,0,215,30);
		vtmakecombobox.setFont(new Font("Dialog", Font.PLAIN, 11));
		vtsettingpanel.add(vtmakecombobox);
		vttypelabel = new java.awt.Label("Type");
		vttypelabel.setBounds(0,30,215,30);
		vtsettingpanel.add(vttypelabel);
		vttypecombobox = new symantec.itools.awt.ComboBox();
		try {
			java.lang.String[] tempString = new java.lang.String[3];
			tempString[0] = new java.lang.String("15 RJC0");
			tempString[1] = new java.lang.String("15 TJBB");
			tempString[2] = new java.lang.String("15 TJCC LMT");
			vttypecombobox.setListItems(tempString);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			vttypecombobox.setEditable(true);
		}
		catch(java.beans.PropertyVetoException e) { }
		vttypecombobox.setBounds(215,30,215,30);
		vttypecombobox.setFont(new Font("Dialog", Font.PLAIN, 11));
		vtsettingpanel.add(vttypecombobox);
		vtclasslabel = new java.awt.Label("Class");
		vtclasslabel.setBounds(0,60,215,30);
		vtsettingpanel.add(vtclasslabel);
		vtclasscombobox = new symantec.itools.awt.ComboBox();
		try {
			java.lang.String[] tempString = new java.lang.String[5];
			tempString[0] = new java.lang.String("0.5");
			tempString[1] = new java.lang.String("1.0");
			tempString[2] = new java.lang.String("1.5");
			tempString[3] = new java.lang.String("2.0");
			tempString[4] = new java.lang.String("2.5");
			vtclasscombobox.setListItems(tempString);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			vtclasscombobox.setEditable(true);
		}
		catch(java.beans.PropertyVetoException e) { }
		vtclasscombobox.setBounds(215,60,215,30);
		vtclasscombobox.setFont(new Font("Dialog", Font.PLAIN, 11));
		vtsettingpanel.add(vtclasscombobox);
		vtphaselabel = new java.awt.Label("Phase");
		vtphaselabel.setBounds(0,90,215,30);
		vtsettingpanel.add(vtphaselabel);
		vtphasechoice = new java.awt.Choice();
		vtphasechoice.addItem("1-Phase 2-Wire");
		vtphasechoice.addItem("3-Phase 3-Wire");
		vtphasechoice.addItem("3-Phase 4-Wire");
		try {
			vtphasechoice.select(0);
		}
		catch (IllegalArgumentException e) { }
		vtphasechoice.setBounds(215,90,215,30);
		vtphasechoice.setFont(new Font("Dialog", Font.PLAIN, 11));
		vtsettingpanel.add(vtphasechoice);
		vtdescriptionlabel = new java.awt.Label("Description");
		vtdescriptionlabel.setBounds(0,150,215,30);
		vtsettingpanel.add(vtdescriptionlabel);
		vtdescriptiontextfield = new java.awt.TextField();
		vtdescriptiontextfield.setBounds(215,150,215,30);
		vtdescriptiontextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		vtsettingpanel.add(vtdescriptiontextfield);
		vtserialnumberlabel = new java.awt.Label("Serial no.");
		vtserialnumberlabel.setBounds(0,180,215,30);
		vtsettingpanel.add(vtserialnumberlabel);
		vtserialnumbertextfield = new java.awt.TextField();
		vtserialnumbertextfield.setBounds(215,180,215,30);
		vtserialnumbertextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		vtsettingpanel.add(vtserialnumbertextfield);
		vtillabel = new java.awt.Label("IL");
		vtillabel.setBounds(0,210,215,30);
		vtsettingpanel.add(vtillabel);
		vtiltextfield = new java.awt.TextField();
		vtiltextfield.setBounds(215,210,215,30);
		vtiltextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		vtsettingpanel.add(vtiltextfield);
		vtratiolabel = new java.awt.Label("Ratio");
		vtratiolabel.setBounds(0,120,215,30);
		vtsettingpanel.add(vtratiolabel);
		vtratiopanel = new java.awt.Panel();
		vtratiopanel.setLayout(new GridLayout(1,2,0,0));
		vtratiopanel.setBounds(215,120,215,30);
		vtsettingpanel.add(vtratiopanel);
		vtratioprimetextfield = new java.awt.TextField();
		vtratioprimetextfield.setBounds(0,0,107,30);
		vtratiopanel.add(vtratioprimetextfield);
		vtratiosecondtextfield = new java.awt.TextField();
		vtratiosecondtextfield.setBounds(107,0,107,30);
		vtratiopanel.add(vtratiosecondtextfield);
		vtvaratinglabel = new java.awt.Label("VA Rating");
		vtvaratinglabel.setBounds(0,240,215,30);
		vtsettingpanel.add(vtvaratinglabel);
		vtvaratingtextfield = new java.awt.TextField();
		vtvaratingtextfield.setBounds(215,240,215,30);
		vtvaratingtextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		vtsettingpanel.add(vtvaratingtextfield);
		//}}

		//{{REGISTER_LISTENERS
		//}}
	}

	//{{DECLARE_CONTROLS
	java.awt.Panel vtsettingpanel;
	java.awt.Label vtmakelabel;
	symantec.itools.awt.ComboBox vtmakecombobox;
	java.awt.Label vttypelabel;
	symantec.itools.awt.ComboBox vttypecombobox;
	java.awt.Label vtclasslabel;
	symantec.itools.awt.ComboBox vtclasscombobox;
	java.awt.Label vtphaselabel;
	java.awt.Choice vtphasechoice;
	java.awt.Label vtdescriptionlabel;
	java.awt.TextField vtdescriptiontextfield;
	java.awt.Label vtserialnumberlabel;
	java.awt.TextField vtserialnumbertextfield;
	java.awt.Label vtillabel;
	java.awt.TextField vtiltextfield;
	java.awt.Label vtratiolabel;
	java.awt.Panel vtratiopanel;
	java.awt.TextField vtratioprimetextfield;
	java.awt.TextField vtratiosecondtextfield;
	java.awt.Label vtvaratinglabel;
	java.awt.TextField vtvaratingtextfield;
	//}}

}