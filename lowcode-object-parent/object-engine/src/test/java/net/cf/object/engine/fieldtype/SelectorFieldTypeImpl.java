package net.cf.object.engine.fieldtype;

import net.cf.object.engine.object.DataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 选项类字段类型
 *
 * @author clouds
 */
public class SelectorFieldTypeImpl extends AbstractSelectableFieldTypeImpl {

    @Override
    public String getDesc() {
        return "选择";
    }

    @Override
    public String getName() {
        return "Options";
    }

    @Override
    public List<AttributeDescriptor> getAttributeDescriptors() {
        List<AttributeDescriptor> descriptors = super.getAttributeDescriptors();
        {
            AttributeDescriptor typeDescr = new AttributeDescriptor();
            typeDescr.setName("数据源类型");
            typeDescr.setCode("sourceType");
            typeDescr.setDataType(DataType.STRING);
            AttributeDescriptor.Option option1 = new AttributeDescriptor.Option("静态数据", "Static");
            AttributeDescriptor.Option option2 = new AttributeDescriptor.Option("数据字典", "Dictionary");
            typeDescr.setOptions(Arrays.asList(option1, option2));
            descriptors.add(typeDescr);
        }
        {
            // 当数据源类型为静态数据时，需要填写字典编号dictKey
            AttributeDescriptor optionsDescr = new AttributeDescriptor();
            optionsDescr.setName("选项列表");
            optionsDescr.setCode("options");
            optionsDescr.setArray(true);
            optionsDescr.setDataType(DataType.STRING);
            descriptors.add(optionsDescr);
        }
        {
            // 当数据源类型为数据字典时，需要填写字典编号dictKey
            AttributeDescriptor dictKeyDescr = new AttributeDescriptor();
            dictKeyDescr.setName("字典编号");
            dictKeyDescr.setCode("dictKey");
            dictKeyDescr.setDataType(DataType.STRING);
            descriptors.add(dictKeyDescr);
        }
        {
            AttributeDescriptor dataTypeDescr = new AttributeDescriptor();
            dataTypeDescr.setName("数据类型");
            dataTypeDescr.setCode("dataType");
            dataTypeDescr.setDataType(DataType.STRING);
            AttributeDescriptor.Option option0 = new AttributeDescriptor.Option("文本", "Text");
            AttributeDescriptor.Option option1 = new AttributeDescriptor.Option("数字", "Number");
            dataTypeDescr.setOptions(Arrays.asList(option0, option1));
            descriptors.add(dataTypeDescr);
        }
        {
            // 是否冗余属性
            AttributeDescriptor redundantDescr = new AttributeDescriptor();
            redundantDescr.setName("是否冗余标签");
            redundantDescr.setCode("redundant");
            redundantDescr.setDataType(DataType.BOOLEAN);
            descriptors.add(redundantDescr);
        }
        {
            // 是否冗余属性
            AttributeDescriptor redundantDescr = new AttributeDescriptor();
            redundantDescr.setName("是否冗余标签");
            redundantDescr.setCode("redundant");
            redundantDescr.setDataType(DataType.BOOLEAN);
            descriptors.add(redundantDescr);
        }
        {
            // 当冗余标签时，需填写标题名称（有默认值）、标题代码（有默认值）、值名称（有默认值）、值代码（有默认值）
            {
                AttributeDescriptor labelNameDescr = new AttributeDescriptor();
                labelNameDescr.setName("Label名称");
                labelNameDescr.setCode("labelName");
                labelNameDescr.setDataType(DataType.STRING);
                labelNameDescr.setDefaultValue("名称");
                descriptors.add(labelNameDescr);
            }
            {
                AttributeDescriptor labelValueDescr = new AttributeDescriptor();
                labelValueDescr.setName("Label值");
                labelValueDescr.setCode("labelValue");
                labelValueDescr.setDataType(DataType.STRING);
                labelValueDescr.setDefaultValue("label");
                descriptors.add(labelValueDescr);
            }
            {
                AttributeDescriptor valueNameDescr = new AttributeDescriptor();
                valueNameDescr.setName("值名称");
                valueNameDescr.setCode("valueName");
                valueNameDescr.setDataType(DataType.STRING);
                valueNameDescr.setDefaultValue("值");
                descriptors.add(valueNameDescr);
            }
            {
                AttributeDescriptor valueValueDescr = new AttributeDescriptor();
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
    public DataType getDataType(FieldSchema fieldSchema) {
        boolean redundant = fieldSchema.isRedundant();
        if (!redundant) {
            String dataType = fieldSchema.getDataType();
            if (dataType != null) {
                return DataType.valueOf(dataType.toString());
            } else {
                return DataType.STRING;
            }
        } else {
            return DataType.OBJECT;
        }
    }

    @Override
    public List<FieldProperty> getProperties(FieldSchema fieldSchema) {
        // 没有冗余时直接返回空列表
        boolean redundant = fieldSchema.isRedundant();
        if (!redundant) {
            return Collections.emptyList();
        }

        List<FieldProperty> properties = new ArrayList<>();
        {
            FieldProperty labelProperty = new FieldProperty();
            Object labelName = fieldSchema.get("labelName");
            labelProperty.setName(labelName.toString());
            Object labelValue = fieldSchema.get("labelValue");
            labelProperty.setCode(labelValue.toString());
            labelProperty.setDataType(DataType.STRING);
            properties.add(labelProperty);
        }
        {
            FieldProperty valueProperty = new FieldProperty();
            Object valueName = fieldSchema.get("valueName");
            valueProperty.setName(valueName.toString());
            Object valueValue = fieldSchema.get("valueValue");
            valueProperty.setCode(valueValue.toString());
            // 值的类型等于字段属性中设置的类型
            valueProperty.setDataType(DataType.valueOf(fieldSchema.getDataType()));
            properties.add(valueProperty);
        }

        return properties;
    }

}

