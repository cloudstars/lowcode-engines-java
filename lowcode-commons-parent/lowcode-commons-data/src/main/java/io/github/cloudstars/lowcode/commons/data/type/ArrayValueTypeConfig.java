package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.data.DataValidationUtils;
import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;

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
    private final static String ITEMS = "items";

    private ValueTypeConfig<V> itemsDataType;

    public ArrayValueTypeConfig() {
    }

    public ArrayValueTypeConfig(JsonObject configJson) {
        super(configJson);

        Object itemsValueType = configJson.get(ITEMS);
        if (itemsValueType == null || !(itemsValueType instanceof JsonObject)) {
            throw new RuntimeException("数组数据类型必须定义" + ITEMS + "，并且是一个JSON对象");
        }

        this.itemsDataType = ValueTypeConfigFactory.newInstance((JsonObject) itemsValueType);

        // 默认值需要在所有属性解析完之后再解析
        this.parseDefaultValue(configJson);
    }

    public ValueTypeConfig<V> getItemsDataType() {
        return itemsDataType;
    }

    public void setItemsDataType(ValueTypeConfig<V> itemsDataType) {
        this.itemsDataType = itemsDataType;
    }

    @Override
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
            listVItems.add(this.itemsDataType.parseTargetValue(listItem));
        }

        return listVItems;
    }

    @Override
    public void validate(List<V> itemValues) throws InvalidDataException {
        ValueTypeConfig itemValueTypeConfig = this.itemsDataType;
        for (V itemValue : itemValues) {
            DataValidationUtils.validate(itemValue, itemValueTypeConfig);
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ITEMS, itemsDataType.toJson());
        return configJson;
    }

}
