package io.github.cloudstars.lowcode.commons.lang.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 配置对象接口，表示某个概念的配置
 *
 * @author clouds
 */
public abstract class AbstractConfig implements XConfig {

    public AbstractConfig() {
    }

    public AbstractConfig(JsonObject configJson) {
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = new JsonObject();
        return configJson;
    }

}
