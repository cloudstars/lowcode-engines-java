package net.cf.object.engine.def.field;

import net.cf.object.engine.object.DataType;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.util.ObjectConstants;

/**
 * 字段的子属性
 *
 * @author clouds
 */
public class PropertyTestImpl implements XProperty {

    /**
     * 子属性归属的字段
     */
    private final FieldTestImpl owner;

    private final PropertyDef propertyDef;

    /*private String name;

    private String code;

    private String columnName;

    private DataType dataType;

    private Integer dataLength;

    private Integer dataPrecision;*/

    public PropertyTestImpl(FieldTestImpl field, PropertyDef propertyDef) {
        this.owner = field;
        this.propertyDef = propertyDef;
    }

    @Override
    public FieldTestImpl getOwner() {
        return this.owner;
    }

    @Override
    public String getName() {
        return this.propertyDef.getName();
    }

    @Override
    public DataType getDataType() {
        return this.propertyDef.getDataType();
    }

    @Override
    public String getColumnName() {
        String columnName = this.propertyDef.getColumnName();
        if (columnName == null) {
            columnName = this.propertyDef.getName();
        }

        return this.owner.getColumnName() + ObjectConstants.FIELD_SEPARATOR + columnName;
    }

    @Override
    public Integer getDataLength() {
        return this.propertyDef.getDataLength();
    }

    @Override
    public Integer getDataPrecision() {
        return this.propertyDef.getDataPrecision();
    }

}
