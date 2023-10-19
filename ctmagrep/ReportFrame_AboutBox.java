
/**
 * Title:        CT Mag Test Reporter<p>
 * Description:  <p>
 * Copyright:    Copyright (c) M.J.Colley<p>
 * Company:      Q Solutions<p>
 * @author M.J.Colley
 * @version 1.0
 */
package ctmagrep;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ReportFrame_AboutBox extends JDialog implements ActionListener
{
	JPanel aboutPanel = new JPanel();
	String appNameText = "Current Transformer Mag Test Reporter";
	String appVersionText = "Version 0.9";
	String appCopyText = "Copyright Q Solutions";
	String appCoderText = "M.J.C. 02/02/2000";
        JLabel appVersion = new JLabel();
        JLabel appName = new JLabel();
        JPanel aboutTextPanel = new JPanel();
        JLabel appCopy = new JLabel();
        JLabel appCoder = new JLabel();
        GridLayout textLayout = new GridLayout();
        JButton okButton = new JButton();
        BorderLayout aboutLayout = new BorderLayout();
        JButton jButton1 = new JButton();

	public ReportFrame_AboutBox(Frame parent)
	{
		super(parent);
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try { jbInit(); }
		catch (Exception e) { e.printStackTrace(); }
		pack();
	}

	private void jbInit() throws Exception
 	{
                this.setTitle("Version Information");
		setResizable(false);
                aboutPanel.setBackground(Color.white);
                aboutPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                aboutPanel.setLayout(aboutLayout);
                appVersion.setHorizontalAlignment(SwingConstants.CENTER);
                appVersion.setText(appVersionText);
                appName.setAlignmentY((float) 0.0);
                appName.setHorizontalAlignment(SwingConstants.CENTER);
                appName.setText(appNameText);
                aboutTextPanel.setLayout(textLayout);
                aboutTextPanel.setBackground(Color.white);
                appCopy.setHorizontalAlignment(SwingConstants.CENTER);
                appCopy.setText(appCopyText);
                textLayout.setRows(4);
                textLayout.setColumns(1);
                appCoder.setHorizontalAlignment(SwingConstants.CENTER);
                appCoder.setText(appCoderText);
                okButton.setText("Ok");
                okButton.addActionListener(this);
                jButton1.setFont(new java.awt.Font("SansSerif", 3, 50));
                jButton1.setText("ProMet");
                this.getContentPane().add(aboutPanel, null);
                aboutPanel.add(aboutTextPanel, BorderLayout.CENTER);
                aboutTextPanel.add(appName, null);
                aboutTextPanel.add(appVersion, null);
                aboutTextPanel.add(appCoder, null);
                aboutTextPanel.add(appCopy, null);
                aboutPanel.add(okButton, BorderLayout.SOUTH);
                aboutPanel.add(jButton1, BorderLayout.NORTH);
	}

	protected void processWindowEvent(WindowEvent e)
 	{
		if (e.getID() == WindowEvent.WINDOW_CLOSING)
  		{
			cancel();
		}
		super.processWindowEvent(e);
	}

	void cancel()
 	{
		dispose();
	}

	public void actionPerformed(ActionEvent e)
 	{
		if (e.getSource() == okButton)
  		{
			cancel();
		}
	}
}
