package Transport;

import java.util.ArrayList;

/*********** Title: key server protocol project ***********
 * 
 * Description of the Class or method purpose: Singleton 
 * used to hold run time variables for the Project.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillipse ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  SReg
 *********************************************************/

public class SReg 
{
    private ArrayList<ServerServices> sSto; 
    
    private static  SReg ad = null;
    
    public SReg() {sSto = new ArrayList<ServerServices>();}
    
    public static SReg Instance()
    {
        if ( ad == null ) ad = new SReg();
        return ad;
    }  
    
    public void add(ServerServices s)
    {
        sSto.add(s);
    }
    
    public void notifyAll(String x)
    {
        sSto.stream().forEach((s) -> {s.send(x);});   
    }  
}