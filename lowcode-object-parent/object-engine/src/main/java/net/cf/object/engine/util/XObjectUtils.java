package net.cf.object.engine.util;

import net.cf.object.engine.object.ObjectRefType;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;

import java.util.ArrayList;
import java.util.List;

public final class XObjectUtils {

    private XObjectUtils() {
    }

    /**
     * 获取一个模型下的子模型字段
     *
     * @param object
     * @return
     */
    public static List<XObjectRefField> getDetailFields(XObject object) {
        List<XField> fields = object.getFields();
        List<XObjectRefField> detailFields = new ArrayList<>();
        for (XField field : fields) {
            if (field instanceof XObjectRefField) {
                if (((XObjectRefField) field).getRefType() == ObjectRefType.MASTER) {
                    detailFields.add((XObjectRefField) field);
                }
            }
        }

        return detailFields;
    }

    /**
     * 判断一个模型下是否有子表字段
     *
     * @param object
     * @return
     */
    public static boolean hasDetailFields(XObject object) {
        List<XField> fields = object.getFields();
        for (XField field : fields) {
            if (field instanceof XObjectRefField) {
                if (((XObjectRefField) field).getRefType() == ObjectRefType.MASTER) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 获取一个字段名称的复数形式
     *
     * @param field
     * @return
     */
    public static String  getFieldNamePlural(XField field) {
        return field.getName() + "s";
    }

}
