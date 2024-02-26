package net.cf.form.engine.def.fieldtype;

import net.cf.form.engine.def.field.FieldPropertyTestImpl;
import net.cf.form.engine.def.field.FieldTestImpl;
import net.cf.form.engine.object.DataType;
import net.cf.form.engine.object.XField;
import net.cf.form.engine.object.XFieldProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 带名称与代码一类的并且可选择的字段类型抽象
 *
 * @author clouds
 */
public abstract class AbstractNameCodeSelectableFieldTypeImpl extends AbstractSelectableFieldTypeImpl {

    @Override
    public <T extends XField> DataType getDataType(T field) {
        return DataType.OBJECT;
    }

    @Override
    public <T extends XField> List<XFieldProperty> getProperties(T field) {
        assert (field instanceof FieldTestImpl);

        FieldTestImpl refField = (FieldTestImpl) field;
        List<XFieldProperty> properties = new ArrayList<>();
        {
            FieldPropertyTestImpl nameProp = new FieldPropertyTestImpl(refField);
            nameProp.setName("名称");
            nameProp.setCode("name");
            nameProp.setColumnName("NAME");
            nameProp.setDataType(DataType.STRING);
            properties.add(nameProp);
        }
        {
            FieldPropertyTestImpl codeProp = new FieldPropertyTestImpl(refField);
            codeProp.setName("编号");
            codeProp.setCode("code");
            codeProp.setColumnName("CODE");
            codeProp.setDataType(DataType.STRING);
            properties.add(codeProp);
        }

        return properties;
    }
}
