package fans.java.esm.core.builder;

/**
 * 动作执行
 *
 * @author sss
 */
public interface Action<S, E, C, R> {

    R doAction(S sourceState, S targetState, E event, C context);

}
