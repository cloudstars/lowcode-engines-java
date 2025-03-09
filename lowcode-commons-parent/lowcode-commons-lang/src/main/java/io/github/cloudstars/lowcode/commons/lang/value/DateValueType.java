package io.github.cloudstars.lowcode.commons.lang.value;

import io.github.cloudstars.lowcode.commons.lang.DataType;

import java.util.Date;

/**
 * 日期数据格式
 *
 * @author clouds
 */
public class DateValueType extends AbstractValueType implements ValueType {

    public DateValueType(boolean isArray) {
        super(isArray);
    }

    @Override
    public DataType getDataType() {
        return DataType.DATE;
    }

    @Override
    protected void validateNonNullValue(Object value) throws InvalidDataFormatException {
    }

    @Override
    protected Object getCorrectNonNullValue(Object value) {
        if (value instanceof Long) {
            return new Date(Long.valueOf((Long) value));
        }

        return value;
    }
}
