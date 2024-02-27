package net.cf.object.engine.def.field;

import net.cf.object.engine.def.ObjectTestImpl;
import net.cf.object.engine.object.XObjectRefField;

/**
 * 模型引用字段类型测试实现类
 *
 * @author clouds
 */
public class ObjectRefFieldTestImpl extends FieldTestImpl implements XObjectRefField {

    /**
     * 模型引用的类型
     */
    private ObjectRefType objectRefType;

    /**
     * 引用的模型
     */
    private ObjectTestImpl refObject;

    /**
     * 是否允许引用多个相关表记录，当模型引用类型是相关表是有效
     */
    private boolean isMultiLookupRef;

    public ObjectRefFieldTestImpl(FieldDef fieldDef, ObjectRefType objectRefType, ObjectTestImpl refObject) {
        super(refObject, fieldDef);
        this.objectRefType = objectRefType;
        this.refObject = refObject;
    }

    @Override
    public ObjectTestImpl getRefObject() {
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
