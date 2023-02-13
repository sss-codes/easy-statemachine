package fans.java.esm.core.handlers.impl;


import fans.java.esm.core.builder.Choice;
import fans.java.esm.core.handlers.EsmHandlerComponent;

/**
 * 状态机handler抽象类
 *
 * @author SSS
 */
public abstract class AbstractEsmHandler<S, E, C, R> implements EsmHandlerComponent<S, E, C, R> {

    public Boolean doCheck(C context) {
        return true;
    }

    public Choice<S, E, C> choice() {
        return (from, to, event, context) -> true;
    }

}
