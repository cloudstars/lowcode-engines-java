package io.github.cloudstars.lowcode.object.commons.config;

import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的字段配置
 *
 * @author clouds
 */
public class AbstractFieldConfig implements XConfig {

    private String key;

    private String code;

    private String name;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = new JsonObject();
        configJson.put("key", this.key);
        configJson.put("code", this.code);
        configJson.put("name", this.name);
        return configJson;
    }

}
