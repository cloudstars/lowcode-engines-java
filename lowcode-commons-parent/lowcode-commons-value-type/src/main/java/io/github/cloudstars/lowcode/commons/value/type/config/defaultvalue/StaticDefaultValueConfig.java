package io.github.cloudstars.lowcode.commons.value.type.config.defaultvalue;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 常量默认值配置
 *
 * @author clouds
 */
@DefaultValueConfigClass(name = "STATIC")
public class StaticDefaultValueConfig extends AbstractDefaultValueConfig {

    // value配置名称
    private static final String ATTR_VALUE = "value";

    /**
     * 静态值
     */
    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public StaticDefaultValueConfig() {
    }

    public StaticDefaultValueConfig(JsonObject configJson) {
        super(configJson);

        this.value = configJson.get(ATTR_VALUE);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_VALUE, this.value);

        return configJson;
    }

}
