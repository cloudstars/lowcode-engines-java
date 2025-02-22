package net.cf.object.engine.def.field;

import net.cf.object.engine.object.DataType;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.util.ObjectConstants;

/**
 * 字段的子属性
 *
 * @author clouds
 */
public class TestPropertyImpl implements XProperty {

    /**
     * 子属性归属的字段
     */
    private final TestFieldImpl owner;

    private final PropertyDef propertyDef;

    public TestPropertyImpl(TestFieldImpl field, PropertyDef propertyDef) {
        this.owner = field;
        this.propertyDef = propertyDef;
    }

    @Override
    public TestFieldImpl getOwner() {
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
        // 如果属性上配置了列名，则直接返回
        if (columnName == null) {
            String fieldColumnName = this.owner.getColumnName();
            if (fieldColumnName == null) {
                fieldColumnName = this.owner.getName();
            }
            columnName = fieldColumnName + ObjectConstants.FIELD_SEPARATOR + this.propertyDef.getName();
        }

        return columnName;
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
