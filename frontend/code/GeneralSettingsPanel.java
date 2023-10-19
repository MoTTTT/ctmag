import java.awt.*;
import java.beans.*;
class GeneralSettingsPanel extends java.awt.Panel 
{
	public GeneralSettingsPanel()
	{
		super();
		//{{INIT_CONTROLS
		setLayout(new GridLayout(1,1,0,0));
		setSize(430,270);
		generalsettingspanel = new java.awt.Panel();
		generalsettingspanel.setLayout(new GridLayout(5,2,0,0));
		generalsettingspanel.setBounds(0,0,430,270);
		generalsettingspanel.setFont(new Font("Dialog", Font.PLAIN, 11));
		generalsettingspanel.setBackground(new Color(12632256));
		add(generalsettingspanel);
		testnamelabel = new java.awt.Label("File Name");
		testnamelabel.setBounds(0,0,430,54);
		testnamelabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		generalsettingspanel.add(testnamelabel);
		testnametextfield = new java.awt.TextField();
		testnametextfield.setEditable(false);
		testnametextfield.setBounds(215,0,430,54);
		testnametextfield.setFont(new Font("Dialog", Font.PLAIN, 11));
		testnametextfield.setBackground(new Color(16777215));
		generalsettingspanel.add(testnametextfield);
		testerlabel = new java.awt.Label("Recorded by");
		testerlabel.setBounds(0,54,215,54);
		testerlabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		generalsettingspanel.add(testerlabel);
		testertextfield = new java.awt.TextField();
		testertextfield.setBounds(215,54,215,54);
		testertextfield.setFont(new Font("Dialog", Font.PLAIN, 11));
		generalsettingspanel.add(testertextfield);
		supplyauthoritylabel = new java.awt.Label("Supply Authority");
		supplyauthoritylabel.setBounds(0,108,215,54);
		supplyauthoritylabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		generalsettingspanel.add(supplyauthoritylabel);
		supplyauthoritytextfield = new java.awt.TextField();
		supplyauthoritytextfield.setBounds(215,108,215,54);
		supplyauthoritytextfield.setFont(new Font("Dialog", Font.PLAIN, 11));
		generalsettingspanel.add(supplyauthoritytextfield);
		substationlabel = new java.awt.Label("Substation");
		substationlabel.setBounds(0,162,215,54);
		substationlabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		generalsettingspanel.add(substationlabel);
		substationtextfield = new java.awt.TextField();
		substationtextfield.setBounds(215,162,215,54);
		substationtextfield.setFont(new Font("Dialog", Font.PLAIN, 11));
		generalsettingspanel.add(substationtextfield);
		feederlabel = new java.awt.Label("Feeder");
		feederlabel.setBounds(0,216,215,54);
		feederlabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		generalsettingspanel.add(feederlabel);
		feedertextfield = new java.awt.TextField();
		feedertextfield.setBounds(215,216,215,54);
		feedertextfield.setFont(new Font("Dialog", Font.PLAIN, 11));
		generalsettingspanel.add(feedertextfield);
		//}}

		//{{REGISTER_LISTENERS
		//}}
	}

	//{{DECLARE_CONTROLS
	java.awt.Panel generalsettingspanel;
	java.awt.Label testnamelabel;
	java.awt.TextField testnametextfield;
	java.awt.Label testerlabel;
	java.awt.TextField testertextfield;
	java.awt.Label supplyauthoritylabel;
	java.awt.TextField supplyauthoritytextfield;
	java.awt.Label substationlabel;
	java.awt.TextField substationtextfield;
	java.awt.Label feederlabel;
	java.awt.TextField feedertextfield;
	//}}

}