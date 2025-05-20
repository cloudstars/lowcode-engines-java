package io.github.cloudstars.lowcode.commons.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 具有身份标识的的配置抽类
 *
 * @author clouds
 */
public abstract class AbstractResourceConfig extends AbstractTypedConfig implements XResourceConfig {

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
    private String title;

    public AbstractResourceConfig() {
    }

    public AbstractResourceConfig(JsonObject configJson) {
        super(configJson);

        this.key = ConfigUtils.getNonNullString(configJson, ATTR_KEY);
        this.code = (String) configJson.get(ATTR_CODE);
        this.title = (String) configJson.get(ATTR_NAME);
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
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.put(configJson, ATTR_KEY, this.key);
        ConfigUtils.putIfNotNull(configJson, ATTR_CODE, this.code);
        ConfigUtils.putIfNotNull(configJson, ATTR_NAME, this.title);

        return configJson;
    }

}
