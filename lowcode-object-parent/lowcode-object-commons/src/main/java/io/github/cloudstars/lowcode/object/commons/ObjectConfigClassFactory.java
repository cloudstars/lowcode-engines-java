package io.github.cloudstars.lowcode.object.commons;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型配置Java类工厂
 *
 * @author clouds
 */
public class ObjectConfigClassFactory {

    /**
     * key是模型名称，值是模型配置类的映射表
     */
    private static final Map<String, Class<? extends XObjectConfig>> CONFIG_CLASS_MAP = new HashMap<>();

    private ObjectConfigClassFactory() {
    }

    /**
     * 注册一种模型配置类
     *
     * @param configClass 模型配置类
     */
    public static void register(Class<? extends XObjectConfig> configClass) {
        String typeName = configClass.getName();
        ObjectConfigClass[] annotations = configClass.getAnnotationsByType(ObjectConfigClass.class);
        if (annotations.length == 0) {
            throw new RuntimeException("模型配置类型" + typeName + "必须添加注解@ObjectConfigClass，注册失败！");
        }

        try {
            configClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("模型配置类型" + typeName + "必须有一个带JsonObject参数的public构造函数！");
        }

        CONFIG_CLASS_MAP.put(annotations[0].name(), configClass);
    }

    /**
     * 根据模型的名称获取模型配置的类型
     *
     * @param typeName 模型配置类型的名称
     * @return 模型配置的类型
     */
    public static Class<? extends XObjectConfig> get(String typeName) {
        Class<? extends XObjectConfig> configClass = CONFIG_CLASS_MAP.get(typeName);
        if (configClass == null) {
            throw new RuntimeException("模型类型" + typeName + "不存在！");
        }

        return configClass;
    }

}
