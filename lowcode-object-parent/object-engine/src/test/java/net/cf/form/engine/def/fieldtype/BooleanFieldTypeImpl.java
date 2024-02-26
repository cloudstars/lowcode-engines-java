package net.cf.form.engine.def.fieldtype;

import net.cf.form.engine.def.field.FieldTestImpl;
import net.cf.form.engine.object.DataType;

/**
 * 布尔类字段类型
 *
 * @author clouds
 */
public class BooleanFieldTypeImpl extends AbstractFieldTypeImpl {

    @Override
    public String getName() {
        return "布尔值";
    }

    @Override
    public String getCode() {
        return "Boolean";
    }

    @Override
    public DataType getDataType(FieldTestImpl field) {
        return DataType.BOOLEAN;
    }
}
