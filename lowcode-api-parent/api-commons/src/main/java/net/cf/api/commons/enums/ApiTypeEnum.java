package net.cf.api.commons.enums;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 11:21
 */
public enum ApiTypeEnum {
    API_PROXY(1, "apiProxy"),
    API_FLOW(2, "apiFlow"),
    API_FUNC(3, "apiFunc");
    private final int code;
    private final String name;
    ApiTypeEnum(final int code, final String name) {
        this.code = code;
        this.name = name;
    }
}
