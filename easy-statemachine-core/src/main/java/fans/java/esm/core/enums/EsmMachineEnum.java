package fans.java.esm.core.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 状态机枚举
 *
 * @author sss
 */
@AllArgsConstructor
@Getter
public enum EsmMachineEnum {

    COMMON("ESM_COMMON_MACHINE_ID", "通用状态机"),
    ;
    private final String machineId;
    private final String desc;

}
