//************************************************************
// MagcurveTest contains a main magcurve representing current
// test data and three phase magcurves representing the assigned
// test data.
//************************************************************

import java.io.*;

class MagCurve extends Curve implements java.io.Serializable
{
    private final int KNEE_PDY_PERCENT=     10;
    private final int KNEE_PDX_PERCENT=     50;
    private final int HUNDRED_PERCENT=      100;
    private final int P_100=                100;
    private final int P_110=                110;
    private final int P_150=                150;
    private final int MIN_CURRENT_TEST=     9;
    protected final int SEARCH_START= (MIN_CURRENT_TEST* 2)/ 3;
    private final int MIN_VOLT_TEST=        11;
    protected Datum knee;
    private int search_start;
    private int search_stop;
//    private static final long serialVersionUID = 7595973527467175335L;
    private static final long serialVersionUID = -5135955638157033866L;
    public MagCurve()
    {
        knee = new Datum();
    }

    public Datum getKneePoint() { return knee; }

//    public int readData(int i)	{ return data[i]; }

    public boolean wayPastKneePoint(int c1, int v1 )
    {
        int c2, v2, vk, x_mod, y_mod;
        if  (( c1 < MIN_CURRENT_TEST )|| ( v1 < MIN_VOLT_TEST ))
            return false;           // Assume not 150% above kneepoint
        c2= ( c1 * 2 )/ 3;          // Find current to test voltage at
        v2= data[c2];               // Read voltage to test
        if  ( v2 == 0 )             // No voltage at test point
            return false;           // Assume not 150% above kneepoint
        vk= ( v1 * 10 )/ 11;        // Calculate kneepoint voltage threshold
        if  ( v2 > vk )             // Voltage is above kneepoint threshold
        {
            search_stop = c2;       // Set end point for precice detection
            knee.setX(c2);          // Set kneepoint current estimate
            knee.setY(v2);          // Set kneepoint voltage estimate
            System.out.println( "Kneepoint #1 Current= "+ c2+ " Voltage= "+ v2 );
            return true;            // Way above kneepoint == true
        }
        else search_start= c2;      // Save last point below kneepoint
        return false;               // Not 150% above kneepoint
    }

    public void calculateKneePoint( )
    {
    int c, c1, v, v1, vt;
        search_stop= super.last_current;
        if  ( search_stop- search_start < 2 )
            return;                     // We can't zoom in further
        for ( c= SEARCH_START; c< search_stop; c++ )
        {
            v=  data[c];                // Read voltage
            c1= ( c * 3 )/ 2;           // Find current to test voltage at
            v1= data[c1];               // Read voltage to test
            vt= ( v* 11 )/ 10;          // Calculate threshold voltage
            if  ( v1<= vt )             // Point at/above kneepoint detected
            {
                knee.setX(c);           // Set kneepoint current
                knee.setY(v);           // Set kneepoint voltage
                System.out.println( "Kneepoint #2 Current= "+ c+ " Voltage= "+ v );
                return;
            }
        }
    }

    public void calculateKneePoint(int current_start )  //!!!
    {
    int c, c1, v, v1, vt;
        search_stop= super.last_current;
        search_start= (current_start> SEARCH_START)? current_start: SEARCH_START ;
        if  ( search_stop- search_start < 2 )
            return;                     // We can't zoom in further
        for ( c= search_start; c< search_stop; c++ )
        {
            v=  data[c];                // Read voltage
            c1= ( c * 3 )/ 2;           // Find current to test voltage at
            v1= data[c1];               // Read voltage to test
            vt= ( v* 11 )/ 10;          // Calculate threshold voltage
            if  ( v1<= vt )             // Point at/above kneepoint detected
            {
                knee.setX(c);           // Set kneepoint current
                knee.setY(v);           // Set kneepoint voltage
                System.out.println( "Kneepoint #2 Current= "+ c+ " Voltage= "+ v );
                return;
            }
        }
    }


    public void resetKneePoint()
    {
       knee.setX(0);
       knee.setY(0);
    }

    public void setKneePoint(Datum d)
    {
       knee.setX(d.getX());
       knee.setY(d.getY());
    }

}
