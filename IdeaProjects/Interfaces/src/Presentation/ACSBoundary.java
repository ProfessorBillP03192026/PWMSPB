package Presentation;

import Transport .*;

public class ACSBoundary
{
   private ACSInterface ACSConnector;

   private static boolean     DBG = true;
   private static ACSBoundary  aB = null;
   private ACSInterface        aC = null;
   private RunTimeVars        rtv = null;
   private static CState       cS = null;

   private ACSBoundary()
   {
      if (aC == null)
         aC = new ACSConnector();
      rtv = RunTimeVars.Instance();
      cS  = new CState();
      cS.mid = MessageID.AUTH;
   }

   public static ACSBoundary Instance()
   {
      if (aB == null)  aB = new ACSBoundary();
      return aB;
   }

   public void setValues(final CState c)
   {
      final String userName = c.getUsername();
      final String passWord = c.getPassword();
      final SubsystemRoles role = c.getRole();
      aC.setUserName(userName);
      aC.setPassword(passWord);
      aC.setRole(role);
      aC.msgReceived();
   }

   public boolean getAuth()
   {
      boolean a = aC.getAuth();
      return a;
   }

   //
   // Test purposes only!
   //
   public void processAuthResponse()
   {
      final String toAddr = rtv.getTSTIP();
      final int  toPort = rtv.getTSTPort();
      final SubsystemRoles role = aC.getRole();
      aC.setRole(role);
      ClientServices cs = new ClientServices();
      cs.Connect(toAddr, toPort);
      cs.send(cS);
      cs.Disconnect();
      cS.mid = MessageID.AUTH;
   }
   public void processInputs(ACSConnector aC)
   {
      if (cS.mid == MessageID.AUTH) {
         final String uN1 = aC.getUserName();
         final String pw1 = aC.getPassword();
         final SubsystemRoles role1 = aC.getRole();
         final String uN2 = "Bill";
         final String pw2 = "password";
         final SubsystemRoles role2 =
               SubsystemRoles.DATAANALYST;
         if ((uN1.equals(uN2) == false) ||
               (pw1.equals(pw2) == false) ||
               (role1.equals(role2) == false))
            aC.setNotAuth();
         else
            aC.setAuth();
         cS.mid = MessageID.AUTHRESP;
      }
      else this.processAuthResponse();



   }

}

