package net.cf.form.engine.def.fieldtype;

/**
 * 数字类字段类型
 *
 * @author clouds
 */
public class NumberFieldTypeImpl extends AbstractFieldTypeImpl {

    @Override
    public String getName() {
        return "数字";
    }

    @Override
    public String getCode() {
        return "Number";
    }

}
