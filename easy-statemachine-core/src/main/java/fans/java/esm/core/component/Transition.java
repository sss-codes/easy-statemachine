package fans.java.esm.core.component;


import fans.java.esm.core.builder.Choice;
import fans.java.esm.core.enums.TransitionTypeEnum;
import fans.java.esm.core.builder.Action;

/**
 * 状态转移
 *
 * @author sss
 */
public interface Transition<S, E, C, R> {
    StateInterface<S, E, C, R> getSource();

    void setSource(StateInterface<S, E, C, R> stateInterface);

    StateInterface<S, E, C, R> getTarget();

    void setTarget(StateInterface<S, E, C, R> stateInterface);

    E getEvent();

    void setEvent(E event);

    void setTransitionType(TransitionTypeEnum transitionType);

    Choice<S, E, C> getChoice();

    void setChoice(Choice<S, E, C> choice);

    Action<S, E, C, R> getAction();

    void setAction(Action<S, E, C, R> action);

    R doTransit(C context);

}
