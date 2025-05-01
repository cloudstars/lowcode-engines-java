package io.github.cloudstars.lowcode.commons.api.config.response;

import io.github.cloudstars.lowcode.commons.lang.enums.ICodedEnum;

/**
 * 媒体内容类型
 *
 * @author clouds
 */
public enum ResponseContentTypeEnum implements ICodedEnum {

    APPLICATION_JSON("application/json");

    private String code;

    ResponseContentTypeEnum(String name) {
        this.code = name;
    }

    @Override
    public String getCode() {
        return this.code;
    }

}
