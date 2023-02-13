package fans.java.esm.core.handlers.impl;


import fans.java.esm.core.exception.EsmException;

/**
 * 内部流转-状态机handler抽象类
 *
 * @author SSS
 */
public abstract class AbstractInternalEsmHandler<S, E, C, R> extends AbstractEsmHandler<S, E, C, R> {

    public S[] fromStates() {
        throw new EsmException("状态内流转不支持此方法");
    }

    public S toState() {
        throw new EsmException("状态内流转不支持此方法");
    }

}
