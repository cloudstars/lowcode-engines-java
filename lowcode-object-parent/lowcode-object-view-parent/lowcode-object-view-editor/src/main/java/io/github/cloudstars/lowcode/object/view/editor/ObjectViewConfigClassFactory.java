package io.github.cloudstars.lowcode.object.view.editor;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型视图配置Java类工厂
 *
 * @author clouds
 */
public class ObjectViewConfigClassFactory {

    /**
     * key是模型视图名称，值是模型视图配置Java类的映射表
     */
    private static final Map<String, Class<? extends XObjectViewConfig>> CONFIG_CLASS_MAP = new HashMap<>();

    private ObjectViewConfigClassFactory() {
    }

    /**
     * 注册一种模型视图Java类
     *
     * @param configClass 模型视图配置Java类
     */
    public static void register(Class<? extends XObjectViewConfig> configClass) {
        String typeName = configClass.getName();
        ObjectViewConfigClass[] annotations = configClass.getAnnotationsByType(ObjectViewConfigClass.class);
        if (annotations.length == 0) {
            throw new RuntimeException("模型视图配置类型[" + typeName + "]必须添加注解@ObjectViewConfigClass，注册失败！");
        }

        try {
            configClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("模型视图配置类型[" + typeName + "]必须有一个带JsonObject参数的public构造函数！");
        }

        CONFIG_CLASS_MAP.put(annotations[0].name().toUpperCase(), configClass);
    }

    /**
     * 根据模型视图的名称获取模型视图的Java类
     *
     * @param typeName 模型视图类型的名称
     * @return 模型视图配置的Java类
     */
    public static Class<? extends XObjectViewConfig> get(String typeName) {
        Class<? extends XObjectViewConfig> configClass = CONFIG_CLASS_MAP.get(typeName.toUpperCase());
        if (configClass == null) {
            throw new RuntimeException("模型视图配置类型[" + typeName + "]不存在！");
        }

        return configClass;
    }

}
