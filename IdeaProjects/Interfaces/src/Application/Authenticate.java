package Application;
import Presentation.*;

public class Authenticate
{
   private static boolean DBG = false;

   private static ACSInterface aC = null;
   private static AASInterface aS = null;

   private static AccessControlList acC = AccessControlList.Instance();

   public Authenticate()
   {
      if (aC == null) aC = new ACSConnector();
      if (aS == null) aS = new AASConnector();
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

         final String uN1 = aC.getUserName();
         final String pw1 = aC.getPassword();
         final SubsystemRoles role1 = aC.getRole();
         final SubsystemEnums enum1 = aC.getDest();
         if (DBG) dumpVals(uN1, pw1, role1, enum1);
         //
         // Authentication is done here
         // Replace the following line
         // with authentication code.
         //

         final boolean auth = acC.find(uN1,pw1,enum1,role1);
         //
         if (auth == false)
         {
            aC.setNotAuth();
            aS.setMsg("Actor " + role1 + " NOT Authorized");
            aS.setAlertable();
         }
         else
         {
            aC.setAuth();
            aS.setMsg("Actor " + role1 + " Authorized");
         }
         aS.sendMsg();






      }
}