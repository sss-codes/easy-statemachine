package fans.java.esm.core.builder;


import fans.java.esm.core.component.StateMachine;

/**
 * 状态机builder
 *
 * @author sss
 */
public interface StateMachineBuilder<S, E, C, R> {
    /**
     * 状态间流转
     */
    TransitionBuilder<S, E, C, R> external();

    /**
     * 状态内部流转
     */
    TransitionBuilder<S, E, C, R> internal();

    StateMachine<S, E, C, R> build(String machineId);

}
