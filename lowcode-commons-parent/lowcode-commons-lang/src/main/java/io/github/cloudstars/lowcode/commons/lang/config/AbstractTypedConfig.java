package io.github.cloudstars.lowcode.commons.lang.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 特定类型的配置抽类
 *
 * @author clouds
 */
public abstract class AbstractTypedConfig extends AbstractConfig implements XTypedConfig {

    private static final String ATTR_TYPE = "type";
    private static final String ATTR_KEY = "key";
    private static final String ATTR_NAME = "name";

    /**
     * 配置的类型
     */
    private String type;

    /**
     * 配置的编号
     */
    private String key;

    /**
     * 配置的名称
     */
    private String name;


    public AbstractTypedConfig() {
    }

    public AbstractTypedConfig(JsonObject configJson) {
        super(configJson);

        this.type = (String) configJson.get(ATTR_TYPE);
        this.key = (String) configJson.get(ATTR_KEY);
        this.name = (String) configJson.get(ATTR_NAME);
    }

    @Override
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = new JsonObject();
        configJson.put(ATTR_TYPE, this.type);
        configJson.put(ATTR_KEY, this.key);
        configJson.put(ATTR_NAME, this.name);

        return configJson;
    }

}
