package Transport;
import Presentation.*;

import java.util.*;
import java.io.*;

/** **************** Title: CSCI121 *******************
 *
 * This class reads a config file containing an ID
 * an IP address and a port number for each subsystem
 * of the Predictive Wastewater Management System
 *
 *
 * Company: Moravian University
 *
 * @author <your name>
 *
 * @version $ Revision log: 1.0
 *
 * @see  ConfigFile
 ************************************************************************/
//
//  The configuration file should contain records:
//       ID | IP | Port where:
//           ID is a SubsystemEnum equal to one of: TST, AAS, ACS, DGS, DAS
//           IP is a String
//           Port is a positive int > 0
//
//  TO DO:
//      Modify the code to make ID a SubsystemEnum instead of an int
//         you will need to read a String from the ConfigFile and
//            convert it to a SubsystemEnum
//                If ID is "TST"  store SubsysteEnums.TST to the
//                    ArrayList of Config Records.
//                      Identify any errors and you can terminate.
//      Modify configRecord for the above
//      Implement getPort to return the port number for an ID
//      Modify getIP to accept a string ID instead of an int
//      Add javadoc comments to heading and each function.
//      Create a UML class diagram using the astah UML tool
//          turn this in along with your test driver and code
//
//

public class ConfigFile
{
   private static String configFile = "ID.config";
   private static ConfigFile cf = null;
   private static SubsystemEnums mySubsys = null;

   public static void chgFilename(String fN)
   {
      configFile = fN;
   }

   //
   // The Singleton Design Pattern.
   //
   public static ConfigFile Instance()
   {
      if (cf == null) cf = new ConfigFile();
      return cf;
   }

   public class ConfigRecord
   {
      final private int port;
      final private String IPAddress;
      final private SubsystemEnums id;

      public ConfigRecord(SubsystemEnums iD, String ip, int p)
      {
         id = iD;
         IPAddress = ip;
         port = p;
      }

      public String getIP()
      {
         return IPAddress;
      }
      public int getPort()  { return port; }

      public SubsystemEnums getId() {
         return id;
      }
   }

   private final ArrayList<ConfigRecord> cList;

   private ConfigFile()
   {

      this.cList = new ArrayList<>();

      //
      // Read the config file.
      //
      if (this.readConfigFile(configFile) == 0)
      {
         System.out.print("\nProblem reading config file: ");
         System.out.print(configFile);
         System.out.println(" *****");
         System.exit(-1);
      }

   }

   private SubsystemEnums checkID(String id)
   {
      SubsystemEnums se = SubsystemEnums.NUL;
      try
      {
         se = SubsystemEnums.valueOf(id);
      }
      catch (Exception e)
      {
         System.out.print("No such enum: ");
         System.out.println(id);
         System.exit(0);
      }
      if (se == SubsystemEnums.NUL)
      {
         System.out.println("NUL not allowed");
         System.exit(0);
      }
      return se;
   }

   private void checkPort(int p)
   {
      final int LOW  = 1000;
      final int HIGH = 2000;
      if (( p < LOW ) || ( p > HIGH ))
      {
         System.out.print ("Port out of range: ");
         System.out.println(p);
      }
   }

   private int readConfigFile(final String fileName)
   {
      Scanner input = null;
      try
      {
         input = new Scanner(new File(fileName));
         boolean firstRec = true;
         while (input.hasNext())
         {
            if (firstRec)
            {
               final String m = input.next();
               mySubsys       =   checkID(m);
               firstRec = false;
            }
            final String id = input.next();
            SubsystemEnums ie = checkID(id);
            final String ip = input.next();
            final int port = input.nextInt();
            checkPort(port);
            final ConfigRecord cr =
                  new ConfigRecord(ie, ip, port);
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
      final int sz = cList.size();
      return sz;

   }

   synchronized final public SubsystemEnums getMySubsys()
   {
      return mySubsys;
   }

   synchronized final public int getPort(final SubsystemEnums ID)
   {
      for (ConfigRecord c : cList)
         if (c.getId() == ID)
            return c.getPort();
      return -1;
   }


   synchronized final public String getIP(final SubsystemEnums ID)
   {
      for (ConfigRecord c : cList)
         if (c.getId() == ID)
            return c.getIP();
      return null;
   }
}
