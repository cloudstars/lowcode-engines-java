package net.cf.object.engine.fieldtype;

import net.cf.object.engine.object.DataType;

import java.util.Arrays;
import java.util.List;

/**
 * 日期类字段类型
 *
 * @author clouds
 */
public class DateFieldTypeImpl extends AbstractDateTimeFieldTypeImpl {

    @Override
    public String getDesc() {
        return "日期";
    }

    @Override
    public String getName() {
        return "Date";
    }

    @Override
    public List<AttributeDescriptor> getAttributeDescriptors() {
        AttributeDescriptor attribute = new AttributeDescriptor();
        attribute.setName("日期格式");
        attribute.setCode("dateFormat");
        attribute.setDataType(DataType.STRING);
        attribute.setDefaultValue("YMDHMS");
        AttributeDescriptor.Option option0 = new AttributeDescriptor.Option("yyyy-MM-dd hh:mm:ss", "YMDHMS");
        AttributeDescriptor.Option option1 = new AttributeDescriptor.Option("yyyy-MM-dd hh:mm", "YMDHM");
        AttributeDescriptor.Option option2 = new AttributeDescriptor.Option("yyyy-MM-dd", "YMD");
        attribute.setOptions(Arrays.asList(option0, option1, option2));
        return Arrays.asList(attribute);
    }

    @Override
    public DataType getDataType(FieldSchema fieldSchema) {
        boolean saveAsText = fieldSchema.isSaveAsText();
        if (saveAsText) {
            return DataType.STRING;
        }

        return DataType.DATE;
    }

}
