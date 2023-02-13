package fans.java.esm.demo.controller;

import fans.java.esm.core.context.EsmContext;
import fans.java.esm.core.domain.EventUser;
import fans.java.esm.core.utils.EsmUtil;
import fans.java.esm.demo.enums.EventEnum;
import fans.java.esm.demo.enums.StateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ESM状态机测试
 *
 * @author SSS
 */
@RestController
@RequestMapping("/esm")
@Slf4j
public class EsmController {

    /**
     * 测试状态机
     */
    @GetMapping(value = "/testEsm")
    public String testEsm() {
        EventUser eventUser = new EventUser();
        eventUser.setOperatorCode("333L");
        eventUser.setOperatorName("张三");
        eventUser.setTargetUserCode("444L");
        eventUser.setTargetUserName("李四");
        EsmContext esmContext = new EsmContext(eventUser, "123", "备注");
        String o1 = EsmUtil.processEvent(StateEnum.INIT, EventEnum.CREATE_ORDER, esmContext);
        StateEnum o2 = EsmUtil.processEvent(StateEnum.WAIT_PAY, EventEnum.PAY, esmContext);
        String o3 = EsmUtil.processEvent(StateEnum.WAIT_PAY, EventEnum.MODIFY_INFO, esmContext);
        return "success";
    }
}
