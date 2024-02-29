package net.cf.object.engine.def.fieldtype;

import net.cf.object.engine.def.field.FieldTestImpl;
import net.cf.object.engine.object.DataType;

/**
 * 布尔类字段类型
 *
 * @author clouds
 */
public class BooleanFieldTypeImpl  implements XFieldType {

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
