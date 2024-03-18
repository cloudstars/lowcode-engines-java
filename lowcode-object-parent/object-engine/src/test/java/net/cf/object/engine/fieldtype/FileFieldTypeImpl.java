package net.cf.object.engine.fieldtype;

/**
 * 文件字段类型
 *
 * @author clouds
 */
public class FileFieldTypeImpl extends AbstractNameCodeSelectableFieldTypeImpl {

    @Override
    public String getDesc() {
        return "文件";
    }

    @Override
    public String getName() {
        return "File";
    }

}
