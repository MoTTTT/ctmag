
/**
 * Title:        Graphics Printing<p>
 * Description:  graphics printing code tests<p>
 * Copyright:    Copyright (c) M.J.Colley<p>
 * Company:      Q Solutions<p>
 * @author M.J.Colley
 * @version 1.0
 */
package graphprint;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphPrintFrame extends JFrame
{
        JPanel contentPane;
        JMenuBar menuBar1 = new JMenuBar();
        JMenu menuFile = new JMenu();
        JMenuItem menuFileExit = new JMenuItem();
        JMenu menuHelp = new JMenu();
        JMenuItem menuHelpAbout = new JMenuItem();
        JToolBar toolBar = new JToolBar();
        JButton jButton1 = new JButton();
        JButton jButton2 = new JButton();
        JButton jButton3 = new JButton();
        ImageIcon image1;
        ImageIcon image2;
        ImageIcon image3;
        JLabel statusBar = new JLabel();
        BorderLayout borderLayout1 = new BorderLayout();
        JPanel jPanel1 = new JPanel();
        GraphPrintCanvas canvas1 = new GraphPrintCanvas();
        BorderLayout borderLayout2 = new BorderLayout();

        //Construct the frame
        public GraphPrintFrame()
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
                image1 = new ImageIcon(GraphPrintFrame.class.getResource("openFile.gif"));
                image2 = new ImageIcon(GraphPrintFrame.class.getResource("closeFile.gif"));
                image3 = new ImageIcon(GraphPrintFrame.class.getResource("help.gif"));
                contentPane = (JPanel) this.getContentPane();
                contentPane.setLayout(borderLayout1);
                this.setSize(new Dimension(400, 300));
                this.setTitle("Graph Print");
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
                jButton1.setIcon(image1);
                jButton1.setToolTipText("Open File");
                jButton2.setIcon(image2);
                jButton2.setToolTipText("Close File");
                jButton3.setIcon(image3);
                jButton3.setToolTipText("Help");
                jPanel1.setLayout(borderLayout2);
                menuFile.add(menuFileExit);
                menuHelp.add(menuHelpAbout);
                menuBar1.add(menuFile);
                menuBar1.add(menuHelp);
                this.setJMenuBar(menuBar1);
                contentPane.add(statusBar, BorderLayout.SOUTH);
                contentPane.add(toolBar, BorderLayout.NORTH);
                toolBar.add(jButton1);
                toolBar.add(jButton2);
                toolBar.add(jButton3);
                contentPane.add(jPanel1, BorderLayout.CENTER);
                jPanel1.add(canvas1, BorderLayout.CENTER);
        }

        //File | Exit action performed
        public void fileExit_actionPerformed(ActionEvent e)
        {
                System.exit(0);
        }

        //Help | About action performed
        public void helpAbout_actionPerformed(ActionEvent e)
        {
                GraphPrintFrame_AboutBox dlg = new GraphPrintFrame_AboutBox(this);
                Dimension dlgSize = dlg.getPreferredSize();
                Dimension frmSize = getSize();
                Point loc = getLocation();
                dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
                dlg.setModal(true);
                dlg.show();
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
}