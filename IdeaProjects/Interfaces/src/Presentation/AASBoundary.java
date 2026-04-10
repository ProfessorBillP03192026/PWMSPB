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
      if (sm == null)
      {
         System.out.println("No Audit Message Sent!");
      }
      final boolean v = aS.getAlertable();
      System.out.println("Audit Message Sent:");
      System.out.println(sm);
      if (v) System.out.println("ALERT SET");

   }

   public void sendMsg2()
   {
      final String sm = aS.getMsg();
      final boolean v = aS.getAlertable();
      final int vi = v? 1 : 0;
      cS.mid = MessageID.MSG;
      cS.setMessage(sm);
      cS.setV(vi);
      String toIP = rtv.getTSTIP();
      final int p = rtv.getTSTPort();
      cS.setToAddr(toIP);
      cS.setPort(p);
      cS.setHostname(toIP);
      if (cSv == null) cSv = new ClientServices();
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

