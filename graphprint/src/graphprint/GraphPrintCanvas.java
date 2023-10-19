
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
import java.awt.print.*;
//import java.awt.print;
//import java.awt.Canvas;

public class GraphPrintCanvas extends Canvas implements Printable
{

        public GraphPrintCanvas()
        {
                super();
        }

        public void paint (Graphics g)
        {
                super(g);
        }

        public int print (Graphics g, PageFormat pf, int pi)
        {
                if (pi>0) return Printable.NO_SUCH_PAGE;
                return Printable.PAGE_EXISTS;
        }
}