package net.cf.form.engine.repository.data;

/**
 * 数据类型
 *
 * @author clouds
 */
@Deprecated
public class DataType {

    private boolean multiple;

    private net.cf.form.engine.repository.data.value.DataType valueType;

    /**
     * 值的长度，一般用于文本
     */
    private int length;

    /**
     * 值的精度，一般用于BigDecimal
     */
    private int precision;

    public DataType(net.cf.form.engine.repository.data.value.DataType valueType) {
        this.valueType = valueType;
    }

    public DataType(net.cf.form.engine.repository.data.value.DataType valueType, boolean multiple) {
        this.valueType = valueType;
        this.multiple = multiple;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public net.cf.form.engine.repository.data.value.DataType getValueType() {
        return valueType;
    }

    public void setValueType(net.cf.form.engine.repository.data.value.DataType valueType) {
        this.valueType = valueType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }
}
