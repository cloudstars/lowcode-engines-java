package io.github.cloudstars.lowcode.commons.data.valuetype;

import io.github.cloudstars.lowcode.commons.data.valuetype.config.ArrayValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.config.XValueTypeConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayValueTypeImpl extends AbstractValueTypeImpl<ArrayValueTypeConfig, List> {

    public ArrayValueTypeImpl(ArrayValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);
    }

    @Override
    public List parseDefaultValue() {
        /*XDefaultValueConfig defaultValueConfig = this.valueTypeConfig.getItemsValueType();
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
        }*/

        return null;
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
