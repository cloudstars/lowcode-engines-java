package net.cf.commons.test.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
            List<String> sourceFieldNames = ObjectTestUtils.getDeclaredFieldNames(source);
            List<String> targetFieldNames = ObjectTestUtils.getDeclaredFieldNames(target);
            if (sourceFieldNames.size() != targetFieldNames.size()) {
                return false;
            }

            return ObjectTestUtils.isAssignableFromWithProperties(source, target, sourceFieldNames);
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
            Class sourceClass = source.getClass();
            Class targetClass = target.getClass();
            for (String property : properties) {
                Object spv = ObjectTestUtils.getFieldValue(source, property);
                Object tpv = ObjectTestUtils.getFieldValue(target, property);
                boolean isSGV = ObjectTestUtils.isGeneralValue(spv);
                boolean isTGV = ObjectTestUtils.isGeneralValue(tpv);
                if (isSGV && isTGV) {
                    // 两个都是原子类型时，直接equals比较
                    if (!ObjectTestUtils.compareObjectNullSafe(spv, tpv, (sf, tf) -> {
                        Date date = null;
                        String dateStr = null;
                        if (sf instanceof Date && tf instanceof String) {
                            date = (Date) sf;
                            dateStr = (String) tf;
                        } else if (sf instanceof String && tf instanceof Date) {
                            date = (Date) tf;
                            dateStr = (String) sf;
                        }

                        if (date != null && dateStr != null) {
                            int dateStrLen = dateStr.length();
                            String dateFormatStr = null;
                            if (dateStrLen == 10) {
                                dateFormatStr = (new SimpleDateFormat("yyyy-MM-dd")).format((Date) sf);
                            } else if (dateStrLen == 8) {
                                dateFormatStr = (new SimpleDateFormat("hh:mm:ss")).format((Date) sf);
                            } else if (dateStrLen == 19) {
                                dateFormatStr = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format((Date) sf);
                            }

                            return dateStr.equals(dateFormatStr);
                        } else {
                            return sf.equals(tf);
                        }
                    })) {
                        return false;
                    }
                } else if (!isSGV && !isTGV) {
                    return ObjectTestUtils.isAssignableFromWithProperties(spv, tpv, properties);
                } else {
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
