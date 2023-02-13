package fans.java.esm.core.handlers;


import fans.java.esm.core.builder.Action;
import fans.java.esm.core.builder.Choice;

/**
 * 状态机handler组件
 *
 * @author SSS
 */
public interface EsmHandlerComponent<S, E, C, R> {

    S[] fromStates();

    S toState();

    S[] withinStates();

    E onEvent();

    Boolean doCheck(C context);

    Choice<S, E, C> choice();

    Action<S, E, C, R> action();

}
