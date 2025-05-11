package io.github.cloudstars.lowcode.commons.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

public class ConfigTest extends AbstractConfig {
    /**
     * 配置描述
     */
    private String description;

    public ConfigTest(JsonObject configJson) {
        this.description = (String) configJson.get(ATTR_DESCRIPTION);
    }

    public ConfigTest() {
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
