package net.cf.form.engine.def.fieldtype;

/**
 * 布尔类字段类型
 *
 * @author clouds
 */
public class BooleanFieldTypeImpl extends AbstractFieldTypeImpl {

    @Override
    public String getName() {
        return "布尔值";
    }

    @Override
    public String getCode() {
        return "Boolean";
    }

}
