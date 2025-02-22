package net.cf.commons.test.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 对象测试工具类
 *
 * @author clouds
 */
public final class ObjectTestUtils {

    private ObjectTestUtils() {
    }

    /**
     * 比较两个对象是否相等（不考虑嵌套属性）
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean equals(Object source, Object target) {
        return ObjectTestUtils.compareObjectNullSafe(source, target, (s, t) -> {
            boolean isSGV = ObjectTestUtils.isGeneralValue(s);
            boolean isTGV = ObjectTestUtils.isGeneralValue(t);
            if (isSGV && isTGV) { // 两个都是原子类型时，直接equals比较
                Date date = null;
                String dateStr = null;
                if (s instanceof Date && t instanceof String) {
                    date = (Date) s;
                    dateStr = (String) t;
                } else if (s instanceof String && t instanceof Date) {
                    date = (Date) t;
                    dateStr = (String) s;
                }

                if (date != null && dateStr != null) {
                    int dateStrLen = dateStr.length();
                    String dateFormatStr = null;
                    if (dateStrLen == 10) {
                        dateFormatStr = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
                    } else if (dateStrLen == 8) {
                        dateFormatStr = (new SimpleDateFormat("hh:mm:ss")).format(date);
                    } else if (dateStrLen == 19) {
                        dateFormatStr = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(date);
                    }

                    return dateStr.equals(dateFormatStr);
                }

                if (s instanceof Number && t instanceof Number) {
                    if (s instanceof BigDecimal && t instanceof BigDecimal) {
                         return ((BigDecimal) s).compareTo((BigDecimal) t) == 0;
                    }

                    if ((s instanceof Long && t instanceof Integer) || (s instanceof Integer && t instanceof Long)) {
                        return s.toString().equals(t.toString());
                    }
                }

                return s.equals(t);
            } else if (!isSGV && !isTGV) { // 两个都是非原子类型
                List<String> sourceFieldNames = ObjectTestUtils.getDeclaredFieldNames(s);
                List<String> targetFieldNames = ObjectTestUtils.getDeclaredFieldNames(t);
                if (sourceFieldNames.size() != targetFieldNames.size()) {
                    return false;
                }

                return ObjectTestUtils.isAssignableFromWithProperties(s, t, sourceFieldNames);
            } else { // 有一个是原子类型，一个不是原子类型
                return false;
            }
        });
    }

    /**
     * 判断源对象是否可以从目标对象派生出来（不考虑嵌套属性）
     *
     * @param source
     * @param target
     * @return
     */
    public static boolean isAssignableFrom(Object source, Object target) {
        return ObjectTestUtils.compareObjectNullSafe(source, target, (s, t) -> {
            List<String> fieldNames = ObjectTestUtils.getDeclaredFieldNames(source);
            return ObjectTestUtils.isAssignableFromWithProperties(source, target, fieldNames);
        });
    }

    /**
     * 判断源对象指定的属性是否可以从目标对象派生出来（不考虑嵌套属性）
     *
     * @param source
     * @param target
     * @param properties
     * @return
     */
    public static boolean isAssignableFromWithProperties(Object source, Object target, List<String> properties) {
        return ObjectTestUtils.compareObjectNullSafe(source, target, (s, t) -> {
            for (String property : properties) {
                Object sv = ObjectTestUtils.getFieldValue(s, property);
                Object tv = ObjectTestUtils.getFieldValue(t, property);
                if (!ObjectTestUtils.equals(sv, tv)) {
                    return false;
                }
            }

            return true;
        });
    }

    /**
     * 比较两个对象，并且自动判断Null值的情况
     *
     * @param source
     * @param target
     * @param comparator
     * @return
     */
    public static <S extends Object, T extends Object> boolean compareObjectNullSafe(S source, T target, Comparator<S, T> comparator) {
        if (source == null && target == null) {
            return true;
        }

        if (source == null || target == null) {
            return false;
        }

        return comparator.compareNullSafe(source, target);
    }

    @FunctionalInterface
    protected interface Comparator<S, T> {

        /**
         * 比较两个对象的值是否相等，前提是两个对象都非空
         *
         * @param source
         * @param target
         * @return
         */
        boolean compareNullSafe(S source, T target);

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
     * 获取对象的属性名称列表
     *
     * @param object
     * @return
     */
    public static List<String> getDeclaredFieldNames(Object object) {
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
     * 获取对象的属性的值
     *
     * @param object
     * @return
     */
    public static Object getFieldValue(Object object, String fieldName) {
        if (object == null) {
            return null;
        }

        Class<?> clazz = object.getClass();
        Field field = null;
        boolean invokeSuccess;
        try {
            field = clazz.getDeclaredField(fieldName);
            if (field == null) {
                return null;
            }

            return field.get(object);
        } catch (NoSuchFieldException ex) {
            invokeSuccess = false;
        } catch (IllegalAccessException ex) {
            invokeSuccess = false;
        }

        if (!invokeSuccess) {
            String methodName = "get" + StringUtils.capitalize(fieldName);
            if (field != null) {
                Class<?> fieldType = field.getType();
                if (fieldType == Boolean.class) {
                    methodName = "is" + StringUtils.capitalize(fieldName);
                } else if (fieldType == boolean.class) {
                    methodName = fieldName;
                }
            }

            try {
                Method method = clazz.getDeclaredMethod(methodName);
                try {
                    return method.invoke(object);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            } catch (NoSuchMethodException e) {
                // get/is方法也不存在时返回null
                return null;
            }
        }

        return null;
    }

}
