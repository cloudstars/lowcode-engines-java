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
public class ArrayValueTypeConfig extends AbstractValueTypeConfig {

    /**
     * 数组下元素的属性名称
     */
    private static final String ATTR_ITEMS = "items";

    /**
     * 数组下元数的数据格式
     */
    private XValueTypeConfig itemsValueType;

    /**
     * 数组下元数是否必填
     */
    private Boolean itemsRequired;

    public ArrayValueTypeConfig() {
    }

    public ArrayValueTypeConfig(JsonObject configJson) {
        super(configJson);

        JsonObject itemsConfigJson = (JsonObject) configJson.get(ATTR_ITEMS);
        this.itemsValueType = ValueTypeConfigFactory.newInstance(itemsConfigJson);
        this.itemsRequired = (Boolean) itemsConfigJson.get(GlobalAttrNames.ATTR_REQUIRED);
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

    public void setItemsRequired(Boolean itemsRequired) {
        this.itemsRequired = itemsRequired;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        // 将items的信息合并在一个json中
        JsonObject itemConfigJson = this.itemsValueType.toJson();
        ConfigUtils.putIfNotNull(itemConfigJson, GlobalAttrNames.ATTR_REQUIRED, this.itemsRequired);
        configJson.put(ATTR_ITEMS, itemConfigJson);

        return configJson;
    }

}
