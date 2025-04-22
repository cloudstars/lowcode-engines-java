package io.github.cloudstars.lowcode.object.bpm.form.field;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractIdentifiedConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.dynamic.ValueConfigFactory;
import io.github.cloudstars.lowcode.commons.value.dynamic.XValueConfig;
import io.github.cloudstars.lowcode.commons.value.type.config.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.value.type.config.XValueTypeConfig;
import io.github.cloudstars.lowcode.commons.value.type.config.defaultvalue.XDefaultValueConfig;

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
     * 动态值配置
     */
    private XValueConfig value;

    protected AbstractBpmFieldConfig() {
    }

    protected AbstractBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setType(this.getClass().getAnnotation(BpmFieldConfigClass.class).name());
        this.required = (Boolean) configJson.get(XBpmFieldConfig.ATTR_REQUIRED);
        this.valueType = ValueTypeConfigFactory.newInstance(configJson);
        JsonObject valueConfigJson = (JsonObject) configJson.get(XDefaultValueConfig.ATTR);
        if (valueConfigJson != null) {
            this.value = ValueConfigFactory.newInstance(valueConfigJson);
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

    public XValueConfig getValue() {
        return value;
    }

    public void setValue(XValueConfig value) {
        this.value = value;
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

        configJson.putJsonIfNotNull(XDefaultValueConfig.ATTR, this.value);

        return configJson;
    }

}
