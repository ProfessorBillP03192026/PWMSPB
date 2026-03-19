package Transport;

import java.util.*;
import javax.swing.*;
import java.io.File;
import java.net.InetAddress;

/********* Title: key server protocol project ************
 * 
 * Description of the Class or method purpose: This code is
 * for the client. Client gave a Command on runtime and they
 * could connect your server. only get run time variable 
 * from the RunTimeVars singleton object, not from the 
 * ConfigFile object. 
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillipse ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  P2PClient
 *********************************************************/

public class P2PClient implements Runnable
{

    private boolean suspended = true;

    private ClientTransactionLogger ctl = 
                         ClientTransactionLogger.Instance();
  
    private RunTimeVars   rtv =     RunTimeVars.Instance();
    private ClientServices cservices;

    //private CState cc = new CState();
    private CState cs;// = new CState();

    private boolean   isSearch;
    private boolean isRetrieve;
    private boolean  isPublish;
    
    private ClientCommandLineInterface cli;

    private String  hostName = 	RunTimeVars.Instance().
                                            getMyHostAddr();
    private String  fileName;
    private String  fileType;

    private final int MAINPORT = RunTimeVars.Instance().
                                            getServerPort();

    private ClientProcessing cpr;

    public P2PClient()
    {
        // Create a client services object.
        cservices = new ClientServices();

        cpr = new ClientProcessing(cservices);

        ctl.writeToLogger("Client Created");
        
        cli = new ClientCommandLineInterface
                                        (hostName, "11212");
    }

    public final void run()
    {
        // Get the command from the user input panel.
        ctl.writeToLogger("Client Started");
        CState cs = cli.getUserSelection();
        while(cs!=null)
        {
            if (cs.mid == MessageID.EPROTO)
            {
               ClientProcessing cp;
               cp = new ClientProcessing(cservices);
               cp.RKProto(cs);
            }
            else
            {
            // getClientCommand();
            
            // Connect to the main server.
               final String targetIP = cs.getToAddr();
               if (cservices.Connect(targetIP, MAINPORT))
               {
                // Send request.
                cservices.send(cs);
                //ProcessServerResponse();

                cservices.Disconnect();
            }
            }
            cs = cli.getUserSelection();
        } 
       System.exit(0);
    }

    public final void ProcessServerResponse()
    {
        // Process the response from the server.
        if      (isRetrieve) 
        {
        }
        else if (isSearch)   
        {
        }
        else
        {
           ctl.writeToLogger("Unknown command");
           System.exit(-1);
        }
    }

    public final void getClientCommand()
    {

    }

    private void waitForMessage( final long wt )
    {
        try
        {
            Thread.sleep( wt );
        }
        catch (Exception e)
        {
            System.err.println( "Wait failed" );
            System.exit( -1 );
        }
    }

    public boolean getRequest()
    {
        // Get the request from the user input panel, 
        //set up the message and log it.
        isSearch   = false;
        isRetrieve = false;
        isPublish  = false;

        return true;
    }
}
