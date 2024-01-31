package net.cf.form.engine.object;

import net.cf.form.commons.FieldDefinition;
import net.cf.form.commons.ObjectDefinition;
import net.cf.form.engine.field.UniqueFields;
import net.cf.form.engine.field.XField;
import net.cf.form.engine.repository.data.DbType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * XObject的测试实现类
 *
 * @author clouds
 */
public class XObjectTestImpl implements XObject {

    private final ObjectDefinition objectDef;

    private final List<XField> fields = new ArrayList<>();

    public XObjectTestImpl(ObjectDefinition objectDef) {
        this.objectDef = objectDef;
        List<FieldDefinition> fieldDefs = objectDef.getFields();
        for (FieldDefinition fieldDef : fieldDefs) {
            XField field = new XFieldTestImpl(fieldDef);
            fields.add(field);
        }
    }

    @Override
    public String getName() {
        return objectDef.getName();
    }

    @Override
    public String getTitle() {
        return objectDef.getTitle();
    }

    @Override
    public boolean isMultiTenant() {
        return false;
    }

    @Override
    public boolean isAutoPrimaryField() {
        return false;
    }

    @Override
    public List<UniqueFields> getUniqueFields() {
        return null;
    }

    @Override
    public List<XField> getFields() {
        return this.fields;
    }

    @Override
    public String getPrimaryFieldName() {
        return this.objectDef.getPrimaryFieldName();
    }

    @Override
    public ValidationResult validate(Map<String, Object> dataMap) {
        return null;
    }

    @Override
    public boolean isPersistence() {
        return false;
    }

    @Override
    public DbType getDbType() {
        return null;
    }

    @Override
    public String getTableName() {
        return this.getName();
    }

    @Override
    public List<XObject> getSubObjects() {
        return null;
    }

    @Override
    public List<XObject> getOneByOneRefObjects() {
        return null;
    }

    @Override
    public List<XObject> getOneByManyRefObjects() {
        return null;
    }

    @Override
    public XObject getParentObject() {
        return null;
    }
}
