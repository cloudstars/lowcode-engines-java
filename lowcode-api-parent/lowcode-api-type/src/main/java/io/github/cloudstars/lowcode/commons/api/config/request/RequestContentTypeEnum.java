package io.github.cloudstars.lowcode.commons.api.config.request;

/**
 * 媒体内容类型
 *
 * @author clouds
 */
public enum RequestContentTypeEnum {

    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
    APPLICATION_JSON("application/json"),
    MULTIPART_FORM_DATA("multipart/form-data");

    private String name;

    RequestContentTypeEnum(String name) {
        this.name = name;
    }

    /**
     * 根据名称获取枚举值
     *
     * @param name
     * @return
     */
    public static RequestContentTypeEnum valueOfName(String name) {
        RequestContentTypeEnum[] requestContentTypes = RequestContentTypeEnum.values();
        for (RequestContentTypeEnum requestContentType : requestContentTypes) {
            if (requestContentType.name.equalsIgnoreCase(name)) {
                return requestContentType;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
