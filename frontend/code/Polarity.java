import java.lang.*;
class Polarity extends java.lang.Object 
{
    final int MASK = 0x05;
    final int IN_PHASE = 85;
    private int polarity_counter;
    private int inphase_counter;
    private int inphase_total;
        
    public Polarity()
    {
        resetValues();        
    }
    
    public void resetValues()
    {
        inphase_counter = 0;
        inphase_total = 0;
        polarity_counter = 0;
    }
    
    public boolean testPolarity(int i)
    {
        i &= MASK;
        if((i == 0)|| (i ==5))        
            inphase_counter++;
        polarity_counter++;  
        return true;
    }
    
    public int fuzzyPhaseTest()
    {
        inphase_total = (inphase_counter* 100)/polarity_counter;
        
        if(inphase_total >= IN_PHASE)
            return 1;            
        else
            return 0;
    }
}