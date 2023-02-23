package fans.java.esm.core.component.impl;


import fans.java.esm.core.component.StateInterface;
import fans.java.esm.core.component.StateMachine;
import fans.java.esm.core.component.Transition;
import fans.java.esm.core.exception.EsmException;
import fans.java.esm.core.factory.StateComponentFactory;
import fans.java.esm.core.persistence.TransitionPersistInterface;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 状态机实现
 *
 * @author sss
 */
@Component
@Data
public class StateMachineComponent<S, E, C, R> implements StateMachine<S, E, C, R> {

    @Resource
    private TransitionPersistInterface<S, E, C> transitionPersistInterface;

    private String machineId;

    @Override
    public R processEvent(S sourceState, E event, C context) {
        List<Transition<S, E, C, R>> transitionList = this.getTransitionList(sourceState, event, context);
        if (CollectionUtils.isEmpty(transitionList)) {
            throw new EsmException("未找到当前事件的状态转移Handler");
        }
        if (transitionList.size() > 1) {
            throw new EsmException("当前状态在当前事件可转到不同的目标状态,请检查!");
        }

        Transition<S, E, C, R> transition = transitionList.get(0);
        S targetState = transition.getTarget().getState();
        this.beforeTransit(sourceState, targetState, event, context);
        R result = transition.doTransit(context);
        this.afterTransit(sourceState, targetState, event, context);
        return result;
    }

    private void beforeTransit(S sourceState, S targetState, E event, C context) {
        transitionPersistInterface.beforeTransit(sourceState, targetState, event, context);
    }

    private void afterTransit(S sourceState, S targetState, E event, C context) {
        transitionPersistInterface.afterTransit(sourceState, targetState, event, context);
    }

    private List<Transition<S, E, C, R>> getTransitionList(S sourceState, E event, C context) {
        StateInterface<S, E, C, R> sourceStateComponent = StateComponentFactory.getStateComponent(sourceState);

        return Optional.ofNullable(sourceStateComponent.getEventTransitionList(event))
                .map(List::stream)
                .orElseGet(Stream::empty)
                .filter(transition ->
                        null != transition.getTarget()
                                && null != transition.getChoice()
                                && transition.getChoice().doChoice(sourceState, transition.getTarget().getState(), event, context))
                .collect(Collectors.toList());
    }

}
