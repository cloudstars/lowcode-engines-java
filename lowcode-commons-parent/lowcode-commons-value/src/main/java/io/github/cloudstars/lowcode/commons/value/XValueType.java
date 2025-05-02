package io.github.cloudstars.lowcode.commons.value;


/**
 * 数据格式
 *
 * @author clouds
 * @param <C> 配置类
 * @param <V> 数据格式的值类型
 */
public interface XValueType<C, V> {

    /**
     * 获数数据格式的配置
     *
     * @return
     */
    C getValueTypeConfig();

    /**
     * 解析解析默认值
     *
     * @return 符合数据格式的默认值
     */
    V parseDefaultValue() throws InvalidDataException;

    /**
     * 根据输入的数据和默认值合并产生一个新的值
     *
     * @param rawValue
     * @return
     */
    default V mergeDefaultValue(Object rawValue) {
        if (rawValue != null) {
            return this.parseValue(rawValue);
        }

        return this.parseDefaultValue();
    }

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
