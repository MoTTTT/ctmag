
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

public class Frame2 extends JFrame
{
        JPanel contentPane;
        JMenuBar menuBar1 = new JMenuBar();
        JMenu menuFile = new JMenu();
        JMenuItem menuFileExit = new JMenuItem();
        JMenu menuHelp = new JMenu();
        JMenuItem menuHelpAbout = new JMenuItem();
        JToolBar toolBar = new JToolBar();
        JButton jButton3 = new JButton();
        ImageIcon image1;
        ImageIcon image2;
        ImageIcon image3;
        JLabel statusBar = new JLabel();
        BorderLayout borderLayout1 = new BorderLayout();
        PrintPanel printPanel1 = new PrintPanel();
        CurveGraph curveGraph1 = new CurveGraph();

        //Construct the frame
        public Frame2()
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
                image1 = new ImageIcon(Frame2.class.getResource("openFile.gif"));
                image2 = new ImageIcon(Frame2.class.getResource("closeFile.gif"));
                image3 = new ImageIcon(Frame2.class.getResource("help.gif"));
                contentPane = (JPanel) this.getContentPane();
                contentPane.setLayout(borderLayout1);
                this.getContentPane().setBackground(Color.white);
                this.setSize(new Dimension(400, 300));
                this.setTitle("Frame Title");
                statusBar.setText(" ");
                menuFile.setText("File");
                menuFileExit.setText("Exit");
                menuFileExit.addActionListener(new ActionListener()
                {

                        public void actionPerformed(ActionEvent e)
                        {
                                fileExit_actionPerformed(e);
                        }
                });
                menuHelp.setText("Help");
                menuHelpAbout.setText("About");
                menuHelpAbout.addActionListener(new ActionListener()
                {

                        public void actionPerformed(ActionEvent e)
                        {
                                helpAbout_actionPerformed(e);
                        }
                });
                jButton3.setIcon(image3);
                jButton3.addActionListener(new java.awt.event.ActionListener()
                {

                        public void actionPerformed(ActionEvent e)
                        {
                                jButton3_actionPerformed(e);
                        }
                });
                jButton3.setToolTipText("Help");
                toolBar.add(jButton3);
                menuFile.add(menuFileExit);
                menuHelp.add(menuHelpAbout);
                menuBar1.add(menuFile);
                menuBar1.add(menuHelp);
                this.setJMenuBar(menuBar1);
                contentPane.add(toolBar, BorderLayout.NORTH);
                contentPane.add(statusBar, BorderLayout.SOUTH);
                contentPane.add(printPanel1, BorderLayout.CENTER);
                printPanel1.add(curveGraph1, BorderLayout.CENTER);
        }

        //File | Exit action performed
        public void fileExit_actionPerformed(ActionEvent e)
        {
                System.exit(0);
        }

        //Help | About action performed
        public void helpAbout_actionPerformed(ActionEvent e)
        {
        }

        //Overridden so we can exit when window is closed
        protected void processWindowEvent(WindowEvent e)
        {
                super.processWindowEvent(e);
                if (e.getID() == WindowEvent.WINDOW_CLOSING)
                {
                        fileExit_actionPerformed(null);
                }
        }

        void jButton3_actionPerformed(ActionEvent e)
        {
		printPanel1.printTest();
        }
}