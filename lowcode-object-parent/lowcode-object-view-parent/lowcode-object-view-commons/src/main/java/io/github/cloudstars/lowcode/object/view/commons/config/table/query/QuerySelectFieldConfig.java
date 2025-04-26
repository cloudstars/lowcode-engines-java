package io.github.cloudstars.lowcode.object.view.commons.config.table.query;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 查询字段配置
 *
 * @author clouds
 */
public class QuerySelectFieldConfig extends AbstractConfig {

    /**
     * 字段的编号
     */
    private String key;

    /**
     * 字段的代码
     */
    private String code;

    /**
     * 字段的名称
     */
    private String name;

    public QuerySelectFieldConfig() {
    }

    public QuerySelectFieldConfig(JsonObject configJson) {
        super(configJson);

        this.key = (String) configJson.get("key");
        this.code = (String) configJson.get("code");
        this.name = (String) configJson.get("name");
    }

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
        JsonObject configJson = super.toJson();
        configJson.put("key", this.key);
        configJson.put("code", this.code);
        configJson.put("name", this.name);
        return configJson;
    }
}