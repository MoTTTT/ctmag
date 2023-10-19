import java.awt.*;

class PhaseCanvas extends MainCanvas 
{
    private int fullscale;
    
    public final int CURVE_RED = 0;
    public final int CURVE_WHITE = 1;
    public final int CURVE_BLUE = 2;    
    public final int MAX_PHASE = 3;
    private final int SPACE = 12;
    private final int FULLSCALE = 1024;
    
    private int j;
    public int state;
    private int display_data[][];
    private int phase_current_threshold[];
    private int phase_voltage_threshold[];
    protected int max_last_current;
    protected int max_last_voltage;
    
   public PhaseCanvas()
   {
        fullscale = FULLSCALE;
        display_data = new int[MAX_PHASE][];
        display_data[0] = new int[fullscale];
        display_data[1] = new int[fullscale];
        display_data[2] = new int[fullscale];
        resetData( );
        phase_current_threshold = new int[MAX_PHASE];
        phase_voltage_threshold = new int[MAX_PHASE];
        setFullScaleIncrements();
        repaint();
   }
       
    protected void initMagCurveString()
    {
        x_string_x1 = (canvaswidth/2) - ((x_string.length()/2)*5);
        x_string_y1 = y_axis_stop + getFont().getSize() + 5;
        
        y_string_y1 = y_axis_start + getFont().getSize();        
        y_string_x1 = 5;
    }
              
    protected void initValues()
    {        
        canvaswidth = getSize().width;
        canvasheight = getSize().height;
        
        y_axis_start = (getFont().getSize() + 15);
        y_axis_stop = canvasheight - y_axis_start;
        x_axis_start = x_string.length() * getFont().getSize()- 5;
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

    protected void readDataIn(Curve c, int phase)
    {       
//        fullscale = c.getFullscale();
        
//        phase_current_threshold[phase] = c.getCurrentThreshold();
//        phase_voltage_threshold[phase] = c.getVoltageThreshold(); 

        phase_current_threshold[phase] = c.getLastCurrent();
        phase_voltage_threshold[phase] = c.getLastVoltage(); 

        for (int i = 0; i < fullscale; i++)
            this.display_data[phase][i] = c.readData(i);    
        repaint();     
    }
    
    public void setIncrements(int current, int voltage)
    {
            max_last_current = current;
            max_last_voltage = voltage;
//        x_axis_increment = (float)x_axis_length / max_last_current;
//        y_axis_increment = (float)y_axis_length / max_last_voltage;               
    }
    
    public void setFullScaleIncrements()
    {
        max_last_current = (fullscale-1);
        max_last_voltage = (fullscale-1);
//        x_axis_increment = (float)x_axis_length / (fullscale-1);
//        y_axis_increment = (float)y_axis_length / (fullscale-1);       
    }   


    public void paint (Graphics g)
    {
        initValues();
        initMagCurveString();
        
//        x_axis_increment = (float)x_axis_length / (fullscale-1);
//        y_axis_increment = (float)y_axis_length / (fullscale-1);       


        g.setColor(Color.black);
        g.drawLine(y_base_x1, y_base_y1, y_base_x2, y_base_y2);
        g.drawLine(x_base_x1, x_base_y1, x_base_x2, x_base_y2);
        
        g.drawString(x_string, x_string_x1, x_string_y1);
        g.drawString(y_string, y_string_x1, y_string_y1);
        
        for (int j = 0; j < MAX_PHASE; j++)
        {
            if(testDraw(display_data, j))
            {
                displayData(g, j);
            }
        }
    }

    private boolean testDraw(int test[][], int r)
    {
        for(int q = 0; q < fullscale; q++)
        {
            if(test[r][q] > 0)
                return true;
        }        
        return false;
    }


    public void resetData( )
    {
        for (int j = 0; j < MAX_PHASE; j++)
        {
            for (int i = 0; i < (fullscale); i++)
                display_data[j][i] = 0;    
        }
    }  

    
    public void resetData(int phase)
    {
        for (int i = 0; i < (fullscale); i++)
           display_data[phase][i] = 0;    
    }  

    protected boolean displayData(Graphics g, int phase)
    {
        int x0, x1, y0, y1;
        switch  ( phase )
        {
        case CURVE_RED:     g.setColor(Color.red);
                break;
        case CURVE_BLUE:    g.setColor(Color.blue);
                break;
        case CURVE_WHITE:   g.setColor(Color.gray);
                break;
        default:return false;
        }
        for (int j = 0; j < (fullscale-1); j++)
        {
            if(display_data[phase][j] == 0)
                continue;            
            x0= x_axis_start+(int)(j*x_axis_increment);
            y0= y_axis_stop-(int)(display_data[phase][j]*y_axis_increment);
            x1= x_axis_start+(int)((j+1)*x_axis_increment);
            y1= y_axis_stop-(int)(display_data[phase][j+1]*y_axis_increment);
            if(y1 < y_axis_start || x1 > x_axis_stop)
                break;
            g.drawLine(x0, y0, x1, y1 );
            if(((j+1)>= phase_current_threshold[phase])||(display_data[phase][j+1]>= phase_voltage_threshold[phase]))
                break;
        }
        return  true;
    }
}

