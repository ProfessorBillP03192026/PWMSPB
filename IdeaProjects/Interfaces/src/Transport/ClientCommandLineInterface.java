package Transport;

import Presentation.*;

import java.util.*;

/********* Title: key server protocol project ************
 * 
 * Description of the Class or method purpose: This program 
 * is interacting with a functions where the user issues 
 * commands to the program in the form of successive lines 
 * of text. 
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillips ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  ClientCommandLineInterface
 *********************************************************/

public class ClientCommandLineInterface
{
    private final RunTimeVars rtv = RunTimeVars.Instance();


    private final String hostaddr   = rtv.getMyHostAddr();
    private final int    AASPort    = rtv.getAASPort();
    private final int    ACSPort    = rtv.getAASPort();
    private final String AASIPaddr  = rtv.getAASIP();
    private final String ACSIPaddr  = rtv.getACSIP();
    private final static int MAXVAL = 9;
    


    private String prompt;
    private Scanner sc;
    private int sel;
    
    public ClientCommandLineInterface(final String hname, 
                                             final int m)
    {

        prompt =
                "Host: " + hostaddr +
                " Port: " + m + "\n" +
                "1) Data Analyst Successful Log In (not alertable)\n" +
                "2) Data Analyst Unsuccessful Log In "
                                  + "Attempt (alertable)\n" +
                "3) Data Gatherer Successful Log In (not alertable)\n" +
                "4) Data Gatherer Unsuccessful Log In "
                      + "Attempt (alertable)\n" +
                "5) Auditor Successful Log In (not alertable)\n" +
                "6) Auditor Unsuccessful Log In "
                      + "Attempt (alertable)\n" +
                "7) Watchman Successful Log In (not alertable)\n" +
                "8) Watchman Unsuccessful Log In "
                      + "Attempt (alertable)\n" +
                "9) Leave\n\n";
        
        sc = new Scanner(System.in);
    }

    public void serverOnly()
    {
       ClientTransactionLogger.Instance();
       ServerTransactionLogger.Instance();
       System.out.println("Enter any character to leave");
       sc.nextLine();
       System.exit(0);
    }
    
    public CState getUserSelection()
    {
       CState cs = new CState();
       cs.mid = MessageID.MSG;
             
       System.out.print(prompt);

        while (true)
       {
          try
          {
            sel    = sc.nextInt();
            if ((sel >0) && (sel<MAXVAL+1))
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


       final String dest = rtv.getMyHostAddr();
       cs.setHostname(dest);
       
        switch (sel)
        {
            case 1:
                cs.setV(0);
                cs.mid = MessageID.AUTH;
                cs.setToAddr(ACSIPaddr);
                cs.setPort(ACSPort);
                cs.setDest(SubsystemEnums.DAS);
                cs.setRole(SubsystemRoles.DATAANALYST);
                cs.setUsername("Bill");
                cs.setPassword("password");
                cs.setMessage("Data Analyst Log In");
                break;
            case 2:
               cs.setV(0);
               cs.mid = MessageID.AUTH;
               cs.setToAddr(ACSIPaddr);
               cs.setPort(ACSPort);
               cs.setDest(SubsystemEnums.DAS);
               cs.setRole(SubsystemRoles.DATAANALYST);
               cs.setUsername("Billx");
               cs.setPassword("password");
               cs.setMessage("Data Analyst NOT Logged In");
               break;
           case 3:
              cs.setV(0);
              cs.mid = MessageID.AUTH;
              cs.setToAddr(ACSIPaddr);
              cs.setPort(ACSPort);
              cs.setDest(SubsystemEnums.DGS);
              cs.setRole(SubsystemRoles.DATAGATHERER);
              cs.setUsername("Joe");
              cs.setPassword("JoePass");
              cs.setMessage("Data Gatherer Log In");
              break;
           case 4:
              cs.setV(0);
              cs.mid = MessageID.AUTH;
              cs.setToAddr(ACSIPaddr);
              cs.setPort(ACSPort);
              cs.setDest(SubsystemEnums.DGS);
              cs.setRole(SubsystemRoles.DATAGATHERER);
              cs.setUsername("Joe");
              cs.setPassword("JoePassXYZ");
              cs.setMessage("Data Gatherer NOT Logged In");
              break;
           case 5:
              cs.setV(0);
              cs.mid = MessageID.AUTH;
              cs.setToAddr(ACSIPaddr);
              cs.setPort(ACSPort);
              cs.setDest(SubsystemEnums.AAS);
              cs.setRole(SubsystemRoles.AUDITOR);
              cs.setUsername("Mary");
              cs.setPassword("MaryPass");
              cs.setMessage("Auditor Log In");
              break;
           case 6:
              cs.setV(0);
              cs.mid = MessageID.AUTH;
              cs.setToAddr(ACSIPaddr);
              cs.setPort(ACSPort);
              cs.setDest(SubsystemEnums.DAS);
              cs.setRole(SubsystemRoles.AUDITOR);
              cs.setUsername("Mary");
              cs.setPassword("MaryPass");
              cs.setMessage("Auditor NOT Logged In");
              break;
           case 7:
              cs.setV(0);
              cs.mid = MessageID.AUTH;
              cs.setToAddr(ACSIPaddr);
              cs.setPort(ACSPort);
              cs.setDest(SubsystemEnums.AAS);
              cs.setRole(SubsystemRoles.WATCHMAN);
              cs.setUsername("Amy");
              cs.setPassword("AmyPass");
              cs.setMessage("Watchman Log In");
              break;
           case 8:
              cs.setV(0);
              cs.mid = MessageID.AUTH;
              cs.setToAddr(ACSIPaddr);
              cs.setPort(ACSPort);
              cs.setDest(SubsystemEnums.DAS);
              cs.setRole(SubsystemRoles.SYSTEMADMIN);
              cs.setUsername("Amy");
              cs.setPassword("AmyPass");
              cs.setMessage("Watchman NOT Logged In");
              break;
           case 9:
                System.exit(0);
            default:
                System.out.println("Invalid Selection");
                cs= null;
                break;
                //System.exit(0);
        }
       
       return cs;
    }
}
