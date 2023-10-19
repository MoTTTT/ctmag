
/**
 * Title:        Graphics Printing<p>
 * Description:  graphics printing code tests<p>
 * Copyright:    Copyright (c) M.J.Colley<p>
 * Company:      Q Solutions<p>
 * @author M.J.Colley
 * @version 1.0
 */

/**
 * Title:        Graphics Printing<p>
 * Description:  graphics printing code tests<p>
 * Copyright:    Copyright (c) M.J.Colley<p>
 * Company:      Q Solutions<p>
 * @author M.J.Colley
 * @version 1.0
 */

/**
 * Title:        Graphics Printing<p>
 * Description:  graphics printing code tests<p>
 * Copyright:    Copyright (c) M.J.Colley<p>
 * Company:      Q Solutions<p>
 * @author M.J.Colley
 * @version 1.0
 */

/**
 * Title:        Graphics Printing<p>
 * Description:  graphics printing code tests<p>
 * Copyright:    Copyright (c) M.J.Colley<p>
 * Company:      Q Solutions<p>
 * @author M.J.Colley
 * @version 1.0
 */

/**
 * Title:        Graphics Printing<p>
 * Description:  graphics printing code tests<p>
 * Copyright:    Copyright (c) M.J.Colley<p>
 * Company:      Q Solutions<p>
 * @author M.J.Colley
 * @version 1.0
 */
package graphprint;

import javax.swing.UIManager;
import java.awt.*;

public class graphprint
{
        boolean packFrame = false;

        //Construct the application
        public graphprint()
        {
                GraphPrintFrame frame = new GraphPrintFrame();
                //Validate frames that have preset sizes
                //Pack frames that have useful preferred size info, e.g. from their layout
                if (packFrame)
                {
                        frame.pack();
                }
                else
                {
                        frame.validate();
                }
                //Center the window
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension frameSize = frame.getSize();
                if (frameSize.height > screenSize.height)
                {
                        frameSize.height = screenSize.height;
                }
                if (frameSize.width > screenSize.width)
                {
                        frameSize.width = screenSize.width;
                }
                frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
                frame.setVisible(true);
        }

        //Main method
        public static void main(String[] args)
        {
                try
                {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
                new graphprint();
        }
}