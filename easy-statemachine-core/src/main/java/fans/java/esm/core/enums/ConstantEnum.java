package fans.java.esm.core.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 常量枚举
 *
 * @author sss
 */
@AllArgsConstructor
@Getter
public enum ConstantEnum {

    SYSTEM("0", "系统"),
    ;
    private final String code;
    private final String desc;

}
