package io.github.cloudstars.lowcode.commons.value;

import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.value.type.ArrayValueTypeConfig;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组数据格式实现
 *
 * @param <T> 数组元素的类型
 */
public class ArrayValueTypeImpl<T extends Object> extends AbstractValueTypeImpl<ArrayValueTypeConfig<T>, List<T>> {

    public ArrayValueTypeImpl(ArrayValueTypeConfig<T> valueTypeConfig) {
        super(valueTypeConfig);
    }

    @Override
    public List<T> parseDefaultValue(Object defaultValueConfig) {
        if (defaultValueConfig != null) {
            if (defaultValueConfig instanceof List) {
                XValueTypeConfig itemValueTypeConfig = this.valueTypeConfig.getItemsValueType();
                List defaultValueConfigList = (List) defaultValueConfig;
                List<T> itemValues = new ArrayList<>();
                for (Object defaultValueConfigItem : defaultValueConfigList) {
                    T itemValue = (T) ValueTypeFactory.newInstance(itemValueTypeConfig).parseDefaultValue(defaultValueConfigItem);
                    itemValues.add(itemValue);
                }
                return itemValues;
            } else {
                throw new InvalidDataException("数组数据格式的默认值不正确，请检查您的数据：" + JsonUtils.toJsonString(defaultValueConfig));
            }
        }

        return (List<T>) defaultValueConfig;
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
