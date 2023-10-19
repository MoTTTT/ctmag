
/**
 * Title:        CT Mag Test Reporter<p>
 * Description:  <p>
 * Copyright:    Copyright (c) M.J.Colley<p>
 * Company:      Q Solutions<p>
 * @author M.J.Colley
 * @version 1.0
 */
package ctmagrep;

import java.io.*;
import javax.swing.table.*;
import javax.swing.JFileChooser;
import MagCurveTest;
import MagCurveTestReader;
import java.beans.*;

public class ReportEngine
{
	public MagCurveTest test1;
	private String testName = new String (" ");
	private String testDir = new String (" ");
	private String statusMessage = new String (" ");
	private String errorMessage = new String (" ");
	private File testDirFile;
	private JFileChooser openTestDialog;
	private DataInputStream datainputstream;
	private DataOutputStream dataoutputstream;
	private ObjectInputStream objectinputstream;
 	private	MagCurveTestReader reader= new MagCurveTestReader();
        private final int DATA_0= 70;		// Start of data table (%)
        private final int dDATA= 5;		// Increment (%)
	private final int DATAROWS= 13;
	private final int NUM_GS= 5;
	private final int NUM_CTS= 9;
	private final int NUM_VTS= 9;

	final Object[][] genSetData = {{"Test Name", new String("No Test Loaded")},
				{"Recorded by", new String(" ")}, {"Supply Authority", new String(" ")},
				{"Substation", new String(" ")}, {"Feeder", new String(" ")}};
	final Object[][] ctSetData= {{"Make", new String(" ")}, {"Type", new String(" ")},
                                {"Class", new String(" ")}, {"Application", new String(" ")},
                                {"Ratio", new String(" ")}, {"Red serial no.", new String(" ")},
                                {"White serial no.", new String(" ")}, {"Blue serial no.", new String(" ")},
                                {"VA Rating", new String(" ")}};
	final Object[][] vtSetData= {{"Make", new String(" ")}, {"Type", new String(" ")},
                                {"Class", new String(" ")}, {"Phase", new String(" ")},
                                {"Ratio", new String(" ")}, {"Description", new String(" ")},
                                {"Serial no.", new String(" ")}, {"IL", new String(" ")},
                                {"VA Rating", new String(" ")}};
	Object[][] curveDataHeadings= {{"Red","Red","White","White","Blue","Blue"},
				{"Volt", "mA", "Volt", "mA", "Volt", "mA" }};
	Object[][] kneeData =	{{" ", "Voltage", "Current" }, {"RED", "0V", "0mA" },
      			       	{"WHITE", "0V", "0mA" }, {"BLUE", "0V", "0mA"  }};
	Object[][] currentData=	{{" ", "Error", "Result", "Polarity" },
                            	{"RED", "0%", " ", " " }, {"WHITE", "0%", " ", " " },
                            	{"BLUE", "0%", " ", " "  }};
	Object[][] voltageData=	{{" ", "Error", "Result", "Polarity" },
                            	{"RED", "0%", " ", " " },{"WHITE", "0%", " ", " " },
                            	{"BLUE", "0%", " ", " "  }};

	int[][] curveData= 	new int[6][DATAROWS];


    public TableModel genSetModel = new AbstractTableModel()
    {
        public int getColumnCount() { return 2; }
        public int getRowCount() { return genSetData.length; }
        public Object getValueAt(int row, int col){return genSetData[row][col];}
    };

    public TableModel ctSetModel = new AbstractTableModel()
    {
        public int getColumnCount() { return 2; }
        public int getRowCount() { return ctSetData.length; }
        public Object getValueAt(int row, int col){return ctSetData[row][col];}
    };

    public TableModel vtSetModel = new AbstractTableModel()
    {
        public int getColumnCount() { return 2; }
        public int getRowCount() { return vtSetData.length; }
        public Object getValueAt(int row, int col) {return vtSetData[row][col];}
    };

    public TableModel curveModel = new AbstractTableModel()
    {
        public int getColumnCount() { return 6; }
        public int getRowCount() { return DATAROWS+ 2; }
        public Object getValueAt(int row, int col)
        {
                if ( row< 2 ) return curveDataHeadings[row][col];
                return " "+ curveData[col][row- 2];
        }
    };

    public TableModel kneeModel = new AbstractTableModel()
    {
        public int getColumnCount() { return 3; }
        public int getRowCount() { return kneeData.length; }
        public Object getValueAt(int row, int col){return kneeData[row][col]; }
    };

    public TableModel voltageModel = new AbstractTableModel()
    {
        public int getColumnCount() { return 4; }
        public int getRowCount() { return voltageData.length; }
        public Object getValueAt(int row,int col){return voltageData[row][col];}
    };

    public TableModel currentModel = new AbstractTableModel()
    {
        public int getColumnCount() { return 4; }
        public int getRowCount() { return currentData.length; }
        public Object getValueAt(int row, int col){return currentData[row][col];}
    };


//	public void printTest ( ReportFrame parent )
//	{
  //       	reportPanel.
//		PrinterJob printJob= PrinterJob.getPrinterJob( );
//	}

	public void	fillCurveData( )
 	{
        int	percent;
		for	( int p= 0; p< 3; p++ )
 		{
           		if	( reader.getKneeCurrent(test1, p)== 0)	break;
             		percent=DATA_0;
           		for	(int i= 0; i< DATAROWS; i++ )
			{
           			curveData[p*2][DATAROWS- i- 1]=
              				reader.getVoltPercent(test1, p, percent);
           			curveData[p*2+ 1][DATAROWS- i- 1]=
              				reader.getAmpPercent(test1, p, percent);
              			percent+= dDATA;
              		}
		}
	}

	private String padField ( String in )
 	{
          	if (( in== null)||(in.length()<=0)) return " ";
		return in;
	}

	private void fillSettings ()
	{
         int i= 0;
         	genSetData[i++][1]= padField (test1.getTestname());
          	genSetData[i++][1]= padField (test1.getTestername());
       		genSetData[i++][1]= padField (test1.getSupplyauthority());
         	genSetData[i++][1]= padField (test1.getSubstation());
          	genSetData[i++][1]= padField (test1.getFeeder());
		i=0;
  		ctSetData[i++][1]= padField (test1.getCTMake());
		ctSetData[i++][1]= padField (test1.getCTType());
		ctSetData[i++][1]= padField (test1.getCTClassification());
		ctSetData[i++][1]= padField (test1.getCTApplication());
  		ctSetData[i++][1]= padField (test1.getTruePrimCTRatio()+
    			":"+ test1.getTrueSeconCTRatio());
		ctSetData[i++][1]= padField (test1.getRednumber());
		ctSetData[i++][1]= padField (test1.getWhitenumber());
		ctSetData[i++][1]= padField (test1.getBluenumber());
		ctSetData[i++][1]= padField (test1.getCTVarating());
		i=0;
		vtSetData[i++][1]= padField (test1.getVTMake());
		vtSetData[i++][1]= padField (test1.getVTType());
		vtSetData[i++][1]= padField (test1.getVTClassification());
		vtSetData[i++][1]= padField (test1.getVTPhase());
  		vtSetData[i++][1]= padField (test1.getTruePrimVTRatio()+
    			":"+ test1.getTrueSeconVTRatio());
		vtSetData[i++][1]= padField (test1.getVTDescription());
		vtSetData[i++][1]= padField (test1.getVTSerialno());
		vtSetData[i++][1]= padField (test1.getVTIl());
		vtSetData[i++][1]= padField (test1.getVTVarating());
	}

	private void fillResults ()
	{
	int p,i;
 		for (p= 0; p< 3; p++)
   		{
			kneeData[p+ 1][1]= reader.getKneeVoltage(test1, p)+ "V";
			kneeData[p+ 1][2]= reader.getKneeCurrent(test1, p)+ "mA";
	   		for ( i= 0; i< 3; i++ )
      			{
				voltageData[p+1][i+ 1]=
    					reader.getVoltageResult(test1, p, i);
				currentData[p+1][i+ 1]=
    					reader.getCurrentResult(test1, p, i);
  			}
  		}
	}

 	private void fillGraph ( ReportFrame parent, MagCurveTest mct )
  	{
    		int[][] mcdata= new int[3][1024];
                int aMinMax= reader.getAMinMax( mct );
                int vMaxMax= reader.getVMaxMax( mct, aMinMax );
    		for ( int p= 0; p< 3; p++ )
      		{
                 	for ( int i= 0; i<= aMinMax; i++ )
                           	mcdata[p][i]= reader.getVoltage( mct, p, i );
                }
           	parent.mcgraph.setTest( mcdata, aMinMax , vMaxMax+ 2);
	}

	public boolean openTest (ReportFrame parent)
	{
		if ( !openFile ( parent ) ) return false;
		reader.initSettings(test1);
	        parent.timeField.setText( reader.getTime(test1) );
	        parent.dateField.setText( reader.getDate(test1) );
		fillCurveData( );
  		fillSettings();
    		fillResults();
      		fillGraph(parent,test1);
  		parent.repaint();
		return true;
	}

	public void saveTest (ReportFrame parent)
	{
 		MagCurveTest dummytest = new MagCurveTest ();
	        JFileChooser openTestDialog = new JFileChooser(testDirFile);
        	openTestDialog.setDialogTitle("Save Mag Curve Test");
	        openTestDialog.setFileSelectionMode( JFileChooser.FILES_ONLY );
        	statusMessage= "Saving Test.";
		parent.statusBar.setText(statusMessage);
        	if (openTestDialog.showSaveDialog(parent)!= JFileChooser.APPROVE_OPTION)
			return;
        	try
         	{
			dataoutputstream = new DataOutputStream(
				new FileOutputStream("/home/colleymj/dummy.cmt"));
		        ObjectOutputStream p = new ObjectOutputStream(
              			dataoutputstream);
			p.writeObject (dummytest);  // Write serializable object to file
			p.flush();
			dataoutputstream.close();
		}
  		catch (FileNotFoundException fnfe)
    		{
			parent.statusBar.setText("An error occured saving to file. ");
		}
		catch (IOException e)
  		{
			parent.statusBar.setText(e + " : An error occurred writing file." );
   		}
	}


	private boolean openFile ( ReportFrame parent )
	{
	        JFileChooser openTestDialog = new JFileChooser(testDirFile);
        	openTestDialog.setDialogTitle("Open Mag Curve Test");
	        openTestDialog.setFileSelectionMode( JFileChooser.FILES_ONLY );
        	if (openTestDialog.showOpenDialog(parent)!= JFileChooser.APPROVE_OPTION)
			return false;
	        File tFile = new File ("");
        	tFile= openTestDialog.getSelectedFile();
	        testDirFile = openTestDialog.getCurrentDirectory();
        	testDir= testDirFile.getName();
	        testName = openTestDialog.getName( tFile );
        	try
	        {
			statusMessage= "Importing "+ testName+ " from "+ testDir;
			parent.statusBar.setText(statusMessage);
			System.out.println (statusMessage);
        		FileInputStream f = new FileInputStream( tFile );
			datainputstream = new DataInputStream( f );
			objectinputstream = new ObjectInputStream( datainputstream );
			test1 = (MagCurveTest)(objectinputstream.readObject()) ;
			datainputstream.close();
			test1.setTestname(testName);
	        }
        	catch(FileNotFoundException fnfe)
         	{
	        	parent.statusBar.setText("File \""+ testName+ "\" not found.");
			return false;
	        }
        	catch(IOException e)
         	{
			parent.statusBar.setText( "Incompatable test version." );
			System.out.println ( "IOException importing " + testName );
			System.out.println (e.getMessage());
			return false;
	        }
        	catch (ClassNotFoundException c)
         	{
        		c.printStackTrace();
	        	System.out.println ( "ClassNotFound importing " + testName);
	        	return false;
        	}
	        catch(NullPointerException npe)
         	{
	        	System.out.println( "Null Pointer importing "+ testName );
			return false;
	        }
        	return true;
	}



    public ReportEngine()
    {
    }
}