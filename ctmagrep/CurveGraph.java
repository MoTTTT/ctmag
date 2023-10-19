
/**
 * Title:        CT Mag Test Reporter<p>
 * Description:  <p>
 * Copyright:    Copyright (c) M.J.Colley<p>
 * Company:      Q Solutions<p>
 * @author M.J.Colley
 * @version 1.0
 */
package ctmagrep;

import MagCurveTest;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public class CurveGraph extends Component {

	private MagCurveTest mct1;
 	private BufferedImage mcbi;
 	private Graphics2D mcbig;
	protected final int FULLSCALE = 1024;
	private int curves[][]= new int[3][FULLSCALE];
	private Font f= new Font("Serif", 1, 10);

	protected final String A_AXIS = "Amp";
	protected final String V_AXIS = "Volt";

   	private float xt;
    	private float yt;
   	private float ax0;
    	private float ay0;
 	private float ax1;
 	private float ay1;
 	private float atextx;
 	private float atexty;
 	private float vtextx;
 	private float vtexty;

 	private float amax= FULLSCALE- 1;
 	private float vmax= FULLSCALE- 1;
   	private float x0;
    	private float y0;
  	private float x1;
   	private float y1;

  	private float dx;
   	private float dy;

    	private final boolean antialiase= false;
    	private final int debug= 1;

        public CurveGraph()
        {
        }

        public void setTest ( int[][] curveData, int amax_in, int vmax_in )
        {
                amax= amax_in;
                vmax= vmax_in;
    		for ( int p=0; p< 3; p++ ) for ( int i= 0; i<= amax; i++ )
               		curves[p][i]= curveData[p][i];
                refreshConstants ( );
                initBuffer();
                drawCurveGraph( mcbig );
                repaint();
    		if (debug >2)
      		{
	    		if (debug >4) System.out.println ("dx/dy : "+ dx+ "/"+ dy );
                	System.out.print ( "Scaling canvas. " );
                	System.out.println ("A max: "+ amax+ "; V max: "+ vmax );
                }
        }

        private void refreshConstants ( )
        {
                float fsize= f.getSize2D();
                int slength= V_AXIS.length();
         	float ymargin= fsize+ 15;
                x0=(float)getLocation().getX();	y0=(float)getLocation().getY();
		x1= getSize().width;		y1= getSize().height;
	        ax0= slength* fsize;		ax1= x1- 5;
        	ay1= 0;				ay0= y1- ymargin;
		dx= (ax1-ax0)/ amax;  		dy= (ay0-ay1)/ vmax;
		atextx= ax1- slength* fsize;	atexty= ay0+ (ymargin* 2)/ 3;
                vtextx= 5;	                vtexty= ay1+ fsize;
        }

        private void initBuffer ( )
        {
                mcbi = (BufferedImage)createImage((int) x1,(int) y1);
                mcbig= mcbi.createGraphics();
		if ( antialiase ) mcbig.setRenderingHints(new RenderingHints
			(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        }

        private void drawCurveGraph ( Graphics2D g2 )
        {
                g2.setColor(Color.white);
                g2.fillRect(0,0,(int)x1,(int)y1);
                g2.setColor(Color.black);
                g2.drawRect((int) ax0, (int) ay1, (int) (ax1- ax0), (int) (ay0- ay1));
  		g2.setFont(f);
	        g2.drawString( A_AXIS, atextx, atexty );
        	g2.drawString( V_AXIS, vtextx, vtexty );
	        for (int p= 0; p < 3; p++) displayData ( g2, p );
        }

        public void paint (Graphics g)
	{
         	Graphics2D g2= (Graphics2D) g;
       		if (debug >1) System.out.println("Painting CurveGraph");
                if (y1!=getSize().height||x1!=getSize().width)
                {
                        refreshConstants ();
                        initBuffer();
                        drawCurveGraph( mcbig );
                }
	        g2.drawImage(mcbi, 0, 0, Color.white, this);
	}

        public void print (Graphics g)
        {
         	Graphics2D g2= (Graphics2D) g;
       		if (debug >1) System.out.println("Printing CurveGraph");
		drawCurveGraph( g2 );
        }

	private void displayData(Graphics2D g2, int p)
	{
	int x1, y1;
        	switch  ( p )
	        {
	        	case 0: g2.setColor(Color.red);		break;
		        case 1:	g2.setColor(Color.gray);	break;
	        	case 2:	g2.setColor(Color.blue);	break;
		        default: return;
        	}
         	xt= ax0;	yt= ay0;
	        for (int i= 1; i<= amax; i++)
	        {
        	    if	( curves[p][i]== 0 ) continue;
	            x1= (int) (ax0+ i* dx);
		    y1= (int) (ay0- curves[p][i]* dy);
	            if	( y1< ay1 || x1 > ax1 ) break;
             	    if  ( (y1== yt) && (i!= amax ) ) continue;
        	    g2.drawLine((int) xt, (int) yt, x1, y1 );
             	    xt= x1;	yt= y1;
	        }
	}
}
