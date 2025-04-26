package io.github.cloudstars.lowcode.commons.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 特定类型的配置抽类
 *
 * @author clouds
 */
public abstract class AbstractTypedConfig extends AbstractConfig implements XTypedConfig {

    // 类型配置名称
    private static final String ATTR_TYPE = "type";

    /**
     * 配置的类型
     */
    private String type;

    public AbstractTypedConfig() {
    }

    public AbstractTypedConfig(JsonObject configJson) {
        super(configJson);

        this.type = (String) configJson.get(ATTR_TYPE);
    }

    @Override
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_TYPE, this.type);

        return configJson;
    }

}
