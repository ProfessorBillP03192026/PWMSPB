package Transport;

import javax.swing.JFrame;
import java.net.*;
import java.io.*;

 /********* Title: key server protocol project ************
 * 
 * Description of the Class or method purpose: 
 * TransactionLogger outputs messages to the Transaction 
 * Output Panel and or the log file.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillipse ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  ServerTransactionLogger
 *********************************************************/
public class ServerTransactionLogger
{

    private String localhost;
    private String  filename;

    public final static int XLOC   = 240;
    public final static int YLOC   = 550;
    public final static int WIDTH  = 600;
    public final static int HEIGHT = 200;

    private boolean isguiinterface = RunTimeVars.Instance()
                                          .isGUIInterface();

    private static  TransactionOutputPanel  topanel;
    private static  ServerTransactionLogger stolgger = null;

    // The Singleton Design Pattern.
    public static ServerTransactionLogger Instance()
    {
        if ( stolgger == null ) stolgger = 
                              new ServerTransactionLogger();
        return stolgger;
    }

    synchronized public void writeToLogger( final String S )
    {
        if (isguiinterface)
        {
            topanel.setText(S);
            topanel.repaint();
        }
        SimpleLog.write(filename,S);
    }

    public void writeToLogger( final String S, final int i )
    {
        writeToLogger(String.format("%s %d", S, i));
    }

    private ServerTransactionLogger()
    {
        try
        {
       localhost = InetAddress.getLocalHost().getHostName();
        }
        catch (Exception e)
        {
        }

        if ( isguiinterface )
        {

            topanel = new TransactionOutputPanel(
                    "Server Transactions for host: " + 
                                         localhost, 20, 80);
            topanel.setDefaultCloseOperation
                               (JFrame.DO_NOTHING_ON_CLOSE);
            topanel.setSize(WIDTH, HEIGHT);
            topanel.setLocation(XLOC, YLOC);
            topanel.setResizable(true);
            topanel.setVisible(true);
        }

        // Create the log file name.
        // If a log file exists, delete it.
        filename = "ServerOutputLog" + "-From-" + localhost
                                                   + ".txt";
        File f = new File(filename);
        if (f.exists()) f.delete();
    }
}
