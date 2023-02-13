package fans.java.esm.core.context;


import fans.java.esm.core.domain.EventUser;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 状态机上下文接口
 *
 * @author SSS
 */
@Data
public class EsmContext {

    private EventUser eventUser;

    private String indexId;

    private String remark;

    private Context context;

    public EsmContext(EventUser eventUser, String indexId) {
        this.eventUser = eventUser;
        this.indexId = indexId;
    }

    public EsmContext(EventUser eventUser, String indexId, String remark) {
        this.eventUser = eventUser;
        this.indexId = indexId;
        this.remark = remark;
    }

    public static class Context {

        private final Map<AttrKey<?>, Object> dataMap = new ConcurrentHashMap<>();

        public <T> void put(AttrKey<T> attrKey, T value) {
            dataMap.put(attrKey, value);
        }

        @SuppressWarnings("unchecked")
        public <T> T get(AttrKey<T> attrKey) {
            return (T) dataMap.get(attrKey);
        }
    }

}
