package net.cf.object.engine.def.fieldtype;

import net.cf.object.engine.def.field.DefaultValueConfig;
import net.cf.object.engine.def.field.DefaultValueType;
import net.cf.object.engine.def.field.FieldTestImpl;
import net.cf.object.engine.object.XFieldType;

import java.util.Collections;
import java.util.List;

/**
 * 抽象的字段类型实现类
 *
 * @author clouds
 */
public abstract class AbstractFieldTypeImpl implements XFieldType<FieldTestImpl> {

    @Override
    public Object getDefaultValue(FieldTestImpl field) {
        DefaultValueConfig defaultValueConfig = field.getFieldDef().getDefaultValueConfig();
        if (defaultValueConfig != null) {
            if (defaultValueConfig.getType() == DefaultValueType.LITERAL) {
                return defaultValueConfig.getValue();
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
