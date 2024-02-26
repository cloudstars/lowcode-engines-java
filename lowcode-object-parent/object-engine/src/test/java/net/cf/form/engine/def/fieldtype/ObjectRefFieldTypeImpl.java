package net.cf.form.engine.def.fieldtype;

import net.cf.form.engine.def.ObjectTestImpl;
import net.cf.form.engine.def.field.FieldPropertyTestImpl;
import net.cf.form.engine.def.field.FieldTestImpl;
import net.cf.form.engine.def.field.ObjectRefFieldTestImpl;
import net.cf.form.engine.object.DataType;
import net.cf.form.engine.object.XFieldProperty;
import net.cf.form.engine.object.XObjectRefField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        List<AttributeDescriptor> descriptors = new ArrayList<>();

        {
            AttributeDescriptor refTypeDescr = new AttributeDescriptor();
            refTypeDescr.setName("模型引用类型");
            refTypeDescr.setCode("refType");
            AttributeDescriptor.Option option1 = new AttributeDescriptor.Option("引用主表", "MASTER");
            AttributeDescriptor.Option option2 = new AttributeDescriptor.Option("引用子表", "DETAIL");
            AttributeDescriptor.Option option3 = new AttributeDescriptor.Option("引用相关表", "LOOKUP");
            refTypeDescr.setOptions(Arrays.asList(option1, option2, option3));
            descriptors.add(refTypeDescr);
        }

        {
            AttributeDescriptor isMultiLookupRefDescr = new AttributeDescriptor();
            isMultiLookupRefDescr.setName("是否一对多引用");
            isMultiLookupRefDescr.setCode("isMultiRef");
            isMultiLookupRefDescr.setDataType(DataType.BOOLEAN);
            descriptors.add(isMultiLookupRefDescr);
        }

        {
            // 当引用类型是相关表时，可以配置是否冗余，可以选择冗余哪些字段（仅可冗余数据类型基础类型的字段，最多冗余3个）
            {
                AttributeDescriptor redundantDescr = new AttributeDescriptor();
                redundantDescr.setName("是否冗余");
                redundantDescr.setCode("redundant");
                redundantDescr.setDataType(DataType.BOOLEAN);
                descriptors.add(redundantDescr);
            }
            {
                AttributeDescriptor redundantFieldsDescr = new AttributeDescriptor();
                redundantFieldsDescr.setName("冗余字段");
                redundantFieldsDescr.setCode("redundantFieldCodes");
                redundantFieldsDescr.setCollection(true);
                redundantFieldsDescr.setDataType(DataType.STRING);
                descriptors.add(redundantFieldsDescr);
            }
        }

        return descriptors;
    }

    @Override
    public boolean isCollection(FieldTestImpl field) {
        XObjectRefField refField = (XObjectRefField) field;
        return refField.isMultiRef();
    }

    @Override
    public DataType getDataType(FieldTestImpl field) {
        assert (field instanceof XObjectRefField);

        XObjectRefField refField = (XObjectRefField) field;
        // 关联表字段的数据类型等于被关联的模型的主键字段的数据类型
        return refField.getRefObject().getPrimaryField().getDataType();
    }

    @Override
    public List<XFieldProperty> getProperties(FieldTestImpl field) {
        assert (field instanceof ObjectRefFieldTestImpl);

        ObjectRefFieldTestImpl refField = (ObjectRefFieldTestImpl) field;
        ObjectTestImpl refObject = refField.getOwner();
        List<String> redundantFieldCodes = (List<String>) refField.getAttrValues().get("redundantFieldCodes");
        // 当有冗余字段时，需要返回冗余字段的子属性
        if (redundantFieldCodes != null && redundantFieldCodes.size() > 0) {
            List<XFieldProperty> properties = new ArrayList<>();
            for (String redundantFieldCode : redundantFieldCodes) {
                FieldTestImpl refFieldImpl = refField.getRefObject().getField(redundantFieldCode);
                FieldPropertyTestImpl property = new FieldPropertyTestImpl(refFieldImpl);
                property.setName(refFieldImpl.getName());
                property.setCode(refFieldImpl.getCode());
                property.setColumnName(refFieldImpl.getColumnName());
                property.setDataType(refFieldImpl.getDataType());
                properties.add(property);
            }
            return properties;
        }

        return Collections.emptyList();
    }
}
