import java.awt.*;
import java.io.*;
class MainCanvas extends java.awt.Canvas 
{
    private int max_last_current;
    private int max_last_voltage;
    
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

    protected String x_string = "Amp";
    protected String y_string = "Volt";
    
    private int display_data[][];
    protected int testnonzero;
    protected int begin;
    protected int end;
    
    protected boolean real_time;
    protected boolean draw_real_time;
    protected boolean draw_upsweep;

    protected float x_axis_increment;
    protected float y_axis_increment;
    
    protected int voltage_threshold;
    protected int current_threshold;
    private Graphics main_graphics;
    private int hi_current;

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
   
    private void testGraphicsObject()
    {
        main_graphics.drawString("test realtime drawing.", 60, 20);        
    }
    
    public void paint (Graphics g)
    {
        main_graphics = g;
        if  ( real_time )
        {
            real_time = false;
            if  ( !draw_real_time )
                return;
            main_graphics.drawLine(x_axis_start+(int)(rt_c1* x_axis_increment),
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
//        System.out.println("max_last_current = "+max_last_current+ " max_last_voltage = "+max_last_voltage);
    }
    
    public void setFullScaleIncrements(int fullscale)
    {
        max_last_current = (fullscale-1);
        max_last_voltage = (fullscale-1);
    }       

    protected void setupMainCurve(int drawing_area)
    {        
        fullscale= voltage_threshold = current_threshold = FULLSCALE;
        setIncrements ( drawing_area, drawing_area );
        initValues();                
        hi_current = 0;
        resetData();
        repaint();
    }
    
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

    public void update(Graphics g)
    {
        if(real_time) paint(g);                           
        else super.update(g);            
    }
    
    protected void drawMainCurve(Curve c)
    {
        fullscale = c.getFullscale();
        voltage_threshold = c.getLastVoltage();    //***
        current_threshold = c.getLastCurrent();    //***        
//        display_data[1] = new int[fullscale];
        resetData();
        for (int i = 0; i < fullscale; i++)
        {
           display_data[1][i] = c.readData(i);    
        }
        repaint();
    }
    
    public int testNonZeroData(int testdata[], int limit)
    {
        for(int a = 0; a < limit; a++)
        {
            if (testdata[a] > 0)
                return a;
        }
        return -1;
    }
    
//    protected void testGraph()  // Method to check whether the graph
  //  {                           // is drawn correct.
    //    int zoom_factor = 100, size;
      //  size = FULLSCALE/zoom_factor;
        //fullscale = size;
        
//        x_axis_increment = (float)x_axis_length / (size-1);
 //      y_axis_increment = (float)y_axis_length / (size-1);  
//
    //    display_data = new int[size];
  //      int k = 0;
      //   for (int i = 0; i < size; i++)
//        {
  //         display_data[i] = k;                
    //       k++;
      //  }   
        //voltage_threshold = display_data[size-1];    //***
//        current_threshold = size-1;    //***  
  //      setIncrements(current_threshold, voltage_threshold);         
    //    
    //}
    
    public void resetData()
    {        
        for (int i = 0; i < (FULLSCALE); i++)
        {
           display_data[0][i] = 0;
           display_data[1][i] = 0;
        }
    }
    
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
    
    protected void displayData(Graphics g)
    {      
//        testGraph();  // Method to test if a linear line is drawn correct
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


