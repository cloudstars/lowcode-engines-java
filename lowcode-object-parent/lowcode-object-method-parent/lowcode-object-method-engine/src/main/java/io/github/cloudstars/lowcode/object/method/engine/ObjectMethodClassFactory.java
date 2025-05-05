package io.github.cloudstars.lowcode.object.method.engine;

import io.github.cloudstars.lowcode.object.method.editor.XObjectMethodConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型方法类工厂
 *
 * @author clouds
 */
public class ObjectMethodClassFactory {

    /**
     * key是模型方法配置，值是模型方法实现的Java类的映射表
     */
    private static final Map<Class<? extends XObjectMethodConfig>, Class<? extends XObjectMethod>> OBJECT_METHOD_CALSS_MAP = new HashMap<>();


    private ObjectMethodClassFactory() {
    }

    /**
     * 注册一种模型方法类
     *
     * @param objectMethodClass 模型方法类
     */
    public static void register(Class<? extends XObjectMethod> objectMethodClass) {
        String typeName = objectMethodClass.getName();
        ObjectMethodClass[] annotations = objectMethodClass.getAnnotationsByType(ObjectMethodClass.class);
        if (annotations.length == 0) {
            throw new RuntimeException("模型方法" + typeName + "必须添加注解@ObjectMethodClass，注册失败！");
        }

        Class<? extends XObjectMethodConfig> objectMethodConfigClass = annotations[0].configClass();
        try {
            objectMethodClass.getConstructor(objectMethodConfigClass);
            OBJECT_METHOD_CALSS_MAP.put(objectMethodConfigClass, objectMethodClass);
        } catch (Exception e) {
            String objectMethodConfigClassName = objectMethodConfigClass.getName();
            throw new RuntimeException("模型方法" + typeName + "必须有一个带" + objectMethodConfigClassName + "参数的public构造函数！");
        }
    }


    /**
     * 根据模型方法的Java类获取模型方法的Java类
     *
     * @param objectMethodConfigClass 模型方法配置Java类
     * @return 模型方法Java类
     */
    public static Class<? extends XObjectMethod> get(Class<? extends XObjectMethodConfig> objectMethodConfigClass) {
        Class<? extends XObjectMethod> objectMethodClass = OBJECT_METHOD_CALSS_MAP.get(objectMethodConfigClass);
        if (objectMethodClass == null) {
            throw new RuntimeException("模型方法[" + objectMethodConfigClass.getName() + "]不存在！");
        }

        return objectMethodClass;
    }
}
