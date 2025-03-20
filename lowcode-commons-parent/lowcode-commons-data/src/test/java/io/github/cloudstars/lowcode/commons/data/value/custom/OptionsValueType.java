package io.github.cloudstars.lowcode.commons.data.value.custom;

import io.github.cloudstars.lowcode.commons.data.value.AbstractValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.value.DataTypeConfigClass;
import io.github.cloudstars.lowcode.commons.data.value.ObjectProperty;
import io.github.cloudstars.lowcode.commons.data.value.StoreValueType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 选项数据格式
 *
 * @author clouds
 */
@DataTypeConfigClass(name = "OBJECT")
public class OptionsValueType extends AbstractValueTypeConfig<OptionValue> {

    public OptionsValueType() {
    }

    @Override
    public StoreValueType getStoreDataType() {
        return StoreValueType.OBJECT;
    }

    @Override
    protected OptionValue parseDefaultValue(Object defaultValueConfig) {
        return null;
    }

    @Override
    public Object parseNonNullValue(Object nonNullValue) {
        return null;
    }

    public List<ObjectProperty> getProperties(Map<String, Object> options) {
        List<ObjectProperty> OPTIONS_PROPS = new ArrayList<>();
        String labelField = (String) options.getOrDefault("labelField", "label");
        String valueField = (String) options.getOrDefault("valueField", "value");
        //OPTIONS_PROPS.add(new DataProperty(labelField, DataTypeClassFactory.get(BuildInDataTypeConstants.TEXT)));
        //OPTIONS_PROPS.add(new DataProperty(valueField, DataTypeClassFactory.get(BuildInDataTypeConstants.TEXT)));

        return OPTIONS_PROPS;
    }
}
