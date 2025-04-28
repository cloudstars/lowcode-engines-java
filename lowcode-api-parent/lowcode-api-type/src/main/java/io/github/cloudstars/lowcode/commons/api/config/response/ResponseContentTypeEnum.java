package io.github.cloudstars.lowcode.commons.api.config.response;

/**
 * 媒体内容类型
 *
 * @author clouds
 */
public enum ResponseContentTypeEnum {

    APPLICATION_JSON("application/json");

    private String name;

    ResponseContentTypeEnum(String name) {
        this.name = name;
    }

}
