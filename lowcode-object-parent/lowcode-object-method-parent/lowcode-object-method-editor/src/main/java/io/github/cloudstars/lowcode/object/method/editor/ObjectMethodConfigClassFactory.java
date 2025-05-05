package io.github.cloudstars.lowcode.object.method.editor;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型方法配置Java类工厂
 *
 * @author clouds
 */
public class ObjectMethodConfigClassFactory {

    /**
     * key是模型方法名称，值是模型方法配置Java类的映射表
     */
    private static final Map<String, Class<? extends XObjectMethodConfig>> configClassMap = new HashMap<>();

    private ObjectMethodConfigClassFactory() {
    }

    /**
     * 注册一种模型方法Java类
     *
     * @param configClass 模型方法配置Java类
     */
    public static void register(Class<? extends XObjectMethodConfig> configClass) {
        String typeName = configClass.getName();
        ObjectMethodConfigClass[] annos = configClass.getAnnotationsByType(ObjectMethodConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("模型方法配置类型[" + typeName + "]必须添加注解@DataSourceConfigClass，注册失败！");
        }

        try {
            configClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("模型方法配置类型[" + typeName + "]必须有一个带JsonObject参数的public构造函数！");
        }

        configClassMap.put(annos[0].name().toUpperCase(), configClass);
    }

    /**
     * 根据模型方法的名称获取模型方法的Java类
     *
     * @param typeName 模型方法类型的名称
     * @return 模型方法配置的Java类
     */
    public static Class<? extends XObjectMethodConfig> get(String typeName) {
        Class<? extends XObjectMethodConfig> configClass = configClassMap.get(typeName.toUpperCase());
        if (configClass == null) {
            throw new RuntimeException("模型方法配置类型[" + typeName + "]不存在！");
        }

        return configClass;
    }

}
