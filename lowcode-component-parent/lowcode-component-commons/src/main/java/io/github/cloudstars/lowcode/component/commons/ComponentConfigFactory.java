package io.github.cloudstars.lowcode.component.commons;

import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 组件配置工厂
 *
 * @author clouds
 */
public class ComponentConfigFactory {

    private ComponentConfigFactory() {
    }

    /**
     * 根据组件的Json配置实例化一个组件配置
     *
     * @param configJson 配置JSON
     * @return 组件配置
     */
    public static XComponentConfig newInstance(JsonObject configJson) {
        Object typeName = configJson.get(XTypedConfig.ATTR);
        if (typeName == null) {
            throw new RuntimeException("组件配置类型[type]不存在！");
        }

        Class<? extends XComponentConfig> configClass = ComponentConfigClassFactory.get(typeName.toString());
        try {
            Constructor<? extends XComponentConfig> constructor = configClass.getConstructor(JsonObject.class);
            return constructor.newInstance(configJson);
        } catch (Exception e) {
            throw new RuntimeException("实例化组件配置类型[" + typeName + "]出错！", e);
        }
    }

}
