package io.github.cloudstars.lowcode.dynamic.value;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * API动态值配置
 *
 * @author clouds
 */
@DynamicValueConfigClass(name = "API")
public class ApiDynamicDynamicValueConfig extends AbstractDynamicValueConfig {

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

    public ApiDynamicDynamicValueConfig() {
    }

    public ApiDynamicDynamicValueConfig(JsonObject configJson) {
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
