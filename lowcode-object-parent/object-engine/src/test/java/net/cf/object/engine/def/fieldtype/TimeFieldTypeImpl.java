package net.cf.object.engine.def.fieldtype;

import net.cf.object.engine.def.field.FieldTestImpl;
import net.cf.object.engine.object.DataType;

import java.util.Arrays;
import java.util.List;

/**
 * 时间类字段类型
 *
 * @author clouds
 */
public class TimeFieldTypeImpl  implements XFieldType {

    @Override
    public String getName() {
        return "时间";
    }

    @Override
    public String getCode() {
        return "Time";
    }

    @Override
    public DataType getDataType(FieldTestImpl field) {
        Boolean saveAsText = (Boolean) field.getAttrValues().getOrDefault("saveAsText", false);
        if (saveAsText) {
            return DataType.STRING;
        }

        return DataType.TIME;
    }

    @Override
    public List<AttributeDescriptor> getAttributeDescriptors() {
        AttributeDescriptor attribute = new AttributeDescriptor();
        attribute.setName("时间格式");
        attribute.setCode("timeFormat");
        attribute.setDataType(DataType.STRING);
        attribute.setDefaultValue("HMS");
        AttributeDescriptor.Option option1 = new AttributeDescriptor.Option("hh:mm:ss", "HMS");
        AttributeDescriptor.Option option2 = new AttributeDescriptor.Option("hh:mm", "HM");
        attribute.setOptions(Arrays.asList(option1, option2));
        return Arrays.asList(attribute);
    }

}
