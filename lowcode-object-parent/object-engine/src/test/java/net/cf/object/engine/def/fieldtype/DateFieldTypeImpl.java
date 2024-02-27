package net.cf.object.engine.def.fieldtype;

import net.cf.object.engine.def.field.FieldTestImpl;
import net.cf.object.engine.object.DataType;

import java.util.Arrays;
import java.util.List;

/**
 * 日期类字段类型
 *
 * @author clouds
 */
public class DateFieldTypeImpl extends AbstractFieldTypeImpl {

    @Override
    public String getName() {
        return "日期";
    }

    @Override
    public String getCode() {
        return "Date";
    }

    @Override
    public DataType getDataType(FieldTestImpl field) {
        Boolean saveAsText = (Boolean) field.getAttrValues().getOrDefault("saveAsText", false);
        if (saveAsText) {
            return DataType.STRING;
        }

        return DataType.DATE;
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

}
