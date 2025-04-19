package io.github.cloudstars.lowcode.commons.data.defaultvalue;

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
     * @param dataTypeConfig
     * @return
     */
    public static XDefaultValueConfig newInstance(JsonObject dataTypeConfig) {
        Object typeValue = dataTypeConfig.get("type");
        if (typeValue == null) {
            throw new RuntimeException("默认值配置类型[type]不存在！");
        }

        Class<? extends XDefaultValueConfig> dataTypeClass = DefaultValueConfigClassFactory.get(typeValue.toString());
        try {
            Constructor<? extends XDefaultValueConfig> constructor = dataTypeClass.getConstructor(JsonObject.class);
            return constructor.newInstance(dataTypeConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化默认值配置类型[" + typeValue + "]出错！", e);
        }
    }

}
