package io.github.cloudstars.lowcode.commons.api.config;

import io.github.cloudstars.lowcode.commons.value.ValueConfigFactory;
import io.github.cloudstars.lowcode.commons.value.XValueConfig;
import io.github.cloudstars.lowcode.commons.value.type.config.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.value.type.config.XValueTypeConfig;
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
    private XValueConfig defaultValue;

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

    public XValueConfig getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(XValueConfig defaultValue) {
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
        this.required = (Boolean) configJson.get(XValueConfig.ATTR_REQUIRED);
        JsonObject defaultValueConfigJson = (JsonObject) configJson.get(XValueConfig.ATTR_DEFAULT_VALUE);
        if (defaultValueConfigJson != null) {
            this.defaultValue = ValueConfigFactory.newInstance(defaultValueConfigJson);
        }
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        configJson.putIfNotNull(XValueConfig.ATTR_REQUIRED, this.required);
        configJson.putJsonIfNotNull(XValueTypeConfig.ATTR, this.valueType);
        configJson.putJsonIfNotNull(XValueConfig.ATTR_DEFAULT_VALUE, this.defaultValue);

        return configJson;
    }

}
