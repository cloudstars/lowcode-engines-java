package net.cf.api.commons.enums;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 11:21
 */
public enum ApiPublicizedEnum {
    PRIVATE(0, "private"),
    PUBLIC(1, "public");
    private final int code;
    private final String name;
    ApiPublicizedEnum(final int code, final String name) {
        this.code = code;
        this.name = name;
    }
}
