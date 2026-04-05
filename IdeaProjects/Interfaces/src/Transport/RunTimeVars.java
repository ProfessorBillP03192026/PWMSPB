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
   

    private static RunTimeVars  rtvars            =  null;
    private static SubsystemEnums mySubsys        = null;
    private static String hostaddr                =  null;
    private  static boolean isguiinterface        = false;
    private static int         serverport         =     0;
    private ConfigFile cf  = ConfigFile.Instance();

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
 //           System.exit(0);
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

    public final String getTSTIP()
    {
        final String ip = cf.getIP(SubsystemEnums.TST);
        return ip;
    }
    public final int getTSTPort()
    {
        final int p = cf.getPort(SubsystemEnums.TST);
        return p;
    }
    public final String getDASIP()
    {
        final String ip = cf.getIP(SubsystemEnums.DAS);
        return ip;
    }
    public final int getDASPort()
    {
        final int p = cf.getPort(SubsystemEnums.DAS);
        return p;
    }

    public final String getDGSIP()
    {
        final String ip = cf.getIP(SubsystemEnums.DGS);
        return ip;
    }
    public final int getDGSPort()
    {
        final int p = cf.getPort(SubsystemEnums.DGS);
        return p;
    }

    public final String getAASIP()
    {   
       final String ip = cf.getIP(SubsystemEnums.AAS);
       return ip;
    }
    public final int getAASPort()
    {
        final int p = cf.getPort(SubsystemEnums.AAS);
        return p;
    }

    public final String getACSIP()
    {
        final String ip = cf.getIP(SubsystemEnums.ACS);
        return ip;
    }
    public final int getACSPort()
    {
        final int p = cf.getPort(SubsystemEnums.ACS);
        return p;
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
