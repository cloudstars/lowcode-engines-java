package io.github.cloudstars.lowcode.commons.data.field;

import io.github.cloudstars.lowcode.commons.data.defaultvalue.XDefaultValueConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.XValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 字段配置
 *
 * @author clouds
 */
public class FieldValueConfig extends AbstractConfig {


    // 名称属性名称
    public static final String ATTR_NAME = "name";

    // 标题属性名称
    public static final String ATTR_TITLE = "title";

    // 必填属性名称
    public static final String ATTR_REQUIRED = "required";

    /**
     * 字段名
     */
    private String name;

    /**
     * 字段标题
     */
    private String title;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 数据格式
     */
    private XValueTypeConfig valueType;

    /**
     * 默认值
     */
    private XDefaultValueConfig defaultValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public XValueTypeConfig getValueType() {
        return valueType;
    }

    public void setValueType(XValueTypeConfig valueType) {
        this.valueType = valueType;
    }

    public XDefaultValueConfig getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(XDefaultValueConfig defaultValue) {
        this.defaultValue = defaultValue;
    }

    public FieldValueConfig() {
    }

    public FieldValueConfig(JsonObject configJson) {
        super(configJson);

        this.name = (String) configJson.get(ATTR_NAME);
        this.title = (String) configJson.get(ATTR_TITLE);
        this.required = (Boolean) configJson.get(ATTR_REQUIRED);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_NAME, this.name);
        configJson.putIfNotNull(ATTR_TITLE, this.title);
        configJson.putIfNotNull(ATTR_REQUIRED, this.required);
        return configJson;
    }
}
