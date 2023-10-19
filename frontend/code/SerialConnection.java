import java.util.TooManyListenersException;
import javax.comm.*;
import java.io.*;
import java.awt.event.*;
import java.util.Enumeration;

class SerialConnection implements java.io.Serializable, SerialPortEventListener
{
    public final int NO_ERROR=                  0;
    public final int ERROR_GET_PORT_ID=         1;
    public final int ERROR_OPEN_PORT=           2;
    public final int ERROR_SET_PARAMETERS=      3;
    public final int ERROR_GET_IO_STREAM=       4;
    public final int ERROR_ADD_LISTENER=        5;
    public final int ERROR_CLOSE_IO_STREAM=     6;
    public final int ERROR_NOT_OPEN=            7; 
    public final int ERROR_IO_EXCEPTION=        8;
    public final int ERROR_NO_PORTS=            9; 
    public final int INPUTSTREAM_ERROR=         10;
    public final int BUFFER_SIZE=               100;
    public String errormessage = "No Error";
    private OutputStream os;
    protected InputStream is;
    private CommPortIdentifier portId;
    private SerialPort sPort;
    private boolean open;    
    private Sampler parent;
    private String comport;
    private byte in[];
    
    public void SerialConnection ( )
    {
    }
    
    public void initSerialConnection ( Sampler parent, String port )
    {
    	this.parent = parent;
	    open = false;
	    in = new byte [BUFFER_SIZE];
	    comport = port;
    }
  
    public void setPort(String s)
    {
        comport = s;
    }
    public int openConnection()
    {
        Enumeration ports= CommPortIdentifier.getPortIdentifiers();
        if  ( !ports.hasMoreElements())
            return ( ERROR_NO_PORTS );
    	try 
    	{
    	    portId = CommPortIdentifier.getPortIdentifier(comport);
    	}
	    catch (NoSuchPortException e)
    	{
    	    errormessage = e.getMessage();
    	    System.out.println(errormessage);
            return ( ERROR_GET_PORT_ID );
    	}
    	try
    	{
    	    sPort = (SerialPort)portId.open("CTMag", 3000);
    	}
	    catch (PortInUseException e)
	    {
    	    errormessage = e.getMessage();
            return ( ERROR_OPEN_PORT );
	    }
    	try
    	{
    	    sPort.setSerialPortParams(9600,8,1,0);
    	}
		catch (UnsupportedCommOperationException e) 
    	{
	        sPort.close();
    	    errormessage = e.getMessage();
    	    System.out.println(errormessage);    	    
            return ( ERROR_SET_PARAMETERS );
    	}
    	try
    	{
	        os = sPort.getOutputStream();
    	    is = sPort.getInputStream();
	    }
    	catch (IOException e)
	    {	        
	        sPort.close();
            errormessage = e.getMessage();
    	    System.out.println(errormessage);    	    
            return ( ERROR_GET_IO_STREAM );
    	}
    	try
    	{
    	    sPort.addEventListener(this);
    	}
	    catch (TooManyListenersException e)
    	{
	        sPort.close();
            errormessage = e.getMessage();
    	    System.out.println(errormessage);    	                
            return ( ERROR_ADD_LISTENER );
    	}
    	try
    	{
    	    is.skip(is.available());
    	}
    	catch (IOException e)
	    {
            errormessage = e.getMessage();
    	    System.out.println(errormessage);    	    
            return ( ERROR_IO_EXCEPTION );
    	}
	    sPort.notifyOnDataAvailable(true);
	    sPort.notifyOnOverrunError(true);
	    open = true;
	    return  ( NO_ERROR );
    }
    
    public int closeConnection()
    {
	   if (!open)
	      return    ( ERROR_NOT_OPEN );
    	if (sPort != null)
	    {
	        try
    	    {
	        	os.close();
	        	is.close();
	        }
	        catch (IOException e)
	        {
        	    errormessage = e.getMessage();
    	        System.out.println(errormessage);    	            	    
                return ( ERROR_CLOSE_IO_STREAM );
	        }
	        sPort.close();
	    }
    	open = false;
    	return  ( NO_ERROR );
    }

    public boolean isOpen()
    {
	   return open;
    }

    public int getAvail ( )
    {
        int i;
        try
        {
   	        i= is.available();
        }
        catch (IOException f)
	    {
        	errormessage = f.getMessage();
    	    System.out.println(errormessage);    	            	    	        
	        parent.comms_status = INPUTSTREAM_ERROR;
            parent.stopSampler ( parent.COMMS_ERROR );
            return  ( -1 );
        }
        return  ( i );
    }
    
    public void processData ( )
    {
        int i, i_max;
        i_max= getAvail();
        if  ( i_max> BUFFER_SIZE- 1 )
        {
            System.out.println ( "Skipping: "+ (i_max- 1)+ "characters.\n");
    	    try
            {
                is.skip( i_max- 1 );
                i_max= 1;
            }
            catch (IOException f)
	        {
        	    errormessage = f.getMessage();
    	        System.out.println(errormessage);    	            	    	        
    	        parent.comms_status = INPUTSTREAM_ERROR;
                parent.stopSampler ( parent.COMMS_ERROR );
                return;
            }
        }
        try
		{
            is.read(in, 0, i_max);
        }    
		catch ( IOException ex )
		{
        	errormessage = ex.getMessage();
    	    System.out.println(errormessage);    	            	    	        
	        parent.comms_status = INPUTSTREAM_ERROR;
            parent.stopSampler(parent.COMMS_ERROR);
            return;
        }
        for ( i=0; i< i_max; i++ )
		   	parent.frameHandler( in[i] );            
    }

    public void serialEvent(SerialPortEvent e)
    {
        if  ( e.getEventType()== SerialPortEvent.DATA_AVAILABLE )
            processData ( );
    }
}

