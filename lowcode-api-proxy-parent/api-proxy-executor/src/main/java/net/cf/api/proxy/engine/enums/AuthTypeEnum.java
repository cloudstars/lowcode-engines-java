package net.cf.api.proxy.engine.enums;

/**
 * 认证类型
 * @author 80345746
 * @version v1.0
 * @date 2024/1/26 9:35
 */
public enum AuthTypeEnum {
    NONE(0, "NONE"),
    XFT_JWT(1, "XFT_JWT"),
    XFT_SIGN(2, "XFT_SIGN"),
    OAUTH(3, "OAuth2.0"),
    CUSTOM(4, "CUSTOM")
    ;
    private final int code;
    private final String name;

    AuthTypeEnum(int code, String name) {
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
