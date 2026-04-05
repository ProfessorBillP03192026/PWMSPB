package Transport;

import java.net.*;
import java.io.*;
//import java.util.concurrent.*;
//import java.util.concurrent.locks.*;

/********** Title: key server protocol project ************
 * 
 * Description of the Class or method purpose: IO services 
 * for the server.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillips ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  ServerServices
 *********************************************************/

public class ServerServices
{
    private ObjectInputStream       input;
    private ObjectOutputStream      output;
    private Socket                  socket = new Socket();
    private ServerTransactionLogger stl;
    private int port;

    public Socket GetSocket()         
    {
        return socket; 
    }

    public ServerServices(final Socket S)
    {
        stl     = ServerTransactionLogger.Instance();
        socket = S;
    }

    public void getStreams()
    {
        try
        {
            output = new ObjectOutputStream
                                ( socket.getOutputStream());
            output.flush();
            input = new ObjectInputStream
                                ( socket.getInputStream() );
        }
        catch ( Exception e )
        {
            stl.writeToLogger(" Error getting i/o streams "
                                              + "(Server)");
        }
     stl.writeToLogger("Server thread connected to Client");
    }

    public Object get()
    {
        Object O = null;

        try
        {
            O = null;
            O = input.readObject();

            if (O == null)
            {
                stl.writeToLogger ( "Error reading object "
                        + "(Server)-- Sender terminated?" );
            }
        }
        catch ( Exception e )
        {
            stl.writeToLogger ( "Error reading object "
                        + "(Server)-- Sender terminated?" );
            e.printStackTrace();
        }
        return O;
    }

    public void send(final Object c)
    {
        try
        {
            output.reset();
            output.writeObject(c);
            output.flush();
        }
        catch ( Exception e )
        {
         stl.writeToLogger("Error sending object (Server)");
        }
    }

    public void Disconnect()
    {
        try
        {
            input.close();
            output.close();
            socket.close();
        }
        catch ( Exception e )
        {
         stl.writeToLogger("Error closing socket (Server)");
        }

        stl.writeToLogger("Server Disconnected" +
            System.getProperty("line.separator") + "*****");
    }
}



