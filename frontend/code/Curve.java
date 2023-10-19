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
        
    public Curve()
    {
        int settings_length = 7;
        
        fullscale = BYTE_MAX+ 1;
        data = new int [fullscale + settings_length];
        resetDataArray();
    }

    public Curve(Sampler sampler)
    {
        int settings_length = 6;
        
        fullscale = BYTE_MAX+ 1;
        data = new int [fullscale + settings_length];
        resetDataArray();        
    }
    
    public void setCurrentThreshold(int i)
    {
        current_threshold = i;
    }

    public void setVoltageThreshold(int i)
    {
        voltage_threshold = i;
    }

    public int getVoltageThreshold()
    {
        return voltage_threshold;
    }

    public int getCurrentThreshold()
    {
        return current_threshold;
    }

    public int getFullscale()   
    {
        return fullscale;
    }
    
    public void resetDataArray()    
    {
        for (int x = 0; x < fullscale; x++)
            data[x] = 0;
    }
    public int readData(int i)
    {
        return data[i];
    }
    
//    protected int readConvertedCurrent(int i)
  //  {
//        int settings_amp = 5;        
  //      int amp_mul_index = fullscale + settings_amp;
    //    int conversion_factor = 10000;
      //  int result;
        
        //try{
//            result = i * data[amp_mul_index] / conversion_factor;
  //      }
    //    catch (ArrayIndexOutOfBoundsException a)
      //  {
        //    int default_a_mul_factor = 1000 * 10000/1023;        
          //  result = i * default_a_mul_factor / conversion_factor;            
//        }
  //      return result;
//    }

//    protected int readConvertedVoltage(int i)
  //  {
    //    int settings_volt = 4;        
      //  int volt_mul_index = fullscale + settings_volt;
//        int conversion_factor = 10000;
  //      int result;
        
    //    try{
      //      result = data[i] * data[volt_mul_index] / conversion_factor;
        //}
//        catch (ArrayIndexOutOfBoundsException a)
  //      {
    //        int default_v_mul_factor = 500 * 10000/1023;            
      //      result = data[i] * default_v_mul_factor / conversion_factor;            
        //}
//        return result;        
  //  }
    
    public void setCurvePoint (int x, int y)
    {
        this.data[x] = y;
    }
    public int getLastCurrent()
    {
        return last_current;
    }

    public int getLastVoltage()
    {
        return last_voltage;
    }
    
    public void setLastCurrent(int i)
    {
        last_current = i;
    }

    public void setLastVoltage(int i)
    {
        last_voltage = i;
    }    
}