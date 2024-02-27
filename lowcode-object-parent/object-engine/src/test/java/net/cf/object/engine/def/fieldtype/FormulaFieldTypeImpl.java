package net.cf.object.engine.def.fieldtype;

import net.cf.object.engine.def.field.FieldTestImpl;
import net.cf.object.engine.def.field.FormulaTestUtils;
import net.cf.object.engine.object.DataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 公式字段类型
 *
 * @author clouds
 */
public class FormulaFieldTypeImpl extends AbstractFieldTypeImpl {

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
    public DataType getDataType(FieldTestImpl field) {
        Map<String, Object> attrValueMap = field.getAttrValues();
        Object dataType = attrValueMap.get("dataType");
        if (dataType != null) {
            return DataType.valueOf(dataType.toString());
        } else {
            return DataType.STRING;
        }
    }

    @Override
    public Object getDefaultValue(FieldTestImpl field) {
        Object expression = field.getAttrValues().get("expression");
        assert (expression != null && expression instanceof String);
        // 这里模拟计算一个公式的值
        return FormulaTestUtils.eval((String) expression);
    }
}
