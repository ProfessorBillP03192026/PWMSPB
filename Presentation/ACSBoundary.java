package Presentation;

import Transport .*;

public class ACSBoundary
{
   private ACSInterface ACSConnector;

   private static boolean    DBG = true;
   private static ACSBoundary aB = null;
   private ACSInterface       aC = null;

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
      final SubsystemEnums role = c.getRole();
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

}

