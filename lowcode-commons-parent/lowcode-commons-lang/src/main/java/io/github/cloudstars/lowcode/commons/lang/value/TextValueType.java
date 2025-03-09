package io.github.cloudstars.lowcode.commons.lang.value;

import io.github.cloudstars.lowcode.commons.lang.DataType;

/**
 * 简单数据格式（不含属性信息）
 *
 * @author clouds
 */
public class TextValueType extends AbstractValueType implements ValueType {

    public TextValueType(boolean isArray) {
        super(isArray);
    }

    @Override
    public DataType getDataType() {
        return DataType.TEXT;
    }

    @Override
    protected void validateNonNullValue(Object value) throws InvalidDataFormatException {
    }

    @Override
    protected Object getCorrectNonNullValue(Object value) {
        if (!(value instanceof String)) {
            return value.toString();
        }

        return value;
    }

}
