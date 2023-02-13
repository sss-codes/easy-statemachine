package fans.java.esm.core.factory;


import fans.java.esm.core.component.impl.StateComponent;
import fans.java.esm.core.component.StateInterface;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * StateFactory
 *
 * @author sss
 */
public class StateComponentFactory {

    private static final Map<Object, Object> STATE_MAP = new ConcurrentHashMap<>();

    public static <S, E, C, R> StateInterface<S, E, C, R> getStateComponent(S state) {
        StateInterface<S, E, C, R> stateInterface = (StateInterface) STATE_MAP.get(state);
        if (stateInterface == null) {
            stateInterface = new StateComponent<>(state);
            STATE_MAP.put(state, stateInterface);
        }
        return stateInterface;
    }
}
