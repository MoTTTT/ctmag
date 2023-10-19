//**********************************************************************
// Thread determining the time out if serial communictions not established
// and maintained.
//**********************************************************************

import java.lang.*;
import java.io.*;

public class RuningThread extends java.lang.Thread
{
    private Sampler parent;
    protected final int COMMS_TIME = 1000;
    private int this_count, last_count;

    public void initRuningThread ( Sampler parent )
    {
        this.parent= parent;
        last_count = 0;
        this_count = 0;
    }

//**********************************************************************
// The run() method gets activated by the invocation of the thread start() 
// method called from the sampler. The run method determines whether data
// are being received on the serial line and whether the communication is
// being maintained. 
//**********************************************************************   
   
    public void run()
    {
        do
        {
            last_count = parent.frame_cnt;            
            try
            {
                Thread.sleep( COMMS_TIME + parent.timeout_threshold );
            }
            catch   ( InterruptedException exception )
            {
                stop();
            }
            this_count = parent.frame_cnt;
        }
        while(this_count != last_count); 
        if  ( parent.test_type != parent.NO_TEST)
        {
            System.out.println("Serial communiction interrupted.");            
            parent.stopSampler(parent.TIMEOUT_EXCEEDED);
        }
        System.out.println("Stop serialcommunications running thread!");
        stop();
    }
}

    
