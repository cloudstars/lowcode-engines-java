package net.cf.object.engine.fieldtype;

import net.cf.object.engine.object.DataType;

/**
 * 布尔类字段类型
 *
 * @author clouds
 */
public class BooleanFieldTypeImpl implements XFieldType {

    @Override
    public String getDesc() {
        return "布尔值";
    }

    @Override
    public String getName() {
        return "Boolean";
    }

    @Override
    public DataType getDataType(FieldSchema field) {
        return DataType.BOOLEAN;
    }
}
