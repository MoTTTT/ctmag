//************************************************************
// Object contains an x and y reading.
//************************************************************
import java.io.*;

class Datum implements java.io.Serializable
{
    private int x_value;
    private int y_value;
    public int getX()  { return x_value; }
    public int getY()  { return y_value; }
    public void setX(int i) { x_value = i; }
    public void setY(int i) { y_value = i; }
}