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
 * 支付
 *
 * @author sss
 */
@Component
@Slf4j
public class PayHandler extends AbstractExternalEsmHandler<StateEnum, EventEnum, EsmContext, StateEnum> {
    @Override
    public StateEnum[] fromStates() {
        return new StateEnum[]{StateEnum.WAIT_PAY};
    }

    @Override
    public StateEnum toState() {
        return StateEnum.PAYED;
    }

    @Override
    public EventEnum onEvent() {
        return EventEnum.PAY;
    }

    @Override
    public Action<StateEnum, EventEnum, EsmContext, StateEnum> action() {
        return (from, to, event, context) -> {
            EventUser eventUser = context.getEventUser();
            String indexId = context.getIndexId();
            log.info("{}付款{}", eventUser.getOperatorName(), indexId);
            return to;
        };
    }
}
