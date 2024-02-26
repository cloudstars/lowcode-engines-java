package net.cf.form.engine.def.fieldtype;

import net.cf.form.engine.def.field.FieldTestImpl;
import net.cf.form.engine.def.field.FormulaTestUtils;
import net.cf.form.engine.object.DataType;

import java.util.*;

/**
 * 流水号字段类型
 *
 * @author clouds
 */
public class SequenceFieldTypeImpl extends AbstractFieldTypeImpl {

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
            AttributeDescriptor typeDescr = new AttributeDescriptor();
            typeDescr.setName("表达式"); // 流水号表达式的格式示例：#{$time.YEAR}-OA-#{value}
            typeDescr.setCode("expression");
            typeDescr.setDataType(DataType.STRING);
            descriptors.add(typeDescr);
        }
        return descriptors;
    }

    @Override
    public Object getDefaultValue(FieldTestImpl field) {
        String expression = (String) field.getAttrValues().get("expression");
        Map<String, Object> paramMap = new HashMap<>();
        // 这里模拟返回一个流水号
        String sequenceValue = UUID.randomUUID().toString().substring(0, 8);
        paramMap.put("value", sequenceValue);
        Map<String, Object> dateParamMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        dateParamMap.put("YEAR", calendar.get(Calendar.YEAR));
        dateParamMap.put("MONTH", calendar.get(Calendar.MONTH));
        dateParamMap.put("DATE", calendar.get(Calendar.DATE));
        paramMap.put("$time", dateParamMap);
        return FormulaTestUtils.eval(expression, paramMap);
    }
}
