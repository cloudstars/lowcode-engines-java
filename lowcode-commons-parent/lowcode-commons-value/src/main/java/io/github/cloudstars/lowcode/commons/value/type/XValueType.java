package io.github.cloudstars.lowcode.commons.value.type;


/**
 * 数据格式
 *
 * @author clouds
 */
public interface XValueType<V> {

    // 是否必填配置名称
    String ATTR_REQUIRED = "required";

    /**
     * 获取数据格式对应的数据类型
     *
     * @return 数据类型
     */
    DataTypeEnum getDataType();

    /**
     * 解析Value值
     *
     * @return 符合格式的Value值
     */
    default V parseDefaultValue(Object configValue) {return null;}

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
     * @throws InvalidDataException 非常数据异常
     */
    void validate(V value) throws InvalidDataException;

}
