package net.cf.form.engine.object;

import net.cf.form.engine.def.FieldDefinition;
import net.cf.form.engine.def.FormDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * XObject的测试实现类
 *
 * @author clouds
 */
public class XObjectTestImpl implements XObject<XFieldTestImpl> {

    private final FormDefinition objectDef;

    private final List<XFieldTestImpl> fields = new ArrayList<>();

    public XObjectTestImpl(FormDefinition objectDef) {
        this.objectDef = objectDef;
        List<FieldDefinition> fieldDefs = objectDef.getFields();
        for (FieldDefinition fieldDef : fieldDefs) {
            XFieldTestImpl field = new XFieldTestImpl(fieldDef);
            fields.add(field);
        }
    }

    @Override
    public String getCode() {
        return objectDef.getName();
    }

    @Override
    public String getName() {
        return objectDef.getTitle();
    }

    @Override
    public boolean isAutoPrimaryField() {
        return false;
    }

    @Override
    public List<XFieldTestImpl> getFields() {
        return this.fields;
    }

    @Override
    public XFieldTestImpl getField(String fieldCode) {
        return null;
    }

    @Override
    public XFieldTestImpl getPrimaryField() {
        return null;
    }

    @Override
    public void validate(Map<String, Object> dataMap) {
    }

    @Override
    public String getTableName() {
        return this.getCode();
    }

    @Override
    public List<XObject> getDetailObjects() {
        return null;
    }

    @Override
    public XObject getParentObject() {
        return null;
    }
}
