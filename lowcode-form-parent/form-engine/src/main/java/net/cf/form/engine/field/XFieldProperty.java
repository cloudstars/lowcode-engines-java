package net.cf.form.engine.field;

import net.cf.form.engine.repository.data.value.DataType;

/**
 * 子段的属性
 *
 * @author clouds
 */
public class XFieldProperty {

    /**
     * 字段属性的名称
     */
    private String name;

    /**
     * 字段属性的值类型
     */
    private DataType dataType;

    /**
     * 字段属性的数据长度
     */
    int dataLength;

    /**
     * 字段属性的数据精度
     */
    short dataPrecision;

    /**
     * 字段属性的默认值
     */
    Object defaultValue;
}
