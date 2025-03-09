package io.github.cloudstars.lowcode.commons.lang.value;

import io.github.cloudstars.lowcode.commons.lang.DataType;

/**
 * 数据格式
 *
 * @author clouds
 */
public interface ValueType {

    /**
     * 获取数据类型
     *
     * @return
     */
    DataType getDataType();

    /**
     * 是否数组
     *
     * @return
     */
    boolean isArray();

    /**
     * 校验数据是否合法
     *
     * @param value
     * @throws InvalidDataFormatException
     */
    void validate(Object value) throws InvalidDataFormatException;

    /**
     * 获取正确的数据
     *
     * @param value
     * @return
     */
    Object getCorrectValue(Object value);

}
