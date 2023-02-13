package fans.java.esm.core.domain;

import fans.java.esm.core.enums.ConstantEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 事件相关人
 *
 * @author SSS
 */
@Data
public class EventUser implements Serializable {

    private static final long serialVersionUID = -4776391317205043753L;

    private String operatorCode = ConstantEnum.SYSTEM.getCode();

    private String operatorName = ConstantEnum.SYSTEM.getDesc();

    private String targetUserCode = ConstantEnum.SYSTEM.getCode();

    private String targetUserName = ConstantEnum.SYSTEM.getDesc();

}
