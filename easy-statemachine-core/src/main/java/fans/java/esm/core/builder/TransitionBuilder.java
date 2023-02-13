package fans.java.esm.core.builder;

/**
 * 状态转移Builder
 *
 * @author sss
 */
public interface TransitionBuilder<S, E, C, R> {
    TransitionBuilder<S, E, C, R> fromStates(S... states);

    TransitionBuilder<S, E, C, R> withinStates(S... state);

    TransitionBuilder<S, E, C, R> toState(S state);

    TransitionBuilder<S, E, C, R> onEvent(E event);

    TransitionBuilder<S, E, C, R> choice(Choice<S, E, C> choice);

    void action(Action<S, E, C, R> action);
}
