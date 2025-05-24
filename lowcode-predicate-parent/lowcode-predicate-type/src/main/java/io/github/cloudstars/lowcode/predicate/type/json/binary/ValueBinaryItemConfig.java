package io.github.cloudstars.lowcode.predicate.type.json.binary;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;


/**
 * 数值类型的二元操作项
 *
 * @author clouds
 */
public class ValueBinaryItemConfig extends AbstractTypedConfig implements XBinaryItemConfig {

    // value配置名称
    private static final String ATTR_VALUE = "value";

    /**
     * 数值
     */
    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ValueBinaryItemConfig() {
        this.setType(TYPE_VALUE);
    }

    public ValueBinaryItemConfig(JsonObject configJson) {
        super(configJson);

        this.value = configJson.get(ATTR_VALUE);;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_VALUE, this.value);

        return configJson;
    }
}
