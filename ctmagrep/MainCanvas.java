//****************************************************************
// Main Canvas on which the characteristic curve for the currently
// running magnetisation curve test gets drawn.
//****************************************************************
package ctmagrep;
import java.awt.*;
import java.io.*;
import Curve;

class MainCanvas extends java.awt.Canvas
{
    protected boolean real_time;
    protected boolean draw_real_time;
    protected boolean draw_upsweep;

    protected final int SPACE = 10;
    protected final int FULLSCALE = 1024;

    protected int fullscale;
    protected int canvaswidth;
    protected int canvasheight;
    protected int y_axis_start;
    protected int y_axis_stop;
    protected int x_axis_start;
    protected int x_axis_stop;
    protected int x_axis_length;
    protected int y_axis_length;
    protected int y_base_x1;
    protected int y_base_y1;
    protected int y_base_x2;
    protected int y_base_y2;
    protected int x_base_x1;
    protected int x_base_y1;
    protected int x_base_x2;
    protected int x_base_y2;
    protected int x_string_x1;
    protected int x_string_y1;
    protected int y_string_y1;
    protected int y_string_x1;
    protected int voltage_threshold;
    protected int current_threshold;

    protected String x_string = "Amp";
    protected String y_string = "Volt";

    protected float x_axis_increment;
    protected float y_axis_increment;

    private int hi_current;
    private int max_last_current;
    private int max_last_voltage;
    private int display_data[][];
    private int rt_c1;
    private int rt_c2;
    private int rt_v1;
    private int rt_v2;

    public MainCanvas()
    {
        real_time= false;
        draw_real_time= true;
        draw_upsweep= true;
        display_data= new int[2][];
        display_data[0] = new int[FULLSCALE];
        display_data[1] = new int[FULLSCALE];
        fullscale = FULLSCALE;
        resetData();
        setFullScaleIncrements(fullscale);
        repaint();
    }

    protected void initValues()
    {
        canvaswidth = getSize().width;
        canvasheight = getSize().height;

        y_axis_start = (getFont().getSize() + 5);
        y_axis_stop = canvasheight - y_axis_start;
        x_axis_start = x_string.length() * (getFont().getSize())- 5;
        x_axis_stop = canvaswidth - (canvaswidth/SPACE);
        x_axis_length = x_axis_stop - x_axis_start;
        y_axis_length = y_axis_stop - y_axis_start;
        x_axis_increment = (float)x_axis_length / max_last_current;
        y_axis_increment = (float)y_axis_length / max_last_voltage;

        y_base_x1 = x_axis_start;   // xy start & stop
        y_base_y1 = y_axis_start;   // coordinates of
        y_base_x2 = x_axis_start;   // y-axis.
        y_base_y2 = y_axis_stop;

        x_base_x1 = x_axis_start;   // xy start & stop
        x_base_y1 = y_axis_stop;    // coordinates of
        x_base_x2 = x_axis_stop;    // x-axis.
        x_base_y2 = y_axis_stop;
    }

    protected void initMagCurveString()
    {
        x_string_x1 = (canvaswidth/2) - ((x_string.length()/2)*5);
        x_string_y1 = y_axis_stop + getFont().getSize();

        y_string_y1 = y_axis_start + getFont().getSize();
        y_string_x1 = 5;
    }

//****************************************************************
// Paint method gets called idirectly via repaint(). Seperate the
// real time paint from the non-real time paint.
//****************************************************************

    public void paint (Graphics g)
    {
        if  ( real_time )
        {
            real_time = false;
            if  ( !draw_real_time )
                return;
            g.drawLine(x_axis_start+(int)(rt_c1* x_axis_increment),
                y_axis_stop-(int)(rt_v1* y_axis_increment),
                x_axis_start+(int)(rt_c2* x_axis_increment),
                y_axis_stop-(int)(rt_v2* y_axis_increment));
            return;
        }

        initValues();
        initMagCurveString();
        g.setColor(Color.black);
        g.drawLine(y_base_x1, y_base_y1, y_base_x2, y_base_y2);
        g.drawLine(x_base_x1, x_base_y1, x_base_x2, x_base_y2);

        g.drawString(x_string, x_string_x1, x_string_y1);
        g.drawString(y_string, y_string_x1, y_string_y1);
        displayData(g);
    }

    protected void clearMainCurve()
    {
        resetData();
        repaint();
    }

    public void setIncrements(int current, int voltage)
    {
        max_last_current = current;
        max_last_voltage = voltage;
    }

    public void setFullScaleIncrements(int fullscale)
    {
        max_last_current = (fullscale-1);
        max_last_voltage = (fullscale-1);
    }

//****************************************************************
// Gets called by sampler at start of magcurvetest.
//****************************************************************

    protected void setupMainCurve(int drawing_area)
    {
        fullscale= voltage_threshold = current_threshold = FULLSCALE;
        setIncrements ( drawing_area, drawing_area );
        initValues();
        hi_current = 0;
        resetData();
        repaint();
    }

//****************************************************************
// Called by sampler while a magcurve test is in progress. Add a
// curve point to the canvas data arrays. If the up curve is to be
// drawn it gets called during both the up and down curves. If real
// time drawing is to be employed the paint method is called via
// repaint().
//****************************************************************

    protected void addCurvePoint (int curve_id, int c1, int v1, int c2, int v2)
    {
        rt_c1= c1;
        rt_c2= c2;
        rt_v1= v1;
        rt_v2= v2;

        if(c2 > hi_current)
        {
            current_threshold = c2;
            voltage_threshold = v2;
            hi_current = c2;
        }
        display_data[curve_id][c2] = v2;
        if(draw_real_time)
        {
            real_time= true;
            repaint();
        }
    }

//****************************************************************
// If real time drawing is to be employed, do not clear the screen
// i.e. do not call super.update .
//****************************************************************

    public void update(Graphics g)
    {
        if(real_time) paint(g);
        else super.update(g);
    }

//****************************************************************
// Method gets called to draw the maincurve for an imported test.
// Only draw the down curve on an imported test.
//****************************************************************

    protected void drawMainCurve(Curve c)
    {
        fullscale = c.getFullscale();
        voltage_threshold = c.getLastVoltage();    //***
        current_threshold = c.getLastCurrent();    //***
        resetData();
        for (int i = 0; i < fullscale; i++)
        {
           display_data[1][i] = c.readData(i);
        }
        repaint();
    }

    public void resetData()
    {
        for (int i = 0; i < (FULLSCALE); i++)
        {
           display_data[0][i] = 0;
           display_data[1][i] = 0;
        }
    }

//****************************************************************
// Set current and voltage thresholds, used to accurrately draw upto
// the maximum measured current and voltage readings without a line drawn
// down to zero voltage at the end of the graph i.e. only draw the
// graph upto the maximum reading.
//****************************************************************

    protected void setThresholds(Curve c)
    {
        fullscale = c.getFullscale();

        if (c.getLastCurrent() < current_threshold)
        {
            current_threshold = c.getLastCurrent();    //***
            voltage_threshold = c.getLastVoltage();    //***
            System.out.println("current_threshold = "+current_threshold + " voltage_threshold = " +voltage_threshold);
        }
    }

//****************************************************************
// Draw both the up and down sweeps of the main curve. Up sweep in
// in green, down sweep in black.
//****************************************************************

    protected void displayData(Graphics g)
    {
    int c0, c1, j;
        c0= 0;
        g.setColor(Color.green);
        for ( j = 0; j < (fullscale-1); j++)
        {
            if(display_data[0][j] == 0)
                continue;
            c1 = j;
            if((c1 >= current_threshold)||((display_data[0][c1])>= voltage_threshold)) //***
            {
                j = current_threshold - 1;
                while (display_data[1][j] == 0 && j > 0)
                    j--;
                c0 = j;

                break;
            }

            if(draw_upsweep)
                g.drawLine(x_axis_start+(int)(c0* x_axis_increment),
                    y_axis_stop-(int)(display_data[0][c0]* y_axis_increment),
                    x_axis_start+(int)(c1* x_axis_increment),
                    y_axis_stop-(int)(display_data[0][c1]* y_axis_increment));
            c0= c1;
        }

        g.setColor(Color.black);
        for ( ; j > 0; j-- )
        {
            if(display_data[1][j] == 0)
                continue;
            if( c0 == 0) c0 = j;
            c1 = j;
            g.drawLine(x_axis_start+(int)(c0* x_axis_increment),
                y_axis_stop-(int)(display_data[1][c0]* y_axis_increment)
                ,x_axis_start+(int)(c1* x_axis_increment)
                ,y_axis_stop-(int)(display_data[1][c1]* y_axis_increment));
            c0= c1;
        }
    }
}


