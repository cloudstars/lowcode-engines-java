package net.cf.object.engine.fieldtype;

import net.cf.object.engine.object.DataType;

import java.util.ArrayList;
import java.util.List;

/**
 * 带名称与代码一类的并且可选择的字段类型抽象
 *
 * @author clouds
 */
public abstract class AbstractNameCodeSelectableFieldTypeImpl implements XFieldType {

    @Override
    public DataType getDataType(FieldSchema fieldSchema) {
        return DataType.OBJECT;
    }

    @Override
    public List<FieldProperty> getProperties(FieldSchema fieldSchema) {
        List<FieldProperty> properties = new ArrayList<>();
        {
            FieldProperty nameProp = new FieldProperty();
            nameProp.setName("名称");
            nameProp.setCode("name");
            nameProp.setDataType(DataType.STRING);
            nameProp.setDataLength(32);

            properties.add(nameProp);
        }
        {
            FieldProperty codeProp = new FieldProperty();
            codeProp.setName("编号");
            codeProp.setCode("code");
            codeProp.setDataType(DataType.STRING);
            codeProp.setDataLength(20);
            properties.add(codeProp);
        }

        return properties;
    }
}
