package fans.java.esm.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态枚举
 *
 * @author sss
 */
@AllArgsConstructor
@Getter
public enum StateEnum {
    INIT("ESM000", "任务初始化"),
    WAIT_PAY("ESM001", "待支付"),
    PAYED("ESM002", "已支付"),
    CANCELED("ESM003", "取消"),
    ;
    private final String stateCode;
    private final String stateName;
}
