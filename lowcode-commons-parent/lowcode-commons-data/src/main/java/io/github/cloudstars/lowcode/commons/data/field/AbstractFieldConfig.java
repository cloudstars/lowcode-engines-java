package io.github.cloudstars.lowcode.commons.data.field;

import io.github.cloudstars.lowcode.commons.data.defaultvalue.DefaultValueConfigFactory;
import io.github.cloudstars.lowcode.commons.data.defaultvalue.XDefaultValueConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.XValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractIdentifiedConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的字段配置
 *
 * @author clouds
 */
public abstract class AbstractFieldConfig extends AbstractIdentifiedConfig implements XFieldConfig {

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 数据格式配置
     */
    private XValueTypeConfig valueType;

    /**
     * 默认值配置
     */
    private XDefaultValueConfig defaultValue;

    protected AbstractFieldConfig() {}

    protected AbstractFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setType(this.getClass().getAnnotation(FieldConfigClass.class).name());
        this.required = (Boolean) configJson.get(XFieldConfig.ATTR_REQUIRED);
        JsonObject defaultValueConfigJson = (JsonObject) configJson.get(XDefaultValueConfig.ATTR);
        if (defaultValueConfigJson != null) {
            this.defaultValue = DefaultValueConfigFactory.newInstance(defaultValueConfigJson);
        }
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    @Override
    public boolean isRequired() {
        return required != null && required;
    }

    @Override
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

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.putIfNotNull(XFieldConfig.ATTR_REQUIRED, this.required);

        // 将数据格式的属性添加到当前对象上
        if (this.valueType != null) {
            JsonObject<String, Object> valueTypeConfigJson = this.valueType.toJson();
            valueTypeConfigJson.forEach((k, v) -> {
                if (!XTypedConfig.ATTR.equalsIgnoreCase(k)) { // 避免数据格式的类型覆盖字段类型
                    configJson.put(k, v);
                }
            });
        }

        if (this.defaultValue != null) {
            configJson.put(XDefaultValueConfig.ATTR, this.defaultValue.toJson());
        }

        return configJson;
    }

}
