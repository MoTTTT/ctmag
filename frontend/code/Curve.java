
import java.lang.*;
import java.io.*;

class Curve implements java.io.Serializable
{
	public int voltage_threshold;
	public int current_threshold;
	protected int data [];
	protected int fullscale;
	protected final int BYTE_MAX = 1023;
	protected int last_current;
	protected int last_voltage;
	protected int test_current;
	protected int test_voltage;
	private static final long serialVersionUID = -4609696676259501149L;
	public Curve()
	{
        	int settings_length = 7;
	        fullscale = BYTE_MAX+ 1;
        	data = new int [fullscale + settings_length];
	        resetDataArray();
	}

	public int getFullscale()	{ return fullscale; }
	public int readData(int i)	{ return data[i]; }
// 	public int getData(int i)	{ return data[i]; }
	public int getLastCurrent()	{ return last_current; }
	public int getLastVoltage() { return last_voltage; }
	public void setLastCurrent(int i)	{ last_current = i; }
	public void setLastVoltage(int i)	{ last_voltage = i; }
	public int getVoltageThreshold()	{ return voltage_threshold; }
	public int getCurrentThreshold()    { return current_threshold; }
	public void setCurrentThreshold(int i)	{ current_threshold = i; }
	public void setVoltageThreshold(int i)	{ voltage_threshold = i; }
	public void setCurvePoint(int x, int y)	{ this.data[x] = y; }
	public void resetDataArray(){for (int x= 0; x< fullscale; x++) data[x]= 0; }
}
