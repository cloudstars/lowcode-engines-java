package io.github.cloudstars.lowcode.bpm.commons.config;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 节点配置Java类工厂
 *
 * @author clouds
 */
public class NodeConfigClassFactory {

    /**
     * key是节点类型名称，值是节点配置Java类的映射表
     */
    private static final Map<String, Class<? extends AbstractNodeConfig>> configClassMap = new HashMap<>();

    private NodeConfigClassFactory() {
    }

    /**
     * 注册一种节点配置Java类
     *
     * @param configClass 节点配置Java类
     */
    public static void register(Class<? extends AbstractNodeConfig> configClass) {
        String typeName = configClass.getName();
        if (configClassMap.containsKey(typeName)) {
            throw new RuntimeException("节点配置类型[" + typeName + "]已存在，注册失败！");
        }

        NodeConfigClass[] annos = configClass.getAnnotationsByType(NodeConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("节点配置类型[" + typeName + "]必须添加注解@NodeConfigClass，注册失败！");
        }

        try {
            configClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("节点配置类型" + typeName + "必须有一个带JsonObject参数的public构造函数！");
        }

        configClassMap.put(annos[0].type().toUpperCase(), configClass);
    }

    /**
     * 根据节点类型的名称获取节点配置Java的类
     *
     * @param typeName 节点类型的名称
     * @return 节点配置的Java类
     */
    public static Class<? extends AbstractNodeConfig> get(String typeName) {
        Class<? extends AbstractNodeConfig> configClass = configClassMap.get(typeName.toUpperCase());
        if (configClass == null) {
            throw new RuntimeException("节点配置类型[" + typeName + "]不存在！");
        }

        return configClass;
    }

}
