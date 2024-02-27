package net.cf.object.engine.def.fieldtype;

import net.cf.object.engine.def.field.FieldPropertyTestImpl;
import net.cf.object.engine.def.field.FieldTestImpl;
import net.cf.object.engine.object.DataType;
import net.cf.object.engine.object.XFieldProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 带名称与代码一类的并且可选择的字段类型抽象
 *
 * @author clouds
 */
public abstract class AbstractNameCodeSelectableFieldTypeImpl extends AbstractSelectableFieldTypeImpl {

    @Override
    public DataType getDataType(FieldTestImpl field) {
        return DataType.OBJECT;
    }

    @Override
    public List<XFieldProperty> getProperties(FieldTestImpl field) {
        List<XFieldProperty> properties = new ArrayList<>();
        {
            FieldPropertyTestImpl nameProp = new FieldPropertyTestImpl(field);
            nameProp.setName("名称");
            nameProp.setCode("name");
            nameProp.setColumnName("NAME");
            nameProp.setDataType(DataType.STRING);
            properties.add(nameProp);
        }
        {
            FieldPropertyTestImpl codeProp = new FieldPropertyTestImpl(field);
            codeProp.setName("编号");
            codeProp.setCode("code");
            codeProp.setColumnName("CODE");
            codeProp.setDataType(DataType.STRING);
            properties.add(codeProp);
        }

        return properties;
    }
}
