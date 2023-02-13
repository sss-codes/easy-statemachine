package fans.java.esm.core.builder.impl;


import fans.java.esm.core.builder.TransitionBuilder;
import fans.java.esm.core.enums.TransitionTypeEnum;
import fans.java.esm.core.exception.EsmException;
import fans.java.esm.core.factory.StateComponentFactory;

import java.util.Arrays;

/**
 * 状态内转移实现
 *
 * @author sss
 */
public class InternalTransitionBuilder<S, E, C, R> extends AbstractTransitionBuilder<S, E, C, R> {

    public InternalTransitionBuilder() {
        this.transitionType = TransitionTypeEnum.INTERNAL;
    }

    @Override
    public TransitionBuilder<S, E, C, R> withinStates(S... states) {
        if (null == states || states.length == 0) {
            throw new EsmException("状态不能为空");
        }
        Arrays.stream(states).forEach(state -> this.sourceList.add(StateComponentFactory.getStateComponent(state)));
        return this;
    }

    @Override
    public TransitionBuilder<S, E, C, R> fromStates(S... states) {
        throw new EsmException("状态内流转不支持此方法");
    }

    @Override
    public TransitionBuilder<S, E, C, R> toState(S state) {
        throw new EsmException("状态内流转不支持此方法");
    }

}
