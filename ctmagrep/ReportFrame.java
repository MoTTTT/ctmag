
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

public class ReportFrame extends JFrame {
  ReportEngine engine = new ReportEngine();
  PrintPanel reportPanel = new PrintPanel();
  CurveGraph mcgraph = new CurveGraph();

  JMenuBar menuBar1 = new JMenuBar();
  JMenu menuFile = new JMenu();
  JMenu menuHelp = new JMenu();
  JMenuItem menuFileExit = new JMenuItem();
  JMenuItem menuHelpAbout = new JMenuItem();
  JToolBar toolBar = new JToolBar();
  JPanel contentPane;
  JPanel header = new JPanel();
  JPanel dateTime = new JPanel();
  JPanel settings = new JPanel();
  JPanel data = new JPanel();
  JPanel results = new JPanel();
  JPanel signature = new JPanel();
  JPanel heading = new JPanel();
  JPanel kneedata = new JPanel();
  JLabel testerLabel = new JLabel();
  JLabel statusBar = new JLabel();
  JLabel dateLabel = new JLabel();
  JLabel timeLabel = new JLabel();
  JLabel signLabel = new JLabel();
  JLabel signDateLabel = new JLabel();
  JLabel title = new JLabel();
  JTextField dateField = new JTextField();
  JTextField timeField = new JTextField();
  JTextField testerField = new JTextField();
  JTextField signField = new JTextField();
  JTextField signDateField = new JTextField();
  JButton openButton = new JButton();
  JButton saveButton = new JButton();
  JButton jButton3 = new JButton();
  JButton prometButton = new JButton();

  BorderLayout contentPaneLayout = new BorderLayout();
  BorderLayout curveTableLayout = new BorderLayout();
  GridBagLayout reportLayout = new GridBagLayout();
  GridBagLayout headingLayout = new GridBagLayout();
  GridBagLayout dataLayout = new GridBagLayout();
  GridLayout dateTimeLayout = new GridLayout();
  GridLayout headerLayout = new GridLayout();
  GridLayout settingsLayout = new GridLayout();
  GridLayout signatureLayout = new GridLayout();
  GridLayout resultsLayout = new GridLayout();

  TitledBorder gBorder= new TitledBorder(BorderFactory.createLineBorder(Color.black,1),
  	"General Settings");
  TitledBorder cBorder= new TitledBorder(BorderFactory.createLineBorder(Color.black,1),
  	"Current Transformer Settings");
  TitledBorder vBorder= new TitledBorder(BorderFactory.createLineBorder(Color.black,1),
  	"Voltage Transformer Settings");
  TitledBorder mcBorder= new TitledBorder(BorderFactory.createLineBorder(Color.black,1),
    	"Magnetisation Curves");
  TitledBorder dBorder= new TitledBorder(BorderFactory.createLineBorder(Color.black,1),
  	"Magnetisation Curve Points");
  TitledBorder kBorder= new TitledBorder(BorderFactory.createLineBorder(Color.black,1),
  	"Mag Curve Kneepoints");
  TitledBorder crBorder= new TitledBorder(BorderFactory.createLineBorder(Color.black,1),
  	"Current Ratio Results");
  TitledBorder vrBorder= new TitledBorder(BorderFactory.createLineBorder(Color.black,1),
  	"Voltage Ratio Results");

  public JTable curveTable = new JTable(engine.curveModel);
  JPanel mcPanel = new JPanel();
  GridLayout curveLayout = new GridLayout();
  JMenuItem menuOpen = new JMenuItem();
  JMenuItem menuPrint = new JMenuItem();
  JMenuItem menuSave = new JMenuItem();
  JMenuItem menuPrintSetup = new JMenuItem();

        JPanel vPanel = new JPanel();
        JPanel cPanel = new JPanel();
        JPanel gsPanel = new JPanel();
        JTable genSetTable = new JTable(engine.genSetModel);
        BorderLayout gsLayout = new BorderLayout();
        JTable ctSetTable = new JTable(engine.ctSetModel);
        BorderLayout csLayout = new BorderLayout();
        BorderLayout vsLayout = new BorderLayout();
        JTable vtSetTable = new JTable(engine.vtSetModel);
        JPanel vrPanel = new JPanel();
        JPanel crPanel = new JPanel();
        JPanel kneePanel = new JPanel();
        BorderLayout kneeLayout = new BorderLayout();
        JTable kneeTable = new JTable(engine.kneeModel);
        BorderLayout crLayout = new BorderLayout();
        JTable curentTable = new JTable(engine.currentModel);
        BorderLayout vrLayout = new BorderLayout();
        JTable voltageTable = new JTable(engine.voltageModel);
        JMenu menuView = new JMenu();
        JCheckBoxMenuItem menuViewAntialiased = new JCheckBoxMenuItem();
        JCheckBoxMenuItem menuViewToolbar = new JCheckBoxMenuItem();

  //Construct the frame
  public ReportFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    gBorder = new TitledBorder(BorderFactory.createLineBorder(Color.black,1),"General Settings");
    contentPane.setLayout(contentPaneLayout);
    this.getContentPane().setBackground(Color.white);
    this.setDefaultCloseOperation(3);
    this.setSize(new Dimension(800, 600));
    this.setTitle("CT Mag Curve Reporter");
    statusBar.setBackground(Color.lightGray);
    statusBar.setForeground(SystemColor.desktop);
    statusBar.setOpaque(true);
    statusBar.setText("Select Open from the File menu to view a test.");
    menuFile.setText("File");
    menuFileExit.setText("Exit");
    menuFileExit.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        fileExit_actionPerformed(e);
      }
    });
    menuHelp.setText("Help");
    menuHelpAbout.setText("About");
    menuHelpAbout.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        helpAbout_actionPerformed(e);
      }
    });
    openButton.setIcon(new ImageIcon(ReportFrame.class.getResource("openFile.gif")));
    openButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        openButton_actionPerformed(e);
      }
    });
    openButton.setToolTipText("Open File");
    saveButton.setIcon(new ImageIcon(ReportFrame.class.getResource("closeFile.gif")));
    saveButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        saveButton_actionPerformed(e);
      }
    });
    saveButton.setToolTipText("Close File");
    jButton3.setIcon(new ImageIcon(ReportFrame.class.getResource("help.gif")));
    jButton3.setToolTipText("Help");
    reportPanel.setLayout(reportLayout);
    header.setLayout(headerLayout);
    dateTime.setLayout(dateTimeLayout);
    dateField.setBackground(Color.white);
    dateField.setBorder(null);
    dateField.setDisabledTextColor(Color.gray);
    dateField.setEditable(false);
    dateLabel.setBackground(Color.white);
    dateLabel.setForeground(Color.black);
    dateLabel.setOpaque(true);
    dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
    dateLabel.setText("Test Date");
    timeLabel.setBackground(Color.white);
    timeLabel.setFont(new java.awt.Font("Serif", 1, 12));
    timeLabel.setForeground(Color.black);
    timeLabel.setOpaque(true);
    timeLabel.setDisplayedMnemonic('0');
    timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    timeLabel.setText("Test Time");
    timeField.setBackground(Color.white);
    timeField.setBorder(null);
    timeField.setEditable(false);
    headerLayout.setColumns(1);
    headerLayout.setRows(2);
    dateTimeLayout.setColumns(5);
    header.setBackground(Color.white);
    header.setMinimumSize(new Dimension(0, 0));
    settings.setLayout(settingsLayout);
    settingsLayout.setColumns(3);
    reportPanel.setBackground(Color.white);
    reportPanel.setName("reportPanel");
    reportPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    settings.setBackground(Color.white);
    settings.setAlignmentX((float) 0.0);
    settings.setAlignmentY((float) 0.0);
    settings.setMinimumSize(new Dimension(0, 0));
    settings.setPreferredSize(new Dimension(1024, 176));
    testerLabel.setBackground(Color.white);
    testerLabel.setForeground(Color.black);
    testerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    testerLabel.setText("Name:");
    signature.setLayout(signatureLayout);
    signatureLayout.setColumns(6);
    signLabel.setBackground(Color.white);
    signLabel.setForeground(Color.black);
    signLabel.setHorizontalAlignment(SwingConstants.CENTER);
    signLabel.setText("Signature:");
    signDateLabel.setBackground(Color.white);
    signDateLabel.setForeground(Color.black);
    signDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
    signDateLabel.setText("Date:");
    results.setLayout(resultsLayout);
    resultsLayout.setColumns(3);
    heading.setLayout(headingLayout);
    title.setIconTextGap(10);
    title.setBackground(Color.white);
    title.setFont(new java.awt.Font("Serif", 1, 16));
    title.setForeground(Color.black);
    title.setName("title");
    title.setMinimumSize(new Dimension(0, 0));
    title.setHorizontalAlignment(SwingConstants.CENTER);
    title.setText("Current and Voltage Transformer Calibration Test");
    prometButton.setBackground(Color.white);
    prometButton.setFont(new java.awt.Font("Dialog", 3, 20));
    prometButton.setBorder(null);
    prometButton.setMaximumSize(new Dimension(200, 60));
    prometButton.setMinimumSize(new Dimension(0, 0));
    prometButton.setMargin(new Insets(0, 0, 0, 0));
    prometButton.setMnemonic('0');
    prometButton.setText("ProMet");
    heading.setBackground(Color.white);
    heading.setPreferredSize(new Dimension(1024, 39));
    data.setBackground(Color.white);
    data.setMinimumSize(new Dimension(0, 0));
    data.setLayout(dataLayout);
    results.setBackground(Color.white);
    results.setFont(new java.awt.Font("Dialog", 0, 10));
    results.setMinimumSize(new Dimension(0, 0));
    results.setPreferredSize(new Dimension(1024, 88));
    signature.setBackground(Color.white);
    signature.setMinimumSize(new Dimension(0, 0));
    signature.setPreferredSize(new Dimension(1024, 24));
    signField.setBackground(Color.white);
    signField.setBorder(BorderFactory.createLineBorder(Color.black));
    signField.setEditable(false);
    kneedata.setBackground(Color.white);
                kneedata.setAutoscrolls(true);
    kneedata.setBorder(dBorder);
    kneedata.setMinimumSize(new Dimension(0, 0));
    kneedata.setLayout(curveTableLayout);
    dateTime.setBackground(Color.white);
                dateTime.setBorder(BorderFactory.createLineBorder(Color.black));
    dateTime.setMinimumSize(new Dimension(640, 22));
    dateTime.setPreferredSize(new Dimension(1024, 22));
    contentPaneLayout.setHgap(1);
    contentPaneLayout.setVgap(1);
    curveTable.setCursor(null);
    curveTable.setFont(new java.awt.Font("Serif", 0, 12));
    curveTable.setAlignmentX((float) 0.0);
    curveTable.setAlignmentY((float) 0.0);
    curveTable.setMaximumSize(new Dimension(1000, 1000));
    curveTable.setRequestFocusEnabled(false);
    curveTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    curveTable.setRowHeight(13);
    curveTable.setShowHorizontalLines(false);
    curveTable.setShowVerticalLines(false);
    toolBar.setEnabled(false);
                toolBar.setName("/.,m -+");
                toolBar.setVisible(false);
    toolBar.setAlignmentX((float) 0.0);
    toolBar.setAlignmentY((float) 0.0);
    mcPanel.setBackground(Color.white);
    mcPanel.setBorder(mcBorder);
    mcPanel.setMinimumSize(new Dimension(0, 0));
    mcPanel.setLayout(curveLayout);
    menuPrint.setText("Print...");
    menuPrint.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuPrint_actionPerformed(e);
      }
    });
    menuSave.setEnabled(false);
    menuSave.setText("Save...");
    menuSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuSave_actionPerformed(e);
      }
    });
    menuOpen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuOpen_actionPerformed(e);
      }
    });
    menuPrintSetup.setEnabled(false);
                menuPrintSetup.setText("Print Setup..");
    menuPrintSetup.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        menuPrintSetup_actionPerformed(e);
      }
    });
    testerField.setBorder(BorderFactory.createLineBorder(Color.black));
    signDateField.setBorder(BorderFactory.createLineBorder(Color.black));
    gBorder.setTitleColor(Color.black);
    gBorder.setTitleFont(new java.awt.Font("Serif", 1, 12));
    cBorder.setTitleColor(Color.black);
    cBorder.setTitleFont(new java.awt.Font("Serif", 1, 12));
    vBorder.setTitleColor(Color.black);
    vBorder.setTitleFont(new java.awt.Font("Serif", 1, 12));
    mcBorder.setTitleColor(Color.black);
    mcBorder.setTitleFont(new java.awt.Font("Serif", 1, 12));
    dBorder.setTitleColor(Color.black);
    dBorder.setTitleFont(new java.awt.Font("Serif", 1, 12));
    kBorder.setTitleColor(Color.black);
    kBorder.setTitleFont(new java.awt.Font("Serif", 1, 12));
    crBorder.setTitleColor(Color.black);
    vrBorder.setTitleColor(Color.black);
                mcgraph.setSize(new Dimension(532, 54));
                mcgraph.setSize(new Dimension(5, 5));
                genSetTable.setCursor(null);
                genSetTable.setFont(new java.awt.Font("Serif", 0, 12));
                genSetTable.setMaximumSize(new Dimension(1000, 1000));
                genSetTable.setRequestFocusEnabled(false);
                genSetTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                genSetTable.setRowHeight(12);
                genSetTable.setRowMargin(1);
                genSetTable.setRowSelectionAllowed(false);
                genSetTable.setShowHorizontalLines(false);
                genSetTable.setShowVerticalLines(false);
                gsPanel.setBackground(Color.white);
                gsPanel.setBorder(gBorder);
                gsPanel.setLayout(gsLayout);
                ctSetTable.setRowSelectionAllowed(false);
                ctSetTable.setShowHorizontalLines(false);
                ctSetTable.setShowVerticalLines(false);
                ctSetTable.setRowHeight(12);
                ctSetTable.setRowMargin(1);
                ctSetTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                ctSetTable.setRequestFocusEnabled(false);
                ctSetTable.setMaximumSize(new Dimension(1000, 1000));
                ctSetTable.setCursor(null);
                ctSetTable.setFont(new java.awt.Font("Serif", 0, 12));
                cPanel.setBackground(Color.white);
                cPanel.setAutoscrolls(true);
                cPanel.setBorder(cBorder);
                cPanel.setLayout(csLayout);
                vPanel.setBackground(Color.white);
                vPanel.setAutoscrolls(true);
                vPanel.setBorder(vBorder);
                vPanel.setLayout(vsLayout);
                vtSetTable.setRowSelectionAllowed(false);
                vtSetTable.setShowHorizontalLines(false);
                vtSetTable.setShowVerticalLines(false);
                vtSetTable.setRowHeight(12);
                vtSetTable.setRowMargin(1);
                vtSetTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                vtSetTable.setRequestFocusEnabled(false);
                vtSetTable.setMaximumSize(new Dimension(1000, 1000));
                vtSetTable.setFont(new java.awt.Font("Serif", 0, 12));
                vtSetTable.setCursor(null);
                kneePanel.setBackground(Color.white);
                kneePanel.setBorder(kBorder);
                kneePanel.setLayout(kneeLayout);
                kneeTable.setCursor(null);
                kneeTable.setFont(new java.awt.Font("Serif", 0, 11));
                kneeTable.setAlignmentX((float) 0.0);
                kneeTable.setAlignmentY((float) 0.0);
                kneeTable.setMaximumSize(new Dimension(1000, 1000));
                kneeTable.setRequestFocusEnabled(false);
                kneeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                kneeTable.setRowHeight(11);
                kneeTable.setRowSelectionAllowed(false);
                kneeTable.setShowHorizontalLines(false);
                kneeTable.setShowVerticalLines(false);
                crPanel.setBackground(Color.white);
                crPanel.setBorder(crBorder);
                crPanel.setLayout(crLayout);
                curentTable.setRowSelectionAllowed(false);
                curentTable.setShowHorizontalLines(false);
                curentTable.setShowVerticalLines(false);
                curentTable.setRowHeight(11);
                curentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                curentTable.setRequestFocusEnabled(false);
                curentTable.setMaximumSize(new Dimension(1000, 1000));
                curentTable.setFont(new java.awt.Font("Serif", 0, 11));
                curentTable.setCursor(null);
                vrPanel.setBackground(Color.white);
                vrPanel.setBorder(vrBorder);
                vrPanel.setLayout(vrLayout);
                voltageTable.setRowSelectionAllowed(false);
                voltageTable.setShowHorizontalLines(false);
                voltageTable.setShowVerticalLines(false);
                voltageTable.setRowHeight(11);
                voltageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                voltageTable.setRequestFocusEnabled(false);
                voltageTable.setMaximumSize(new Dimension(1000, 1000));
                voltageTable.setFont(new java.awt.Font("Serif", 0, 11));
                voltageTable.setCursor(null);
                menuView.setText("View");
                menuViewAntialiased.setText("Antialiased Graph");
                menuViewToolbar.setState(true);
                menuViewToolbar.setText("Toolbar");
                menuViewToolbar.addActionListener(new java.awt.event.ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                                menuChangeToolbarState(e);
                        }
                });
                menuFile.add(menuOpen);
    menuFile.add(menuSave);
    menuFile.add(menuPrintSetup);
    menuFile.add(menuPrint);
    menuOpen.setText("Open...");
    menuFile.add(menuFileExit);
    menuHelp.add(menuHelpAbout);
    menuBar1.add(menuFile);
                menuBar1.add(menuView);
    menuBar1.add(menuHelp);
    this.setJMenuBar(menuBar1);
    contentPane.add(statusBar, BorderLayout.SOUTH);
    contentPane.add(reportPanel, BorderLayout.CENTER);
    reportPanel.add(header, new GridBagConstraints(0, 0, 15, 1, 1.0, 3.5
            ,GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    header.add(heading, null);
    heading.add(title, new GridBagConstraints(5, 0, 5, 1, 3.0, 1.0
            ,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    heading.add(prometButton, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    header.add(dateTime, null);
    dateTime.add(dateLabel, null);
    dateTime.add(dateField, null);
    dateTime.add(timeLabel, null);
    dateTime.add(timeField, null);
    reportPanel.add(settings, new GridBagConstraints(0, 1, 15, 10, 1.0, 10.8
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                settings.add(gsPanel, null);
                gsPanel.add(genSetTable, BorderLayout.CENTER);
                settings.add(cPanel, null);
                cPanel.add(ctSetTable, BorderLayout.CENTER);
                settings.add(vPanel, null);
                vPanel.add(vtSetTable, BorderLayout.CENTER);
    reportPanel.add(data, new GridBagConstraints(0, 11, 15, 16, 1.0, 17.7
            ,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    data.add(mcPanel, new GridBagConstraints(0, 0, 2, 1, 2.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                mcPanel.add(mcgraph, null);
             data.add(kneedata, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    kneedata.add(curveTable, BorderLayout.CENTER);
    reportPanel.add(results, new GridBagConstraints(0, 27, 15, 5, 1.0, 6.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    results.add(kneePanel, null);
    kneePanel.add(kneeTable, BorderLayout.NORTH);
    results.add(crPanel, null);
    crPanel.add(curentTable, BorderLayout.CENTER);
    results.add(vrPanel, null);
    vrPanel.add(voltageTable, BorderLayout.CENTER);
    reportPanel.add(signature, new GridBagConstraints(0, 32, 15, 3, 1.0, 1.6
           ,GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    signature.add(testerLabel, null);
    signature.add(testerField, null);
    signature.add(signLabel, null);
    signature.add(signField, null);
    signature.add(signDateLabel, null);
    signature.add(signDateField, null);
    contentPane.add(toolBar, BorderLayout.NORTH);
    toolBar.add(openButton);
    toolBar.add(saveButton);
    toolBar.add(jButton3);
                menuView.add(menuViewAntialiased);
                menuView.add(menuViewToolbar);
  }

  //File | Exit action performed
  public void fileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  //Help | About action performed
  public void helpAbout_actionPerformed(ActionEvent e) {
    ReportFrame_AboutBox dlg = new ReportFrame_AboutBox(this);
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

	void openButton_actionPerformed(ActionEvent e) { engine.openTest(this);}
	void saveButton_actionPerformed(ActionEvent e) { engine.saveTest(this);}
        void menuOpen_actionPerformed(ActionEvent e) { engine.openTest(this);}
        void menuSave_actionPerformed(ActionEvent e) { engine.saveTest(this); }
 	void menuPrint_actionPerformed(ActionEvent e) { reportPanel.printTest();}
        void menuPrintSetup_actionPerformed(ActionEvent e) {reportPanel.printSetup();}

        void menuChangeToolbarState(ActionEvent e) { }

}
