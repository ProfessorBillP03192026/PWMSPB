package Presentation;

public class AASBoundary
{

   private AASInterface AASConnector;

   public AASBoundary(AASInterface a)
   {
      AASConnector = a;
   }

   public AASInterface getConnector()
   {
      return AASConnector;
   }

}

