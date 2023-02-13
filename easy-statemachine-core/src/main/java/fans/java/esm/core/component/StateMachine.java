package fans.java.esm.core.component;

/**
 * 状态机
 *
 * @author sss
 */
public interface StateMachine<S, E, C, R> {

    /**
     * 处理事件
     */
    R processEvent(S sourceState, E event, C context);

    /**
     * 获取状态机ID
     */
    String getMachineId();

}
