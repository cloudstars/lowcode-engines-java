package net.cf.object.engine.object;

/**
 * 模型的字段
 *
 * @author clouds
 */
public interface XField {

    /**
     * 获取字段所属的模型
     *
     * @return
     */
    XObject getOwner();

    /**
     * 获取字段的名称
     *
     * @return
     */
    String getName();

    /**
     * 获取字段的编号
     *
     * @return
     */
    String getCode();

    /**
     * 获取字段的列名
     *
     * @return
     */
    String getColumnName();

    /**
     * 是否主键字段
     *
     * @return
     */
    /*default boolean isPrimary() {
        return false;
    }*/

    /**
     * 是否自动生成的字段
     *
     * @return
     */
    default boolean isAutoGen() {
        return false;
    }

    /**
     * 是否是集合
     *
     * @return
     */
    default boolean isCollection() {
        return false;
    }

    /**
     * 获取数据类型
     *
     * @return
     */
    default DataType getDataType() {
        return DataType.STRING;
    }

    /**
     * 获取数据的长度（当字段类型是文本或数字时有效）
     *
     * @return
     */
    default Integer getDataLength() {
        return null;
    }

    /**
     * 获取数据的精度（当字段类型是数字时有效）
     *
     * @return
     */
    default Integer getDataPrecision() {
        return null;
    }

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

    /**
     * 获取日期的格式类型（当字段类型是日期时有效）
     *
     * @return
     */
    default DateFormatType getDateFormat() {
        return null;
    }

    /**
     * 获取默认值
     *
     * @return
     */
    default Object getDefaultValue() {
        return null;
    }

    /**
     * 获取字段的数据子属性列表
     *
     * @return
     */
    /*default <T extends XFieldProperty> List<T> getProperties() {
        return Collections.emptyList();
    }*/

    /**
     * 获取属性的名称获取子属性
     *
     * @param propertyName
     * @return
     * @param <T>
     */
    default <T extends XFieldProperty> T getProperty(String propertyName) {
        return null;
    }

    /**
     * 获取字段的个性化属性值
     *
     * @return
     */
    /*default Map<String, Object> getAttributeValues() {
        return Collections.emptyMap();
    }*/

}
