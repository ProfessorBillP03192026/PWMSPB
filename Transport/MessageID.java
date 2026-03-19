package Transport;

import java.io.*;
/*********** Title: CS490 project ***********
 * 
 * Description of the Class or method purpose: The purpose 
 * of this program is to provide access to a user and verify
 * the user is authorized user or not.
 * 
 * Company: NJIT Fall 2022
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  MessageID
 **********************************************************/
public enum MessageID implements Serializable
{
    CONNECTING      ( "Connect Request"               ),
    MSG             ( "Message"                       ),
    AUTH            ( "Authenticate"                  ),
    AUTHRESP        ( "Authenticate Response"         ),
    DGLOGSIN        ( "Data Gatherer Logs In"         ),
    REKEYENC        ( "Rekey Encryptor"               ),
    REKEYDEC        ( "Rekey Decryptor"               ),
    REKEYEACK       ( "Rekey Encryptor ACK"           ),
    REKEYDACK       ( "Rekey Decryptor ACK"           ),
    EPROTO          ( "Entire Protocol"               ),
    RESTARTENC      ( "Restart Encryption"            );
    
    private final String message;

    private MessageID( final String msg ) 
    {
        message = msg;  
    }
    
    public  String getMessage()           
    {
        return message; 
    }
}

