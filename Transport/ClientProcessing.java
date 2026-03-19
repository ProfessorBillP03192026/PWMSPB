package Transport;

/********** Title: key server protocol project *************
 * 
 * Description of the Class or method purpose:  This 
 * processing functions completes the process through the 
 * client server information. Here,one more function 
 * included which is worked for entire protocol and function
 * call in P2PClient file.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillipse ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  ClientProcessing 
 *********************************************************/

public class ClientProcessing
{
    private final RunTimeVars  rtv = RunTimeVars.Instance();
    
    private final String     hname =   rtv.getMyHostAddr();
    private final int        port  =   rtv.getServerPort();
    private final ClientTransactionLogger 
            ctl   =     ClientTransactionLogger.Instance();
    private ClientServices cservices;
    
    public ClientProcessing(final ClientServices CS)
    {
        cservices = CS; 
    }
    
    public final void RKProto(CState c)
    {
        ClientServices cs  = new ClientServices();   
        c.setHostname(rtv.getMainClientIP());
        c.mid    = MessageID.REKEYENC;
        String toIP = rtv.getENCIP();
        cs.Connect(toIP, rtv.getServerPort());
        cs.send(c);
        cs.Disconnect();
       
        c.mid    = MessageID.REKEYDEC;
        toIP = rtv.getDECIP();
        cs.Connect(toIP, rtv.getServerPort());
        cs.send(c);
        cs.Disconnect(); 
               
        c.mid    = MessageID.RESTARTENC;
        toIP = rtv.getENCIP();
        cs.Connect(toIP, rtv.getServerPort());
        cs.send(c);       
        cs.Disconnect();
    }
    
 

   public final void ProcessLeave()
    {
        ctl.writeToLogger(hname + " is exiting");
        System.exit(0);
    }
}
