package net.cf.form.engine.def.fieldtype;

import net.cf.form.engine.object.XField;

import java.util.UUID;

/**
 * 流水号字段类型
 *
 * @author clouds
 */
public class SequenceFieldTypeImpl extends AbstractFieldTypeImpl {

    @Override
    public String getName() {
        return "序号";
    }

    @Override
    public String getCode() {
        return "Sequence";
    }

    @Override
    public Object getDefaultValue(XField field) {
        // FieldDef fieldDef = (FieldDef) field;
        // 这里模拟返回一个流水号
        return UUID.randomUUID().toString();
    }
}
