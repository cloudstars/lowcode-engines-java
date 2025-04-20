package io.github.cloudstars.lowcode.commons.data.valuetype;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.data.field.XFieldConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组类型的数据格式
 *
 * @param <V>
 */
@ValueTypeConfigClass(name = "ARRAY")
public class ArrayValueTypeConfig<V> extends AbstractValueTypeConfig<List<V>> {

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
        this.itemsRequired = (Boolean) itemsConfigJson.get(XFieldConfig.ATTR_REQUIRED);
        //Object itemsValueType = configJson.get(ITEMS);
        //this.items = ValueTypeConfigFactory.newInstance((JsonObject) itemsValueType);

        // 默认值需要在所有属性解析完之后再解析
        // this.parseDefaultValue(configJson);
    }

    @Override
    public DataTypeEnum getDataType() {
        return DataTypeEnum.ARRAY;
    }

    /*public XValueTypeConfig<V> getItems() {
        return items;
    }

    public void setItems(XValueTypeConfig<V> items) {
        this.items = items;
    }*/

    public XValueTypeConfig getItemsValueType() {
        return itemsValueType;
    }

    public void setItemsValueType(XValueTypeConfig itemsValueType) {
        this.itemsValueType = itemsValueType;
    }

    public Boolean getItemsRequired() {
        return itemsRequired;
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
    public List<V> parseNonNullValue(Object nonNullValue) {
        List<Object> listItems;
        if (nonNullValue instanceof List) {
            listItems = (List<Object>) nonNullValue;
        } else {
            listItems = Arrays.asList(nonNullValue);
        }

        List<V> listVItems = new ArrayList<>();
        for (Object listItem : listItems) {
            //listVItems.add(this.itemsDataType.parseTargetValue(listItem));
        }

        return listVItems;
    }

    @Override
    public void validateNonNullValue(List<V> itemValues) throws InvalidDataException {
        XValueTypeConfig itemValueTypeConfig = this.itemsValueType;
        for (V itemValue : itemValues) {
            if (itemValue != null) {
                itemValueTypeConfig.validateNonNullValue(itemValue);
            }
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        // 将items的信息合并在一个json中
        JsonObject itemConfigJson = this.itemsValueType.toJson();
        itemConfigJson.putIfNotNull(XFieldConfig.ATTR_REQUIRED, this.itemsRequired);
        configJson.put(ATTR_ITEMS, itemConfigJson);

        return configJson;
    }

}
