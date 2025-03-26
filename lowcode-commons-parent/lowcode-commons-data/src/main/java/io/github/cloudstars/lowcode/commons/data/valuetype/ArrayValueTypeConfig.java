package io.github.cloudstars.lowcode.commons.data.valuetype;

import io.github.cloudstars.lowcode.commons.data.DataTypeEnum;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.lang.value.InvalidValueFormatException;

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
     * 配置中的ITEMS属性名称
     */
    private final static String ITEMS_ATTR = "items";

    private ValueTypeConfig<V> itemsValueType;

    public ArrayValueTypeConfig(JsonObject configJson) {
        super(configJson);

        Object itemsValueType = configJson.get(ITEMS_ATTR);
        if (itemsValueType == null || !(itemsValueType instanceof JsonObject)) {
            throw new RuntimeException("数组数据类型必须定义" + ITEMS_ATTR + "，并且是一个JSON对象");
        }

        this.itemsValueType = ValueTypeFactory.newInstance((JsonObject) itemsValueType);

        // 默认值需要在所有属性解析完之后再解析
        this.parseDefaultValue(configJson);
    }

    @Override
    protected List<V> parseDefaultValue(Object defaultValueConfig) {
        if (defaultValueConfig != null) {
            if (defaultValueConfig instanceof List) {
                List<Object> defaultValueConfigList = (List<Object>) defaultValueConfig;
                List<V> itemValues = new ArrayList<>();
                for (Object defaultValueConfigItem : defaultValueConfigList) {
                    V itemValue = itemsValueType.parseTargetValue(defaultValueConfigItem);
                    itemValues.add(itemValue);
                }
                return itemValues;
            } else {
                throw new InvalidValueFormatException("数组数据格式的默认值不正确，请检查您的数据：" + JsonUtils.toJsonString(defaultValueConfig));
            }
        }

        return null;
    }

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
            listVItems.add(this.itemsValueType.parseTargetValue(listItem));
        }

        return listVItems;
    }

    @Override
    public DataTypeEnum getDataType() {
        return DataTypeEnum.ARRAY;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = super.toJson();
        jsonObject.put(ITEMS_ATTR, itemsValueType.toJson());
        return jsonObject;
    }

    public ValueTypeConfig<V> getItemsValueType() {
        return itemsValueType;
    }
}
