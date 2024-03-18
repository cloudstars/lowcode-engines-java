package net.cf.object.engine.fieldtype;

/**
 * 文本类字段类型
 *
 * @author clouds
 */
public class TextFieldTypeImpl implements XFieldType {

    @Override
    public String getDesc() {
        return "文本";
    }

    @Override
    public String getName() {
        return "Text";
    }
}
