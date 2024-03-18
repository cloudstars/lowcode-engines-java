package net.cf.object.engine.fieldtype;

import net.cf.object.engine.object.DataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 模型引用类字段类型
 *
 * @author clouds
 */
public class ObjectRefFieldTypeImpl implements XFieldType {

    @Override
    public String getDesc() {
        return "模型引用";
    }

    @Override
    public String getName() {
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
            AttributeDescriptor refObjectDescr = new AttributeDescriptor();
            refObjectDescr.setName("引用的模型");
            refObjectDescr.setCode("refObjectKey");
            refObjectDescr.setDataType(DataType.NUMBER);
            descriptors.add(refObjectDescr);
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
                redundantFieldsDescr.setArray(true);
                redundantFieldsDescr.setDataType(DataType.STRING);
                descriptors.add(redundantFieldsDescr);
            }
        }

        return descriptors;
    }

    @Override
    public boolean isArray(FieldSchema fieldSchema) {
        //XObjectRefField refField = (XObjectRefField) field;
        //return refField.isMultiRef();
        // 判断是否一对多引用
        return false;
    }

    @Override
    public DataType getDataType(FieldSchema fieldSchema) {
        //XObjectRefField refField = (XObjectRefField) field;
        // 关联表字段的数据类型等于被关联的模型的主键字段的数据类型
        //return refField.getRefObject().getPrimaryField().getDataType();
        // TODO 非冗余字段时，根据引用表的模型的主键字段的数据类型来生成字段的数据类型，否则就是Object
        return DataType.STRING;
    }

    @Override
    public List<FieldProperty> getProperties(FieldSchema fieldSchema) {
        /* TODO 这段代码有编译问题待改
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
        }*/

        return Collections.emptyList();
    }
}
