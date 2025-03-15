package io.github.cloudstars.lowcode.object.editor.config;

import io.github.cloudstars.lowcode.commons.editor.XConfig;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

import java.util.List;

/**
 * 模型配置
 *
 * @author clouds
 */
public class ObjectConfig implements XConfig {

    /**
     * 编号
     */
    private String key;

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 字段列表
     */
    private List<AbstractFieldConfig> fields;

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

    public List<AbstractFieldConfig> getFields() {
        return fields;
    }

    public void setFields(List<AbstractFieldConfig> fields) {
        this.fields = fields;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("key", this.key);
        jsonObject.put("code", this.code);
        jsonObject.put("name", this.name);
        jsonObject.put("fields", this.fields);
        return jsonObject;
    }

}
