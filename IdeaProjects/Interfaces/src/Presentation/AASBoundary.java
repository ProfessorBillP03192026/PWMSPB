package Presentation;
import Transport.*;

public class AASBoundary
{

   private AASInterface    AASConnector;
   private static AASBoundary aA = null;
   private AASInterface              aS;
   private CState                    cS;
   private ClientServices           cSv;
   private final RunTimeVars  rtv =
                 RunTimeVars.Instance();
   private AASBoundary()
   {
      aS = new AASConnector();
      cS = new       CState();
   }

   public static AASBoundary Instance()
   {
      if (aA == null) aA = new AASBoundary();
      return aA;
   }

   public AASInterface getConnector()
   {
      return aS;
   }

   public void sendMsg()
   {
      final String sm = aS.getMsg();
      final boolean v = aS.getAlertable();
      final int vi = v? 1 : 0;
      cS.mid = MessageID.MSG;
      cS.setMessage(sm);
      cS.setV(vi);
      String toIP = rtv.getAASIP();
      final int p = rtv.getAASPort();
      cS.setToAddr(toIP);
      cS.setPort(p);
      cS.setHostname(toIP);
      cSv = new ClientServices();
      cSv.Connect(toIP, p);
      cSv.send(cS);
      cSv.Disconnect();

      //          C.setMessage(msg);
      //          C.mid = MessageID.MSG;
      //          C.setToAddr(toAddr);
      //          C.setPort(toPort);
      //         sP.sendRMsg(cS, new ClientServices());
   }

}

