package Presentation;

public interface AASInterface
{
   public void msgReceived();

   public void sendMsg();

   public void setAlertable();
   public boolean getAlertable();

   public void setMsg( String msg );
   public String getMsg();

   public void clear();

   public void setDest(SubsystemEnums s);
   public SubsystemEnums getDest();


}
