package Transport;

import Presentation.*;

/********** Title: key server protocol project ************
 * 
 * Description of the Class or method purpose: This are the
 * processing functions for the server.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillips ( 214-36-930)
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
    private final static boolean DBG = true;

    public ServerProcessing(ServerServices SS)
    {
        smservices = SS;
    }
    
    public final void processMessage(CState C)
    {
        final MessageID m = C.mid;
        if ((m == MessageID.AUTH))
        {
            SubsystemEnums se = C.getDest();
            String d = se.toString();
            System.out.print(d);
            System.out.println(" requests Authorization");
            AccessControlPresInterface ap =
                   AccessControlPresInterface.Instance();
            AuditRecordDataStore.Instance().storeAuditRecord(C);
            final boolean a = ap.Authenticate(C);
            System.out.print(d);
            if (a) System.out.println  (" was authorized");
            else System.out.println(" was NOT authorized");
            final String toAddr = rtv.getTSTIP();
            final int  toPort = rtv.getTSTPort();
            C.setToAddr(toAddr);
            C.setPort(toPort);
            if (a) C.setV(1);
            else   C.setV(0);
            C.mid = MessageID.AUTHRESP;
            sendRMsg(C, new ClientServices());
        }
        else if (m == MessageID.AUTHRESP)
        {
            SubsystemRoles sr = C.getRole();
            String d = sr.toString();
            System.out.print(d);
            final int ai = C.getV();
            final boolean ab = (ai==1)? true : false;
            if (ab) System.out.println  (" was authorized");
            else System.out.println(" was NOT authorized");
            if (C.getV()==0)
                stl.writeToLogger("Did Not Authenticate");
            else
                stl.writeToLogger      ("Authenticated");
            if (DBG) System.out.println
                  ("Auth Response Message Received");
            C.mid = MessageID.AUTH;

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
