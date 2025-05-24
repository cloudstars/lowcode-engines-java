package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的数据格式配置
 *
 * @author clouds
 */
public abstract class AbstractValueTypeConfig extends AbstractTypedConfig implements XValueTypeConfig {

    /**
     * 是否必填配置
     */
    private Boolean required;

    /**
     * 默认值配置
     */
    private Object defaultValue;

    public AbstractValueTypeConfig() {
        this.setType(this.getClass().getAnnotation(ValueTypeConfigClass.class).name());
    }

    public AbstractValueTypeConfig(JsonObject configJson) {
        super(configJson);

        this.setType(this.getClass().getAnnotation(ValueTypeConfigClass.class).name());
        this.required = ConfigUtils.getBoolean(configJson, XValueTypeConfig.ATTR_REQUIRED);
        this.defaultValue = ConfigUtils.get(configJson, XValueTypeConfig.ATTR_DEFAULT_VALUE);
    }

    @Override
    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.put(configJson, XValueTypeConfig.ATTR_REQUIRED, this.required);
        ConfigUtils.put(configJson, XValueTypeConfig.ATTR_DEFAULT_VALUE, this.defaultValue);
        
        return configJson;
    }

}
