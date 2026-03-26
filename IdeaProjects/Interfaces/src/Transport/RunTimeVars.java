package Transport;


import Presentation.*;
import com.sun.security.auth.login.*;

import java.net.InetAddress;

 /******** Title: key server protocol project *************
 * 
 * Description of the Class or method purpose: Singleton 
 * used to hold run time variables for the Project.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillips ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  RunTimeVars
 *********************************************************/

public class RunTimeVars
{
    // This singleton object stores run time values.
    // Since it's a singleton, all objects have access
    // without having to pass a pointer around.
   
    private final static int mid = 1;
    private final static int aasid = 2;
    private final static int acsid = 3;
    
    private static RunTimeVars  rtvars            =  null;
    private static SubsystemEnums mySubsys        = null;
    private static String hostaddr                =  null;
    private  static boolean isguiinterface        = false;
    private static int         serverport         =     0;
    private ConfigFile cf  = ConfigFile.Instance();
    private final static String acsIP             = null;
    private final static String aasIP             = null;
    // The Singleton Design Pattern.
    public static RunTimeVars Instance()
    {
        if ( rtvars == null ) rtvars = new RunTimeVars();
        return rtvars;
    }

    private RunTimeVars()
    {
        try
        {
            hostaddr = InetAddress.getLocalHost().
                                  getHostAddress();
        }
        catch (Exception ex)
        {
           System.out.println
                 ("Error getting Host Address");
           ex.printStackTrace(); ;
        }
        mySubsys          =     cf.getMySubsys();
        String hostaddr2  =   cf.getIP(mySubsys);

        if (hostaddr.equals(hostaddr2) == false)
        {
            System.out.print("Host address ");
            System.out.print(hostaddr);
            System.out.print(" does not match ");
            System.out.print(hostaddr2);
            System.out.println(" in Config File");
            System.exit(0);
        }

        serverport = cf.getPort(mySubsys);
    }
    
    public String getMyHostAddr() 
    { 
        return hostaddr; 
    }

    public final int getServerPort()
    {
        return serverport;
    }
    
    public final String getMainClientIP()
    {
       return hostaddr;
    }



    public final String getAASIP()
    {   
       final String ip = cf.getIP(mySubsys);
       return ip;
    }
    
    public final String getACSIP()
    {   
//       final String ip = cf.getIP(acsid);
       return null;
    }

    public final void setIsGUIInterface( final boolean f )
    {
        isguiinterface = f; 
    }
    public final boolean isGUIInterface()
    {
        return isguiinterface;
    }

    public SubsystemEnums getMySubsys()
    {
        return mySubsys;
    }
}
