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
     * @param configJson
     * @return
     */
    public static AbstractNodeConfig newInstance(JsonObject configJson) {
        Object typeValue = configJson.get("type");
        if (typeValue == null) {
            throw new RuntimeException("节点类型[type]不存在！");
        }

        String type = typeValue.toString();
        Class<? extends AbstractNodeConfig> nodeConfigClass = NodeConfigClassFactory.get(type);
        try {
            Constructor<? extends AbstractNodeConfig> constructor = nodeConfigClass.getConstructor(JsonObject.class);
            return constructor.newInstance(configJson);
        } catch (Exception e) {
            throw new RuntimeException("实例化节点配置，类型为[" + type + "]出错！", e);
        }
    }

}
