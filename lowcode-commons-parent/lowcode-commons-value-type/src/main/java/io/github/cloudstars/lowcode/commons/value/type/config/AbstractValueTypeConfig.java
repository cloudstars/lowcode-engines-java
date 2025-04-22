package io.github.cloudstars.lowcode.commons.value.type.config;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.config.defaultvalue.DefaultValueConfigFactory;
import io.github.cloudstars.lowcode.commons.value.type.config.defaultvalue.StaticDefaultValueConfig;
import io.github.cloudstars.lowcode.commons.value.type.config.defaultvalue.XDefaultValueConfig;

/**
 * 抽象的数据格式配置
 *
 * @author clouds
 */
public abstract class AbstractValueTypeConfig extends AbstractTypedConfig implements XValueTypeConfig {

    /**
     * 默认值配置
     */
    private XDefaultValueConfig defaultValue;

    /**
     * 以默认的方式解析，即本置中没有 {type: STATIC}，直接写了value值
     */
    private boolean parseAsDefault;

    public XDefaultValueConfig getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(XDefaultValueConfig defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean getParseAsDefault() {
        return parseAsDefault;
    }

    public void setParseAsDefault(boolean parseAsDefault) {
        this.parseAsDefault = parseAsDefault;
    }

    public AbstractValueTypeConfig() {
    }

    public AbstractValueTypeConfig(JsonObject configJson) {
        super(configJson);

        this.setType(this.getClass().getAnnotation(ValueTypeConfigClass.class).name());
        Object defaultValueValue = configJson.get(XDefaultValueConfig.ATTR);
        if (defaultValueValue != null) {
            if (!(defaultValueValue instanceof JsonObject)) {
                StaticDefaultValueConfig staticDefaultValueConfig = new StaticDefaultValueConfig();
                staticDefaultValueConfig.setValue(configJson.get(XDefaultValueConfig.ATTR));
                this.defaultValue = staticDefaultValueConfig;
                this.parseAsDefault = true;
            } else {
                JsonObject defaultValueConfigJson = (JsonObject) defaultValueValue;
                this.defaultValue = DefaultValueConfigFactory.newInstance(defaultValueConfigJson);
            }
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        if (this.defaultValue != null) {
            if (this.parseAsDefault) {
                StaticDefaultValueConfig staticDefaultValueConfig = (StaticDefaultValueConfig) this.defaultValue;
                configJson.put(XDefaultValueConfig.ATTR, staticDefaultValueConfig.getValue());
            } else {
                configJson.put(XDefaultValueConfig.ATTR, this.defaultValue.toJson());
            }
        }

        return configJson;
    }

}
