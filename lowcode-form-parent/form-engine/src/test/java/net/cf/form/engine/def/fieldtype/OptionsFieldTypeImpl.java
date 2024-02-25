package net.cf.form.engine.def.fieldtype;

import net.cf.form.engine.def.field.FieldPropertyTestImpl;
import net.cf.form.engine.def.field.FieldTestImpl;
import net.cf.form.engine.object.DataType;
import net.cf.form.engine.object.XField;
import net.cf.form.engine.object.XFieldProperty;

import java.util.*;

/**
 * 选项类字段类型
 *
 * @author clouds
 */
public class OptionsFieldTypeImpl extends AbstractFieldTypeImpl {

    @Override
    public String getName() {
        return "选项";
    }

    @Override
    public String getCode() {
        return "Options";
    }

    @Override
    public List<FieldTypeAttributeDescriptor> getAttributeDescriptors() {
        List<FieldTypeAttributeDescriptor> descriptors = new ArrayList<>();
        {
            FieldTypeAttributeDescriptor typeDescr = new FieldTypeAttributeDescriptor();
            typeDescr.setName("数据源类型");
            typeDescr.setCode("sourceType");
            typeDescr.setDataType(DataType.STRING);
            DescriptorOption option1 = new DescriptorOption("静态数据", "Static");
            DescriptorOption option2 = new DescriptorOption("数据字典", "Dictionary");
            typeDescr.setOptions(Arrays.asList(option1, option2));
            descriptors.add(typeDescr);
        }
        {
            // 当数据源类型为静态数据时，需要填写字典编号dictKey
            FieldTypeAttributeDescriptor optionsDescr = new FieldTypeAttributeDescriptor();
            optionsDescr.setName("选项列表");
            optionsDescr.setCode("options");
            optionsDescr.setCollection(true);
            optionsDescr.setDataType(DataType.STRING);
            descriptors.add(optionsDescr);
        }
        {
            // 当数据源类型为数据字典时，需要填写字典编号dictKey
            FieldTypeAttributeDescriptor dictKeyDescr = new FieldTypeAttributeDescriptor();
            dictKeyDescr.setName("字典编号");
            dictKeyDescr.setCode("dictKey");
            dictKeyDescr.setDataType(DataType.STRING);
            descriptors.add(dictKeyDescr);
        }
        {
            // 是否冗余属性
            FieldTypeAttributeDescriptor redundantDescr = new FieldTypeAttributeDescriptor();
            redundantDescr.setName("是否冗余标签");
            redundantDescr.setCode("redundant");
            redundantDescr.setDataType(DataType.BOOLEAN);
            descriptors.add(redundantDescr);
        }
        {
            // 是否冗余属性
            FieldTypeAttributeDescriptor redundantDescr = new FieldTypeAttributeDescriptor();
            redundantDescr.setName("是否冗余标签");
            redundantDescr.setCode("redundant");
            redundantDescr.setDataType(DataType.BOOLEAN);
            descriptors.add(redundantDescr);
        }
        {
            // 当冗余标签时，需填写标题名称（有默认值）、标题代码（有默认值）、值名称（有默认值）、值代码（有默认值）
            {
                FieldTypeAttributeDescriptor labelNameDescr = new FieldTypeAttributeDescriptor();
                labelNameDescr.setName("Label名称");
                labelNameDescr.setCode("labelName");
                labelNameDescr.setDataType(DataType.STRING);
                labelNameDescr.setDefaultValue("名称");
                descriptors.add(labelNameDescr);
            }
            {
                FieldTypeAttributeDescriptor labelValueDescr = new FieldTypeAttributeDescriptor();
                labelValueDescr.setName("Label值");
                labelValueDescr.setCode("labelValue");
                labelValueDescr.setDataType(DataType.STRING);
                labelValueDescr.setDefaultValue("label");
                descriptors.add(labelValueDescr);
            }
            {
                FieldTypeAttributeDescriptor valueNameDescr = new FieldTypeAttributeDescriptor();
                valueNameDescr.setName("值名称");
                valueNameDescr.setCode("valueName");
                valueNameDescr.setDataType(DataType.STRING);
                valueNameDescr.setDefaultValue("值");
                descriptors.add(valueNameDescr);
            }
            {
                FieldTypeAttributeDescriptor valueValueDescr = new FieldTypeAttributeDescriptor();
                valueValueDescr.setName("Label名称");
                valueValueDescr.setCode("valueValue");
                valueValueDescr.setDataType(DataType.STRING);
                valueValueDescr.setDefaultValue("value");
                descriptors.add(valueValueDescr);
            }
        }

        return descriptors;
    }

    @Override
    public <T extends XField> List<XFieldProperty> getProperties(T field) {
        FieldTestImpl fieldImpl = (FieldTestImpl) field;
        /*List<FieldAttributeTestImpl> attributes = fieldImpl.getAttributes();
        Map<String, FieldAttributeTestImpl> attributesMap = new HashMap<>();
        attributes.forEach((attribute) -> {
            attributesMap.put(attribute.getCode(), attribute);
        });*/

        // 没有冗余时直接返回空列表
        Map<String, Object> attrValueMap = field.getAttributeValues();
        Object redundant = attrValueMap.get("redundant");
        if (redundant == null || redundant == Boolean.FALSE) {
            return Collections.emptyList();
        }

        List<XFieldProperty> properties = new ArrayList<>();
        {
            FieldPropertyTestImpl labelProperty = new FieldPropertyTestImpl(fieldImpl);
            /* 以下几块代码暂时注释的原因是默认值是在构建FieldAttribute的时候生成，不是在消费的时候使用，这一策略稳定后可删除注释 */
            /*FieldAttributeTestImpl labelNameAttr = attrValueMap.get("labelName");
            Object labelName = labelNameAttr.getValue();
            if (labelName == null) {
                labelName = labelNameAttr.getAttributeDescriptor().getDefaultValue().toString();
            }*/
            Object labelName = attrValueMap.get("labelName");
            labelProperty.setName(labelName.toString());
            /*FieldAttributeTestImpl labelValueAttr = attributesMap.get("labelValue");
            Object labelValue = labelValueAttr.getValue();
            if (labelValue == null) {
                labelValue = labelValueAttr.getAttributeDescriptor().getDefaultValue();
            }*/
            Object labelValue = attrValueMap.get("labelValue");
            labelProperty.setCode(labelValue.toString());
            labelProperty.setDataType(DataType.STRING);
            properties.add(labelProperty);
        }
        {
            FieldPropertyTestImpl valueProperty = new FieldPropertyTestImpl(fieldImpl);
            /*FieldAttributeTestImpl valueNameAttr = attributesMap.get("valueName");
            Object valueName = valueNameAttr.getValue();
            if (valueName == null) {
                valueName = valueNameAttr.getAttributeDescriptor().getDefaultValue();
            }*/
            Object valueName = attrValueMap.get("valueName");
            valueProperty.setName(valueName.toString());
            /*FieldAttributeTestImpl valueValueAttr = attributesMap.get("valueValue");
            Object valueValue = valueValueAttr.getValue();
            if (valueValue == null) {
                valueValue = valueValueAttr.getAttributeDescriptor().getDefaultValue();
            }*/
            Object valueValue = attrValueMap.get("valueValue");
            valueProperty.setCode(valueValue.toString());
            // 值的类型等于字段属性中设置的类型
            valueProperty.setDataType(fieldImpl.getDataType());
            properties.add(valueProperty);
        }

        return properties;
    }

}

