package fans.java.esm.demo.handlers.external;

import fans.java.esm.core.builder.Action;
import fans.java.esm.core.context.EsmContext;
import fans.java.esm.core.domain.EventUser;
import fans.java.esm.core.handlers.impl.AbstractExternalEsmHandler;
import fans.java.esm.demo.enums.EventEnum;
import fans.java.esm.demo.enums.StateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 下单1
 *
 * @author sss
 */
@Component
@Slf4j
public class CreateOrderChoice1Handler extends AbstractExternalEsmHandler<StateEnum, EventEnum, EsmContext, String> {

    @Override
    public StateEnum[] fromStates() {
        return new StateEnum[]{StateEnum.INIT};
    }

    @Override
    public StateEnum toState() {
        return StateEnum.WAIT_PAY;
    }

    @Override
    public EventEnum onEvent() {
        return EventEnum.CREATE_ORDER;
    }

    @Override
    public Action<StateEnum, EventEnum, EsmContext, String> action() {
        return (from, to, event, context) -> {
            EventUser eventUser = context.getEventUser();
            String indexId = context.getIndexId();
            log.info("{}下单1{}", eventUser.getOperatorName(), indexId);
            return "下单1成功";
        };
    }

}
