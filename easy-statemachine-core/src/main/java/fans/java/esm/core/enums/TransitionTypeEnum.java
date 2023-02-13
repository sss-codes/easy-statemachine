package fans.java.esm.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态转移类型
 *
 * @author sss
 */
@AllArgsConstructor
@Getter
public enum TransitionTypeEnum {
    INTERNAL("INTERNAL", "状态内流转"),
    EXTERNAL("EXTERNAL", "状态间流转"),
    ;

    private final String code;
    private final String desc;
}
