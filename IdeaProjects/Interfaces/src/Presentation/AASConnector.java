package Presentation;

public class AASConnector implements AASInterface
{
   private SubsystemEnums dest;
   private static String           ms;
   private static boolean           v;
   AASBoundary aasb = null;
   @Override
   public void msgReceived(){}

   @Override
   public void sendMsg()
   {
      if(aasb == null) aasb = AASBoundary.Instance();
      aasb.sendMsg();
   }

   @Override
   public void setAlertable()
      { v = true; }

   @Override
   public boolean getAlertable()
   {
      final boolean b = v;
      v =           false;
      return            b;
   }

   @Override
   public void setMsg( String msg )
   {
      ms = msg;
   }
   @Override
   public String getMsg()
   {
      return ms;
   }

   @Override
   public void setDest(SubsystemEnums s) {dest = s;}
   @Override
   public SubsystemEnums getDest(){return dest;}

}
