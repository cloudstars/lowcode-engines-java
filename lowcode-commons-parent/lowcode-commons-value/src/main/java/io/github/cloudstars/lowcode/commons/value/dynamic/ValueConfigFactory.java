package io.github.cloudstars.lowcode.commons.value.dynamic;

import io.github.cloudstars.lowcode.commons.lang.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 默认值配置工厂
 *
 * @author clouds
 */
public class ValueConfigFactory {

    private ValueConfigFactory() {
    }

    /**
     * 根据默认值的Json配置实例化一个默认值配置
     *
     * @param configJson 配置Json
     * @return Value值配置
     */
    public static XValueConfig newInstance(JsonObject configJson) {
        Object typeName = configJson.get(XTypedConfig.ATTR);
        if (typeName == null) {
            throw new RuntimeException("默认值配置类型[type]不存在！");
        }

        Class<? extends XValueConfig> dataTypeClass = ValueConfigClassFactory.get(typeName.toString());
        try {
            Constructor<? extends XValueConfig> constructor = dataTypeClass.getConstructor(JsonObject.class);
            return constructor.newInstance(configJson);
        } catch (Exception e) {
            throw new RuntimeException("实例化默认值配置类型[" + typeName + "]出错！", e);
        }
    }

}
