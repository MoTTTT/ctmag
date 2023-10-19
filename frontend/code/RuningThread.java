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
   
    public void run()
    {
        do
        {
            last_count = parent.frame_cnt;
//            System.out.println("last_count = " + last_count);
            
            try
            {
                Thread.sleep( COMMS_TIME + parent.timeout_threshold );
            }
            catch   ( InterruptedException exception )
            {
//                System.out.println("InterruptedException of runingthread.");
                stop();
            }
            this_count = parent.frame_cnt;
//            System.out.println("this_count = " + this_count);
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

    
