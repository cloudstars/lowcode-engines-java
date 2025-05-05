
package io.github.cloudstars.lowcode.object.view.editor;

import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 模型视图配置工厂
 *
 * @author clouds
 */
public class ObjectViewConfigFactory {

    private ObjectViewConfigFactory() {
    }

    /**
     * 根据模型视图的Json配置实例化一个模型视图配置
     *
     * @param configJson
     * @return
     */
    public static XObjectViewConfig newInstance(JsonObject configJson) {
        Object typeValue = configJson.get(XTypedConfig.ATTR);
        if (typeValue == null) {
            throw new RuntimeException("模型视图配置类型[type]不存在！");
        }

        Class<? extends XObjectViewConfig> configClass = ObjectViewConfigClassFactory.get(typeValue.toString());
        try {
            Constructor<? extends XObjectViewConfig> constructor = configClass.getConstructor(JsonObject.class);
            return constructor.newInstance(configJson);
        } catch (Exception e) {
            throw new RuntimeException("实例化模型视图配置类型[" + typeValue + "]出错！", e);
        }
    }

}
