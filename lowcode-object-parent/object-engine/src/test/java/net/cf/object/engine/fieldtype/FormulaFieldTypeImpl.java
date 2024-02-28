package net.cf.object.engine.fieldtype;

import net.cf.object.engine.object.DataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 公式字段类型
 *
 * @author clouds
 */
public class FormulaFieldTypeImpl implements XDevFieldType {

    @Override
    public String getName() {
        return "序号";
    }

    @Override
    public String getCode() {
        return "Sequence";
    }

    @Override
    public List<AttributeDescriptor> getAttributeDescriptors() {
        List<AttributeDescriptor> descriptors = new ArrayList<>();
        {
            AttributeDescriptor expressionDescr = new AttributeDescriptor();
            expressionDescr.setName("公式");
            expressionDescr.setCode("expression");
            expressionDescr.setDataType(DataType.STRING);
            descriptors.add(expressionDescr);
        }
        {
            AttributeDescriptor dataTypeDescr = new AttributeDescriptor();
            dataTypeDescr.setName("数据类型");
            dataTypeDescr.setCode("dataType");
            dataTypeDescr.setDataType(DataType.STRING);
            AttributeDescriptor.Option option0 = new AttributeDescriptor.Option("文本", "Text");
            AttributeDescriptor.Option option1 = new AttributeDescriptor.Option("数字", "Number");
            AttributeDescriptor.Option option2 = new AttributeDescriptor.Option("日期", "Date");
            AttributeDescriptor.Option option3 = new AttributeDescriptor.Option("时间", "Time");
            AttributeDescriptor.Option option4 = new AttributeDescriptor.Option("布尔", "Boolean");
            dataTypeDescr.setOptions(Arrays.asList(option0, option1, option2, option3, option4));
            descriptors.add(dataTypeDescr);
        }

        return descriptors;
    }

    @Override
    public DataType getDataType(FieldSchema fieldSchema) {
        String dataType = fieldSchema.getDataType();
        if (dataType != null) {
            return DataType.valueOf(dataType);
        } else {
            return DataType.STRING;
        }
    }

    @Override
    public List<FieldAttribute> getAttributes(FieldSchema fieldSchema) {
        List<FieldAttribute> attributes = new ArrayList<>();
        {
            FieldAttribute exprAttr = new FieldAttribute();
            exprAttr.setName("表达式");
            exprAttr.setCode("expression");
            exprAttr.setValue(fieldSchema.getExpression());
            attributes.add(exprAttr);
        }

        return attributes;
    }

}
