//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import Presentation.*;
import Transport.*;


class TestProg
{
   static public void main() {

      ACSInterface aa = new ACSConnector();
      aa.setUserName("XXX");
      aa.setPassword("ZZZ");
      aa.setRole(SubsystemRoles.DATAANALYST);
      aa.setDest(SubsystemEnums.DAS);
      aa.msgReceived();

      ACSBoundary aasB = ACSBoundary.Instance();

      CState cs = new CState();

      cs.setUsername("Bill");
      cs.setPassword("password");
      cs.setRole(SubsystemRoles.DATAANALYST);

      aasB.setValues(cs);

      cs.setUsername("Bill");
      cs.setPassword("password");
      cs.setRole(SubsystemRoles.DATAANALYST);

      aasB.setValues(cs);
   }

}
