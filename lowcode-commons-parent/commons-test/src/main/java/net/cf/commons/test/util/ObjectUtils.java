package net.cf.commons.test.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 对象测试工具类
 *
 * @author clouds
 */
public final class ObjectUtils {

    private ObjectUtils() {
    }

    /**
     * 获取对象的属性名称列表
     *
     * @param object
     * @return
     */
    public static List<String> getFieldNames(Object object) {
        if (object == null) {
            return Collections.emptyList();
        }


        List<String> fieldNames = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 0, l = fields.length; i < l; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if (fieldName.startsWith("this$")) {
                // 如果是内部类的话，存在一些特殊的属性，可以忽略
                continue;
            }
            fieldNames.add(fieldName);
        }

        return fieldNames;
    }

    /**
     * 是否基础类型的值
     *
     * @param value
     */
    public static boolean isGeneralValue(Object value) {
        if (value == null) {
            return true;
        }

        return (value instanceof String || value instanceof Number || value instanceof Boolean || value instanceof Date);
    }

    /**
     * 比较两个对象是否相等
     *
     * @param source
     * @param target
     * @param comparator
     * @return
     */
    public static boolean equalsObject(Object source, Object target, ObjectComparator comparator) {
        if (source == null && target == null) {
            return true;
        }

        if (source == null || target == null) {
            return false;
        }

        return comparator.equalsNullSafe(source, target);
    }


    /**
     * 比较两个列表是否相等
     *
     * @param sourceList
     * @param targetList
     * @param comparator
     * @return
     */
    public static <S extends Object, T extends Object> boolean equalsList(List<S> sourceList, List<T> targetList, ObjectComparator comparator) {
        if (sourceList == null && targetList == null) {
            return true;
        } else if (sourceList == null || targetList == null) {
            return false;
        }

        int sl = sourceList.size();
        int tl = targetList.size();
        if (sl != tl) {
            return false;
        }

        for (int i = 0; i < sl; i++) {
            Object source = sourceList.get(i);
            Object target = targetList.get(i);
            if (ObjectUtils.equalsObject(source, target, comparator)) {
                return false;
            }
        }

        return true;
    }
}
