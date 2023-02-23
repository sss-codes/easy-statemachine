package fans.java.esm.core.component.impl;


import fans.java.esm.core.component.StateInterface;
import fans.java.esm.core.component.Transition;
import fans.java.esm.core.enums.TransitionTypeEnum;
import fans.java.esm.core.exception.EsmException;
import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * StateComponent
 *
 * @author sss
 */
@Data
public class StateComponent<S, E, C, R> implements StateInterface<S, E, C, R> {
    private final S state;
    private final Map<E, List<Transition<S, E, C, R>>> eventMultiTransitionMap = new ConcurrentHashMap<>();

    public StateComponent(S state) {
        this.state = state;
    }

    @Override
    public Transition<S, E, C, R> addAndGetTransition(E event, StateInterface<S, E, C, R> target, TransitionTypeEnum transitionTypeEnum) {
        Transition<S, E, C, R> newTransition = new TransitionComponent<>();
        newTransition.setSource(this);
        newTransition.setTarget(target);
        newTransition.setEvent(event);
        newTransition.setTransitionType(transitionTypeEnum);
        this.addTransition(event, newTransition);
        return newTransition;
    }

    private void addTransition(E event, Transition<S, E, C, R> transition) {
        if (eventMultiTransitionMap.get(event) == null) {
            eventMultiTransitionMap.put(event, Stream.of(transition).collect(Collectors.toList()));
        } else {
            List<Transition<S, E, C, R>> existingTransitionList = eventMultiTransitionMap.get(event);
            this.checkExistTransition(existingTransitionList, transition);
            existingTransitionList.add(transition);
        }
    }

    private void checkExistTransition(List<Transition<S, E, C, R>> existingTransitionList, Transition<S, E, C, R> newTransition) {
        boolean exist = Optional.ofNullable(existingTransitionList)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .anyMatch(transition -> transition.equals(newTransition));
        if (exist) {
            throw new EsmException(newTransition + " 当前转移关系已经存在");
        }
    }

    @Override
    public List<Transition<S, E, C, R>> getEventTransitionList(E event) {
        return eventMultiTransitionMap.get(event);
    }

    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof StateInterface) {
            StateInterface other = (StateInterface) anObject;
            if (this.state.equals(other.getState()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
