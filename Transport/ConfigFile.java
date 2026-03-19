package Transport;
import java.util.*;
import java.io.*;

/****************** Title: key server protocol project *******************
 * 
 * Description of the Class or method purpose:This class retrieves 
 * configuration data. Stores it and makes it available.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Ohillipse ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  ConfigFile
 ************************************************************************/

public class ConfigFile
{
    private final   String     CONFIGFILE     = "ID.config";
    private static  ConfigFile cf             =        null;
 
    // The Singleton Design Pattern.
    public static ConfigFile Instance()
    {
        if ( cf == null ) cf = new ConfigFile();
        return cf;
    }

    public class ConfigRecord implements Serializable
    {
        final private String IPAddress;
        final private int           id;

        public ConfigRecord(int ID, String IP)
        {
            IPAddress = IP;
            id        = ID;
        }
        public final String getIP() 
        {
            return IPAddress; 
        }
        public final int    getId()        
        {
            return id; 
        }
    }

    private final ArrayList <ConfigRecord> cList;

    private ConfigFile()
    {
      this.cList = new   ArrayList<>();
        // Read the config file.
        if (this.readConfigFile(CONFIGFILE) == 0)
        {
            System.out.println
                ("\nProblem reading config file: " 
                                    + CONFIGFILE + "*****");
            System.exit( -1);
        }
    }

    synchronized private int readConfigFile
                                   ( final String fileName )
    {
        Scanner input = null;
        try
        {
            input = new Scanner(new File(fileName));
            while (input.hasNext())
            {
                final int id = input.nextInt();
                final String ip = input.next();
                final ConfigRecord cr = new
                                        ConfigRecord(id,ip);
                cList.add(cr);
            }
        }
        catch (Exception e) 
        {      
            cList.clear(); 
        }
        finally             
        {
            if (input != null) input.close(); 
        }
        return cList.size();
    }

    public final int     getFirstID() 
    {
        return cList.get(0).getId(); 
    }
    
    public final String  getFirstIP() 
    {
        return cList.get(0).getIP(); 
    }

//    synchronized final private ConfigRecord 
    //getConfigRecord(final int ID)
//    {
//        for( ConfigRecord c : cList )
//            if (c.getId() == ID) {return c;}
//        return null;
//    }
    
    synchronized final public String getIP( final int ID )
    {
        for( ConfigRecord c : cList )
            if (c.getId() == ID) 
            {
                return c.getIP();
            }
        return null;
    }
}
