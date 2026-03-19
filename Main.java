//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import Presentation.*;
import Transport.*;
class TestProg
{
   static public void main() {


      ACSBoundary aasB = ACSBoundary.Instance();

      CState cs = new CState();

      cs.setUsername("Bill");
      cs.setPassword("password");
      cs.setRole(SubsystemEnums.TST);

      aasB.setValues(cs);

      cs.setUsername("Billx");
      cs.setPassword("password");
      cs.setRole(SubsystemEnums.TST);

      aasB.setValues(cs);
   }

   static public void processInputs(ACSConnector aC)
   {
      final String uN1 =       aC.getUserName();
      final String pw1 =       aC.getPassword();
      final SubsystemEnums role1 = aC.getRole();
      final String uN2 =                 "Bill";
      final String pw2 =             "password";
      final SubsystemEnums role2 =
                             SubsystemEnums.TST;
      if ((uN1 != uN2) ||
          (pw1 != pw2) ||
          (role1 != role2)) aC.setNotAuth();
      else
         aC.setAuth();

   }
}
