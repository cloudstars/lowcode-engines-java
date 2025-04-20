package io.github.cloudstars.lowcode.commons.data.predicate.json;

import io.github.cloudstars.lowcode.commons.data.field.XFieldConfig;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;


/**
 * 数值类型的二元操作项
 *
 * @author clouds
 */
public class ValueBinaryItem extends AbstractTypedConfig implements BinaryItem {

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
        this.setType(BinaryItem.TYPE_VALUE);
    }

    public ValueBinaryItem(JsonObject configJson) {
        super(configJson);

        this.value = configJson.get(XFieldConfig.ATTR_VALUE);;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(XFieldConfig.ATTR_VALUE, this.value);

        return configJson;
    }
}
