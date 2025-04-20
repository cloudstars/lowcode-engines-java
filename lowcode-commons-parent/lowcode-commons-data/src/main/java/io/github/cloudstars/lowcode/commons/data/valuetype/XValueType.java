package io.github.cloudstars.lowcode.commons.data.valuetype;


/**
 * 数据格式
 *
 * @author clouds
 */
public interface XValueType<V> {

    /**
     * 获取数据格式对应的数据类型
     *
     * @return
     */
    DataTypeEnum getDataType();

    /**
     * 解析默认值
     *
     * @return
     */
    default V parseDefaultValue() {return null;}

    /**
     * 解析一个非空的数据为正确的类型（比如将long转为Date）
     *
     * @param rawValue 原始的数值
     * @return 预期类型的数值对象
     */
    V parseValue(Object rawValue) throws InvalidDataException;

    /**
     * 校验一个数据
     *
     * @param value 符合预期的数值
     * @throws InvalidDataException
     */
    void validate(V value) throws InvalidDataException;

}
