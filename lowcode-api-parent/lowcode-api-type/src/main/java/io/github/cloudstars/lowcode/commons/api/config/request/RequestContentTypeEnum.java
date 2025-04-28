package io.github.cloudstars.lowcode.commons.api.config.request;

/**
 * 媒体内容类型
 *
 * @author clouds
 */
public enum RequestContentTypeEnum {

    APPLICATION_JSON("application/json");

    private String name;

    RequestContentTypeEnum(String name) {
        this.name = name;
    }

}
