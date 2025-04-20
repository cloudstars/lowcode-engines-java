package io.github.cloudstars.lowcode.commons.api.config;

import io.github.cloudstars.lowcode.commons.data.defaultvalue.DefaultValueConfigFactory;
import io.github.cloudstars.lowcode.commons.data.defaultvalue.XDefaultValueConfig;
import io.github.cloudstars.lowcode.commons.data.field.XFieldConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.config.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.data.valuetype.config.XValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * API输入配置
 *
 * @author clouds
 */
public class ApiInputConfig extends AbstractConfig {

    /**
     * 数据格式配置
     */
    private XValueTypeConfig valueType;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 默认值配置
     */
    private XDefaultValueConfig defaultValue;

    public XValueTypeConfig getValueType() {
        return valueType;
    }

    public void setValueType(XValueTypeConfig valueType) {
        this.valueType = valueType;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public XDefaultValueConfig getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(XDefaultValueConfig defaultValue) {
        this.defaultValue = defaultValue;
    }

    public ApiInputConfig() {
    }

    public ApiInputConfig(JsonObject configJson) {
        super(configJson);

        JsonObject valueTypeConfigJson = (JsonObject) configJson.get(XValueTypeConfig.ATTR);
        if (valueTypeConfigJson != null) {
            this.valueType = ValueTypeConfigFactory.newInstance(valueTypeConfigJson);
        }
        this.required = (Boolean) configJson.get(XFieldConfig.ATTR_REQUIRED);
        JsonObject defaultValueConfigJson = (JsonObject) configJson.get(XDefaultValueConfig.ATTR);
        if (defaultValueConfigJson != null) {
            this.defaultValue = DefaultValueConfigFactory.newInstance(defaultValueConfigJson);
        }
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        configJson.putIfNotNull(XFieldConfig.ATTR_REQUIRED, this.required);
        configJson.putJsonIfNotNull(XValueTypeConfig.ATTR, this.valueType);
        configJson.putJsonIfNotNull(XDefaultValueConfig.ATTR, this.defaultValue);

        return configJson;
    }

}
