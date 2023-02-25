package fans.java.esm.demo.handlers.internal;

import fans.java.esm.core.builder.Action;
import fans.java.esm.core.context.EsmContext;
import fans.java.esm.core.domain.EventUser;
import fans.java.esm.core.handlers.impl.AbstractInternalEsmHandler;
import fans.java.esm.demo.enums.EventEnum;
import fans.java.esm.demo.enums.StateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 修改资料
 *
 * @author sss
 */
@Component
@Slf4j
public class ModifyInfoHandler extends AbstractInternalEsmHandler<StateEnum, EventEnum, EsmContext, String> {
    @Override
    public StateEnum[] withinStates() {
        return new StateEnum[]{StateEnum.WAIT_PAY, StateEnum.PAYED};
    }

    @Override
    public EventEnum onEvent() {
        return EventEnum.MODIFY_INFO;
    }

    @Override
    public Action<StateEnum, EventEnum, EsmContext, String> action() {
        return (from, to, event, context) -> {
            EventUser eventUser = context.getEventUser();
            String indexId = context.getIndexId();
            log.info("{}修改资料{}", eventUser.getOperatorName(), indexId);
            return "修改资料成功";
        };
    }
}
