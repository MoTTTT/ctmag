import java.awt.*;
import java.beans.*;
import symantec.itools.awt.BorderPanel;
class VoltageRatioResults extends java.awt.Panel 
{
	public VoltageRatioResults()
	{
		super();
		//{{INIT_CONTROLS
		setLayout(new GridLayout(1,1,0,0));
		setSize(430,270);
		voltageratioborderpanel = new symantec.itools.awt.BorderPanel();
		try {
			voltageratioborderpanel.setIPadBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			voltageratioborderpanel.setLabel("Voltage Ratio Results");
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			voltageratioborderpanel.setIPadSides(2);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			voltageratioborderpanel.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			voltageratioborderpanel.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			voltageratioborderpanel.setIPadTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		voltageratioborderpanel.setLayout(new GridLayout(4,5,0,0));
		voltageratioborderpanel.setBounds(0,0,249,72);
		add(voltageratioborderpanel);
		blankvoltratiolabel = new java.awt.Label("");
		blankvoltratiolabel.setBounds(0,0,46,14);
		voltageratioborderpanel.add(blankvoltratiolabel);
		truevoltratiolabel = new java.awt.Label("Expect");
		truevoltratiolabel.setBounds(46,0,46,14);
		voltageratioborderpanel.add(truevoltratiolabel);
		errvoltratiolabel = new java.awt.Label("Error");
		errvoltratiolabel.setBounds(92,0,46,14);
		voltageratioborderpanel.add(errvoltratiolabel);
		resultvoltratiolabel = new java.awt.Label("Result");
		resultvoltratiolabel.setBounds(138,0,46,14);
		voltageratioborderpanel.add(resultvoltratiolabel);
		polvoltratiolabel = new java.awt.Label("Polarity");
		polvoltratiolabel.setBounds(184,0,46,14);
		voltageratioborderpanel.add(polvoltratiolabel);
		redvoltratiolabel = new java.awt.Label("Red");
		redvoltratiolabel.setBounds(0,14,46,14);
		voltageratioborderpanel.add(redvoltratiolabel);
		redvolttrueratiotextfield = new java.awt.TextField();
		redvolttrueratiotextfield.setBounds(46,14,46,14);
		redvolttrueratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		voltageratioborderpanel.add(redvolttrueratiotextfield);
		redvolterrratiotextfield = new java.awt.TextField();
		redvolterrratiotextfield.setBounds(92,14,46,14);
		redvolterrratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		voltageratioborderpanel.add(redvolterrratiotextfield);
		redvoltresultratiotextfield = new java.awt.TextField();
		redvoltresultratiotextfield.setBounds(138,14,46,14);
		redvoltresultratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		voltageratioborderpanel.add(redvoltresultratiotextfield);
		redvoltpolratiotextfield = new java.awt.TextField();
		redvoltpolratiotextfield.setBounds(184,14,46,14);
		redvoltpolratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		voltageratioborderpanel.add(redvoltpolratiotextfield);
		whiotevoltratiolabel = new java.awt.Label("White");
		whiotevoltratiolabel.setBounds(0,28,46,14);
		voltageratioborderpanel.add(whiotevoltratiolabel);
		whitevolttrueratiotextfield = new java.awt.TextField();
		whitevolttrueratiotextfield.setBounds(46,28,46,14);
		whitevolttrueratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		voltageratioborderpanel.add(whitevolttrueratiotextfield);
		whitevolterrratiotextfield = new java.awt.TextField();
		whitevolterrratiotextfield.setBounds(92,28,46,14);
		whitevolterrratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		voltageratioborderpanel.add(whitevolterrratiotextfield);
		whitevoltresultratiotextfield = new java.awt.TextField();
		whitevoltresultratiotextfield.setBounds(138,28,46,14);
		whitevoltresultratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		voltageratioborderpanel.add(whitevoltresultratiotextfield);
		whitevoltpolratiotextfield = new java.awt.TextField();
		whitevoltpolratiotextfield.setBounds(184,28,46,14);
		whitevoltpolratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		voltageratioborderpanel.add(whitevoltpolratiotextfield);
		bluevoltratiolabel = new java.awt.Label("Blue");
		bluevoltratiolabel.setBounds(0,42,46,14);
		voltageratioborderpanel.add(bluevoltratiolabel);
		bluevolttrueratiotextfield = new java.awt.TextField();
		bluevolttrueratiotextfield.setBounds(46,42,46,14);
		bluevolttrueratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		voltageratioborderpanel.add(bluevolttrueratiotextfield);
		bluevolterrratiotextfield = new java.awt.TextField();
		bluevolterrratiotextfield.setBounds(92,42,46,14);
		bluevolterrratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		voltageratioborderpanel.add(bluevolterrratiotextfield);
		bluevoltresultratiotextfield = new java.awt.TextField();
		bluevoltresultratiotextfield.setBounds(138,42,46,14);
		bluevoltresultratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		voltageratioborderpanel.add(bluevoltresultratiotextfield);
		bluevoltpolratiotextfield = new java.awt.TextField();
		bluevoltpolratiotextfield.setBounds(184,42,46,14);
		bluevoltpolratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		voltageratioborderpanel.add(bluevoltpolratiotextfield);
		//}}

		//{{REGISTER_LISTENERS
		//}}
	}

	//{{DECLARE_CONTROLS
	symantec.itools.awt.BorderPanel voltageratioborderpanel;
	java.awt.Label blankvoltratiolabel;
	java.awt.Label truevoltratiolabel;
	java.awt.Label errvoltratiolabel;
	java.awt.Label resultvoltratiolabel;
	java.awt.Label polvoltratiolabel;
	java.awt.Label redvoltratiolabel;
	java.awt.TextField redvolttrueratiotextfield;
	java.awt.TextField redvolterrratiotextfield;
	java.awt.TextField redvoltresultratiotextfield;
	java.awt.TextField redvoltpolratiotextfield;
	java.awt.Label whiotevoltratiolabel;
	java.awt.TextField whitevolttrueratiotextfield;
	java.awt.TextField whitevolterrratiotextfield;
	java.awt.TextField whitevoltresultratiotextfield;
	java.awt.TextField whitevoltpolratiotextfield;
	java.awt.Label bluevoltratiolabel;
	java.awt.TextField bluevolttrueratiotextfield;
	java.awt.TextField bluevolterrratiotextfield;
	java.awt.TextField bluevoltresultratiotextfield;
	java.awt.TextField bluevoltpolratiotextfield;
	//}}

}