package net.cf.object.engine.def;

import net.cf.object.engine.def.field.FieldDef;
import net.cf.object.engine.def.field.FieldTestImpl;
import net.cf.object.engine.object.XObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * XObject的测试实现类
 *
 * @author clouds
 */
public class ObjectTestImpl implements XObject<FieldTestImpl> {

    /**
     * 模型定义
     */
    private final ObjectDefinition objectDef;

    /**
     * 模型字段
     */
    private final List<FieldTestImpl> fields = new ArrayList<>();

    public ObjectTestImpl(ObjectDefinition objectDef) {
        this.objectDef = objectDef;
        List<FieldDef> fieldDefs = objectDef.getFields();
        for (FieldDef fieldDef : fieldDefs) {
            FieldTestImpl field = new FieldTestImpl(this, fieldDef);
            fields.add(field);
        }
    }

    @Override
    public String getCode() {
        return objectDef.getName();
    }

    @Override
    public String getName() {
        return objectDef.getCode();
    }

    @Override
    public boolean isAutoPrimaryField() {
        return false;
    }

    @Override
    public List<FieldTestImpl> getFields() {
        return this.fields;
    }

    @Override
    public FieldTestImpl getField(String fieldCode) {
        return null;
    }

    @Override
    public FieldTestImpl getPrimaryField() {
        return null;
    }

    @Override
    public void validate(Map<String, Object> dataMap) {
        // 先进行数据合法性校验

        // 再进行唯一性校验
    }

    @Override
    public String getTableName() {
        return this.getCode();
    }
}
