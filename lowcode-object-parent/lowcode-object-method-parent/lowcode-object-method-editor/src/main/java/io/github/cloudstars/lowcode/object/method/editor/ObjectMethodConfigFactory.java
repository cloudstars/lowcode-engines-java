
package io.github.cloudstars.lowcode.object.method.editor;

import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 模型方法配置工厂
 *
 * @author clouds
 */
public class ObjectMethodConfigFactory {

    private ObjectMethodConfigFactory() {
    }

    /**
     * 根据模型方法的Json配置实例化一个模型方法配置
     *
     * @param configJson
     * @return
     */
    public static XObjectMethodConfig newInstance(JsonObject configJson) {
        Object typeValue = configJson.get(XTypedConfig.ATTR);
        if (typeValue == null) {
            throw new RuntimeException("模型方法配置类型[type]不存在！");
        }

        Class<? extends XObjectMethodConfig> configClass = ObjectMethodConfigClassFactory.get(typeValue.toString());
        try {
            Constructor<? extends XObjectMethodConfig> constructor = configClass.getConstructor(JsonObject.class);
            return constructor.newInstance(configJson);
        } catch (Exception e) {
            throw new RuntimeException("实例化模型方法配置类型[" + typeValue + "]出错！", e);
        }
    }

}
