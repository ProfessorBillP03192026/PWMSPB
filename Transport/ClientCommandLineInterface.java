package Transport;

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
//    private final ConfigFile  cf  = ConfigFile.Instance();

    private int    panelselection =    0;

    private String  filename;
    private String  filetype;
    private String sHostname;
    private String         S;

    private final String hostaddr   = rtv.getMyHostAddr();
    private final int    portnumber = rtv.getServerPort();
    private final String mainCAddr = rtv.getMainClientIP();
    private final String AASIPaddr = rtv.getAASIP();
    private final String ACSIPaddr = rtv.getACSIP();
    private final String ENCIPaddr = rtv.getENCIP();
    private final String DECIPaddr = rtv.getDECIP();
    private final int MAXVAL = 11;    
    
    public final void writeStatusMessage(final String S)
    {
        System.out.println(S);
    }

    //
    // Methods to retrieve data from the input fields.
    //
    public final String getFileName()  {return filename;}
    public final String getFileType()  {return filetype;}
    public final String getHostName() {return sHostname;}
    public final int    getPort()    {return portnumber;}

    // Methods to return an int depending on the user selection.
    public final boolean isPublish()  
    {
        return (panelselection==1);
    }
    public final boolean isSearch()   
    {
        return (panelselection==2);
    }
    public final boolean isRetrieve() 
    {
        return (panelselection==3);
    }
    public final boolean isLeave()    
    {
        return (panelselection==4);
    }

    private String prompt;
    private Scanner sc;
    private int sel;
    
    public ClientCommandLineInterface(final String hname, 
                                             final String m)
    {
        //sHostname = hname;
        prompt =
                "Host: " + hostaddr +
                "Node ID: " + m + "\n" +
                "1) Data Analyst Log In (not alertable)\n" +
                "2) Data Gathering Subsystem Intrusion "
                                  + "Attempt(alertable)\n" +
                "3) Audit and Alert Subsystem Crash "
                                         + "(alertable)\n" +
                "4) Valid authentication\n" +
                "5) Invalid authentication\n"  +
                "6) Data Gatherer Log In\n" +
                "7) Send Rekey to ENC\n" +
                "8) Send Rekey to DEC\n" +
                "9) Send Restart to ENC\n" +
                "10) Entire Protocol\n"  +
                "11) Leave\n\n";  
        
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

       

      
       cs.setHostname(mainCAddr);
       
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
                cs.setRole(SubsystemEnums.TST);
                cs.setMessage("Authentication Request");
                break;
            case 5:
                cs.mid = MessageID.AUTH;
                cs.setToAddr(ACSIPaddr);
                cs.setUsername("admin");
                cs.setPassword("password");
                cs.setRole(SubsystemEnums.TST);
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
                cs.mid = MessageID.REKEYENC;
                cs.setMessage("Keys");
                cs.setToAddr(ENCIPaddr);
                break;
            case 8:
                cs.mid = MessageID.REKEYDEC;
                cs.setMessage("Keys");
                cs.setToAddr(DECIPaddr);
                break;
            case 9:
                cs.mid = MessageID.RESTARTENC;
                cs.setToAddr(ENCIPaddr);
                break;
            case 10:
                cs.mid = MessageID.EPROTO;
                cs.setToAddr(ENCIPaddr);
                break;
            case 11:
                cs=null;
                break;
                 //System.exit(0);
            default:
                System.out.println("Invalid Selection");
                cs= null;
                break;
                //System.exit(0);
        }
       
       return cs;
    }
}
