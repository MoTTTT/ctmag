
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
import java.awt.print.*;

public class PrintPanel extends JPanel implements Printable
{
        BorderLayout borderLayout1 = new BorderLayout();
        private PageFormat pageFormat1;
       	private final boolean antialiase= false;
        private final int debug = 3;

        public PrintPanel()
        {
                super ();
                this.setLayout(borderLayout1);
        }

        public void printSetup()
        {
//        	pageFormat1 = pageDialog(pageFormat1);
        }

        public void printTest( )
        {
                if ( debug> 2 ) System.out.print("Printing test...  ");
                PrinterJob printJob = PrinterJob.getPrinterJob();
                printJob.setPrintable(this);
                if (!printJob.printDialog())
                {
                        if ( debug> 2 ) System.out.println("Print cancelled.");
                        return;
                }
                try
                {
                        printJob.print();
		        if ( debug> 2 ) System.out.println("Print succeeded.");
                }
                catch ( Exception PrintException )
                {
		        if ( debug> 2 )
          		{
                    		System.out.println("Print failed. Stack Trace follows:");
				PrintException.printStackTrace();
                      	}
                }
        }

        public void paint (Graphics g)
        {
                Graphics2D g2= (Graphics2D) g;
		if ( antialiase ) g2.setRenderingHints(new RenderingHints
			(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		super.paint(g);
        }

        public int print (Graphics g, PageFormat pf, int pi )
        {
                if ( pi> 0 ) return Printable.NO_SUCH_PAGE;
                Graphics2D g2= (Graphics2D) g;
                pf.setOrientation(PageFormat.LANDSCAPE);
                double w= pf.getWidth();
                double h= pf.getHeight();
                double iw= pf.getImageableWidth();
                double ih= pf.getImageableHeight();
                double iy= pf.getImageableY();
                double ix= pf.getImageableX();
                double rw= (double) this.getWidth();
                double rh= (double) this.getHeight();
                double sw= iw/ rw;
                double sh= ih/ rh;
	        if ( debug> 4 )
         	{
        	        System.out.println ("Report      : "+ rw+ " ; "+ rh );
	                System.out.println ("Page  (1/72): "+ w + " , "+ h  );
	                System.out.println ("Image (1/72): "+ iw+ " , "+ ih );
                	System.out.println ("Margins     : "+ ix+ " ; "+ iy );
                	System.out.println ("Scale       : "+ sw+ " ; "+ sh );
                }
                g2.translate( ix, iy );
                g2.scale( sw, sh );
                g2.setBackground(Color.white);
                g2.setClip	(0,0,(int)rw,(int)rh);
                g2.setColor	(Color.black);
                g2.drawRect 	(0,0,(int)rw,(int)rh);
		printComponents(g);
                return Printable.PAGE_EXISTS;
        }
}