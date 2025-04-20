package io.github.cloudstars.lowcode.commons.data.defaultvalue;

import io.github.cloudstars.lowcode.commons.data.field.XFieldConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 静态默认值配置
 *
 * @author clouds
 */
@DefaultValueConfigClass(name = "STATIC")
public class StaticDefaultValueConfig extends AbstractDefaultValueConfig {

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

        this.value = configJson.get(XFieldConfig.ATTR_VALUE);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(XFieldConfig.ATTR_VALUE, this.value);

        return configJson;
    }

}
