package net.cf.object.engine.fieldtype;

/**
 * 机构字段类型
 *
 * @author clouds
 */
public class OrgFieldTypeImpl extends AbstractNameCodeSelectableFieldTypeImpl {

    @Override
    public String getDesc() {
        return "用户";
    }

    @Override
    public String getName() {
        return "User";
    }

}
