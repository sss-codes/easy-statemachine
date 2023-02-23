package fans.java.esm.core.persistence;

/**
 * 状态转移持久化接口
 *
 * @author SSS
 */
public interface TransitionPersistInterface<S, E, C> {

    /**
     * 转移执行前
     *
     * @param sourceState 源状态
     * @param targetState 目标状态
     * @param event       事件
     * @param context     上下文
     */
    void beforeTransit(S sourceState, S targetState, E event, C context);

    /**
     * 转移执行后
     *
     * @param sourceState 源状态
     * @param targetState 目标状态
     * @param event       事件
     * @param context     上下文
     */
    void afterTransit(S sourceState, S targetState, E event, C context);

}
