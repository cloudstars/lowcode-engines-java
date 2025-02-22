package net.cf.object.engine.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class ObjectBeanUtils {

    private static final Logger logger = LoggerFactory.getLogger(ObjectBeanUtils.class);

    private ObjectBeanUtils() {
    }

    /**
     * 获取模型中的可以调用的get/is方法
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
     * 创建一个模型的引用
     *
     * @param ref
     * @return
     */
    public static <T extends Object> Ref createRef(T ref) {
        return new Ref<T>(ref);
    }

    /**
     * 获取一个模型的属性值
     *
     * @param object
     * @param propName
     * @return
     */
    public static Object getPropertyValue(Object object, String propName) {
        if (object == null) {
            return null;
        }

        if (object instanceof Map) {
            return ((Map) object).get(propName);
        }

        Class clazz = object.getClass();
        Method method;
        if (propName.startsWith("is")) {
            try {
                method = clazz.getDeclaredMethod(propName);
            } catch (NoSuchMethodException e1) {
                return null;
            }
        } else {
            String cPropertyName = StringUtils.capitalize(propName);
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

    public static class Ref<T> {

        private T ref;

        public Ref(T ref) {
            this.ref = ref;
        }

        public T getRef() {
            return ref;
        }

        public void setRef(T ref) {
            this.ref = ref;
        }
    }
}