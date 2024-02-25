package net.cf.form.engine.def.fieldtype;

/**
 * 文件字段类型
 *
 * @author clouds
 */
public class FileFieldTypeImpl extends AbstractFieldTypeImpl {

    @Override
    public String getName() {
        return "文件";
    }

    @Override
    public String getCode() {
        return "File";
    }
    
}
