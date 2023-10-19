//************************************************************
// Characteristic curves for the 3 phases are to be displayed
// on this canvas in the report.
//************************************************************
package ctmagrep;
class ReportPhaseCanvas extends PhaseCanvas
{
    protected final int SPACE = 12;

    // Initialise the positioning values for the canvas header.
    protected void initMagCurveString()
    {
        x_string_x1 = (canvaswidth/2) - ((x_string.length()/2)*5);
        x_string_y1 = y_axis_stop + getFont().getSize();

        y_string_y1 = y_axis_start + getFont().getSize();
        y_string_x1 = 5;
    }

    //Initialise the relevant dimensional values for the report canvas
    protected void initValues()
    {
        canvaswidth = getSize().width;
        canvasheight = getSize().height;

        y_axis_start = ( 5);
        y_axis_stop = canvasheight - 15;
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
}