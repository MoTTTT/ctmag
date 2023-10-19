
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) M.J.Colley<p>
 * Company:      Q Solutions<p>
 * @author M.J.Colley
 * @version 1.0
 */
package ctgraphtest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ctmagrep.*;

public class Frame1 extends JFrame
{
        JPanel contentPane;
        JLabel statusBar = new JLabel();
        BorderLayout borderLayout1 = new BorderLayout();
        PrintPanel printPanel1 = new PrintPanel();
        CurveGraph curveGraph1 = new CurveGraph();
        JButton jButton1 = new JButton();
        JMenuBar jMenuBar1 = new JMenuBar();

        //Construct the frame
        public Frame1()
        {
                enableEvents(AWTEvent.WINDOW_EVENT_MASK);
                try
                {
                        jbInit();
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
        }

        //Component initialization
        private void jbInit() throws Exception
        {
                contentPane = (JPanel) this.getContentPane();
                contentPane.setLayout(borderLayout1);
                this.setJMenuBar(jMenuBar1);
                this.setSize(new Dimension(400, 300));
                this.setTitle("Frame Title");
                statusBar.setText(" ");
                jButton1.setText("Print");
                contentPane.add(statusBar, BorderLayout.SOUTH);
                contentPane.add(printPanel1, BorderLayout.CENTER);
                printPanel1.add(curveGraph1, BorderLayout.CENTER);
                contentPane.add(jButton1, BorderLayout.NORTH);
        }

        //Overridden so we can exit when window is closed
        protected void processWindowEvent(WindowEvent e)
        {
                super.processWindowEvent(e);
                if (e.getID() == WindowEvent.WINDOW_CLOSING)
                {
                        System.exit(0);
                }
        }
}