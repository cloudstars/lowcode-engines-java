package io.github.cloudstars.lowcode.commons.api.config;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

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
    private Object defaultValue;

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

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
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
        this.required = (Boolean) configJson.get(XValueTypeConfig.ATTR_REQUIRED);
        Object defaultValue = configJson.get(XValueTypeConfig.ATTR_DEFAULT_VALUE);
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        configJson.putJsonIfNotNull(XValueTypeConfig.ATTR, this.valueType);
        configJson.putIfNotNull(XValueTypeConfig.ATTR_REQUIRED, this.required);
        configJson.putIfNotNull(XValueTypeConfig.ATTR_DEFAULT_VALUE, this.defaultValue);

        return configJson;
    }

}
