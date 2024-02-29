package net.cf.object.engine.fieldtype;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表一类可以选择（单多选）的字段类型的抽象
 *
 * @author clouds
 */
public abstract class AbstractDateTimeFieldTypeImpl implements XFieldType {

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
