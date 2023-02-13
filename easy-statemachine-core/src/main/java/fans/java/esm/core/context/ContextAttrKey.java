package fans.java.esm.core.context;


import fans.java.esm.core.domain.EventUser;

/**
 * 状态机上下文属性key
 *
 * @author SSS
 */
public interface ContextAttrKey {

    /**
     * 当前登录用户
     */
    AttrKey<EventUser> EVENT_USER = AttrKey.of("EVENT_USER");

    /**
     * TASK_ID
     */
    AttrKey<String> TASK_ID = AttrKey.of("TASK_ID");

    /**
     * REMARK
     */
    AttrKey<String> REMARK = AttrKey.of("REMARK");

}
