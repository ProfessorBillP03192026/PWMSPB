package Transport;

/********** Title: key server protocol project ************
 * 
 * Description of the Class or method purpose: This are the
 * processing functions for the server.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillipse ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  ServerProcessing
 *********************************************************/

public class ServerProcessing
{
   
    private final RunTimeVars  rtv = RunTimeVars.Instance();
    private final ServerTransactionLogger stl =
                         ServerTransactionLogger.Instance();
    private final ServerServices smservices;

    public ServerProcessing(ServerServices SS)
    {
        smservices = SS;
    }
    
    public final void processMessage(CState C)
    {
        final MessageID m = C.mid;
        if ((m == MessageID.MSG)||(m == MessageID.DGLOGSIN))
        AuditRecordDataStore.Instance().storeAuditRecord(C);
        else if (m == MessageID.AUTH)
        {
            C.mid = MessageID.AUTHRESP;
            final boolean a = AccessControlPresInterface.
                                 Instance().Authenticate(C);
            if (a) C.setV(1);
            else   C.setV(0);
            sendRMsg(C, new ClientServices());
        }
        else if (m == MessageID.AUTHRESP)
        {
            if (C.getV()==0)
                stl.writeToLogger("Did Not Authenticate");
            else
                stl.writeToLogger      ("Authenticated");
        }
        else if (m == MessageID.REKEYENC)
        {
           stl.writeToLogger  ("Rekey Encryptor Received");
           C.mid = MessageID.REKEYEACK;
           sendRMsg(C, new ClientServices());
        }
        else if (m == MessageID.REKEYEACK)
        {
         stl.writeToLogger("Rekey Encryptor ACK Received");
        }
        else if (m == MessageID.REKEYDEC)
        {
           stl.writeToLogger ("Rekey Decryptor Received");
           C.mid = MessageID.REKEYDACK;
           sendRMsg(C, new ClientServices());
        }
        else if (m == MessageID.REKEYDACK)
        {
         stl.writeToLogger("Rekey Decryptor ACK Received");
        }
    }


    public final void sendRMsg(CState cr, ClientServices cs)
    {
        final String hName = cr.getHostname();
        cr.setHostname(hName);
        cs.Connect(hName, rtv.getServerPort());
        cs.send(cr);
        cs.Disconnect();
    }
}
