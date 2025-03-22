package io.github.cloudstars.lowcode.commons.lang.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
     * 创建一个对象的引用
     *
     * @param ref
     * @return
     */
    public static <T extends Object> ObjectReference createRef(T ref) {
        return new ObjectReference<T>(ref);
    }


}

