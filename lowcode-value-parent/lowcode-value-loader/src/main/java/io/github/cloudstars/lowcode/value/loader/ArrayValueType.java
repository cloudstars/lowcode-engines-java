package io.github.cloudstars.lowcode.value.loader;

import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.value.type.ArrayValueTypeConfig;
import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组数据格式实现
 *
 * @param <T> 数组元素的类型
 */
@ValueTypeClass(name = "ARRAY", valueTypeConfigClass = ArrayValueTypeConfig.class)
public class ArrayValueType<T extends Object> extends AbstractValueType<ArrayValueTypeConfig, List<T>> {

    public ArrayValueType(ArrayValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);
    }

    @Override
    public List<T> parseDefaultValue() throws InvalidDataException {
        return null;
    }

    @Override
    public List<T> mergeDefaultValue(Object rawValue) {
        if (rawValue != null) {
            if (rawValue instanceof List) {
                XValueTypeConfig itemValueTypeConfig = this.valueTypeConfig.getItemsValueType();
                List defaultValueConfigList = (List) rawValue;
                List<T> itemValues = new ArrayList<>();
                for (Object defaultValueConfigItem : defaultValueConfigList) {
                    T itemValue = (T) ValueTypeFactory.newInstance(itemValueTypeConfig).mergeDefaultValue(defaultValueConfigItem);
                    itemValues.add(itemValue);
                }
                return itemValues;
            } else {
                throw new InvalidDataException("数组数据格式的默认值不正确，请检查您的数据：" + JsonUtils.toJsonString(rawValue));
            }
        }

        return (List<T>) rawValue;
    }

    @Override
    public List parseValue(Object rawValue) throws InvalidDataException {
        List<Object> listItems;
        if (rawValue instanceof List) {
            listItems = (List<Object>) rawValue;
        } else {
            listItems = Arrays.asList(rawValue);
        }

        List targetListItems = new ArrayList<>();
        XValueTypeConfig itemValueTypeConfig = this.valueTypeConfig.getItemsValueType();
        XValueType itemValue = ValueTypeFactory.newInstance(itemValueTypeConfig);
        for (Object listItem : listItems) {
            targetListItems.add(itemValue.parseValue(listItem));
        }

        return targetListItems;
    }

    @Override
    public void validate(List value) throws InvalidDataException {
        XValueTypeConfig itemValueTypeConfig = this.valueTypeConfig.getItemsValueType();
        XValueType itemValueType = ValueTypeFactory.newInstance(itemValueTypeConfig);
        for (Object itemValue : value) {
            if (itemValue != null) {
                itemValueType.validate(itemValue);
            }
        }
    }

}
