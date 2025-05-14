package io.github.cloudstars.lowcode.object.commons;

import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 模型配置工厂
 * 
 * @author clouds 
 */
public class ObjectConfigFactory {

    private ObjectConfigFactory() {
    }

    /**
     * 根据模型的Json配置实例化一个模型配置
     *
     * @param configJson 配置JSON
     * @return 模型配置
     */
    public static XObjectConfig newInstance(JsonObject configJson) {
        Object typeName = configJson.get(XTypedConfig.ATTR);
        if (typeName == null) {
            throw new RuntimeException("模型配置类型[type]不存在！");
        }

        Class<? extends XObjectConfig> configClass = ObjectConfigClassFactory.get(typeName.toString());
        try {
            Constructor<? extends XObjectConfig> constructor = configClass.getConstructor(JsonObject.class);
            return constructor.newInstance(configJson);
        } catch (Exception e) {
            throw new RuntimeException("实例化模型配置类型[" + typeName + "]出错！", e);
        }
    }

}
