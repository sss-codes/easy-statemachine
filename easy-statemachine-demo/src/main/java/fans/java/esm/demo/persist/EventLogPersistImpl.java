package fans.java.esm.demo.persist;

import fans.java.esm.core.context.EsmContext;
import fans.java.esm.core.persistence.EventLogPersistInterface;
import fans.java.esm.demo.enums.EventEnum;
import fans.java.esm.demo.enums.StateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 事件日志记录
 *
 * @author SSS
 */
@Component
@Slf4j
public class EventLogPersistImpl implements EventLogPersistInterface<StateEnum, EventEnum, EsmContext> {

    @Override
    public void beforeAction(StateEnum sourceState, StateEnum targetState, EventEnum event, EsmContext context) {
        log.info("EventLogPersistImpl-beforeAction,{},{},{},{}", sourceState, targetState, event, context);
    }

    @Override
    public void afterAction(StateEnum sourceState, StateEnum targetState, EventEnum event, EsmContext context) {
        log.info("EventLogPersistImpl-afterAction,{},{},{},{}", sourceState, targetState, event, context);
    }
}
