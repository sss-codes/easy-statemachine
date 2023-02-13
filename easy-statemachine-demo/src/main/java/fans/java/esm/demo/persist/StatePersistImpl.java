package fans.java.esm.demo.persist;

import fans.java.esm.core.context.EsmContext;
import fans.java.esm.core.persistence.StatePersistInterface;
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
public class StatePersistImpl implements StatePersistInterface<StateEnum, EventEnum, EsmContext> {

    @Override
    public void beforeAction(StateEnum sourceState, StateEnum targetState, EventEnum event, EsmContext context) {
        log.info("StatePersistImpl-beforeAction,{},{},{},{}", sourceState, targetState, event, context);
    }

    @Override
    public void afterAction(StateEnum sourceState, StateEnum targetState, EventEnum event, EsmContext context) {
        log.info("StatePersistImpl-afterAction,{},{},{},{}", sourceState, targetState, event, context);
    }
}
