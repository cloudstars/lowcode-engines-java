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
     * 默认值配置
     */
    private Object defaultValue;

    public AbstractValueTypeConfig() {
    }

    public AbstractValueTypeConfig(JsonObject configJson) {
        super(configJson);

        this.setType(this.getClass().getAnnotation(ValueTypeConfigClass.class).name());
        this.defaultValue = configJson.get(XValueTypeConfig.ATTR_DEFAULT_VALUE);
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
        ConfigUtils.putIfNotNull(configJson, XValueTypeConfig.ATTR_DEFAULT_VALUE, this.defaultValue);
        return configJson;
    }

}
