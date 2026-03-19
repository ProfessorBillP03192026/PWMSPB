package Transport;
import Transport.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.File;
import java.util.Scanner;

/*********** Title: key server protocol project ************
 * 
 * Description of the Class or method purpose: This is main 
 * for the program. It creates the client and server as 
 * separate runnable and executes them.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillips ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  P2PProject
 **********************************************************/

public class P2PProject
{

    public static void main( String[] args )
    {
        final int BASEPORT   = 11212;

        RunTimeVars.Instance().setServerPort(BASEPORT);


        // Anything on the command line means
        // run with GUI.
        RunTimeVars.Instance().setIsGUIInterface
                                          (args.length > 0);
        // Only one thread needed for the client.
        ExecutorService CandS = Executors.newFixedThreadPool(1);

        // Create the client.
        P2PClient pcm = new P2PClient();

        // Execute the client as a separate thread.
        CandS.execute(pcm);
        
        // Create the server
        P2PServer psm  =  new P2PServer(BASEPORT, null);

        // Start the server.
        psm.start();
    }
}
