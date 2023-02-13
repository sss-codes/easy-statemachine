package fans.java.esm.core.builder;

/**
 * 条件
 *
 * @author sss
 */
public interface Choice<S, E, C> {

    Boolean doChoice(S sourceState, S targetState, E event, C context);

}