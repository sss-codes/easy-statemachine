package fans.java.esm.core.config;

import fans.java.esm.core.builder.StateMachineBuilder;
import fans.java.esm.core.enums.EsmMachineEnum;
import fans.java.esm.core.handlers.impl.AbstractExternalEsmHandler;
import fans.java.esm.core.handlers.impl.AbstractInternalEsmHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * 状态机初始化
 *
 * @author sss
 */
@Component
public class EsmInitConfig<S, E, C, R> {

    @Resource
    private StateMachineBuilder<S, E, C, R> stateMachineBuilder;
    @Resource
    private List<AbstractExternalEsmHandler<S, E, C, R>> abstractExternalEsmHandlerList;
    @Resource
    private List<AbstractInternalEsmHandler<S, E, C, R>> abstractInternalEsmHandlerList;

    @PostConstruct
    public void init() {
        for (AbstractExternalEsmHandler<S, E, C, R> externalEsmHandler : abstractExternalEsmHandlerList) {
            stateMachineBuilder.external()
                    .fromStates(externalEsmHandler.fromStates())
                    .toState(externalEsmHandler.toState())
                    .onEvent(externalEsmHandler.onEvent())
                    .choice(externalEsmHandler.choice())
                    .action(externalEsmHandler.action());
        }

        for (AbstractInternalEsmHandler<S, E, C, R> internalEsmHandler : abstractInternalEsmHandlerList) {
            stateMachineBuilder.internal()
                    .withinStates(internalEsmHandler.withinStates())
                    .onEvent(internalEsmHandler.onEvent())
                    .choice(internalEsmHandler.choice())
                    .action(internalEsmHandler.action());
        }
        stateMachineBuilder.build(EsmMachineEnum.COMMON.getMachineId());
    }
}
