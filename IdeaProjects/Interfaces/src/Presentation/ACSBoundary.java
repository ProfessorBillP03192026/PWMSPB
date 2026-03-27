package Presentation;

import Transport .*;

public class ACSBoundary
{
   private ACSInterface ACSConnector;

   private static boolean     DBG = true;
   private static ACSBoundary  aB = null;
   private ACSInterface        aC = null;

   private ACSBoundary()
   {
      if (aC == null)
         aC = new ACSConnector();
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
   public void processInputs(ACSConnector aC)
   {
      final String uN1 =       aC.getUserName();
      final String pw1 =       aC.getPassword();
      final SubsystemRoles role1 = aC.getRole();
      final String uN2 =                 "Bill";
      final String pw2 =             "password";
      final SubsystemRoles role2 =
            SubsystemRoles.DATAANALYST;
      if ((uN1 != uN2) ||
            (pw1 != pw2) ||
            (role1 != role2)) aC.setNotAuth();
      else
         aC.setAuth();

   }

}

