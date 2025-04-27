package io.github.cloudstars.lowcode.object.form.config.field;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;
import io.github.cloudstars.lowcode.dynamic.value.DynamicValueConfigFactory;
import io.github.cloudstars.lowcode.dynamic.value.XDynamicValueConfig;

/**
 * 抽象的字段配置
 *
 * @author clouds
 */
public abstract class AbstractObjectFieldConfig extends AbstractResourceConfig implements XObjectFieldConfig {

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 数据格式配置
     */
    private XValueTypeConfig valueType;

    /**
     * 动态值配置
     */
    private XDynamicValueConfig dynamicValueConfig;

    protected AbstractObjectFieldConfig() {}

    protected AbstractObjectFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setType(this.getClass().getAnnotation(FieldConfigClass.class).name());
        this.required = (Boolean) configJson.get(XObjectFieldConfig.ATTR_REQUIRED);
        JsonObject dynamicValueConfigJson = (JsonObject) configJson.get(XDynamicValueConfig.ATTR);
        if (dynamicValueConfigJson != null) {
            this.dynamicValueConfig = DynamicValueConfigFactory.newInstance(dynamicValueConfigJson);
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

    @Override
    public XDynamicValueConfig getDynamicValue() {
        return dynamicValueConfig;
    }

    public void setDynamicValueConfig(XDynamicValueConfig dynamicValueConfig) {
        this.dynamicValueConfig = dynamicValueConfig;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, XObjectFieldConfig.ATTR_REQUIRED, this.required);

        // 将数据格式的属性添加到当前对象上
        if (this.valueType != null) {
            JsonObject<String, Object> valueTypeConfigJson = this.valueType.toJson();
            valueTypeConfigJson.forEach((k, v) -> {
                if (!XTypedConfig.ATTR.equalsIgnoreCase(k)) { // 避免数据格式的类型覆盖字段类型
                    configJson.put(k, v);
                }
            });
        }

        ConfigUtils.putJsonIfNotNull(configJson, XDynamicValueConfig.ATTR, this.dynamicValueConfig);

        return configJson;
    }

}
