package fans.java.esm.core.builder.impl;


import fans.java.esm.core.builder.StateMachineBuilder;
import fans.java.esm.core.builder.TransitionBuilder;
import fans.java.esm.core.component.impl.StateMachineComponent;
import fans.java.esm.core.factory.StateMachineFactory;
import fans.java.esm.core.component.StateMachine;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 状态机builder
 *
 * @author SSS
 */
@Component
public class StateMachineBuilderComponent<S, E, C, R> implements StateMachineBuilder<S, E, C, R> {

    @Resource
    private StateMachineComponent<S, E, C, R> stateMachine;

    @Override
    public TransitionBuilder<S, E, C, R> external() {
        return new ExternalTransitionBuilder<>();
    }

    @Override
    public TransitionBuilder<S, E, C, R> internal() {
        return new InternalTransitionBuilder<>();
    }

    @Override
    public StateMachine<S, E, C, R> build(String machineId) {
        stateMachine.setMachineId(machineId);
        StateMachineFactory.register(stateMachine);
        return stateMachine;
    }

}
