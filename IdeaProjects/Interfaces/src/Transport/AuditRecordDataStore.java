package Transport;

/******** Title: key server protocol project **********
 * 
 * Description of the Class or method purpose: This program
 * is to store the messages obtained from the authorized 
 * user.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillips ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  AuditRecordDataStore
 ******************************************************/

public class AuditRecordDataStore 
{
    private static  AuditRecordDataStore ad = null;
    
    public static AuditRecordDataStore Instance()
    {
        if ( ad == null ) ad = new AuditRecordDataStore();
        return ad;
    }  
    
    public void storeAuditRecord(CState cs)
    {
       String s = cs.getMessage();
       ServerTransactionLogger.Instance().writeToLogger
                                         ("Message Stored");
       ServerTransactionLogger.Instance().writeToLogger(s);
       if (cs.getV() !=0 )
       {
           ServerTransactionLogger.Instance().writeToLogger
                           ("******A L E R T A B L E*****");
           SReg.Instance().notifyAll(s);
       }
    }
}
