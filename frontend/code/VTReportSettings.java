//**************************************************************
// Voltage Transformer settings to be displayed in the report.
//**************************************************************

import java.awt.*;
import java.beans.*;
import symantec.itools.awt.ComboBox;
class VTReportSettings extends java.awt.Panel 
{
	public VTReportSettings()
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
		vtmaketextfield = new java.awt.TextField();
		vtmaketextfield.setEditable(false);
		vtmaketextfield.setBounds(215,0,215,30);
		vtsettingpanel.add(vtmaketextfield);
		vttypelabel = new java.awt.Label("Type");
		vttypelabel.setBounds(0,30,215,30);
		vtsettingpanel.add(vttypelabel);
		vttypetextfield = new java.awt.TextField();
		vttypetextfield.setEditable(false);
		vttypetextfield.setBounds(215,30,215,30);
		vtsettingpanel.add(vttypetextfield);
		vtclasslabel = new java.awt.Label("Class");
		vtclasslabel.setBounds(0,60,215,30);
		vtsettingpanel.add(vtclasslabel);
		vtclasstextfield = new java.awt.TextField();
		vtclasstextfield.setEditable(false);
		vtclasstextfield.setBounds(215,60,215,30);
		vtsettingpanel.add(vtclasstextfield);
		vtphaselabel = new java.awt.Label("Phase");
		vtphaselabel.setBounds(0,90,215,30);
		vtsettingpanel.add(vtphaselabel);
		vtphasetextfield = new java.awt.TextField();
		vtphasetextfield.setEditable(false);
		vtphasetextfield.setBounds(215,90,215,30);
		vtsettingpanel.add(vtphasetextfield);
		vtratiolabel = new java.awt.Label("Ratio");
		vtratiolabel.setBounds(0,120,215,30);
		vtsettingpanel.add(vtratiolabel);
		vtratiopanel = new java.awt.Panel();
		vtratiopanel.setLayout(new GridLayout(1,2,0,0));
		vtratiopanel.setBounds(215,120,215,30);
		vtsettingpanel.add(vtratiopanel);
		vtratioprimetextfield = new java.awt.TextField();
		vtratioprimetextfield.setEditable(false);
		vtratioprimetextfield.setBounds(0,0,107,30);
		vtratiopanel.add(vtratioprimetextfield);
		vtratiosecondtextfield = new java.awt.TextField();
		vtratiosecondtextfield.setEditable(false);
		vtratiosecondtextfield.setBounds(107,0,107,30);
		vtratiopanel.add(vtratiosecondtextfield);
		vtdescriptionlabel = new java.awt.Label("Description");
		vtdescriptionlabel.setBounds(0,150,215,30);
		vtsettingpanel.add(vtdescriptionlabel);
		vtdescriptiontextfield = new java.awt.TextField();
		vtdescriptiontextfield.setEditable(false);
		vtdescriptiontextfield.setBounds(215,150,215,30);
		vtdescriptiontextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		vtsettingpanel.add(vtdescriptiontextfield);
		vtserialnumberlabel = new java.awt.Label("Serial no.");
		vtserialnumberlabel.setBounds(0,180,215,30);
		vtsettingpanel.add(vtserialnumberlabel);
		vtserialnumbertextfield = new java.awt.TextField();
		vtserialnumbertextfield.setEditable(false);
		vtserialnumbertextfield.setBounds(215,180,215,30);
		vtserialnumbertextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		vtsettingpanel.add(vtserialnumbertextfield);
		vtillabel = new java.awt.Label("IL");
		vtillabel.setBounds(0,210,215,30);
		vtsettingpanel.add(vtillabel);
		vtiltextfield = new java.awt.TextField();
		vtiltextfield.setEditable(false);
		vtiltextfield.setBounds(215,210,215,30);
		vtiltextfield.setFont(new Font("Dialog", Font.PLAIN, 12));
		vtsettingpanel.add(vtiltextfield);
		vtvaratinglabel = new java.awt.Label("VA Rating");
		vtvaratinglabel.setBounds(0,240,215,30);
		vtsettingpanel.add(vtvaratinglabel);
		vtvaratingtextfield = new java.awt.TextField();
		vtvaratingtextfield.setEditable(false);
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
	java.awt.TextField vtmaketextfield;
	java.awt.Label vttypelabel;
	java.awt.TextField vttypetextfield;
	java.awt.Label vtclasslabel;
	java.awt.TextField vtclasstextfield;
	java.awt.Label vtphaselabel;
	java.awt.TextField vtphasetextfield;
	java.awt.Label vtratiolabel;
	java.awt.Panel vtratiopanel;
	java.awt.TextField vtratioprimetextfield;
	java.awt.TextField vtratiosecondtextfield;
	java.awt.Label vtdescriptionlabel;
	java.awt.TextField vtdescriptiontextfield;
	java.awt.Label vtserialnumberlabel;
	java.awt.TextField vtserialnumbertextfield;
	java.awt.Label vtillabel;
	java.awt.TextField vtiltextfield;
	java.awt.Label vtvaratinglabel;
	java.awt.TextField vtvaratingtextfield;
	//}}

}