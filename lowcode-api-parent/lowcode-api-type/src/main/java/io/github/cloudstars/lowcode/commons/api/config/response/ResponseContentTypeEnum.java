package io.github.cloudstars.lowcode.commons.api.config.response;

import io.github.cloudstars.lowcode.commons.lang.enums.IEnum;

/**
 * 媒体内容类型
 *
 * @author clouds
 */
public enum ResponseContentTypeEnum implements IEnum {

    APPLICATION_JSON("application/json"),
    APPLICATION_OCTET_STREAM("application/octet-stream");

    private String code;

    ResponseContentTypeEnum(String name) {
        this.code = name;
    }

    @Override
    public String getCode() {
        return this.code;
    }

}
