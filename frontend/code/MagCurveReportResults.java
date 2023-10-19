//**************************************************************
// Panel to display the results for the magnetisation curve tests
// in the test report.
//**************************************************************
 
import java.awt.*;
import java.beans.*;
import symantec.itools.awt.BorderPanel;
public class MagCurveReportResults extends java.awt.Panel 
{
	public MagCurveReportResults()
	{
		super();
		//{{INIT_CONTROLS
		setLayout(new GridLayout(1,1,0,0));
		setSize(430,270);
		magcurveresultsborderpanel = new symantec.itools.awt.BorderPanel();
		try {
			magcurveresultsborderpanel.setLabel("Mag Curve Kneepoints");
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
			magcurveresultsborderpanel.setIPadTop(7);
		}
		catch(java.beans.PropertyVetoException e) { }
		magcurveresultsborderpanel.setLayout(new GridLayout(3,3,0,0));
		magcurveresultsborderpanel.setBounds(0,0,249,72);
		add(magcurveresultsborderpanel);
		redkneelabel = new java.awt.Label("Red ");
		redkneelabel.setBounds(1,14,130,14);
		magcurveresultsborderpanel.add(redkneelabel);
		redkneevolttextfield = new java.awt.TextField();
		redkneevolttextfield.setBounds(131,14,50,14);
		redkneevolttextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		magcurveresultsborderpanel.add(redkneevolttextfield);
		redkneeamptextfield = new java.awt.TextField();
		redkneeamptextfield.setBounds(181,14,50,14);
		redkneeamptextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		magcurveresultsborderpanel.add(redkneeamptextfield);
		whitekneelabel = new java.awt.Label("White ");
		whitekneelabel.setBounds(1,28,130,14);
		magcurveresultsborderpanel.add(whitekneelabel);
		whitekneevolttextfield = new java.awt.TextField();
		whitekneevolttextfield.setBounds(131,28,50,14);
		whitekneevolttextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		magcurveresultsborderpanel.add(whitekneevolttextfield);
		whitekneeamptextfield = new java.awt.TextField();
		whitekneeamptextfield.setBounds(181,28,50,14);
		whitekneeamptextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		magcurveresultsborderpanel.add(whitekneeamptextfield);
		bluekneelabel = new java.awt.Label("Blue ");
		bluekneelabel.setBounds(1,42,130,14);
		magcurveresultsborderpanel.add(bluekneelabel);
		bluekneevolttextfield = new java.awt.TextField();
		bluekneevolttextfield.setBounds(131,42,50,14);
		bluekneevolttextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		magcurveresultsborderpanel.add(bluekneevolttextfield);
		bluekneeamptextfield = new java.awt.TextField();
		bluekneeamptextfield.setBounds(181,42,50,14);
		bluekneeamptextfield.setFont(new Font("Dialog", Font.PLAIN, 10));
		magcurveresultsborderpanel.add(bluekneeamptextfield);
		//}}

		//{{REGISTER_LISTENERS
		//}}
	}

	//{{DECLARE_CONTROLS
	symantec.itools.awt.BorderPanel magcurveresultsborderpanel;
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