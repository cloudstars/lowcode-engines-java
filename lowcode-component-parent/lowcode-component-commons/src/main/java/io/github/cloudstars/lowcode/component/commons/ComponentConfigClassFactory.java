package io.github.cloudstars.lowcode.component.commons;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 组件配置Java类工厂
 *
 * @author clouds
 */
public class ComponentConfigClassFactory {

    /**
     * key是组件名称，值是组件配置Java类的映射表
     */
    private static final Map<String, Class<? extends XComponentConfig>> configClassMap = new HashMap<>();

    private ComponentConfigClassFactory() {
    }

    /**
     * 注册一种组件Java类
     *
     * @param configClass 组件配置Java类
     */
    public static void register(Class<? extends XComponentConfig> configClass) {
        String typeName = configClass.getName();
        ComponentConfigClass[] annotations = configClass.getAnnotationsByType(ComponentConfigClass.class);
        if (annotations.length == 0) {
            throw new RuntimeException("组件类型" + typeName + "必须添加注解@ComponentConfigClass，注册失败！");
        }

        try {
            configClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("组件类型" + typeName + "必须有一个带JsonObject参数的public构造函数！");
        }

        configClassMap.put(annotations[0].name(), configClass);
    }

    /**
     * 根据组件的名称获取组件的类型
     *
     * @param typeName 组件类型的名称
     * @return 组件配置的类型
     */
    public static Class<? extends XComponentConfig> get(String typeName) {
        Class<? extends XComponentConfig> configClass = configClassMap.get(typeName);
        if (configClass == null) {
            throw new RuntimeException("组件类型" + typeName + "不存在！");
        }

        return configClass;
    }

}
