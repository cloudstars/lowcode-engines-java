package net.cf.form.engine.object;

/**
 * 字段的数据子属性（当字段需要拆子属性存储时需要定义）
 *
 * @author clouds
 */
public interface XFieldProperty {

    /**
     * 获取子属性归属的字段
     *
     * @return
     * @param <T>
     */
    <T extends XField> T getOwner();

    /**
     * 获取子属性的名称
     *
     * @return
     */
    String getName();

    /**
     * 获取字段属性的编号
     *
     * @return
     */
    String getCode();

    /**
     * 获取字段属性的列名称
     *
     * @return
     */
    String getColumnName();

    /**
     * 获取字段属性的数据类型
     *
     * @return
     */
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

}
