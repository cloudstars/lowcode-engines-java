package net.cf.api.proxy.engine.enums;

/**
 * token位置
 * @author 80345746
 * @version v1.0
 * @date 2024/1/30 19:38
 */
public enum TokenPosEnum {
    QUERY(0, "query"),
    HEADER(1, "header"),
    BODY(1, "header"),
    ;
    private final int code;
    private final String name;

    TokenPosEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
