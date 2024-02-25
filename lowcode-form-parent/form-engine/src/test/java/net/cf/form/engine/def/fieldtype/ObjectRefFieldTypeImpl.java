package net.cf.form.engine.def.fieldtype;

import net.cf.form.engine.object.DataType;
import net.cf.form.engine.object.XField;
import net.cf.form.engine.object.XObjectRefField;

import java.util.Arrays;
import java.util.List;

/**
 * 模型引用类字段类型
 *
 * @author clouds
 */
public class ObjectRefFieldTypeImpl extends AbstractFieldTypeImpl {

    @Override
    public String getName() {
        return "模型引用";
    }

    @Override
    public String getCode() {
        return "ObjectRef";
    }

    @Override
    public List<AttributeDescriptor> getAttributeDescriptors() {
        AttributeDescriptor refTypeAttr = new AttributeDescriptor();
        refTypeAttr.setName("模型引用类型");
        refTypeAttr.setCode("refType");
        AttributeDescriptor.Option option1 = new AttributeDescriptor.Option("引用主表", "MASTER");
        AttributeDescriptor.Option option2 = new AttributeDescriptor.Option("引用子表", "DETAIL");
        AttributeDescriptor.Option option3 = new AttributeDescriptor.Option("引用相关表", "LOOKUP");
        refTypeAttr.setOptions(Arrays.asList(option1, option2, option3));

        AttributeDescriptor isMultipleLookupRefAttr = new AttributeDescriptor();
        isMultipleLookupRefAttr.setName("是否一对多引用");
        refTypeAttr.setCode("isMultiRef");
        refTypeAttr.setDataType(DataType.BOOLEAN);
        return Arrays.asList(refTypeAttr, isMultipleLookupRefAttr);
    }

    @Override
    public <T extends XField> boolean isCollection(T field) {
        XObjectRefField refField = (XObjectRefField) field;
        return refField.isMultiRef();
    }

    @Override
    public <T extends XField> DataType getDataType(T field) {
        assert (field instanceof XObjectRefField);
        XObjectRefField refField = (XObjectRefField) field;
        // 关联表字段的数据类型等于被关联的模型的主键字段的数据类型
        return refField.getRefObject().getPrimaryField().getDataType();
    }
}
