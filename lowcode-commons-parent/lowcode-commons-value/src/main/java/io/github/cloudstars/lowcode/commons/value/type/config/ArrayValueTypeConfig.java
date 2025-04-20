package io.github.cloudstars.lowcode.commons.value.type.config;

import io.github.cloudstars.lowcode.commons.value.XValueConfig;
import io.github.cloudstars.lowcode.commons.value.type.DataTypeEnum;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 数组类型的数据格式
 *
 */
@ValueTypeConfigClass(name = "ARRAY")
public class ArrayValueTypeConfig extends AbstractValueTypeConfig {

    /**
     * 数组下元素的属性名称
     */
    private final static String ATTR_ITEMS = "items";

    /**
     * 数组下元数的数据格式
     */
    private XValueTypeConfig itemsValueType;

    /**
     * 数组下元数是否必填
     */
    private Boolean itemsRequired;

    /**
     * 数组下元素的数据格式
     */
    //private XValueTypeConfig<V> items;

    public ArrayValueTypeConfig() {
    }

    public ArrayValueTypeConfig(JsonObject configJson) {
        super(configJson);

        JsonObject itemsConfigJson = (JsonObject) configJson.get(ATTR_ITEMS);
        this.itemsValueType = ValueTypeConfigFactory.newInstance(itemsConfigJson);
        this.itemsRequired = (Boolean) itemsConfigJson.get(XValueConfig.ATTR_REQUIRED);
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

    /*@Override
    protected List<V> parseDefaultValue(Object defaultValueConfig) {
        if (defaultValueConfig != null) {
            if (defaultValueConfig instanceof List) {
                List<Object> defaultValueConfigList = (List<Object>) defaultValueConfig;
                List<V> itemValues = new ArrayList<>();
                for (Object defaultValueConfigItem : defaultValueConfigList) {
                    V itemValue = itemsDataType.parseTargetValue(defaultValueConfigItem);
                    itemValues.add(itemValue);
                }
                return itemValues;
            } else {
                throw new InvalidDataException("数组数据格式的默认值不正确，请检查您的数据：" + JsonUtils.toJsonString(defaultValueConfig));
            }
        }

        return null;
    }*/

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        // 将items的信息合并在一个json中
        JsonObject itemConfigJson = this.itemsValueType.toJson();
        itemConfigJson.putIfNotNull(XValueConfig.ATTR_REQUIRED, this.itemsRequired);
        configJson.put(ATTR_ITEMS, itemConfigJson);

        return configJson;
    }

}
