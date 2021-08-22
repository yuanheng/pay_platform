package com.bootdo.app.zwlenum;

/**
 * @author Prometheus
 * @date 2021/8/22.
 */
public enum RoleTypeEnum {

    ALL(0),

    MERCHANT(1),

    CODER(2),

    UNKNOWN(9),
    ;

    private Integer code;

    RoleTypeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
