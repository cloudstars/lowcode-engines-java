package net.cf.object.engine.def.fieldtype;

/**
 * 机构字段类型
 *
 * @author clouds
 */
public class OrgFieldTypeImpl extends AbstractNameCodeSelectableFieldTypeImpl {

    @Override
    public String getName() {
        return "用户";
    }

    @Override
    public String getCode() {
        return "User";
    }

}
