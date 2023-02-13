package fans.java.esm.core.persistence;

/**
 * 状态持久化接口
 *
 * @author SSS
 */
public interface StatePersistInterface<S, E, C> {

    /**
     * Action执行前
     *
     * @param sourceState 源状态
     * @param targetState 目标状态
     * @param event       事件
     * @param context     上下文
     */
    void beforeAction(S sourceState, S targetState, E event, C context);

    /**
     * Action执行后
     *
     * @param sourceState 源状态
     * @param targetState 目标状态
     * @param event       事件
     * @param context     上下文
     */
    void afterAction(S sourceState, S targetState, E event, C context);

}
