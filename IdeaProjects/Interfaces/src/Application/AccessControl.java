package Application;

import Transport.*;

import java.util.*;


public class AccessControl
{

   public static void main()
   {
      AccessControl aC = new AccessControl();
      aC.manageACL();
   }

   private String prompt =
         "1) Add or Delete User\n" +
         "2) Add or Delete Roles\n"+
         "3) Leave\n";

   private Scanner sc;
   private int    sel;
   private static final int MAXVAL = 3;
   private Users   uS;

   public AccessControl()
   {
      sc = new Scanner(System.in);
      uS = new Users();
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

   private void manageACL()
   {
      while (true)
      {
         int opt = getUserSelection();
         if (opt == 1){uS.addOrDeleteUser();}
         else if (opt == 2){}
         else System.exit(0);
      }

   }
}
