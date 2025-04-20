package io.github.cloudstars.lowcode.commons.data.valuetype;

import io.github.cloudstars.lowcode.commons.data.defaultvalue.DefaultValueConfigFactory;
import io.github.cloudstars.lowcode.commons.data.defaultvalue.XDefaultValueConfig;
import io.github.cloudstars.lowcode.commons.data.field.XFieldConfig;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XIdentifiedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 对象数据下的属性
 *
 * @author clouds
 *
 */
public class ObjectValueProperty extends AbstractConfig {

    /**
     * 属性代码
     */
    private String code;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 属性的数据格式
     */
    private XValueTypeConfig valueType;

    /**
     * 属性的默认值
     */
    private XDefaultValueConfig defaultValue;

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

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
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

    public ObjectValueProperty() {
    }

    public ObjectValueProperty(JsonObject configJson) {
        super(configJson);

        this.code = (String) configJson.get(XIdentifiedConfig.ATTR_CODE);
        this.name = (String) configJson.get(XIdentifiedConfig.ATTR_NAME);
        this.required = (Boolean) configJson.get(XFieldConfig.ATTR_REQUIRED);
        this.valueType = ValueTypeConfigFactory.newInstance(configJson);

        JsonObject defaultValueConfigJson = (JsonObject) configJson.get(XDefaultValueConfig.ATTR);
        if (defaultValueConfigJson != null) {
            this.defaultValue = DefaultValueConfigFactory.newInstance(defaultValueConfigJson);
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(XIdentifiedConfig.ATTR_CODE, this.code);
        configJson.put(XIdentifiedConfig.ATTR_NAME, this.name);
        configJson.putIfNotNull(XFieldConfig.ATTR_REQUIRED, this.required);
        configJson.putAll(this.valueType.toJson());
        configJson.putJsonIfNotNull(XDefaultValueConfig.ATTR, this.defaultValue);

        return configJson;
    }
}
