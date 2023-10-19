
/**
 * Title:        CT Mag Test Reporter<p>
 * Description:  <p>
 * Copyright:    Copyright (c) M.J.Colley<p>
 * Company:      Q Solutions<p>
 * @author M.J.Colley
 * @version 1.0
 */
package ctmagrep;

import javax.swing.*;
import java.awt.*;


public class ReportExtract extends JPanel {
        JTextField testerField = new JTextField();
        JPanel reportPanel = new JPanel();
        JButton jButton4 = new JButton();
        JTable kneeTable = new JTable(engine.kneeModel);
        JTextField DateField = new JTextField();
        GridBagLayout gridBagLayout3 = new GridBagLayout();
        GridBagLayout gridBagLayout2 = new GridBagLayout();
        GridBagLayout gridBagLayout1 = new GridBagLayout();
        JTextField signDateField = new JTextField();
        JPanel dateTime = new JPanel();
        JPanel vtSetPanel = new JPanel();
        JTable vtSetTable = new JTable(engine.vtSetModel);
        JPanel signature = new JPanel();
        JPanel kneepoints = new JPanel();
        JLabel TimeLabel = new JLabel();
        JPanel mcPanel = new JPanel();
        JPanel data = new JPanel();
        JTable curveTable = new JTable(engine.curveModel);
        JTable voltageTable = new JTable(engine.voltageModel);
        JLabel signLabel = new JLabel();
        JPanel ctSetPanel = new JPanel();
        JPanel heading = new JPanel();
        JTable ctSetTable = new JTable(engine.ctSetModel);
        JPanel results = new JPanel();
        JPanel kneedata = new JPanel();
        JPanel genSetPanel = new JPanel();
        JPanel voltageRatio = new JPanel();
        JTable genSetTable = new JTable(engine.genSetModel);
        CardLayout cardLayout7 = new CardLayout();
        GridLayout gridLayout6 = new GridLayout();
        CardLayout cardLayout6 = new CardLayout();
        GridLayout gridLayout5 = new GridLayout();
        GridLayout gridLayout4 = new GridLayout();
        CardLayout cardLayout5 = new CardLayout();
        CardLayout cardLayout4 = new CardLayout();
        GridLayout gridLayout3 = new GridLayout();
        CardLayout cardLayout3 = new CardLayout();
        GridLayout gridLayout2 = new GridLayout();
        CardLayout cardLayout2 = new CardLayout();
        GridLayout gridLayout1 = new GridLayout();
        CardLayout cardLayout1 = new CardLayout();
        JPanel settings = new JPanel();
        JLabel title = new JLabel();
        JLabel testerLabel = new JLabel();
        CurveGraph mcgraph = new CurveGraph();
        JPanel header = new JPanel();
        JPanel currentRatio = new JPanel();
        JTextField TimeField = new JTextField();
        JLabel DateLabel = new JLabel();
        JTextField signField = new JTextField();
        JLabel signDateLabel = new JLabel();
        JTable curentTable = new JTable(engine.currentModel);
        BorderLayout borderLayout1 = new BorderLayout();

        public ReportExtract() {
                try {
                        jbInit();
                }
                catch(Exception e) {
                        e.printStackTrace();
                }
        }

        private void jbInit() throws Exception {
                reportPanel.setLayout(gridBagLayout1);
                reportPanel.setBackground(Color.white);
                reportPanel.setName("");
                reportPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                reportPanel.setMinimumSize(new Dimension(0, 0));
                jButton4.setBackground(Color.white);
                jButton4.setFont(new java.awt.Font("Dialog", 3, 18));
                jButton4.setBorder(null);
                jButton4.setMnemonic('0');
                jButton4.setText("ProMet  ");
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
                DateField.setBackground(Color.white);
                DateField.setBorder(null);
                DateField.setDisabledTextColor(Color.gray);
                DateField.setEditable(false);
                dateTime.setLayout(gridLayout1);
                dateTime.setBackground(Color.white);
                dateTime.setBorder(BorderFactory.createLineBorder(Color.black));
                dateTime.setMinimumSize(new Dimension(640, 22));
                dateTime.setPreferredSize(new Dimension(1024, 22));
                vtSetPanel.setBackground(Color.white);
                vtSetPanel.setAlignmentX((float) 0.0);
                vtSetPanel.setAlignmentY((float) 0.0);
                vtSetPanel.setMinimumSize(new Dimension(213, 100));
                vtSetPanel.setPreferredSize(new Dimension(341, 176));
                vtSetPanel.setLayout(cardLayout3);
                vtSetTable.setRowSelectionAllowed(false);
                vtSetTable.setShowHorizontalLines(false);
                vtSetTable.setShowVerticalLines(false);
                vtSetTable.setRowHeight(11);
                vtSetTable.setRowMargin(1);
                vtSetTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                vtSetTable.setRequestFocusEnabled(false);
                vtSetTable.setMaximumSize(new Dimension(1000, 1000));
                vtSetTable.setFont(new java.awt.Font("Serif", 0, 11));
                vtSetTable.setCursor(null);
                signature.setLayout(gridLayout4);
                signature.setBackground(Color.white);
                signature.setMinimumSize(new Dimension(0, 0));
                signature.setPreferredSize(new Dimension(1024, 24));
                kneepoints.setBackground(Color.white);
                kneepoints.setAlignmentX((float) 0.0);
                kneepoints.setAlignmentY((float) 0.0);
                kneepoints.setMinimumSize(new Dimension(214, 55));
                kneepoints.setPreferredSize(new Dimension(342, 88));
                kneepoints.setLayout(cardLayout5);
                TimeLabel.setBackground(Color.white);
                TimeLabel.setOpaque(true);
                TimeLabel.setDisplayedMnemonic('0');
                TimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                TimeLabel.setText("Test Time");
                mcPanel.setBackground(Color.white);
                mcPanel.setMinimumSize(new Dimension(0, 0));
                mcPanel.setLayout(gridLayout6);
                data.setBackground(Color.white);
                data.setMinimumSize(new Dimension(0, 0));
                data.setLayout(gridBagLayout3);
                curveTable.setCursor(null);
                curveTable.setFont(new java.awt.Font("Serif", 0, 11));
                curveTable.setAlignmentX((float) 0.0);
                curveTable.setAlignmentY((float) 0.0);
                curveTable.setMaximumSize(new Dimension(1000, 1000));
                curveTable.setRequestFocusEnabled(false);
                curveTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                curveTable.setRowHeight(12);
                curveTable.setShowHorizontalLines(false);
                curveTable.setShowVerticalLines(false);
                voltageTable.setRowSelectionAllowed(false);
                voltageTable.setShowHorizontalLines(false);
                voltageTable.setShowVerticalLines(false);
                voltageTable.setRowHeight(11);
                voltageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                voltageTable.setRequestFocusEnabled(false);
                voltageTable.setToolTipText("");
                voltageTable.setMaximumSize(new Dimension(1000, 1000));
                voltageTable.setFont(new java.awt.Font("Serif", 0, 11));
                voltageTable.setCursor(null);
                signLabel.setBackground(Color.white);
                signLabel.setHorizontalAlignment(SwingConstants.CENTER);
                signLabel.setText("Signature:");
                ctSetPanel.setBackground(Color.white);
                ctSetPanel.setAlignmentX((float) 0.0);
                ctSetPanel.setAlignmentY((float) 0.0);
                ctSetPanel.setMinimumSize(new Dimension(213, 110));
                ctSetPanel.setPreferredSize(new Dimension(341, 176));
                ctSetPanel.setLayout(cardLayout2);
                heading.setLayout(gridBagLayout2);
                heading.setBackground(Color.white);
                heading.setPreferredSize(new Dimension(1024, 39));
                ctSetTable.setRowSelectionAllowed(false);
                ctSetTable.setShowHorizontalLines(false);
                ctSetTable.setShowVerticalLines(false);
                ctSetTable.setRowHeight(11);
                ctSetTable.setRowMargin(1);
                ctSetTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                ctSetTable.setRequestFocusEnabled(false);
                ctSetTable.setMaximumSize(new Dimension(1000, 1000));
                ctSetTable.setCursor(null);
                ctSetTable.setFont(new java.awt.Font("Serif", 0, 11));
                results.setLayout(gridLayout5);
                results.setBackground(Color.white);
                results.setFont(new java.awt.Font("Dialog", 0, 10));
                results.setMinimumSize(new Dimension(0, 0));
                results.setPreferredSize(new Dimension(1024, 88));
                kneedata.setBackground(Color.white);
                kneedata.setMinimumSize(new Dimension(0, 0));
                kneedata.setPreferredSize(new Dimension(341, 282));
                kneedata.setLayout(cardLayout4);
                genSetPanel.setBackground(Color.white);
                genSetPanel.setAlignmentX((float) 0.0);
                genSetPanel.setAlignmentY((float) 0.0);
                genSetPanel.setMinimumSize(new Dimension(0, 0));
                genSetPanel.setPreferredSize(new Dimension(341, 176));
                genSetPanel.setLayout(cardLayout1);
                voltageRatio.setBackground(Color.white);
                voltageRatio.setFont(new java.awt.Font("Dialog", 0, 10));
                voltageRatio.setAlignmentX((float) 0.0);
                voltageRatio.setAlignmentY((float) 0.0);
                voltageRatio.setMinimumSize(new Dimension(213, 55));
                voltageRatio.setPreferredSize(new Dimension(341, 88));
                voltageRatio.setLayout(cardLayout7);
                genSetTable.setCursor(null);
                genSetTable.setFont(new java.awt.Font("Serif", 0, 11));
                genSetTable.setMaximumSize(new Dimension(1000, 1000));
                genSetTable.setRequestFocusEnabled(false);
                genSetTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                genSetTable.setRowHeight(11);
                genSetTable.setRowMargin(1);
                genSetTable.setRowSelectionAllowed(false);
                genSetTable.setShowHorizontalLines(false);
                genSetTable.setShowVerticalLines(false);
                gridLayout5.setColumns(3);
                gridLayout4.setColumns(6);
                gridLayout3.setColumns(3);
                gridLayout2.setColumns(1);
                gridLayout2.setRows(2);
                gridLayout1.setColumns(5);
                settings.setLayout(gridLayout3);
                settings.setAlignmentX((float) 0.0);
                settings.setAlignmentY((float) 0.0);
                settings.setMinimumSize(new Dimension(0, 0));
                settings.setPreferredSize(new Dimension(1024, 176));
                title.setIconTextGap(10);
                title.setBackground(Color.lightGray);
                title.setFont(new java.awt.Font("Serif", 1, 16));
                title.setForeground(Color.black);
                title.setName("");
                title.setMinimumSize(new Dimension(0, 0));
                title.setHorizontalAlignment(SwingConstants.CENTER);
                title.setText("Current and Voltage Transformer Calibration Test");
                testerLabel.setBackground(Color.white);
                testerLabel.setHorizontalAlignment(SwingConstants.CENTER);
                testerLabel.setText("Name:");
                mcgraph.setSize(new Dimension(532, 54));
                header.setLayout(gridLayout2);
                header.setBackground(Color.white);
                header.setMinimumSize(new Dimension(0, 0));
                currentRatio.setBackground(Color.white);
                currentRatio.setFont(new java.awt.Font("Dialog", 0, 10));
                currentRatio.setAlignmentX((float) 0.0);
                currentRatio.setAlignmentY((float) 0.0);
                currentRatio.setMinimumSize(new Dimension(213, 55));
                currentRatio.setPreferredSize(new Dimension(341, 88));
                currentRatio.setLayout(cardLayout6);
                TimeField.setBackground(Color.white);
                TimeField.setBorder(null);
                TimeField.setEditable(false);
                DateLabel.setBackground(Color.white);
                DateLabel.setOpaque(true);
                DateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                DateLabel.setText("Test Date");
                signField.setBackground(Color.white);
                signField.setBorder(BorderFactory.createLineBorder(Color.black));
                signField.setEditable(false);
                signDateLabel.setBackground(Color.white);
                signDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                signDateLabel.setText("Date:");
                curentTable.setRowSelectionAllowed(false);
                curentTable.setShowHorizontalLines(false);
                curentTable.setShowVerticalLines(false);
                curentTable.setRowHeight(11);
                curentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                curentTable.setRequestFocusEnabled(false);
                curentTable.setMaximumSize(new Dimension(1000, 1000));
                curentTable.setFont(new java.awt.Font("Serif", 0, 11));
                curentTable.setCursor(null);
                this.setLayout(borderLayout1);
                this.add(reportPanel, BorderLayout.CENTER);
                reportPanel.add(header, new GridBagConstraints(0, 0, 15, 1, 1.0, 3.5
            ,GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                header.add(heading, null);
                heading.add(jButton4, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                heading.add(title, new GridBagConstraints(5, 0, 5, 1, 3.0, 1.5
            ,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                header.add(dateTime, null);
                dateTime.add(DateLabel, null);
                dateTime.add(DateField, null);
                dateTime.add(TimeLabel, null);
                dateTime.add(TimeField, null);
                reportPanel.add(settings, new GridBagConstraints(0, 1, 15, 10, 1.0, 10.6
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                settings.add(genSetPanel, null);
                genSetPanel.add(genSetTable, "genSetTable");
                settings.add(ctSetPanel, null);
                ctSetPanel.add(ctSetTable, "genSetTable1");
                settings.add(vtSetPanel, null);
                vtSetPanel.add(vtSetTable, "vtSetTable");
                reportPanel.add(data, new GridBagConstraints(0, 11, 15, 16, 1.0, 17.6
            ,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                data.add(mcPanel, new GridBagConstraints(0, 0, 12, 1, 2.0, 1.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                mcPanel.add(mcgraph, null);
                data.add(kneedata, new GridBagConstraints(12, 0, 6, 1, 1.0, 1.0
            ,GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                kneedata.add(curveTable, "curveTable");
                reportPanel.add(results, new GridBagConstraints(0, 27, 15, 5, 1.0, 6.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                results.add(kneepoints, null);
                kneepoints.add(kneeTable, "kneeTable");
                results.add(currentRatio, null);
                currentRatio.add(curentTable, "curentTable");
                results.add(voltageRatio, null);
                voltageRatio.add(voltageTable, "voltageTable");
                reportPanel.add(signature, new GridBagConstraints(0, 32, 15, 3, 1.0, 1.6
            ,GridBagConstraints.SOUTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
                signature.add(testerLabel, null);
                signature.add(testerField, null);
                signature.add(signLabel, null);
                signature.add(signField, null);
                signature.add(signDateLabel, null);
                signature.add(signDateField, null);
        }
}