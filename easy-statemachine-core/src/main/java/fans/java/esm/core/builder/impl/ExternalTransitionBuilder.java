package fans.java.esm.core.builder.impl;


import fans.java.esm.core.builder.TransitionBuilder;
import fans.java.esm.core.enums.TransitionTypeEnum;
import fans.java.esm.core.exception.EsmException;
import fans.java.esm.core.factory.StateComponentFactory;

import java.util.Arrays;

/**
 * 状态间流转
 *
 * @author sss
 */
public class ExternalTransitionBuilder<S, E, C, R> extends AbstractTransitionBuilder<S, E, C, R> {

    public ExternalTransitionBuilder() {
        this.transitionType = TransitionTypeEnum.EXTERNAL;
    }

    @Override
    public TransitionBuilder<S, E, C, R> fromStates(S... states) {
        if (null == states || states.length == 0) {
            throw new EsmException("状态不能为空");
        }
        Arrays.stream(states).forEach(state -> this.sourceList.add(StateComponentFactory.getStateComponent(state)));
        return this;
    }

    @Override
    public TransitionBuilder<S, E, C, R> toState(S state) {
        if (null == state) {
            throw new EsmException("状态不能为空");
        }
        this.target = StateComponentFactory.getStateComponent(state);
        return this;
    }

    public TransitionBuilder<S, E, C, R> withinStates(S... states) {
        throw new EsmException("状态间流转不支持此方法");
    }

}
