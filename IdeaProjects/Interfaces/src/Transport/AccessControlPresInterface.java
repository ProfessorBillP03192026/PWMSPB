package Transport;

import Presentation.*;

/*********** Title: key server protocol project ***********
 * 
 * Description of the Class or method purpose: The purpose 
 * of this program is to provide access to a user and verify
 * the user is authorized user or not.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillips ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  AccessControlPresInterface
 *********************************************************/

public class AccessControlPresInterface
{
   
    private static AccessControlPresInterface ac = null;
    
    public static AccessControlPresInterface Instance()
    {
        if ( ac == null ) ac = new AccessControlPresInterface();
        return ac;
    }  
    
    private AccessControlPresInterface()
    {
    }
    
    public boolean Authenticate(CState cs)
    {
       String s = cs.getMessage();
       ServerTransactionLogger.Instance().writeToLogger
                                    ("Message Received: ");
       ServerTransactionLogger.Instance().writeToLogger(s);

       ACSBoundary aB = ACSBoundary.Instance();

       aB.setValues(cs);

       final boolean authorized = aB.getAuth();
       
       if (authorized)
            ServerTransactionLogger.Instance().writeToLogger
                                            ("Authorized");
       else
            ServerTransactionLogger.Instance().writeToLogger
                                        ("NOT Authorized");   
       
       return authorized; 
    }
}

