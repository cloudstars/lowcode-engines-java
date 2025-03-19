package io.github.cloudstars.lowcode.commons.data.type.custom;

import io.github.cloudstars.lowcode.commons.data.type.DataProperty;
import io.github.cloudstars.lowcode.commons.data.type.ObjectDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 选项数据格式
 *
 * @author clouds
 */
public class OptionsValueType extends ObjectDataType {

    public OptionsValueType() {
    }

    @Override
    public String getName() {
        return "OPTIONS";
    }

    @Override
    public List<DataProperty> getProperties(Map<String, Object> options) {
        List<DataProperty> OPTIONS_PROPS = new ArrayList<>();
        String labelField = (String) options.getOrDefault("labelField", "label");
        String valueField = (String) options.getOrDefault("valueField", "value");
        //OPTIONS_PROPS.add(new DataProperty(labelField, DataTypeClassFactory.get(BuildInDataTypeConstants.TEXT)));
        //OPTIONS_PROPS.add(new DataProperty(valueField, DataTypeClassFactory.get(BuildInDataTypeConstants.TEXT)));

        return OPTIONS_PROPS;
    }
}
