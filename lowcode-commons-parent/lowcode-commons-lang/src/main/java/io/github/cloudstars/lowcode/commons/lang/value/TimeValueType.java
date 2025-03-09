package io.github.cloudstars.lowcode.commons.lang.value;

import io.github.cloudstars.lowcode.commons.lang.DataType;

/**
 * 时间数据格式
 *
 * @author clouds
 */
public class TimeValueType extends AbstractValueType implements ValueType {

    public TimeValueType(boolean isArray) {
        super(isArray);
    }

    @Override
    public DataType getDataType() {
        return DataType.TIME;
    }

    @Override
    protected void validateNonNullValue(Object value) throws InvalidDataFormatException {
    }

    @Override
    protected Object getCorrectNonNullValue(Object value) {
        return value;
    }

}
