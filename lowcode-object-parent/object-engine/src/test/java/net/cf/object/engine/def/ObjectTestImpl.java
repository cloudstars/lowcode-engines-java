package net.cf.object.engine.def;

import net.cf.object.engine.def.field.FieldDef;
import net.cf.object.engine.def.field.FieldTestImpl;
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
public class ObjectTestImpl implements XObject<FieldTestImpl> {

    /**
     * 模型定义
     */
    private final ObjectDef objectDef;

    /**
     * 主键字段
     */
    private FieldTestImpl primaryField = null;

    /**
     * 模型字段列表
     */
    private final List<FieldTestImpl> fields = new ArrayList<>();

    /**
     * 模型字段映射表，方便通过字段名称查找
     */
    private final Map<String, FieldTestImpl> fieldMap = new HashMap<>();

    public ObjectTestImpl(ObjectDef objectDef) {
        this.objectDef = objectDef;
        List<FieldDef> fieldDefs = objectDef.getFields();
        for (FieldDef fieldDef : fieldDefs) {
            FieldTestImpl field = new FieldTestImpl(this, fieldDef);
            this.fields.add(field);
            this.fieldMap.put(field.getName(), field);

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
    public List<FieldTestImpl> getFields() {
        return this.fields;
    }

    @Override
    public FieldTestImpl getField(String fieldCode) {
        return this.fieldMap.get(fieldCode);
    }

    @Override
    public FieldTestImpl getPrimaryField() {
        return this.primaryField;
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
