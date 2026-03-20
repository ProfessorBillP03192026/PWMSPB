package Presentation;

public class AASConnector implements AASInterface
{
   private SubsystemEnums dest;

   @Override
   public void msgReceived(){}

   @Override
   public void sendMsg(){}

   @Override
   public void setAlertable(){}
   @Override
   public boolean getAlertable(){return false;}

   @Override
   public void setMsg( String msg ){}
   @Override
   public String getMsg(){return null;}

   @Override
   public void clear(){}

   @Override
   public void setDest(SubsystemEnums s) {dest = s;}
   @Override
   public SubsystemEnums getDest(){return dest;}

}
