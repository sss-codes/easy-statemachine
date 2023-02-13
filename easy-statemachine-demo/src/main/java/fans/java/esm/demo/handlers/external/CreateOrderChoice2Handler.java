package fans.java.esm.demo.handlers.external;

import fans.java.esm.core.builder.Action;
import fans.java.esm.core.builder.Choice;
import fans.java.esm.core.context.EsmContext;
import fans.java.esm.core.domain.EventUser;
import fans.java.esm.core.handlers.impl.AbstractExternalEsmHandler;
import fans.java.esm.demo.enums.EventEnum;
import fans.java.esm.demo.enums.StateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 下单2
 *
 * @author sss
 */
@Component
@Slf4j
public class CreateOrderChoice2Handler extends AbstractExternalEsmHandler<StateEnum, EventEnum, EsmContext, StateEnum> {

    @Override
    public StateEnum[] fromStates() {
        return new StateEnum[]{StateEnum.INIT};
    }

    @Override
    public StateEnum toState() {
        return StateEnum.CANCELED;
    }

    @Override
    public EventEnum onEvent() {
        return EventEnum.CREATE_ORDER;
    }

    @Override
    public Choice<StateEnum, EventEnum, EsmContext> choice() {
        return (from, to, event, context) -> false;
    }

    @Override
    public Action<StateEnum, EventEnum, EsmContext, StateEnum> action() {
        return (from, to, event, context) -> {
            EventUser eventUser = context.getEventUser();
            String indexId = context.getIndexId();
            log.info("{}下单2{}", eventUser.getOperatorName(), indexId);
            return to;
        };
    }
}
