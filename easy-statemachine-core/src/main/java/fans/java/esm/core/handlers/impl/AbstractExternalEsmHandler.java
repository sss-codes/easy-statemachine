package fans.java.esm.core.handlers.impl;


import fans.java.esm.core.exception.EsmException;

/**
 * 状态间流转-状态机handler抽象类
 *
 * @author SSS
 */
public abstract class AbstractExternalEsmHandler<S, E, C, R> extends AbstractEsmHandler<S, E, C, R> {

    public S[] withinStates() {
        throw new EsmException("状态间流转不支持此方法");
    }

}
