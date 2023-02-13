package fans.java.esm.core.component.impl;


import fans.java.esm.core.component.StateInterface;
import fans.java.esm.core.component.Transition;
import fans.java.esm.core.enums.TransitionTypeEnum;
import fans.java.esm.core.builder.Action;
import fans.java.esm.core.builder.Choice;
import lombok.Data;

/**
 * 状态转移实现
 *
 * @author sss
 */
@Data
public class TransitionComponent<S, E, C, R> implements Transition<S, E, C, R> {

    private StateInterface<S, E, C, R> source;

    private StateInterface<S, E, C, R> target;

    private E event;

    private Choice<S, E, C> choice;

    private Action<S, E, C, R> action;

    private TransitionTypeEnum transitionType;

    @Override
    public R doTransit(C context) {
        R result = null;
        if (action != null) {
            result = action.doAction(source.getState(), target.getState(), event, context);
        }
        return result;
    }

    @Override
    public final String toString() {
        return source + "-[" + event.toString() + ", " + transitionType + "]->" + target;
    }

    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof Transition) {
            Transition other = (Transition) anObject;
            if (this.event.equals(other.getEvent())
                    && this.source.equals(other.getSource())
                    && this.target.equals(other.getTarget())) {
                return true;
            }
        }
        return false;
    }
}
