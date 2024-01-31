package net.cf.form.engine.object;

import net.cf.form.engine.ObjectRecordResolver;
import net.cf.form.engine.field.XField;

import java.util.List;
import java.util.Map;

@Deprecated
public class TestObjectRecordResolver implements ObjectRecordResolver {
    @Override
    public void calculate(XObject object, Map<String, Object> data) {
        List<XField> fieldList = object.getFields();
        fieldList.stream().forEach((field -> {
            /*if("Sequence".equals(field.getFieldType().getName())) {
                data.put(field.getName(), "0001");
            }*/
        }));
    }
}
