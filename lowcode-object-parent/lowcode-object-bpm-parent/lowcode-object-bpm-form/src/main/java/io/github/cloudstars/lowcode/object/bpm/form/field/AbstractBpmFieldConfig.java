package io.github.cloudstars.lowcode.object.bpm.form.field;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractIdentifiedConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.ValueConfigFactory;
import io.github.cloudstars.lowcode.commons.value.XValueConfig;
import io.github.cloudstars.lowcode.commons.value.type.config.XValueTypeConfig;

/**
 * 抽象的字段配置
 *
 * @author clouds
 */
public abstract class AbstractBpmFieldConfig extends AbstractIdentifiedConfig implements XBpmFieldConfig {

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
    private XValueConfig defaultValue;

    protected AbstractBpmFieldConfig() {}

    protected AbstractBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setType(this.getClass().getAnnotation(BpmFieldConfigClass.class).name());
        this.required = (Boolean) configJson.get(XBpmFieldConfig.ATTR_REQUIRED);
        JsonObject defaultValueConfigJson = (JsonObject) configJson.get(XValueConfig.ATTR_DEFAULT_VALUE);
        if (defaultValueConfigJson != null) {
            this.defaultValue = ValueConfigFactory.newInstance(defaultValueConfigJson);
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

    public XValueConfig getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(XValueConfig defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.putIfNotNull(XBpmFieldConfig.ATTR_REQUIRED, this.required);

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
            configJson.put(XValueConfig.ATTR_DEFAULT_VALUE, this.defaultValue.toJson());
        }

        return configJson;
    }

}
