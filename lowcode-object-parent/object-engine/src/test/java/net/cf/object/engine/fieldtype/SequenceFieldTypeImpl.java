package net.cf.object.engine.fieldtype;

import net.cf.object.engine.object.DataType;

import java.util.ArrayList;
import java.util.List;

/**
 * 流水号字段类型
 *
 * @author clouds
 */
public class SequenceFieldTypeImpl implements XFieldType {

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
    public List<FieldAttribute> getAttributes(FieldSchema fieldSchema) {
        List<FieldAttribute> attributes = new ArrayList<>();
        {
            FieldAttribute exprAttr = new FieldAttribute();
            exprAttr.setName("格式");
            exprAttr.setCode("format");
            exprAttr.setValue(fieldSchema.getExpression());
            attributes.add(exprAttr);
        }

        return attributes;
    }

}
