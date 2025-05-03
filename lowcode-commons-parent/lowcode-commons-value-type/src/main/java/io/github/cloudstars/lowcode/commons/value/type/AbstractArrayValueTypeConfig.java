package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象的数组类型的数据格式
 *
 * @author clouds
 *
 */
public abstract class AbstractArrayValueTypeConfig extends AbstractValueTypeConfig {

    /**
     * 数组下元素的属性名称
     */
    protected static final String ATTR_ITEMS = "items";

    /**
     * 数组下元数的数据格式
     */
    protected XValueTypeConfig itemsValueType;

    public AbstractArrayValueTypeConfig() {
    }

    public AbstractArrayValueTypeConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public DataTypeEnum getDataType() {
        return DataTypeEnum.ARRAY;
    }

    public XValueTypeConfig getItemsValueType() {
        return itemsValueType;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }

}
