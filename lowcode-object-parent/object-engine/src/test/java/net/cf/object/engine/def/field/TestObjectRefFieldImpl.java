package net.cf.object.engine.def.field;

import net.cf.object.engine.def.TestObjectImpl;
import net.cf.object.engine.object.ObjectRefType;
import net.cf.object.engine.object.XObjectRefField;

/**
 * 模型引用字段类型测试实现类
 *
 * @author clouds
 */
public class TestObjectRefFieldImpl extends TestFieldImpl implements XObjectRefField {

    public TestObjectRefFieldImpl(TestObjectImpl owner, FieldDef fieldDef) {
        super(owner, fieldDef);
    }

    @Override
    public String getRefObjectName() {
        return this.fieldDef.getRefObjectName();
    }

    @Override
    public boolean isMultiRef() {
        return this.fieldDef.isMultiRef();
    }

    @Override
    public ObjectRefType getRefType() {
        return this.fieldDef.getRefType();
    }
}
