package net.cf.form.engine.def.fieldtype;

/**
 * 用户字段类型
 *
 * @author clouds
 */
public class UserFieldTypeImpl extends AbstractNameCodeSelectableFieldTypeImpl {

    @Override
    public String getName() {
        return "用户";
    }

    @Override
    public String getCode() {
        return "User";
    }

}
