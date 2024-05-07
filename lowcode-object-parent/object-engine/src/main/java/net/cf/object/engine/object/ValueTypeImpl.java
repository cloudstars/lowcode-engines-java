package net.cf.object.engine.object;

/**
 * 值类型
 *
 * @author clouds
 */
public class ValueTypeImpl implements ValueType {

    /**
     * 是否是一个数组
     */
    private boolean isArray;

    /**
     * 数据类型（如果是数组的话，那是每一项的数据类型）
     */
    private DataType dataType;

    public ValueTypeImpl() {
    }

    public ValueTypeImpl(DataType dataType) {
        this(dataType, false);
    }

    public ValueTypeImpl(DataType dataType, boolean isArray) {
        this.isArray = isArray;
        this.dataType = dataType;
    }

    @Override
    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    @Override
    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
}
