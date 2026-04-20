package Application;

import Presentation.*;

import java.util.*;


public class Users
{


   private String prompt =
         "1) Add User\n" +
         "2) Delete User\n"+
         "3) Leave\n";

   private Scanner sc;
   private int    sel;
   private static final int MAXVAL = 3;
   private AccessControlList acL = null;

   public Users()
   {
      sc = new Scanner(System.in);
      acL = AccessControlList.Instance();
   }

   private int getUserSelection()
   {

      System.out.print(prompt);

      while (true)
      {
         try
         {
            sel = sc.nextInt();
            if ((sel > 0) && (sel < MAXVAL + 1))
               break;
            else
               System.out.println
                     ("Enter an integer between 1 and "
                           + String.valueOf(MAXVAL));
         }
         catch (Exception e)
         {
            sc.nextLine();
            System.out.println
                  ("Invalid option, enter an integer");

         }

      }
      return sel;
   }

   private SubsystemEnums getSubsys(SubsystemRoles sE)
   {
      switch (sE)
      {
         case SubsystemRoles.AUDITOR:
            return SubsystemEnums.AAS;
         case SubsystemRoles.WATCHMAN:
            return SubsystemEnums.AAS;
         case SubsystemRoles.DATAANALYST:
            return SubsystemEnums.DAS;
         case SubsystemRoles.DATAGATHERER:
            return SubsystemEnums.DGS;
         default:
            return null;
      }

   }

   private void addUser()
   {
      boolean okay = false;
      do
      {
         try
         {
            System.out.println("Enter User Name");
            String un = sc.next();
            System.out.println("Enter password");
            String pw = sc.next();
            System.out.println("Enter role");
            String r = sc.next();
            SubsystemRoles sE = SubsystemRoles.valueOf(r);
            SubsystemEnums sN = getSubsys(sE);
            acL.addUser(un, pw, sN, sE);
            okay = true;
         }
         catch (Exception e)
         {
             System.out.println("Bad Value, try again");
         }
      } while (okay == false);
   }

   private void deleteUser()
   {
      System.out.println("Delete user called");
   }

   public void addOrDeleteUser()
   {
      while (true)
      {
         int opt = getUserSelection();
         if (opt == 1)
            addUser();
         else if (opt == 2)
            deleteUser();
         else return;
      }

   }
}

