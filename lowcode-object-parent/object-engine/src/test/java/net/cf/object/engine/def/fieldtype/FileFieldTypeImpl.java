package net.cf.object.engine.def.fieldtype;

/**
 * 文件字段类型
 *
 * @author clouds
 */
public class FileFieldTypeImpl extends AbstractNameCodeSelectableFieldTypeImpl {

    @Override
    public String getName() {
        return "文件";
    }

    @Override
    public String getCode() {
        return "File";
    }

}
