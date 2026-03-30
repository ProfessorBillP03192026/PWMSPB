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
    private final int    AASPort    = rtv.getAASPort();
    private final int    ACSPort    = rtv.getAASPort();
    private final String AASIPaddr  = rtv.getAASIP();
    private final String ACSIPaddr  = rtv.getACSIP();
    private final static int MAXVAL = 3;
    


    private String prompt;
    private Scanner sc;
    private int sel;
    
    public ClientCommandLineInterface(final String hname, 
                                             final int m)
    {

        prompt =
                "Host: " + hostaddr +
                " Node ID: " + m + "\n" +
                "1) Data Analyst Successful Log In (not alertable)\n" +
                "2) Data Analyst Unsuccessful Log In "
                                  + "Attempt (not alertable)\n" +
                "3) Leave\n\n";
        
        sc = new Scanner(System.in);
    }

    public void serverOnly()
    {
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

        //
         // TODO Figure out return mechanism
        //
       final String dest = rtv.getMyHostAddr();
       cs.setHostname(dest);
       
        switch (sel) {
            case 1:
                cs.setV(0);
                cs.mid = MessageID.AUTH;
                cs.setToAddr(AASIPaddr);
                cs.setPort(AASPort);
                cs.setDest(SubsystemEnums.DAS);
                cs.setRole(SubsystemRoles.DATAANALYST);
                cs.setUsername("Bill");
                cs.setPassword("password");
                cs.setMessage("Data Analyst Log In");
                break;
            case 2:
               cs.setV(0);
               cs.mid = MessageID.AUTH;
               cs.setToAddr(AASIPaddr);
               cs.setPort(AASPort);
               cs.setDest(SubsystemEnums.DAS);
               cs.setRole(SubsystemRoles.DATAANALYST);
               cs.setUsername("Billx");
               cs.setPassword("password");
               cs.setMessage("Data Analyst NOT Logged In");
               break;
            case 3:
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
