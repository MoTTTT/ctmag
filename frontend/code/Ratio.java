//***********************************************************************
// The Object Ratio stores the measured ratios in the magcurvetest. 
//***********************************************************************

import java.io.*;
class Ratio extends Datum implements java.io.Serializable     
{
    public Ratio()
    {
    }

    public Datum getRatio()
    {
        return this;
    }

    public void setRatio(Datum d)
    {
        this.setX(d.getX());
        this.setY(d.getY());
    }
    
    public void setRatio(int i1, int i2)
    {
        this.setX(i1);
        this.setY(i2);
    }    
}


