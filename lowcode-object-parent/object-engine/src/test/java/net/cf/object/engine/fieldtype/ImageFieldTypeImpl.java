package net.cf.object.engine.fieldtype;

/**
 * 图片字段类型
 *
 * @author clouds
 */
public class ImageFieldTypeImpl extends AbstractSelectableFieldTypeImpl {

    @Override
    public String getDesc() {
        return "图片";
    }

    @Override
    public String getName() {
        return "Image";
    }
}
