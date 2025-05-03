package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 数组类型的数据格式
 *
 */
@ValueTypeConfigClass(name = "ARRAY", valueClass = List.class)
public class ArrayValueTypeConfig extends AbstractArrayValueTypeConfig {

    /**
     * 标识字段名配置名称
     */
    private String keyField;

    public ArrayValueTypeConfig() {
    }

    public ArrayValueTypeConfig(JsonObject configJson) {
        super(configJson);

        JsonObject itemsConfigJson = (JsonObject) configJson.get(ATTR_ITEMS);
        this.itemsValueType = ValueTypeConfigFactory.newInstance(itemsConfigJson);
        this.keyField = ConfigUtils.getString(configJson, GlobalAttrNames.ATTR_KEY_FIELD);
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

    public String getParentKeyField() {
        return keyField;
    }

    public void setParentKeyField(String parentKeyField) {
        this.keyField = parentKeyField;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putJson(configJson, ATTR_ITEMS, this.itemsValueType);

        return configJson;
    }

}
