package net.cf.form.engine.def.field;

import net.cf.form.engine.object.DataType;
import net.cf.form.engine.object.XFieldProperty;

/**
 * 字段的子属性
 *
 * @author clouds
 */
public class FieldPropertyTestImpl implements XFieldProperty {

    /**
     * 子属性归属的字段
     */
    private final FieldTestImpl owner;

    private String name;

    private String code;

    private String columnName;

    private DataType dataType;

    private Integer dataLength;

    private Integer dataPrecision;

    public FieldPropertyTestImpl(FieldTestImpl field) {
        this.owner = field;
    }

    @Override
    public FieldTestImpl getOwner() {
        return this.owner;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    @Override
    public String getColumnName() {
        if (this.columnName != null) {
            return this.columnName;
        }

        return this.owner.getCode() + "_" + this.code;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public Integer getDataLength() {
        return this.dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    @Override
    public Integer getDataPrecision() {
        return this.dataPrecision;
    }

    public void setDataPrecision(Integer dataPrecision) {
        this.dataPrecision = dataPrecision;
    }

}
