//****************************************************************
// Object residing in sampler. Determine whether the tested phase
// polarity is in phase or out of phase. 
//****************************************************************

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
    
//****************************************************************
// Method to test whether the received four character ASCII value
// reflecting the value measured by channel 5 of the PROMET sensing
// unit indicate that the phases are in phase or out of phase. 
//****************************************************************
    
    public boolean testPolarity(int i)
    {
        i &= MASK;
        if((i == 0)|| (i ==5))        
            inphase_counter++;
        polarity_counter++;  
        return true;
    }
 
//****************************************************************
// Method employing fuzzy logic to determine whether the measured
// phases can be considered to be in phase or out of phase. 
//****************************************************************
    
    public int fuzzyPhaseTest()
    {
        inphase_total = (inphase_counter* 100)/polarity_counter;
        
        if(inphase_total >= IN_PHASE)
            return 1;            
        else
            return 0;
    }
}