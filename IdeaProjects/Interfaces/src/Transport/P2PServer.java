package Transport;

import Presentation.*;

import java.util.*;
import java.net.*;
import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.InetAddress;

/*********** Title: key server protocol project ***********
 * 
 * Description of the Class or method purpose: This is for 
 * the server response to their Client on runtime.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillips ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  P2PServer
 *********************************************************/
public class P2PServer implements Runnable
{
    private ServerSocket                 server;
    private Socket                       socket;
    private ServerTransactionLogger         stl;
    private ServerServices           smservices;

    private CState cs;

    private ExecutorService Sservice;

    private static int                  NUMTHREADS   =   40;
    private int                         port;

    public P2PServer(int p, Socket s)
    {
        port   = p;
        socket = s;
        stl    = ServerTransactionLogger.Instance();
        stl.writeToLogger("Server Created. Port: "
                                + String.format("%d",port));
    }

    public void start()
    {
        Sservice = Executors.newFixedThreadPool(NUMTHREADS);

        stl.writeToLogger("Server Started");
        try
        {
            server = new ServerSocket(port, NUMTHREADS);
        }
        catch (Exception e)
        {
         stl.writeToLogger("Error creating server socket" );
         System.exit(-1);
        }

        do
        {
            // Connect to the client.
            try
            {
               stl.writeToLogger("Connecting -- waiting for"
                        + " client on port ", port);
                                                                        

               socket = server.accept();
               stl.writeToLogger("Accepted client on port ",
                                                      port);

            }
            catch ( Exception e )
            {
            stl.writeToLogger("Error connecting to client");
              System.exit(-1);
            }

            //Create a server thread to process the request.
            Sservice.execute(new P2PServer(port, socket));

        } while (true);
    }

    public void run()
    {
        stl.writeToLogger("Server thread started.");
        smservices = new ServerServices(socket);

        smservices.getStreams();

        // Process the client command.
        try
        {
           cs = (CState)smservices.get();
           ProcessClientResponse();

           smservices.Disconnect();
        }
        catch (Exception e)
        {
            SReg.Instance().add(smservices);
        }
    }

    public final void ProcessClientResponse() 
    {
        try
        {
            stl.writeToLogger("Server received " +
                                       cs.mid.getMessage());
        }
        catch (Exception e)
        {
            stl.writeToLogger("No request received (telnet"
                                               + "test?) ");
            return;
        }
        
        // Process the request.
         if (cs.getRole() != SubsystemRoles.NONE)
           new    ServerProcessing(smservices).
                                         processMessage(cs);
    }
}
