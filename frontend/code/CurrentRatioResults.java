import java.awt.*;
import java.beans.*;
import symantec.itools.awt.BorderPanel;
class CurrentRatioResults extends java.awt.Panel 
{
	public CurrentRatioResults()
	{
		super();
		//{{INIT_CONTROLS
		setLayout(new GridLayout(1,1,0,0));
		setSize(430,270);
		currentratioborderpanel = new symantec.itools.awt.BorderPanel();
		try {
			currentratioborderpanel.setIPadBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			currentratioborderpanel.setLabel("Current Ratio Results");
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			currentratioborderpanel.setIPadSides(2);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			currentratioborderpanel.setPaddingBottom(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			currentratioborderpanel.setPaddingTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		try {
			currentratioborderpanel.setIPadTop(0);
		}
		catch(java.beans.PropertyVetoException e) { }
		currentratioborderpanel.setLayout(new GridLayout(4,5,0,0));
		currentratioborderpanel.setBounds(0,0,430,270);
		add(currentratioborderpanel);
		blankampratiolabel = new java.awt.Label("");
		blankampratiolabel.setBounds(0,0,82,63);
		currentratioborderpanel.add(blankampratiolabel);
		trueampratiolabel = new java.awt.Label("Expect");
		trueampratiolabel.setBounds(82,0,82,63);
		currentratioborderpanel.add(trueampratiolabel);
		errampratiolabel = new java.awt.Label("Error");
		errampratiolabel.setBounds(164,0,82,63);
		currentratioborderpanel.add(errampratiolabel);
		resultampratiolabel = new java.awt.Label("Result");
		resultampratiolabel.setBounds(246,0,82,63);
		currentratioborderpanel.add(resultampratiolabel);
		polampratiolabel = new java.awt.Label("Polarity");
		polampratiolabel.setBounds(328,0,82,63);
		currentratioborderpanel.add(polampratiolabel);
		redampratiolabel = new java.awt.Label("Red");
		redampratiolabel.setBounds(0,63,82,63);
		currentratioborderpanel.add(redampratiolabel);
		redamptrueratiotextfield = new java.awt.TextField();
		redamptrueratiotextfield.setBounds(82,63,82,63);
		redamptrueratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(redamptrueratiotextfield);
		redamperrratiotextfield = new java.awt.TextField();
		redamperrratiotextfield.setBounds(164,63,82,63);
		redamperrratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(redamperrratiotextfield);
		redampresultratiotextfield = new java.awt.TextField();
		redampresultratiotextfield.setBounds(246,63,82,63);
		redampresultratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(redampresultratiotextfield);
		redamppolratiotextfield = new java.awt.TextField();
		redamppolratiotextfield.setBounds(328,63,82,63);
		redamppolratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(redamppolratiotextfield);
		whiteampratiolabel = new java.awt.Label("White");
		whiteampratiolabel.setBounds(0,126,82,63);
		currentratioborderpanel.add(whiteampratiolabel);
		whiteamptrueratiotextfield = new java.awt.TextField();
		whiteamptrueratiotextfield.setBounds(82,126,82,63);
		whiteamptrueratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(whiteamptrueratiotextfield);
		whiteamperrratiotextfield = new java.awt.TextField();
		whiteamperrratiotextfield.setBounds(164,126,82,63);
		whiteamperrratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(whiteamperrratiotextfield);
		whiteampresultratiotextfield = new java.awt.TextField();
		whiteampresultratiotextfield.setBounds(246,126,82,63);
		whiteampresultratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(whiteampresultratiotextfield);
		whiteamppolratiotextfield = new java.awt.TextField();
		whiteamppolratiotextfield.setBounds(328,126,82,63);
		whiteamppolratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(whiteamppolratiotextfield);
		blueampratiolabel = new java.awt.Label("Blue");
		blueampratiolabel.setBounds(0,189,82,63);
		currentratioborderpanel.add(blueampratiolabel);
		blueamptrueratiotextfield = new java.awt.TextField();
		blueamptrueratiotextfield.setBounds(82,189,82,63);
		blueamptrueratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(blueamptrueratiotextfield);
		blueamperrratiotextfield = new java.awt.TextField();
		blueamperrratiotextfield.setBounds(164,189,82,63);
		blueamperrratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(blueamperrratiotextfield);
		blueampresultratiotextfield = new java.awt.TextField();
		blueampresultratiotextfield.setBounds(246,189,82,63);
		blueampresultratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(blueampresultratiotextfield);
		blueamppolratiotextfield = new java.awt.TextField();
		blueamppolratiotextfield.setBounds(328,189,82,63);
		blueamppolratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(blueamppolratiotextfield);
		//}}

		//{{REGISTER_LISTENERS
		//}}
	}

	//{{DECLARE_CONTROLS
	symantec.itools.awt.BorderPanel currentratioborderpanel;
	java.awt.Label blankampratiolabel;
	java.awt.Label trueampratiolabel;
	java.awt.Label errampratiolabel;
	java.awt.Label resultampratiolabel;
	java.awt.Label polampratiolabel;
	java.awt.Label redampratiolabel;
	java.awt.TextField redamptrueratiotextfield;
	java.awt.TextField redamperrratiotextfield;
	java.awt.TextField redampresultratiotextfield;
	java.awt.TextField redamppolratiotextfield;
	java.awt.Label whiteampratiolabel;
	java.awt.TextField whiteamptrueratiotextfield;
	java.awt.TextField whiteamperrratiotextfield;
	java.awt.TextField whiteampresultratiotextfield;
	java.awt.TextField whiteamppolratiotextfield;
	java.awt.Label blueampratiolabel;
	java.awt.TextField blueamptrueratiotextfield;
	java.awt.TextField blueamperrratiotextfield;
	java.awt.TextField blueampresultratiotextfield;
	java.awt.TextField blueamppolratiotextfield;
	//}}

}