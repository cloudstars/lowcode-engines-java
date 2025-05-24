package io.github.cloudstars.lowcode.commons.lang.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Time;
import java.util.*;

/**
 * JavaObject 工具类
 *
 * @author clouds
 */
public final class ObjectUtils {

    private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);

    private ObjectUtils() {
    }

    /**
     * 根据类名实例化一个对象
     *
     * @param clazz
     * @return
     */
    public static Object newInstance(Class<?> clazz) {
        Object instance = null;
        String className = clazz.getName();
        try {
            Constructor<?> constructor = clazz.getConstructor();
            instance = constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("类[" + className + "]实例化失败，必须包含一个public的无参构造函数", e);
        }

        return instance;
    }


    /**
     * 根据类名实例化一个对象
     *
     * @param className
     * @return
     */
    public static Object newInstance(String className) {
        Object instance = null;
        try {
            Class<?> clz = ClassLoader.getSystemClassLoader().loadClass(className);
            Constructor<?> constructor = clz.getDeclaredConstructor();
            instance = constructor.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("类[" + className + "]不存在");
        } catch (Exception e) {
            throw new RuntimeException("类[" + className + "]必须包含一个public的无参构造函数");
        }

        return instance;
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
     * @param o           被克隆的对象
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

    /**
     * 获取对象中的可以调用的get/is方法
     *
     * @param object
     * @return
     */
    public static Map<String, Method> getDeclaredGetMethodMap(Object object) {
        assert (object != null);

        Map<String, Method> methodMap = new HashMap<>();
        Class clz = object.getClass();
        Field[] fields = clz.getDeclaredFields();
        for (int i = 0, l = fields.length; i < l; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if (fieldName.startsWith("this$")) {
                // 如果是内部类的话，存在一些特殊的属性，可以忽略
                continue;
            }

            String methodName;
            if (field.getType() == boolean.class) {
                if (fieldName.startsWith("is")) {
                    methodName = fieldName;
                } else {
                    methodName = "is" + StringUtils.capitalize(fieldName);
                }
            } else {
                methodName = "get" + StringUtils.capitalize(fieldName);
            }


            try {
                methodMap.put(fieldName, clz.getMethod(methodName));
            } catch (NoSuchMethodException e) {
                logger.error("尝试获取属性{}的get方法{}失败", fieldName, methodName);
            }
        }

        return methodMap;
    }

    /**
     * 获取对象的属性名称列表
     *
     * @param object 对象
     * @return List
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
     * 创建一个对象的引用
     *
     * @param ref
     * @return
     */
    public static <T extends Object> ObjectRef createRef(T ref) {
        return new ObjectRef<T>(ref);
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
     * @param o          设值的对象
     * @param fieldName  对象的属性名
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
     * 获取一个值，如果值为空则返回默认值
     *
     * @param a 期望获取的值
     * @param b 默认值
     * @return 结果值
     */
    public static <T extends Object> T getOrDefault(T a, T b) {
        return a != null ? a : b;
    }
}

