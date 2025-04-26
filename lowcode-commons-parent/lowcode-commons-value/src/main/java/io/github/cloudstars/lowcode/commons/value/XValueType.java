package io.github.cloudstars.lowcode.commons.value;


import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * 数据格式
 *
 * @author clouds
 * @param <T> 数据格式的值类型
 */
public interface XValueType<T> {

    /**
     * 获数数据格式的配置
     *
     * @return
     */
    XValueTypeConfig getValueTypeConfig();

    /**
     * 解析默认值
     *
     * @return 符合格式的Value值
     */
    T parseDefaultValue(Object defaultValueConfig) throws InvalidDataException;

    /**
     * 解析一个非空的数据为正确的类型（比如将long转为Date）
     *
     * @param rawValue 原始的数值
     * @return 预期类型的数值对象
     */
    T parseValue(Object rawValue) throws InvalidDataException;

    /**
     * 校验一个数据
     *
     * @param value 符合预期的数值
     * @throws InvalidDataException 非常数据异常
     */
    void validate(T value) throws InvalidDataException;

}
