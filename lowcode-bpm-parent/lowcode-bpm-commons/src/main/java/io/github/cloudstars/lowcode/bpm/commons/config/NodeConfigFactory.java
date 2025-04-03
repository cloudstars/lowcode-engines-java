package io.github.cloudstars.lowcode.bpm.commons.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 节点配置工厂
 *
 * @author clouds
 */
public class NodeConfigFactory {

    private NodeConfigFactory() {
    }

    /**
     * 根据节点的Json配置实例化一个节点配置
     *
     * @param nodeConfig
     * @return
     */
    public static AbstractNodeConfig newInstance(JsonObject nodeConfig) {
        Object typeValue = nodeConfig.get("type");
        if (typeValue == null) {
            throw new RuntimeException("节点类型[type]不存在！");
        }
        String bpmNodeType = typeValue.toString();
        if (NodeTypeEnum.valueOf(typeValue.toString().toUpperCase()) == null) {
            throw new RuntimeException("不支持的节点类型：" + bpmNodeType);
        }

        Object subTypeValue = nodeConfig.get("subType");
        if (subTypeValue == null) {
            throw new RuntimeException("节点子类型[subType]不存在！");
        }

        String userNodeType = subTypeValue.toString();
        Class<? extends AbstractNodeConfig> nodeConfigClass = NodeConfigClassFactory.get(userNodeType);
        try {
            Constructor<? extends AbstractNodeConfig> constructor = nodeConfigClass.getConstructor(JsonObject.class);
            return constructor.newInstance(nodeConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化节点配置，类型为[" + userNodeType + "]出错！", e);
        }
    }

}
