package net.cf.form.engine.def.fieldtype;

import net.cf.form.engine.object.DataType;
import net.cf.form.engine.object.XField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 代表一类可以选择（单多选）的字段类型的抽象
 *
 * @author clouds
 */
public abstract class AbstractSelectableFieldTypeImpl extends AbstractFieldTypeImpl {

    @Override
    public <T extends XField> boolean isCollection(T field) {
        Map<String, Object> attrValueMap = field.getAttrValues();
        Object multiple = attrValueMap.get("multiple");
        if (multiple == null || multiple == Boolean.FALSE) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<AttributeDescriptor> getAttributeDescriptors() {
        List<AttributeDescriptor> descriptors = new ArrayList<>();
        {
            AttributeDescriptor multipleDescr = new AttributeDescriptor();
            multipleDescr.setName("是否多选");
            multipleDescr.setCode("multiple");
            multipleDescr.setDataType(DataType.BOOLEAN);
            descriptors.add(multipleDescr);
        }
        return descriptors;
    }
}
