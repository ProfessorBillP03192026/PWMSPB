package Transport;

import Presentation.*;

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
    private String   password =    null;
    private SubsystemRoles role =
                     SubsystemRoles.NONE;
    private SubsystemEnums dest =
                      SubsystemEnums.TST;
    private int      v        =        0;
    private long     fsize    =        0;

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

    public final String getMessage()                
    {
        return message;
    }
    public final void   setMessage(final String M )    
    {
        message = M;
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
    
    public final SubsystemRoles getRole()
    {
        return role;
    }
    public final void   setRole( final SubsystemRoles S )
    {
        role = S; 
    }

    public final SubsystemEnums getDest()
    {
        return dest;
    }
    public final void   setDest( final SubsystemEnums S )
    {
        dest = S;
    }
    
    public MessageID mid;

}
