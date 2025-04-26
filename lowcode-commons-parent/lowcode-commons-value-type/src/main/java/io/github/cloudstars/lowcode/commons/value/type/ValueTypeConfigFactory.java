package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.lang.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 数据格式配置工厂
 *
 * @author clouds
 */
public class ValueTypeConfigFactory {

    private ValueTypeConfigFactory() {
    }

    /**
     * 根据数据格式的Json配置实例化一个数据格式配置
     *
     * @param configJson 配置JSON
     * @return 数据格式配置
     */
    public static XValueTypeConfig newInstance(JsonObject configJson) {
        Object typeName = configJson.get(XTypedConfig.ATTR);
        if (typeName == null) {
            throw new RuntimeException("数据格式配置类型[type]不存在！");
        }

        Class<? extends XValueTypeConfig> configClass = ValueTypeConfigClassFactory.get(typeName.toString());
        try {
            Constructor<? extends XValueTypeConfig> constructor = configClass.getConstructor(JsonObject.class);
            return constructor.newInstance(configJson);
        } catch (Exception e) {
            throw new RuntimeException("实例化数据格式配置类型[" + typeName + "]出错！", e);
        }
    }

}
