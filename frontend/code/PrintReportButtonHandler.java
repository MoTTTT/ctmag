//************************************************************
// Handler to fill in the fields of the displayed TestReport
// and generate a PrintJob to print the report to hardcopy.
//************************************************************

import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.text.DateFormat;
import java.io.*;
import java.sql.Date;

class PrintReportButtonHandler implements java.awt.event.ActionListener//, java.io.Serializable 
{
    String datestring;
    String timestring;
    
    private final int MARGIN= 28;
    private Properties printproperties;
    private MagCurveTest magcurvetest;
    private Ctmag ctmag;
    private Dimension dimension;
    private Dimension report_dimension;
    private Panel printer_panel;
    private Date report_date;

    public PrintReportButtonHandler(Ctmag f)
    {
        ctmag = f;
        dimension = new Dimension();
        report_dimension = new Dimension();
    }
    
    public void setObjects(MagCurveTest m)
    {
        magcurvetest = m;        
    }

    public void actionPerformed(ActionEvent e)
    {
        ctmag.saveproc.saveTestSettings();
        getReportValues();
        ctmag.testreportdialog.setVisible(true); 
        ctmag.statustextfield.setText("Select \"OK\" in ReportDialog to proceed with printing options, \"Cancel\" to exit printing." );        
    }

//*******************************************************************
// Insert settings values into the appropriate textfields of the TestReport.
//*******************************************************************

    private void getReportValues()
    {        
        report_date = new Date(ctmag.magcurvetest.date.getTime());
        datestring = report_date.toString();
        timestring = DateFormat.getTimeInstance().format(ctmag.magcurvetest.date);
        ctmag.reportheader.reportdatetextfield.setText(""+datestring);
        ctmag.reportheader.reporttimetextfield.setText(""+timestring);
       
        ctmag.generalreportsettings.testnametextfield.setText(magcurvetest.getTestname());
        ctmag.generalreportsettings.testertextfield.setText(magcurvetest.getTestername());
        ctmag.generalreportsettings.supplyauthoritytextfield.setText(magcurvetest.getSupplyauthority());      
        ctmag.generalreportsettings.substationtextfield.setText(magcurvetest.getSubstation());
        ctmag.generalreportsettings.feedertextfield.setText(magcurvetest.getFeeder());
        ctmag.generalreportsettings.testnametextfield.setText(magcurvetest.getTestname());               
        
        ctmag.ctreportsettings.ctrednumbertextfield.setText(magcurvetest.getRednumber());      
        ctmag.ctreportsettings.ctwhitenumbertextfield.setText(magcurvetest.getWhitenumber());      
        ctmag.ctreportsettings.ctbluenumbertextfield.setText(magcurvetest.getBluenumber());      
        ctmag.ctreportsettings.ctvaratingtextfield.setText(magcurvetest.getCTVarating()); 
        ctmag.ctreportsettings.ctmaketextfield.setText(magcurvetest.getCTMake());
        ctmag.ctreportsettings.cttypetextfield.setText(magcurvetest.getCTType());
        ctmag.ctreportsettings.ctclasstextfield.setText(magcurvetest.getCTClassification());
        ctmag.ctreportsettings.ctapplicationtextfield.setText(magcurvetest.getCTApplication());           
        ctmag.ctreportsettings.ctratioprimetextfield.setText
                   (""+ magcurvetest.getTruePrimCTRatio());
        ctmag.ctreportsettings.ctratiosecondtextfield.setText
                   (""+ magcurvetest.getTrueSeconCTRatio());
                                      
        ctmag.vtreportsettings.vtserialnumbertextfield.setText(magcurvetest.getVTSerialno());      
        ctmag.vtreportsettings.vtdescriptiontextfield.setText(magcurvetest.getVTDescription());      
        ctmag.vtreportsettings.vtiltextfield.setText(magcurvetest.getVTIl());      
        ctmag.vtreportsettings.vtvaratingtextfield.setText(magcurvetest.getVTVarating()); 
        ctmag.vtreportsettings.vtmaketextfield.setText(magcurvetest.getVTMake());
        ctmag.vtreportsettings.vttypetextfield.setText(magcurvetest.getVTType());
        ctmag.vtreportsettings.vtclasstextfield.setText(magcurvetest.getVTClassification());
        ctmag.vtreportsettings.vtphasetextfield.setText(magcurvetest.getVTPhase());           
        ctmag.vtreportsettings.vtratioprimetextfield.setText
                   (""+ magcurvetest.getTruePrimVTRatio());
        ctmag.vtreportsettings.vtratiosecondtextfield.setText
                   (""+ magcurvetest.getTrueSeconVTRatio());
     
        if(ctmag.magcurvetest.kneepoint_reached)
            ctmag.reportphasecanvas.setIncrements(ctmag.magcurvetest.max_last_current, ctmag.magcurvetest.max_last_voltage);                                    
        else
            ctmag.reportphasecanvas.setFullScaleIncrements();

        for (int i = ctmag.magcurvetest.RED; i <= ctmag.magcurvetest.BLUE; i++)
            ctmag.reportphasecanvas.readDataIn(
                        ctmag.magcurvetest.vimagcurve[i], i);
    }

//*******************************************************************
// Method initiate and execute a print job. Renders a Graphics object
// to a print device.
//*******************************************************************

    public void printReport()
    { 
        printproperties = new Properties();
        
        dimension.setSize(ctmag.testreportdialog.testreportdialogpanel.getSize());
        PrintJob pjob = ctmag.getToolkit().getPrintJob(ctmag,"Printing Test", printproperties);
        printproperties.put("awt.print.paperSize","a4");       
        printproperties.put("awt.print.orientation","landscape");        
        System.out.println("" + pjob.getPageDimension());
        if(pjob != null)
        {
            report_dimension.setSize(pjob.getPageDimension());
            ctmag.testreportdialog.testreportholdingpanel.setSize(report_dimension.width,report_dimension.height); //***
            Graphics pg = pjob.getGraphics();
            if(pg != null)
            {
                ctmag.testreportdialog.testreportholdingpanel.printAll(pg);
                pg.dispose();
            }
            ctmag.testreportdialog.testreportholdingpanel.setSize(dimension.getSize());            
            pjob.end();
        }
    }
}
