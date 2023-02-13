package fans.java.esm.demo.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 事件枚举
 *
 * @author sss
 */
@AllArgsConstructor
@Getter
public enum EventEnum {

    CREATE_ORDER("CREATE_ORDER", "下单"),
    PAY("PAY", "支付"),
    MODIFY_INFO("MODIFY_INFO", "修改资料"),
    ;
    private final String eventCode;
    private final String eventContent;

}
