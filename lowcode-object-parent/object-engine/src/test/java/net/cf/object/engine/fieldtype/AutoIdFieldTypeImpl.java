package net.cf.object.engine.fieldtype;

/**
 * 自动ID字段类型
 *
 * @author clouds
 */
public class AutoIdFieldTypeImpl implements XFieldType {

    @Override
    public String getDesc() {
        return "自动ID";
    }

    @Override
    public String getName() {
        return FieldTypeCodeConstants.AUTO_ID;
    }

}
