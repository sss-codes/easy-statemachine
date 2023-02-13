package fans.java.esm.core.component;


import fans.java.esm.core.enums.TransitionTypeEnum;

import java.util.Collection;
import java.util.List;

/**
 * 状态
 *
 * @author sss
 */
public interface StateInterface<S, E, C, R> {

    S getState();

    Transition<S, E, C, R> addAndGetTransition(E event, StateInterface<S, E, C, R> target, TransitionTypeEnum transitionType);

    List<Transition<S, E, C, R>> getEventTransitionList(E event);

    Collection<Transition<S, E, C, R>> getAllTransitions();

}
