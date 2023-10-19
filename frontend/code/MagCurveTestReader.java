
/**
 * Title:        CT Mag Test Reporter<p>
 * Description:  <p>
 * Copyright:    Copyright (c) M.J.Colley<p>
 * Company:      Q Solutions<p>
 * @author M.J.Colley
 * @version 1.0
 */

import ctmagrep.*;
import java.text.DateFormat;
import java.sql.Date;

public class MagCurveTestReader {

	private final int HARDW_FULL = 1023;
       	private final int HARDW_CONV = 10000;
	private final int HARDW_CP = 100000;
 	private final int HARDW_CS = 1000;
	private final int HARDW_VP = 500;
	private final int HARDW_VS = 500;
	private final int HARDW_AMP_MUL=  HARDW_CS * HARDW_CONV/ HARDW_FULL;
	private final int HARDW_VOLT_MUL= HARDW_VS * HARDW_CONV/ HARDW_FULL;

	protected int settings [];
	protected final int RELEASE = 0;
	protected final int LEVEL = 1;
	protected final int BRANCH = 2;
	protected final int SEQUENCE = 3;
	protected final int VOLT_MUL = 4;
	protected final int AMP_MUL = 5;
	protected final int CS_SETTING = 6;
	protected final int SETTINGS_LENGTH = 7;

 	private final int debug = 4;

 	private int vFactor;
  	private int aFactor;

        public MagCurveTestReader()
        {
        }

        public void initSettings( MagCurveTest mct )
        {
                settings = new int[SETTINGS_LENGTH];
                try
                {
	            	for (int i= 0; i< SETTINGS_LENGTH; i++)
                  	{
        	            settings[i]= mct.maincurve.data[HARDW_FULL+ i+ 1];
                     	    if ( debug > 4 ) System.out.println
                          	( "Setting "+ i+ ": "+ settings[i] );
                        }
                }
                catch (ArrayIndexOutOfBoundsException a)
                {
                        settings[AMP_MUL]= HARDW_AMP_MUL;
                        settings[VOLT_MUL]= HARDW_VOLT_MUL;
                        if ( debug > 1 )
                        {
                               	System.out.print("Ancient test version, ");
                        	System.out.println("using default multipliers.");
                        }
                }
                aFactor= settings[AMP_MUL];
                vFactor= settings[VOLT_MUL];
                if ( debug>2 )
                {
                        System.out.println("aFactor: "+aFactor+"; vFactor: "+vFactor);
                        System.out.print("Test Version: ");
                        for ( int i= RELEASE; i<= SEQUENCE; i++ )
                        	System.out.print(settings[i]+ ".");
                        System.out.println();
                }
        }

        public int getKneeCurrent (MagCurveTest mct, int p )
        {
                int i= mct.vimagcurve[p].getKneePoint().getX();
                int si= i* aFactor/ HARDW_CONV;
                if ( debug > 4 ) System.out.println
                	( "Kneepoint["+ p+ "] current: "+ i+ " ; Scaled: "+ si );
                return ( si );
        }

        public int getKneeVoltage (MagCurveTest mct, int p )
        {
                int v= mct.vimagcurve[p].getKneePoint().getY();
                int sv= v* vFactor/HARDW_CONV;
                if ( debug > 4 ) System.out.println
                	( "Kneepoint["+ p+ "] voltage: "+ v+ " ; Scaled: "+ sv );
                return ( sv );
        }

        public int getAMinMax ( MagCurveTest mct )
        {
  	int amax;
		int aminmax= HARDW_FULL;
		for ( int p= 0; p< 3; p++ )
  		{
                	if ( (amax= getMaxCurrent( mct, p ))== 0) continue;
                  	aminmax= ( aminmax< amax )? aminmax: amax;
		}
                return aminmax;
        }

        public int getVMaxMax ( MagCurveTest mct, int c )
        {
           	int vmax;
		int vmaxmax= 0;
		for ( int p= 0; p< 3; p++ )
  		{
			vmax= getVoltage( mct, p, c );
                  	vmaxmax= ( vmaxmax> vmax )? vmaxmax: vmax;
		}
                return vmaxmax;
        }

        public int getFullScale	( )
        {
                return HARDW_FULL;
        }

        public int getMaxVoltage ( MagCurveTest mct, int p )
        {
                return mct.vimagcurve[p].getLastVoltage();
        }

        public int getMaxCurrent ( MagCurveTest mct, int p )
        {
                return mct.vimagcurve[p].getLastCurrent();
        }

        public int getVoltage (MagCurveTest mct, int p, int c)
        {
                return (mct.vimagcurve[p].readData(c)* vFactor/HARDW_CONV);
        }

        private int getCurrent ( int c)
        {
                return (c* aFactor/HARDW_CONV);
        }

	public int	getVoltPercent( MagCurveTest mct, int p, int pcent )
 	{
		int i=	( mct.vimagcurve[p].getKneePoint().getX()* pcent )/ 100;
           	if	(( i< 1)||(i> HARDW_FULL )) return ( 0 );
    		return	( getVoltage(mct, p, i));
	}

	public int	getAmpPercent( MagCurveTest mct, int p, int pcent )
 	{
		int i=	( mct.vimagcurve[p].getKneePoint().getX()* pcent )/ 100;
           	if	(( i< 1)||(i> HARDW_FULL )) return ( 0 );
    		return	( getCurrent(i));
	}

 	private String getRatioError ( int s0, int p0, int s1, int p1 )
	{
		if ( (s0== 0)||(p0== 0)  ) return "Not Set";
		float r1= p1/s1;			// Measured Ratio
  		float r0= p0/s0;			// Expected ratio
     		float e= ((r1- r0)/r0)* 100;		// ratio error (%)
	  	return ( (int) e+ " %" );
	}

 	private String  getRatioResult ( int s0, int p0, int s1, int p1 )
	{
		if (p0== 0 ) return( p1+ " : "+ s1 ) ;	// Not scaled
  		float r1= p1/s1;			// Measured Ratio
       		float ss1= p0/ r1;			// Scale secondary
	    	return (p0+" : "+ ss1);			// Scaled to expected
	}

	public String getCurrentResult (MagCurveTest mct, int p, int col)
 	{
	int	p0,p1,s0,s1;
  		p0= mct.getTruePrimCTRatio();
		s0= mct.getTrueSeconCTRatio();
  		p1= mct.phasecurrentratio[p].getY();
		s1= mct.phasecurrentratio[p].getX()* HARDW_CS/ HARDW_CP;
		if ((s1== 0 )||(p1==0)) return " ";
		switch ( col )
 		{
		case 0:	return (getRatioError( s0, p0, s1, p1));
  		case 1:	return (getRatioResult(s0, p0, s1, p1));
		case 2:	return ((mct.phase_amppolarity[p]==1)?"OK":"NO");
		}
  		return "Invalid column.";
	}

	public String getVoltageResult (MagCurveTest mct, int p, int col)
 	{
	int	p0,p1,s0,s1;
		p0= mct.getTruePrimVTRatio();
		s0= mct.getTrueSeconVTRatio();
  		p1= mct.phasevoltageratio[p].getY();
		s1= mct.phasevoltageratio[p].getX();
		if ((s1== 0 )||(p1==0)) return " ";
		switch ( col )
 		{
		case 0:	return (getRatioError( s0, p0, s1, p1));
  		case 1:	if ((s1== 0 )||(p1==0)) return " ";
    			return (getRatioResult(s0, p0, s1, p1));
		case 2:	return ((mct.phase_voltpolarity[p]==1)?"OK":"NO");
		}
  		return "Invalid column.";
	}


        public String getTime (MagCurveTest mct )
        {
		return ( DateFormat.getTimeInstance().format(mct.date));
        }

        public String getDate (MagCurveTest mct )
        {
                Date date = new Date(mct.date.getTime());
		return ( date.toString() );
        }
}