import java.awt.*;
import java.beans.*;
import symantec.itools.awt.BorderPanel;
public class CurrentRatioReportResults extends java.awt.Panel
{
	public CurrentRatioReportResults()
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
		currentratioborderpanel.setLayout(new GridLayout(4,4,0,0));
		currentratioborderpanel.setBounds(0,0,299,426);
		add(currentratioborderpanel);
		blankampratiolabel = new java.awt.Label("");
		blankampratiolabel.setBounds(0,0,70,102);
		currentratioborderpanel.add(blankampratiolabel);
		errampratiolabel = new java.awt.Label("%Error");
		errampratiolabel.setBounds(70,0,70,102);
		currentratioborderpanel.add(errampratiolabel);
		resultampratiolabel = new java.awt.Label("Result");
		resultampratiolabel.setBounds(140,0,70,102);
		currentratioborderpanel.add(resultampratiolabel);
		polampratiolabel = new java.awt.Label("Polarity");
		polampratiolabel.setBounds(210,0,70,102);
		currentratioborderpanel.add(polampratiolabel);
		redampratiolabel = new java.awt.Label("Red");
		redampratiolabel.setBounds(0,102,70,102);
		currentratioborderpanel.add(redampratiolabel);
		redamperrratiotextfield = new java.awt.TextField();
		redamperrratiotextfield.setBounds(70,102,70,102);
		redamperrratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(redamperrratiotextfield);
		redampresultratiotextfield = new java.awt.TextField();
		redampresultratiotextfield.setBounds(140,102,70,102);
		redampresultratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(redampresultratiotextfield);
		redamppolratiotextfield = new java.awt.TextField();
		redamppolratiotextfield.setBounds(210,102,70,102);
		redamppolratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(redamppolratiotextfield);
		whiteampratiolabel = new java.awt.Label("White");
		whiteampratiolabel.setBounds(0,204,70,102);
		currentratioborderpanel.add(whiteampratiolabel);
		whiteamperrratiotextfield = new java.awt.TextField();
		whiteamperrratiotextfield.setBounds(70,204,70,102);
		whiteamperrratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(whiteamperrratiotextfield);
		whiteampresultratiotextfield = new java.awt.TextField();
		whiteampresultratiotextfield.setBounds(140,204,70,102);
		whiteampresultratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(whiteampresultratiotextfield);
		whiteamppolratiotextfield = new java.awt.TextField();
		whiteamppolratiotextfield.setBounds(210,204,70,102);
		whiteamppolratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(whiteamppolratiotextfield);
		blueampratiolabel = new java.awt.Label("Blue");
		blueampratiolabel.setBounds(0,306,70,102);
		currentratioborderpanel.add(blueampratiolabel);
		blueamperrratiotextfield = new java.awt.TextField();
		blueamperrratiotextfield.setBounds(70,306,70,102);
		blueamperrratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(blueamperrratiotextfield);
		blueampresultratiotextfield = new java.awt.TextField();
		blueampresultratiotextfield.setBounds(140,306,70,102);
		blueampresultratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(blueampresultratiotextfield);
		blueamppolratiotextfield = new java.awt.TextField();
		blueamppolratiotextfield.setBounds(210,306,70,102);
		blueamppolratiotextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		currentratioborderpanel.add(blueamppolratiotextfield);
		//}}

		//{{REGISTER_LISTENERS
		//}}
	}

	//{{DECLARE_CONTROLS
	symantec.itools.awt.BorderPanel currentratioborderpanel;
	java.awt.Label blankampratiolabel;
	java.awt.Label errampratiolabel;
	java.awt.Label resultampratiolabel;
	java.awt.Label polampratiolabel;
	java.awt.Label redampratiolabel;
	java.awt.TextField redamperrratiotextfield;
	java.awt.TextField redampresultratiotextfield;
	java.awt.TextField redamppolratiotextfield;
	java.awt.Label whiteampratiolabel;
	java.awt.TextField whiteamperrratiotextfield;
	java.awt.TextField whiteampresultratiotextfield;
	java.awt.TextField whiteamppolratiotextfield;
	java.awt.Label blueampratiolabel;
	java.awt.TextField blueamperrratiotextfield;
	java.awt.TextField blueampresultratiotextfield;
	java.awt.TextField blueamppolratiotextfield;
	//}}

}