package Transport;

import java.io.*;
import java.text.*;
import java.util.*;
/********* Title: key server protocol project *************
 * 
 * Description of the Class or method purpose: Simple logger
 * simply gave a current Date and time for the fileWriter.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillips ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see http://www.rgagnon.com/javadetails/java-0063.html
 * 
 * @see  SimpleLog
 *********************************************************/

public class SimpleLog
{
    private final static DateFormat df = new SimpleDateFormat
                                  ("yyyy.MM.dd  hh:mm:ss ");

    synchronized public static void write
                      ( final String file, final String msg)
    {
        try
        {
            Date now = new Date();
            String currentTime = SimpleLog.df.format(now);
            FileWriter aWriter = new FileWriter(file, true);
            aWriter.write(currentTime + " " + msg
                    + System.getProperty("line.separator"));
            aWriter.flush();
            aWriter.close();
        }
        catch (Exception e)
        {
          System.out.println("Could not write to log file");
          System.exit(-1);
        }
    }
}
