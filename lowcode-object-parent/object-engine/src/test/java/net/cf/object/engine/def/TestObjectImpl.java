package net.cf.object.engine.def;

import net.cf.object.engine.def.field.FieldDef;
import net.cf.object.engine.def.field.TestFieldImpl;
import net.cf.object.engine.def.field.TestObjectRefFieldImpl;
import net.cf.object.engine.fieldtype.FieldTypeConstants;
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
     * 字段列表
     */
    private final List<TestFieldImpl> fields = new ArrayList<>();

    /**
     * 字段映射表，方便通过字段名称查找
     */
    private final Map<String, TestFieldImpl> fieldMap = new HashMap<>();


    /**
     * 关联其它模型的字段映射表，方便通过字段名称查找
     */
    private final Map<String, TestObjectRefFieldImpl> refObjectFieldMap = new HashMap<>();

    public TestObjectImpl(ObjectDef objectDef) {
        this.objectDef = objectDef;
        List<FieldDef> fieldDefs = objectDef.getFields();
        for (FieldDef fieldDef : fieldDefs) {
            String fieldName = fieldDef.getName();
            TestFieldImpl field;
            if (FieldTypeConstants.OBJECT_REF.equals(fieldDef.getType())) {
                String refObjectName = fieldDef.getRefObjectName();
                field = new TestObjectRefFieldImpl(this, fieldDef);
                this.refObjectFieldMap.put(refObjectName, (TestObjectRefFieldImpl) field);
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
    public String getMasterName() {
        return objectDef.getMasterName();
    }

    @Override
    public List<TestFieldImpl> getFields() {
        return this.fields;
    }

    @Override
    public TestFieldImpl getField(String fieldCode) {
        return this.fieldMap.get(fieldCode);
    }

    @Override
    public TestFieldImpl getPrimaryField() {
        return this.primaryField;
    }

    @Override
    public TestObjectRefFieldImpl getObjectRefField(String refObjectName) {
        return this.refObjectFieldMap.get(refObjectName);
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
