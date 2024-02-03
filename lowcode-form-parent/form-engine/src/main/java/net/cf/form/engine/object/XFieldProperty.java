package net.cf.form.engine.object;

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
    private DataTypeEnums dataType;

    /**
     * 字段属性的数据长度
     */
    private int dataLength;

    /**
     * 字段属性的数据精度
     */
    private short dataPrecision;

    /**
     * 字段属性的默认值
     */
    private Object defaultValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataTypeEnums getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeEnums dataType) {
        this.dataType = dataType;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public short getDataPrecision() {
        return dataPrecision;
    }

    public void setDataPrecision(short dataPrecision) {
        this.dataPrecision = dataPrecision;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
}
