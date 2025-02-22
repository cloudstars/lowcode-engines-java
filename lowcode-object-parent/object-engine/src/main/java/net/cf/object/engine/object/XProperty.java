package net.cf.object.engine.object;

/**
 * 字段的数据子属性（当字段需要拆子属性存储时需要定义）
 *
 * @author clouds
 */
public interface XProperty extends ValueType {

    /**
     * 获取子属性归属的字段
     *
     * @return
     */
    XField getOwner();

    /**
     * 获取字段属性名称
     *
     * @return
     */
    String getName();

    /**
     * 获取字段属性的列名称
     *
     * @return
     */
    String getColumnName();

    /**
     * 是否是数组
     *
     * @return
     */
    @Override
    default boolean isArray() {
        return false;
    }

    /**
     * 获取字段属性的数据类型
     *
     * @return
     */
    @Override
    DataType getDataType();

    /**
     * 获取字段属性的最大长度
     *
     * @return
     */
    Integer getDataLength();

    /**
     * 获取字段属性的精度
     *
     * @return
     */
    Integer getDataPrecision();

    /**
     * 获取最小值（当字段类型是数字时有效）
     *
     * @return
     */
    default Number getMinValue() {
        return null;
    }

    /**
     * 获取最大值（当字段类型是数字时有效）
     *
     * @return
     */
    default Number getMaxValue() {
        return null;
    }

}
