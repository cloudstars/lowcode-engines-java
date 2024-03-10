package net.cf.object.engine.def.field;

import net.cf.object.engine.object.DataType;

/**
 * 模型字段属性定义
 *
 * @author clouds
 */
public class FieldPropertyDef {

    /**
     * 名称
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 是否集合
     */
    private boolean isCollection;

    /**
     * 数据类型
     */
    private DataType dataType;

    /**
     * 数据长度
     */
    private Integer dataLength;

    /**
     * 数据精度
     */
    private Integer dataPrecision;

    /**
     * 默认值
     */
    private Object defaultValue;

    /**
     * 日期格式
     */
    private String dateFormat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public Integer getDataPrecision() {
        return dataPrecision;
    }

    public void setDataPrecision(Integer dataPrecision) {
        this.dataPrecision = dataPrecision;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
