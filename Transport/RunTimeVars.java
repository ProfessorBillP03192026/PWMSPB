package Transport;

import java.net.InetAddress;

 /******** Title: key server protocol project *************
 * 
 * Description of the Class or method purpose: Singleton 
 * used to hold run time variables for the Project.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillipse ( 214-36-930)
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
    private final static int encid = 4;
    private final static int decid = 5;
    
    private static RunTimeVars  rtvars            =  null;
    private String              hostaddr;
    private boolean             isguiinterface    = false;
    private int                 serverport        =     0;
    private ConfigFile          cf  = ConfigFile.Instance();
    
// private ConfigFile.ConfigRecord   prevneighbor   =  null;
// private ConfigFile.ConfigRecord   nextneighbor   =  null;

    // The Singleton Design Pattern.
    public static RunTimeVars Instance()
    {
        if ( rtvars == null ) rtvars = new RunTimeVars();
        return rtvars;
    }

    private RunTimeVars()
    {
       // Get this host's ID.
        try
        {
            hostaddr = InetAddress.getLocalHost().
                                           getHostAddress();
        
        }
        catch (Exception ex)
        {
           System.out.println("Error getting Host Address");
           System.exit(0) ;
        }
    }
    
    public String getMyHostAddr() 
    { 
        return hostaddr; 
    }
    public final void setServerPort( final int s )
    { 
        serverport = s;  
    }
    public final int getServerPort()
    {
        return serverport;
    }
    
    public final String getMainClientIP()
    {   
       final String ip;
        ip = cf.getIP(mid);
       return ip;
    }
    
    public final String getAASIP()
    {   
       final String ip = cf.getIP(aasid);
       return ip;
    }
    
    public final String getACSIP()
    {   
       final String ip = cf.getIP(acsid);
       return ip;
    }
    
    public final String getENCIP()
    {   
       final String ip = cf.getIP(encid);
       return ip;
    }           
            
    public final String getDECIP()
    {   
       final String ip = cf.getIP(decid);
       return ip;
    } 
                
//    synchronized public final void setPrev( final 
    //ConfigFile.ConfigRecord p )
 //       { prevneighbor = p;   }
//    synchronized public final ConfigFile.ConfigRecord  
    //getPrev()
//        { return prevneighbor;}
//    synchronized public final void setNext( final 
    //ConfigFile.ConfigRecord p )
//        { nextneighbor = p;   }
 //   synchronized public final ConfigFile.ConfigRecord 
    //getNext()
//        { return nextneighbor;}

    public final void setIsGUIInterface( final boolean f )
    {
        isguiinterface = f; 
    }
    public final boolean isGUIInterface()
    {
        return isguiinterface;
    }

    

}
