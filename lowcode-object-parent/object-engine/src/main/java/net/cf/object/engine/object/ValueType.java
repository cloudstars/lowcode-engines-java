package net.cf.object.engine.object;

/**
 * 值类型接口
 *
 * @author clouds
 */
public interface ValueType {

    /**
     * 判断值是不是一个数组值（即含有多个值）
     * @return
     */
    default boolean isArray() {
        return false;
    }


    /**
     * 获取值的数据类型
     *
     * @return
     */
    default DataType getDataType() {
        return DataType.OBJECT;
    }

}
