package Presentation;

public class ACSConnector implements ACSInterface
{
   private String     userName;
   private String     passWord;
   private SubsystemRoles role;
   private Boolean        auth;
   private SubsystemEnums dest;
   private final boolean DBG = false;
   private ACSBoundary aB = null;

   public ACSConnector()
   {}

   /**
    *  When an authorization
    *  request is received, this
    *  function will be called.
    *  In my example I am calling
    *  TestProg.processInputs.
    *  You should remove this call
    *  and call your own
    *  authorization function.
    */
   @Override
   public void msgReceived()
   {
      if (DBG) System.out.println("Msg processing");
      //
      // Run test.
      //
      if (aB == null) aB = ACSBoundary.Instance();
      aB.processInputs(this);
   }

   /**
    * The functions below are the
    * functions you need.  You will
    * get the UserName, Password and
    * Role using these functions, and
    * you will call setAuth or
    * setNoAuth depending on whether
    * the user is authorized or
    * not authorized.
    */
   @Override
   public String getUserName(){ return userName; }
   @Override
   public String getPassword(){ return passWord; }
   @Override
   public SubsystemRoles getRole(){ return role; }
   @Override
   public void setAuth()
   {
      auth = true;
      if (DBG) System.out.println("Auth sent");
   }
   @Override
   public void setNotAuth()
   {
      auth = false;
      if (DBG) System.out.println("NOT Auth sent");
   }
   public SubsystemEnums getDest()
   {
      return dest;
   };

   /**
    * These functions you should
    * not modify. These are used
    * to set the UserName, Password
    * and role to be authenticated.
    *
    */
   @Override
   public void setPassword(String pw)
   {
      this.passWord = pw;
   }
   @Override
   public void setRole(SubsystemRoles r)
   {
      this.role = r;
   }
   @Override
   public void setUserName(String userName)
   {
      this.userName = userName;
   }

   @Override
   public boolean getAuth()
   {
      return auth;
   }

   @Override
   public void setDest(SubsystemEnums s)
   {
      dest = s;
   };


}
