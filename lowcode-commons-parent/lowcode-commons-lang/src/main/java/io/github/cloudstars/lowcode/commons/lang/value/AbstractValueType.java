package io.github.cloudstars.lowcode.commons.lang.value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 抽象数据类型
 *
 * @author clouds
 */
public abstract class AbstractValueType implements ValueType {

    /**
     * 是否数组
     */
    protected boolean isArray;

    public AbstractValueType() {
    }

    public AbstractValueType(boolean isArray) {
        this.isArray = isArray;
    }

    @Override
    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean isArray) {
        this.isArray = isArray;
    }

    @Override
    public void validate(Object value) throws InvalidDataFormatException {
        if (value == null) {
            return;
        }

        if (!this.isArray) {
            this.validateNonNullValue(value);
        } else {
            if (value instanceof List) {
                for (Object listItem : (List) value) {
                    if (listItem != null) {
                        this.validateNonNullValue(listItem);
                    }
                }
            }
        }
    }

    /**
     * 校验一个非null的值
     *
     * @param value
     * @throws InvalidDataFormatException
     */
    protected abstract void validateNonNullValue(Object value) throws InvalidDataFormatException;

    @Override
    public Object getCorrectValue(Object value) {
        if (value == null) {
            return null;
        }

        if (!this.isArray) {
            return this.getCorrectNonNullValue(value);
        }

        if (value instanceof List) {
            List newValue = new ArrayList();
            for (Object listItem : (List) value) {
                newValue.add(this.getCorrectNonNullValue(listItem));
            }
        }

        return Arrays.asList(this.getCorrectNonNullValue(value));
    }

    /**
     * 获取正确的非Null的数据
     *
     * @param value
     * @return
     */
    protected abstract Object getCorrectNonNullValue(Object value);

}
