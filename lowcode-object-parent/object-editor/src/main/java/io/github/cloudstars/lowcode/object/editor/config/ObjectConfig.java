package io.github.cloudstars.lowcode.object.editor.config;

import io.github.cloudstars.lowcode.commons.editor.XConfig;

import java.util.List;

/**
 * 模型配置
 *
 * @author clouds
 */
public class ObjectConfig implements XConfig {

    private String key;

    private String code;

    private String name;

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
    public String toJsonString() {
        return null;
    }
}
