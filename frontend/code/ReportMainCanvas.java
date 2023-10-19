import java.awt.*;
import java.io.*;

class ReportMainCanvas extends MainCanvas 
{
    protected final int SPACE = 10;
//    private int fullscale;

    protected void initValues()
    {
        fullscale = FULLSCALE;
        canvaswidth = getSize().width;
        canvasheight = getSize().height;

        y_axis_start = ( 5);
        y_axis_stop = canvasheight - 15;
        x_axis_start = x_string.length() * (getFont().getSize())- 5;
        x_axis_stop = canvaswidth - (canvaswidth/SPACE);
        x_axis_length = x_axis_stop - x_axis_start;
        y_axis_length = y_axis_stop - y_axis_start;
        x_axis_increment = (float)x_axis_length / (fullscale-1);
        y_axis_increment = (float)y_axis_length / (fullscale-1);               
       
        y_base_x1 = x_axis_start;   // xy start & stop 
        y_base_y1 = y_axis_start;   // coordinates of
        y_base_x2 = x_axis_start;   // y-axis.
        y_base_y2 = y_axis_stop;
        
        x_base_x1 = x_axis_start;   // xy start & stop
        x_base_y1 = y_axis_stop;    // coordinates of
        x_base_x2 = x_axis_stop;    // x-axis.
        x_base_y2 = y_axis_stop;       
    }    
    
}