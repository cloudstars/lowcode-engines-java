package io.github.cloudstars.lowcode.commons.lang.value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JavaObject 工具类
 *
 * @author clouds
 */
public final class ObjectValueUtils {

    private static final Logger logger = LoggerFactory.getLogger(ObjectValueUtils.class);

    private ObjectValueUtils() {
    }


    /**
     * 是否基础类型的值
     *
     * @param value
     */
    public static boolean isGeneralValue(Object value) {
        return (value == null || value instanceof String || value instanceof Number || value instanceof Boolean || value instanceof Date || value instanceof Time);
    }

    /**
     * 获取对象的属性值
     *
     * @param o
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object o, String fieldName) {
        if (o == null) {
            return null;
        }

        if (o instanceof Map) {
            return ((Map<?, ?>) o).get(fieldName);
        }

        try {
            Field field = o.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(o);
        } catch (NoSuchFieldException e) {
            // 找不到属性时返回null
            return null;
        } catch (IllegalAccessException e) {
            // 其它的异常情况，取值失败，TODO 记下日志
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置对象的属性值
     *
     * @param o 设值的对象
     * @param fieldName 对象的属性名
     * @param fieldValue 对象的属性值
     */
    public static void setFieldValue(Object o, String fieldName, Object fieldValue) {
        if (o == null) {
            return;
        }

        if (o instanceof Map) {
            ((Map<String, Object>) o).put(fieldName, fieldValue);
        }

        try {
            Field field = o.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(o, fieldValue);
        } catch (NoSuchFieldException e) {
            // 找不到属性时返回null
            return;
        } catch (IllegalAccessException e) {
            // 其它的异常情况，取值失败，TODO 记下日志
            throw new RuntimeException(e);
        }
    }


    /**
     * 克隆一个对象
     *
     * @param o 被克隆的对象
     * @return 克隆后的对象
     */
    public static Object clone(Object o) {
        return clone(o, null);
    }

    /**
     * 克隆一个对象，并给新对象赋值
     *
     * @param o 被克隆的对象
     * @param fieldValues 给新对象赋的值
     * @return 克隆后的对象
     */
    public static Object clone(Object o, Map<String, Object> fieldValues) {
        if (o == null) {
            return null;
        }

        if (o instanceof Map) {
            Map<String, Object> cmo = new HashMap<>();
            Map<String, ?> mo = (Map<String, ?>) o;
            mo.forEach((k, v) -> {
                if (fieldValues != null && fieldValues.containsKey(k)) {
                    cmo.put(k, fieldValues.get(k));
                } else {
                    cmo.put(k, mo.get(k));
                }
            });
            return cmo;
        }

        Object co = null;
        Class ocls = o.getClass();
        Constructor constructor = null;
        try {
            constructor = ocls.getConstructor();
            co = constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(ocls.getName() + "必须声明一个public的无参构造函数");
        }

        Field[] fields = ocls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field f = fields[i];
            String fName = f.getName();
            f.setAccessible(true);
            try {
                if (fieldValues != null && fieldValues.containsKey(fName)) {
                    f.set(co, fieldValues.get(fName));
                } else {
                    f.set(co, f.get(o));
                }
            } catch (IllegalAccessException e) {
                // 异常情况，拷贝失败，TODO 记下日志
                return new RuntimeException(e);
            }
        }

        return co;
    }

}

