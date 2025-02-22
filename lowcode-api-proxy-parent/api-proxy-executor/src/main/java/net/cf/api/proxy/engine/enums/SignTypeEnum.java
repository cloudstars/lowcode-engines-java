package net.cf.api.proxy.engine.enums;

/**
 * 签名类型
 * @author 80345746
 * @version v1.0
 * @date 2024/1/26 13:09
 */
public enum SignTypeEnum {
    NONE(0, "NONE"),
    ;
    private final int code;
    private final String name;

    SignTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
