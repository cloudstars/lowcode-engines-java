package io.github.cloudstars.lowcode.object.view.table.config.query;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
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

    public QuerySelectFieldConfig(JsonObject jsonObject) {
        this.key = (String) jsonObject.get("key");
        this.code = (String) jsonObject.get("code");
        this.name = (String) jsonObject.get("name");
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
}