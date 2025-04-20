package io.github.cloudstars.lowcode.commons.data.defaultvalue;

import io.github.cloudstars.lowcode.commons.lang.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 默认值配置工厂
 *
 * @author clouds
 */
public class DefaultValueConfigFactory {

    private DefaultValueConfigFactory() {
    }

    /**
     * 根据默认值的Json配置实例化一个默认值配置
     *
     * @param configJson
     * @return
     */
    public static XDefaultValueConfig newInstance(JsonObject configJson) {
        Object typeName = configJson.get(XTypedConfig.ATTR);
        if (typeName == null) {
            throw new RuntimeException("默认值配置类型[type]不存在！");
        }

        Class<? extends XDefaultValueConfig> dataTypeClass = DefaultValueConfigClassFactory.get(typeName.toString());
        try {
            Constructor<? extends XDefaultValueConfig> constructor = dataTypeClass.getConstructor(JsonObject.class);
            return constructor.newInstance(configJson);
        } catch (Exception e) {
            throw new RuntimeException("实例化默认值配置类型[" + typeName + "]出错！", e);
        }
    }

}
