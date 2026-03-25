package Presentation;

public interface ACSInterface
{
   public void msgReceived();

   public void   setUserName( String uN );
   public String getUserName();

   public void   setPassword(String pw);
   public String getPassword();

   public void   setRole(SubsystemRoles r);
   public SubsystemRoles getRole();

   public void setAuth();
   public void setNotAuth();
   public boolean getAuth();

   public void setDest(SubsystemEnums s);
   public SubsystemEnums getDest();
}
