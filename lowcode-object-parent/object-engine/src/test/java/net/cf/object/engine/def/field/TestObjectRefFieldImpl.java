package net.cf.object.engine.def.field;

import net.cf.object.engine.def.TestObjectImpl;
import net.cf.object.engine.object.ObjectRefType;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.FastOqlException;

/**
 * 模型引用字段类型测试实现类
 *
 * @author clouds
 */
public class TestObjectRefFieldImpl extends TestFieldImpl implements XObjectRefField {

    /**
     * 引用的模武的类型
     */
    private final ObjectRefType refType;

    /**
     * 是否允许引用多个相关表记录，当模型引用类型是相关表是有效
     */
    private boolean isMultiRef;

    public TestObjectRefFieldImpl(TestObjectImpl owner, FieldDef fieldDef) {
        super(owner, fieldDef);
        this.refType = fieldDef.getRefType();
        if (this.refType == ObjectRefType.MASTER) {
            this.isMultiRef = false;
        } else if (this.refType == ObjectRefType.DETAIL) {
            this.isMultiRef = true;
        }
    }

    @Override
    public String getRefObjectName() {
        return this.fieldDef.getRefObjectName();
    }

    @Override
    public boolean isMultiRef() {
        return this.isMultiRef;
    }

    /**
     * 设置是否一对多引用关系
     *
     * @param multiRef
     */
    public void setMultiRef(boolean multiRef) {
        if (this.refType == ObjectRefType.MASTER || this.refType == ObjectRefType.DETAIL) {
            throw new FastOqlException("主子表关系不允许设置是否一对多引用，默认为一对多关系");
        }

        isMultiRef = multiRef;
    }

    @Override
    public net.cf.object.engine.object.ObjectRefType getRefType() {
        return this.refType;
    }
}
