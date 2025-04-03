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
    private static final Map<String, Class<? extends NodeConfig>> nodeConfigClassMap = new HashMap<>();

    private NodeConfigClassFactory() {
    }

    /**
     * 注册一种节点配置Java类
     *
     * @param nodeConfigClass 节点配置Java类
     */
    public static void register(Class<? extends NodeConfig> nodeConfigClass) {
        String nodeType = nodeConfigClass.getName();
        if (nodeConfigClassMap.containsKey(nodeType)) {
            throw new RuntimeException("节点类型" + nodeType + "已存在，注册失败！");
        }

        NodeConfigClass[] annos = nodeConfigClass.getAnnotationsByType(NodeConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("节点类型" + nodeType + "必须添加注解@NodeConfigClass，注册失败！");
        }

        try {
            nodeConfigClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("节点类型" + nodeType + "必须有一个带JsonObject参数的public构造函数！");
        }

        nodeConfigClassMap.put(annos[0].type(), nodeConfigClass);
    }

    /**
     * 根据节点类型的名称获取节点配置Java的类
     *
     * @param nodeType 节点类型
     * @return 节点配置的Java类
     */
    public static Class<? extends NodeConfig> get(String nodeType) {
        Class<? extends NodeConfig> nodeConfigClass = nodeConfigClassMap.get(nodeType);
        if (nodeConfigClass == null) {
            throw new RuntimeException("节点类型" + nodeType + "不存在！");
        }

        return nodeConfigClass;
    }

}
