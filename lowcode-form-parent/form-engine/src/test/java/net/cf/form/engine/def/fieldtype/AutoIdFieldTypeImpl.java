package net.cf.form.engine.def.fieldtype;

/**
 * 自动ID字段类型
 *
 * @author clouds
 */
public class AutoIdFieldTypeImpl extends AbstractFieldTypeImpl {

    @Override
    public String getName() {
        return "自动ID";
    }

    @Override
    public String getCode() {
        return FieldTypeCodeConstants.AUTO_ID;
    }

}
