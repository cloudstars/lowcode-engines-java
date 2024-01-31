package net.cf.api.proxy.engine.enums;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 23:25
 */
public enum MethodEnum {
    GET(0, "GET"),
    POST(1, "POST"),
    PUT(2, "PUT"),
    DELETE(3, "DELETE"),
    ;
    private final int code;
    private final String name;
    MethodEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
