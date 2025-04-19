package io.github.cloudstars.lowcode.commons.data.expression.json;

import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;


/**
 * 数值类型的二元操作项
 *
 * @author clouds
 */
public class ValueBinaryItem extends AbstractConfig implements BinaryItem {

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

    public ValueBinaryItem() {
    }

    public ValueBinaryItem(JsonObject configJson) {
        super(configJson);

        this.value = configJson.get(XConfig.ATTR_VALUE);;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_TYPE, BinaryItem.TYPE_VALUE);
        configJson.put(ATTR_VALUE, this.value);
        return configJson;
    }
}
