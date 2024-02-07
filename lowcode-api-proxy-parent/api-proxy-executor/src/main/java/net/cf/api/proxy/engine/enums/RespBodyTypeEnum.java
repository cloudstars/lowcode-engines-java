package net.cf.api.proxy.engine.enums;

/**
 * 应答体类型
 * @author 80345746
 * @version v1.0
 * @date 2024/1/18 0:19
 */
public enum RespBodyTypeEnum {
    JSON(0, "JSON"),
    FILE(1, "FILE");
    private final int code;
    private final String name;
    RespBodyTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
