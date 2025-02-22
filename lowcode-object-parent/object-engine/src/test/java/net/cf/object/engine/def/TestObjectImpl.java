package net.cf.object.engine.def;

import net.cf.object.engine.def.field.FieldDef;
import net.cf.object.engine.def.field.TestFieldImpl;
import net.cf.object.engine.def.field.TestObjectRefFieldImpl;
import net.cf.object.engine.fieldtype.FieldTypeConstants;
import net.cf.object.engine.object.ObjectRefType;
import net.cf.object.engine.object.XObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * XObject的测试实现类
 *
 * @author clouds
 */
public class TestObjectImpl implements XObject<TestFieldImpl, TestObjectRefFieldImpl> {

    /**
     * 模型定义
     */
    private final ObjectDef objectDef;

    /**
     * 主键字段
     */
    private TestFieldImpl primaryField = null;

    /**
     * 引用主模型的字段
     */
    private TestObjectRefFieldImpl masterField = null;

    /**
     * 字段列表
     */
    private final List<TestFieldImpl> fields = new ArrayList<>();

    /**
     * 字段映射表，方便通过字段名称查找
     */
    private final Map<String, TestFieldImpl> fieldMap = new HashMap<>();

    /**
     * 引用模型的字段映射表，方便通过字段名称查找
     */
    private final Map<String, TestObjectRefFieldImpl> refObjectFieldMap = new HashMap<>();

    public TestObjectImpl(ObjectDef objectDef) {
        this.objectDef = objectDef;
        List<FieldDef> fieldDefs = objectDef.getFields();
        for (FieldDef fieldDef : fieldDefs) {
            String fieldName = fieldDef.getName();
            TestFieldImpl field;
            if (FieldTypeConstants.OBJECT_REF.equals(fieldDef.getType())) {
                TestObjectRefFieldImpl objectRefField = new TestObjectRefFieldImpl(this, fieldDef);
                this.refObjectFieldMap.put(fieldName, objectRefField);
                if (objectRefField.getRefType() == ObjectRefType.MASTER) {
                    this.masterField = objectRefField;
                }
                field = objectRefField;
            } else {
                field = new TestFieldImpl(this, fieldDef);
            }
            this.fields.add(field);
            this.fieldMap.put(fieldName, field);

            // 设置主键
            if (fieldDef.getName().equals(objectDef.getPrimaryFieldName())) {
                this.primaryField = field;
            }
        }
    }

    @Override
    public String getName() {
        return objectDef.getName();
    }

    @Override
    public List<TestFieldImpl> getFields() {
        return this.fields;
    }

    @Override
    public TestFieldImpl getField(String fieldName) {
        return this.fieldMap.get(fieldName);
    }

    @Override
    public TestFieldImpl getPrimaryField() {
        return this.primaryField;
    }

    @Override
    public TestObjectRefFieldImpl getMasterField() {
        return this.masterField;
    }

    /*@Override
    public TestObjectRefFieldImpl getObjectRefField(String refObjectName) {
        return this.refObjectFieldMap.get(refObjectName);
    }*/

    @Override
    public TestObjectRefFieldImpl getObjectRefField2(String refFieldName) {
        return this.refObjectFieldMap.get(refFieldName);
    }

    @Override
    public void validate(Map<String, Object> dataMap) {
        // 先进行数据合法性校验

        // 再进行唯一性校验
    }

    @Override
    public String getTableName() {
        return this.objectDef.getTableName();
    }
}
