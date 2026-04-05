package Presentation;

import Transport.*;

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
      final SubsystemEnums ssys = c.getDest();
      cS = c;
      aC.setUserName(userName);
      aC.setPassword(passWord);
      aC.setRole(role);
      aC.setDest(ssys);
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

   private void dumpVals
         (String u, String p, SubsystemRoles r, SubsystemEnums e)
   {
      System.out.println("\nTest function called with the following:");
      System.out.println("ACS received:");
      System.out.println("User Name: " + u);
      System.out.println("Password: " + p);
      System.out.println("Role: " + r);
      System.out.println("Subsys: " +e);
   }

   public void processInputs(ACSConnector aC)
   {
      if (cS.mid == MessageID.AUTH) {
         final String uN1 = aC.getUserName();
         final String pw1 = aC.getPassword();
         final SubsystemRoles role1 = aC.getRole();
         final SubsystemEnums enum1 = aC.getDest();
         if (DBG) dumpVals(uN1, pw1, role1, enum1);
         final String uN2 = "Bill";
         final String pw2 = "password";
         final SubsystemEnums enum2 = SubsystemEnums.DAS;
         final SubsystemRoles role2 =
               SubsystemRoles.DATAANALYST;
         if ((uN1.equals(uN2) == false) ||
               (pw1.equals(pw2) == false) ||
               (role1.equals(role2) == false)||
               (enum1.equals(enum2) == false))
            aC.setNotAuth();
         else
            aC.setAuth();
         cS.mid = MessageID.AUTHRESP;
      }
      else this.processAuthResponse();



   }

}

