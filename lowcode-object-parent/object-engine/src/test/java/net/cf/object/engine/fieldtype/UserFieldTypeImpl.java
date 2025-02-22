package net.cf.object.engine.fieldtype;

/**
 * 用户字段类型
 *
 * @author clouds
 */
public class UserFieldTypeImpl extends AbstractNameCodeSelectableFieldTypeImpl {

    @Override
    public String getDesc() {
        return "用户";
    }

    @Override
    public String getName() {
        return "User";
    }

}
