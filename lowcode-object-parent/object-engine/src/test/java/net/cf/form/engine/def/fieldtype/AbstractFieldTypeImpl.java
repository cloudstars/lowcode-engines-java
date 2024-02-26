package net.cf.form.engine.def.fieldtype;

import net.cf.form.engine.def.field.DefaultValue;
import net.cf.form.engine.def.field.DefaultValueType;
import net.cf.form.engine.def.field.FieldDef;
import net.cf.form.engine.object.XField;
import net.cf.form.engine.object.XFieldType;

import java.util.Collections;
import java.util.List;

/**
 * 抽象的字段类型实现类
 *
 * @author clouds
 */
public abstract class AbstractFieldTypeImpl implements XFieldType {

    @Override
    public <T extends XField> Object getDefaultValue(T field) {
        FieldDef fieldDef = (FieldDef) field;
        DefaultValue defaultValue = fieldDef.getDefaultValue();
        if (defaultValue != null) {
            if (defaultValue.getType() == DefaultValueType.LITERAL) {
                return defaultValue.getValue();
            }
        }

        return null;
    }


    /**
     * 获取字段类型的个性化属性描述列表
     *
     * @return
     */
    public List<AttributeDescriptor> getAttributeDescriptors() {
        return Collections.emptyList();
    }

}
