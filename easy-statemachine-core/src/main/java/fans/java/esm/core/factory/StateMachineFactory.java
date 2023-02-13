package fans.java.esm.core.factory;


import fans.java.esm.core.exception.EsmException;
import fans.java.esm.core.component.StateMachine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 状态机工厂
 *
 * @author sss
 */
public class StateMachineFactory {
    static Map<String, StateMachine> STATEMACHINE_MAP = new ConcurrentHashMap<>();

    public static <S, E, C, R> void register(StateMachine<S, E, C, R> stateMachine) {
        String machineId = stateMachine.getMachineId();
        if (STATEMACHINE_MAP.get(machineId) != null) {
            throw new EsmException(machineId + "状态机已注册,请勿重复注册");
        }
        STATEMACHINE_MAP.put(stateMachine.getMachineId(), stateMachine);
    }

    public static <S, E, C, R> StateMachine<S, E, C, R> get(String machineId) {
        StateMachine stateMachine = STATEMACHINE_MAP.get(machineId);
        if (stateMachine == null) {
            throw new EsmException("未查询到状态机" + machineId + ",请检查");
        }
        return stateMachine;
    }
}
