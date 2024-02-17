package net.cf.commons.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class ObjectUtils {

    private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);

    private ObjectUtils() {
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

    /**
     * 将一个字符串解析为Java对象
     *
     * @param content
     * @param classType
     * @return
     */
    public static <T extends Object> T parseObject(String content, Class<T> classType) {
        return JSONObject.parseObject(content, classType);
    }


    /**
     * 获取一个对象的属性值
     *
     * @param object
     * @param propertyName
     * @return
     */
    public static Object getPropertyValue(Object object, String propertyName) {
        if (object == null) {
            return null;
        }

        if (object instanceof Map) {
            return ((Map) object).get(propertyName);
        }

        Class clazz = object.getClass();
        Method method;
        if (propertyName.startsWith("is")) {
            try {
                method = clazz.getDeclaredMethod(propertyName);
            } catch (NoSuchMethodException e1) {
                return null;
            }
        } else {
            String cPropertyName = StringUtils.capitalize(propertyName);
            try {
                method = clazz.getDeclaredMethod("get" + cPropertyName);
            } catch (NoSuchMethodException e2) {
                try {
                    method = clazz.getDeclaredMethod("is" + cPropertyName);
                } catch (NoSuchMethodException e3) {
                    return null;
                }
            }
        }

        try {
            return method.invoke(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}