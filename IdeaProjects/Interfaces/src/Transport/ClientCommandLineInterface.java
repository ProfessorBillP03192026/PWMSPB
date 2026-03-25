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
 * @author Bill Phillipse ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  ClientCommandLineInterface
 *********************************************************/

public class ClientCommandLineInterface
{
    private final RunTimeVars rtv = RunTimeVars.Instance();

    private int    panelselection =    0;

    private String  filename;
    private String  filetype;
    private String sHostname;
    private String         S;

    private final String hostaddr   = rtv.getMyHostAddr();
    private final int    portnumber = rtv.getServerPort();
    private final String AASIPaddr  = rtv.getAASIP();
    private final String ACSIPaddr  = rtv.getACSIP();
    private final static int MAXVAL = 7;
    


    private String prompt;
    private Scanner sc;
    private int sel;
    
    public ClientCommandLineInterface(final String hname, 
                                             final int m)
    {

        prompt =
                "Host: " + hostaddr +
                " Node ID: " + m + "\n" +
                "1) Data Analyst Log In (not alertable)\n" +
                "2) Data Gathering Subsystem Intrusion "
                                  + "Attempt(alertable)\n" +
                "3) Audit and Alert Subsystem Crash "
                                         + "(alertable)\n" +
                "4) Valid authentication\n" +
                "5) Invalid authentication\n"  +
                "6) Data Gatherer Log In\n" +
                "7) Leave\n\n";
        
        sc = new Scanner(System.in);
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

        //
         // TODO Figure out return mechanism
        //
       final String dest = rtv.getMyHostAddr();
       cs.setHostname(dest);
       
        switch (sel) {
            case 1:
                cs.setV(0);
                cs.mid = MessageID.MSG;
                cs.setToAddr(AASIPaddr);
                cs.setMessage("Data Analyst Log In");
                break;
            case 2:
                cs.setV(1);
                cs.mid = MessageID.MSG;
                cs.setToAddr(AASIPaddr);
                cs.setMessage
             ("Data Gathering Subsystem Intrusion Attempt");
                break;
            case 3:
                cs.setV(1);
                cs.mid = MessageID.MSG;
                cs.setToAddr(AASIPaddr);
                cs.setMessage
                       ("Audit and Alert Subsystem Crash");
                break;
            case 4:
                cs.mid = MessageID.AUTH;
                cs.setToAddr(ACSIPaddr);
                cs.setUsername("admin");
                cs.setPassword("passWord");
                cs.setRole(SubsystemRoles.DATAANALYST);
                cs.setMessage("Authentication Request");
                break;
            case 5:
                cs.mid = MessageID.AUTH;
                cs.setToAddr(ACSIPaddr);
                cs.setUsername("admin");
                cs.setPassword("password");
                cs.setRole(SubsystemRoles.DATAANALYST);
                cs.setMessage("Authentication Request");
                break;
            case 6:
                cs.setV(0);
                cs.mid = MessageID.DGLOGSIN;
                cs.setMessage ("Here is another message you"
                    + "could write into the AAS Database");
                cs.setToAddr(AASIPaddr);
                break;
            case 7:
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
