import java.awt.*;
import java.beans.*;
class ReportFooter extends java.awt.Panel 
{
	public ReportFooter()
	{
		super();
		//{{INIT_CONTROLS
		setLayout(new GridLayout(1,6,0,0));
		setSize(430,270);
		reportsignofflabel1 = new java.awt.Label("Sign Off:",Label.CENTER);
		reportsignofflabel1.setBounds(0,0,100,40);
		add(reportsignofflabel1);
		reportsignofflabel2 = new java.awt.Label("___________________");
		reportsignofflabel2.setBounds(0,0,100,40);
		add(reportsignofflabel2);
		reportfooterdatelabel1 = new java.awt.Label("Date:",Label.CENTER);
		reportfooterdatelabel1.setBounds(0,0,100,40);
		add(reportfooterdatelabel1);
		reportfooterdatelabel2 = new java.awt.Label("___________________");
		reportfooterdatelabel2.setBounds(0,0,100,40);
		add(reportfooterdatelabel2);
		label5 = new java.awt.Label("");
		label5.setBounds(0,0,100,40);
		add(label5);
		label6 = new java.awt.Label("");
		label6.setBounds(0,0,100,40);
		add(label6);
		//}}

		//{{REGISTER_LISTENERS
		//}}
	}

	//{{DECLARE_CONTROLS
	java.awt.Label reportsignofflabel1;
	java.awt.Label reportsignofflabel2;
	java.awt.Label reportfooterdatelabel1;
	java.awt.Label reportfooterdatelabel2;
	java.awt.Label label5;
	java.awt.Label label6;
	//}}

}