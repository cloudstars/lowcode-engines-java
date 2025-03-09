package io.github.cloudstars.lowcode.commons.lang.value;

import io.github.cloudstars.lowcode.commons.lang.DataType;

/**
 * 布尔数据格式
 *
 * @author clouds
 */
public class BooleanValueType extends AbstractValueType implements ValueType {

    public BooleanValueType(boolean isArray) {
        super(isArray);
    }

    @Override
    public DataType getDataType() {
        return DataType.BOOLEAN;
    }

    @Override
    protected void validateNonNullValue(Object value) throws InvalidDataFormatException {
    }

    @Override
    protected Object getCorrectNonNullValue(Object value) {
        if (value instanceof Boolean) {
            return value;
        }

        if (value instanceof String) {
            return "true".equalsIgnoreCase((String) value);
        }

        if (value instanceof Integer) {
            return 0 != (Integer) value;
        }

        return false;
    }

}
