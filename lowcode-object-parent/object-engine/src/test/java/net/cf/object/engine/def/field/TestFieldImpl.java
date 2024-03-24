package net.cf.object.engine.def.field;

import net.cf.object.engine.def.TestObjectImpl;
import net.cf.object.engine.object.DataType;
import net.cf.object.engine.object.XField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFieldImpl implements XField {

    protected final TestObjectImpl owner;

    protected final FieldDef fieldDef;

    /**
     * 模型字段属性列表
     */
    protected final List<TestPropertyImpl> properties = new ArrayList<>();

    /**
     * 模型字段属性映射表，方便通过属性名称查找
     */
    protected final Map<String, TestPropertyImpl> propertyMap = new HashMap<>();

    public TestFieldImpl(TestObjectImpl owner, FieldDef fieldDef) {
        this.owner = owner;
        this.fieldDef = fieldDef;
        List<PropertyDef> propertyDefs = fieldDef.getProperties();
        if (propertyDefs != null) {
            for (PropertyDef propertyDef : propertyDefs) {
                TestPropertyImpl property = new TestPropertyImpl(this, propertyDef);
                this.properties.add(property);
                this.propertyMap.put(property.getName(), property);
            }
        }
    }

    @Override
    public TestObjectImpl getOwner() {
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
    public List<TestPropertyImpl> getProperties() {
        return this.properties;
    }

    @Override
    public TestPropertyImpl getProperty(String propertyCode) {
        return this.propertyMap.get(propertyCode);
    }
}
