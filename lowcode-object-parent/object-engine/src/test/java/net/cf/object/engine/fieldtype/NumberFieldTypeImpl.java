package net.cf.object.engine.fieldtype;

/**
 * 数字类字段类型
 *
 * @author clouds
 */
public class NumberFieldTypeImpl implements XFieldType {

    @Override
    public String getName() {
        return "数字";
    }

    @Override
    public String getCode() {
        return "Number";
    }

    @Override
    public Integer getDataLength(FieldSchema fieldSchema) {
        return fieldSchema.getDataLength();
    }

    @Override
    public Integer getDataPrecision(FieldSchema fieldSchema) {
        return fieldSchema.getDataPrecision();
    }
}
