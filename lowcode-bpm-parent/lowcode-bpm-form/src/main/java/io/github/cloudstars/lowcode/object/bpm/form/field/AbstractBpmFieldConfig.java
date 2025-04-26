package io.github.cloudstars.lowcode.object.bpm.form.field;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * 抽象的字段配置
 *
 * @author clouds
 */
public abstract class AbstractBpmFieldConfig extends AbstractResourceConfig implements XBpmFieldConfig {

    // 和流程相关的配 private

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 数据格式配置
     */
    private XValueTypeConfig valueType;

    protected AbstractBpmFieldConfig() {
    }

    protected AbstractBpmFieldConfig(JsonObject configJson) {
        super(configJson);

        this.setType(this.getClass().getAnnotation(BpmFieldConfigClass.class).name());
        this.required = (Boolean) configJson.get(XBpmFieldConfig.ATTR_REQUIRED);
        this.valueType = ValueTypeConfigFactory.newInstance(configJson);
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
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, XBpmFieldConfig.ATTR_REQUIRED, this.required);

        // 将数据格式的属性添加到当前对象上
        if (this.valueType != null) {
            JsonObject<String, Object> valueTypeConfigJson = this.valueType.toJson();
            valueTypeConfigJson.forEach((k, v) -> {
                if (!XTypedConfig.ATTR.equalsIgnoreCase(k)) { // 避免数据格式的类型覆盖字段类型
                    configJson.put(k, v);
                }
            });
        }

        return configJson;
    }

}
