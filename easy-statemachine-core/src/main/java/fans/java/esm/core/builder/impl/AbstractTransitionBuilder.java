package fans.java.esm.core.builder.impl;

import fans.java.esm.core.builder.TransitionBuilder;
import fans.java.esm.core.builder.Choice;
import fans.java.esm.core.enums.TransitionTypeEnum;
import fans.java.esm.core.exception.EsmException;
import fans.java.esm.core.builder.Action;
import fans.java.esm.core.component.StateInterface;
import fans.java.esm.core.component.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * 状态流转
 *
 * @author sss
 */
public abstract class AbstractTransitionBuilder<S, E, C, R> implements TransitionBuilder<S, E, C, R> {
    public final List<StateInterface<S, E, C, R>> sourceList = new ArrayList<>();

    public StateInterface<S, E, C, R> target;

    public final List<Transition<S, E, C, R>> transitionList = new ArrayList<>();

    public TransitionTypeEnum transitionType;

    @Override
    public TransitionBuilder<S, E, C, R> onEvent(E event) {
        if (null == event) {
            throw new EsmException("事件不能为空");
        }
        sourceList.forEach(source -> transitionList.add(source.addAndGetTransition(event, null != target ? target : source, transitionType)));
        return this;
    }

    @Override
    public TransitionBuilder<S, E, C, R> choice(Choice<S, E, C> choice) {
        transitionList.forEach(transition -> transition.setChoice(choice));
        return this;
    }

    @Override
    public void action(Action<S, E, C, R> action) {
        transitionList.forEach(transition -> transition.setAction(action));
    }
}
