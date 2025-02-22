package net.cf.object.engine.fieldtype;

import net.cf.object.engine.object.DataType;

import java.util.Arrays;
import java.util.List;

/**
 * 时间类字段类型
 *
 * @author clouds
 */
public class TimeFieldTypeImpl extends AbstractDateTimeFieldTypeImpl {

    @Override
    public String getDesc() {
        return "时间";
    }

    @Override
    public String getName() {
        return "Time";
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

    @Override
    public DataType getDataType(FieldSchema fieldSchema) {
        boolean saveAsText = fieldSchema.isSaveAsText();
        if (saveAsText) {
            return DataType.STRING;
        }

        return DataType.TIME;
    }
}
