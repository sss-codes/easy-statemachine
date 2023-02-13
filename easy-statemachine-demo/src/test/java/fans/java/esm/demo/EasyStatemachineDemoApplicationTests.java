package fans.java.esm.demo;

import fans.java.esm.core.context.EsmContext;
import fans.java.esm.core.domain.EventUser;
import fans.java.esm.core.utils.EsmUtil;
import fans.java.esm.demo.enums.EventEnum;
import fans.java.esm.demo.enums.StateEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EasyStatemachineDemoApplicationTests {

    @Test
    void contextLoads() {
        EventUser eventUser = new EventUser();
        eventUser.setOperatorCode("333L");
        eventUser.setOperatorName("张三");
        eventUser.setTargetUserCode("444L");
        eventUser.setTargetUserName("李四");
        EsmContext esmContext = new EsmContext(eventUser, "123", "备注");
        String o1 = EsmUtil.processEvent(StateEnum.INIT, EventEnum.CREATE_ORDER, esmContext);
        StateEnum o2 = EsmUtil.processEvent(StateEnum.WAIT_PAY, EventEnum.PAY, esmContext);
        String o3 = EsmUtil.processEvent(StateEnum.WAIT_PAY, EventEnum.MODIFY_INFO, esmContext);
    }

}
