package Transport;

import com.sun.jdi.connect.*;

import java.io.*;
/*********** Title: CS490 project ***********
 * 
 * Description of the Class or method purpose: The purpose 
 * of this program is to provide access to a user and verify
 * the user is authorized user or not.
 * 
 * Company: NJIT Fall 2022
 * 
 * @author Bill Phillips 
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  CState
 ********************************************************/

public class CState implements Serializable
{
    private String   toaddr   =    null;
    private String   hostname =    null;
    private String   filename =    null;
    private String   filetype =    null;
    private String   message  =    null;
    private String   username =    null;
    private String   password = null;
    private SubsystemEnums   role     = SubsystemEnums.TST;
    private int      v        =    0;
    private long     fsize    =    0;

    public  String[]  files;
    public  String[]  types;
    public  boolean[] flags;
    public  int[]      vals;

    public CState(){}

    public final void setV( final int x )                     
    {
        v = x;
    }
    public final int  getV()                                
    {
        return v;
    }

    public final void setFilesize( final long x )        
    { 
        fsize = x;
    }
    public final long getFilesize()                   
    {
        return fsize;
    }

    public final String getMessage()                
    {
        return message;
    }
    public final void   setMessage(final String M )    
    {
        message = M;
    }
    
    public final String getFilename()             
    {
        return filename;
    }
    public final void   setFilename(final String S )  
    {
        filename = S; 
    }

    public final String getFiletype()               
    { 
        return filetype;
    }
    public final void   setFiletype( final String S ) 
    {
        filetype = S; 
    }

    public final String getHostname()              
    {
        return hostname;
    }
    public final void   setHostname( final String S ) 
    {
        hostname = S; 
    }
    
    public final String getToAddr()                   
    {
        return toaddr;
    }
    public final void   setToAddr( final String S )     
    { 
        toaddr = S; 
    }
    
    public final String getUsername()              
    {
        return username;
    }
    public final void   setUsername( final String S )
    {
        username = S; 
    }
    
    public final String getPassword()              
    {
        return password;
    }
    public final void   setPassword( final String S ) 
    {
        password = S; 
    }
    
    public final SubsystemEnums getRole()
    {
        return role;
    }
    public final void   setRole( final SubsystemEnums S )
    {
        role = S; 
    }
    
    public MessageID mid;

}
