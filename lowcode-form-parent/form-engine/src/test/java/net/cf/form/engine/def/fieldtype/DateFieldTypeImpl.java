package net.cf.form.engine.def.fieldtype;

import net.cf.form.engine.object.DataType;
import net.cf.form.engine.object.XField;

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
    public <T extends XField> DataType getDataType(T field) {
        Boolean saveAsText = (Boolean) field.getAttributeValues().getOrDefault("saveAsText", false);
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
        AttributeDescriptor.Option option1 = new AttributeDescriptor.Option("格式：yyyy-MM-dd hh:mm:ss", "DateTime");
        AttributeDescriptor.Option option2 = new AttributeDescriptor.Option("格式：yyyy-MM-dd", "Date");
        AttributeDescriptor.Option option3 = new AttributeDescriptor.Option("格式：hh:mm:ss", "Time");
        attribute.setOptions(Arrays.asList(option1, option2, option3));
        return Arrays.asList(attribute);
    }

}
