package fans.java.esm.core.utils;

import fans.java.esm.core.enums.EsmMachineEnum;
import fans.java.esm.core.exception.EsmException;
import fans.java.esm.core.factory.StateMachineFactory;
import fans.java.esm.core.component.StateMachine;

/**
 * 状态机工具
 *
 * @author sss
 */
public class EsmUtil {
    public static <S, E, C, R> R processEvent(S sourceState, E event, C context) {
        return processEvent(EsmMachineEnum.COMMON.getMachineId(), sourceState, event, context);
    }

    private static <S, E, C, R> R processEvent(String machineId, S sourceState, E event, C context) {
        StateMachine<S, E, C, R> stateMachine = StateMachineFactory.get(machineId);

        if (null == sourceState || null == event || null == context) {
            throw new EsmException("sourceState event context 均不能为空");
        }
        return stateMachine.processEvent(sourceState, event, context);
    }
}
