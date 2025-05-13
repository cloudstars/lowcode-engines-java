package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 数组类型的数据格式
 *
 */
@ValueTypeConfigClass(name = "ARRAY", valueClass = List.class)
public class ArrayValueTypeConfig extends AbstractArrayValueTypeConfig {

    public ArrayValueTypeConfig() {
    }

    public ArrayValueTypeConfig(JsonObject configJson) {
        super(configJson);

        ConfigUtils.consume(configJson, ATTR_ITEMS, (v) -> {
            this.itemsValueType = ValueTypeConfigFactory.newInstance((JsonObject) v);
        });
    }

    @Override
    public DataTypeEnum getDataType() {
        return DataTypeEnum.ARRAY;
    }

    public XValueTypeConfig getItemsValueType() {
        return itemsValueType;
    }

    public void setItemsValueType(XValueTypeConfig itemsValueType) {
        this.itemsValueType = itemsValueType;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putJson(configJson, ATTR_ITEMS, this.itemsValueType);

        return configJson;
    }

}
