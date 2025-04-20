package io.github.cloudstars.lowcode.commons.lang.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 具有身份标识的的配置抽类
 *
 * @author clouds
 */
public abstract class AbstractIdentifiedConfig extends AbstractTypedConfig implements XIdentifiedConfig {

    /**
     * 配置的编号
     */
    private String key;

    /**
     * 配置的代码
     */
    private String code;

    /**
     * 配置的名称
     */
    private String name;

    public AbstractIdentifiedConfig() {
    }

    public AbstractIdentifiedConfig(JsonObject configJson) {
        super(configJson);

        this.key = (String) configJson.get(ATTR_KEY);
        this.code = (String) configJson.get(ATTR_CODE);
        this.name = (String) configJson.get(ATTR_NAME);
    }


    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_KEY, this.key);
        configJson.putIfNotNull(ATTR_CODE, this.code);
        configJson.putIfNotNull(ATTR_NAME, this.name);

        return configJson;
    }

}
