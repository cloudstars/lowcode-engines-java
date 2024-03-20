package net.cf.object.engine.def.field;

import net.cf.object.engine.def.TestObjectImpl;
import net.cf.object.engine.object.XObjectRefField;

/**
 * 模型引用字段类型测试实现类
 *
 * @author clouds
 */
public class ObjectRefTestFieldImpl extends TestFieldImpl implements XObjectRefField {

    /**
     * 模型引用的类型
     */
    private ObjectRefType objectRefType;

    /**
     * 引用的模型
     */
    private TestObjectImpl refObject;

    /**
     * 是否允许引用多个相关表记录，当模型引用类型是相关表是有效
     */
    private boolean isMultiLookupRef;

    public ObjectRefTestFieldImpl(FieldDef fieldDef, ObjectRefType objectRefType, TestObjectImpl refObject) {
        super(refObject, fieldDef);
        this.objectRefType = objectRefType;
        this.refObject = refObject;
    }

    @Override
    public TestObjectImpl getRefObject() {
        return this.refObject;
    }

    @Override
    public boolean isMultiRef() {
        if (this.objectRefType == ObjectRefType.REF_MASTER) {
            return false;
        } else if (this.objectRefType == ObjectRefType.REF_DETAIL) {
            return true;
        }

        return this.isMultiLookupRef;
    }

    public boolean isMultiLookupRef() {
        return isMultiLookupRef;
    }

    public void setMultiLookupRef(boolean multiLookupRef) {
        isMultiLookupRef = multiLookupRef;
    }
}
