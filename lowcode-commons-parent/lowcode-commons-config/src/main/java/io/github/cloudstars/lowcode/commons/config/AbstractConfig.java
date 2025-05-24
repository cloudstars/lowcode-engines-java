package io.github.cloudstars.lowcode.commons.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 配置对象接口，表示某个概念的配置
 *
 * @author clouds
 */
public abstract class AbstractConfig implements XConfig {

    /**
     * 配置描述
     */
    private String description;

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AbstractConfig() {
    }

    public AbstractConfig(JsonObject configJson) {
        this.description = (String) configJson.get(ATTR_DESCRIPTION);
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject<String, Object> configJson = new JsonObject<>();
        ConfigUtils.put(configJson, ATTR_DESCRIPTION, this.description);

        return configJson;
    }

}
