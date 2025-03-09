package io.github.cloudstars.lowcode.commons.lang.value;

import io.github.cloudstars.lowcode.commons.lang.DataType;

/**
 * 数字数据格式
 *
 * @author clouds
 */
public class NumberValueType extends AbstractValueType implements ValueType {

    public NumberValueType(boolean isArray) {
        super(isArray);
    }

    @Override
    public DataType getDataType() {
        return DataType.NUMBER;
    }

    @Override
    protected void validateNonNullValue(Object value) throws InvalidDataFormatException {
    }

    @Override
    protected Object getCorrectNonNullValue(Object value) {
        return value;
    }

}
