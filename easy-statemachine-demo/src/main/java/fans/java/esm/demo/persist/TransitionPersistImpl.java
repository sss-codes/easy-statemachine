package fans.java.esm.demo.persist;

import fans.java.esm.core.context.EsmContext;
import fans.java.esm.core.persistence.TransitionPersistInterface;
import fans.java.esm.demo.enums.EventEnum;
import fans.java.esm.demo.enums.StateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 状态记录
 *
 * @author SSS
 */
@Component
@Slf4j
public class TransitionPersistImpl implements TransitionPersistInterface<StateEnum, EventEnum, EsmContext> {
    @Override
    public void beforeTransit(StateEnum sourceState, StateEnum targetState, EventEnum event, EsmContext context) {
        log.info("TransitionPersistImpl-beforeTransit,{},{},{},{}", sourceState, targetState, event, context);
    }
    @Override
    public void afterTransit(StateEnum sourceState, StateEnum targetState, EventEnum event, EsmContext context) {
        log.info("TransitionPersistImpl-afterTransit,{},{},{},{}", sourceState, targetState, event, context);
    }
}
