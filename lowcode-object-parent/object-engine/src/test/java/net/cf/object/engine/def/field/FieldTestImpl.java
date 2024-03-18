package net.cf.object.engine.def.field;

import net.cf.object.engine.def.ObjectTestImpl;
import net.cf.object.engine.object.DataType;
import net.cf.object.engine.object.XField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldTestImpl implements XField {

    private final ObjectTestImpl owner;

    private final FieldDef fieldDef;

    /**
     * 模型字段属性列表
     */
    private final List<PropertyTestImpl> properties = new ArrayList<>();

    /**
     * 模型字段属性映射表，方便通过属性名称查找
     */
    private final Map<String, PropertyTestImpl> propertyMap = new HashMap<>();

    public FieldTestImpl(ObjectTestImpl owner, FieldDef fieldDef) {
        this.owner = owner;
        this.fieldDef = fieldDef;
        List<PropertyDef> propertyDefs = fieldDef.getProperties();
        if (propertyDefs != null) {
            for (PropertyDef propertyDef : propertyDefs) {
                PropertyTestImpl fieldProperty = new PropertyTestImpl(this, propertyDef);
                this.properties.add(fieldProperty);
                this.propertyMap.put(fieldProperty.getName(), fieldProperty);
            }
        }
    }

    @Override
    public ObjectTestImpl getOwner() {
        return owner;
    }

    public FieldDef getFieldDef() {
        return fieldDef;
    }

    @Override
    public String getName() {
        return fieldDef.getName();
    }

    @Override
    public String getColumnName() {
        return fieldDef.getColumnName();
    }

    @Override
    public boolean isAutoGen() {
        return fieldDef.isAutoGen();
    }

    @Override
    public boolean isArray() {
        return fieldDef.isArray();
    }

    @Override
    public DataType getDataType() {
        return fieldDef.getDataType();
    }

    @Override
    public Object getDefaultValue() {
        return this.fieldDef.getDefaultValue();
    }

    @Override
    public List<PropertyTestImpl> getProperties() {
        return this.properties;
    }

    @Override
    public PropertyTestImpl getProperty(String propertyCode) {
        return this.propertyMap.get(propertyCode);
    }
}
