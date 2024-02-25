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
    public List<FieldTypeAttributeDescriptor> getAttributeDescriptors() {
        FieldTypeAttributeDescriptor refTypeAttr = new FieldTypeAttributeDescriptor();
        refTypeAttr.setName("模型引用类型");
        refTypeAttr.setCode("refType");
        DescriptorOption option1 = new DescriptorOption("引用主表", "MASTER");
        DescriptorOption option2 = new DescriptorOption("引用子表", "DETAIL");
        DescriptorOption option3 = new DescriptorOption("引用相关表", "LOOKUP");
        refTypeAttr.setOptions(Arrays.asList(option1, option2, option3));

        FieldTypeAttributeDescriptor isMultipleLookupRefAttr = new FieldTypeAttributeDescriptor();
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
