package io.github.cloudstars.lowcode.commons.data.value;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 数据格式工厂
 *
 * @author clouds
 */
public class ValueTypeConfigFactory {

    private ValueTypeConfigFactory() {
    }

    /**
     * 根据数据格式的Json配置实例化一个数据格式配置
     *
     * @param dataTypeConfig
     * @return
     */
    public static ValueTypeConfig newInstance(JsonObject dataTypeConfig) {
        Object dataTypeValue = dataTypeConfig.get("type");
        if (dataTypeValue == null) {
            throw new RuntimeException("数据格式[type]不存在！");
        }

        Class<? extends ValueTypeConfig> dataTypeClass = ValueTypeConfigClassFactory.get(dataTypeValue.toString());
        try {
            Constructor<? extends ValueTypeConfig> constructor = dataTypeClass.getConstructor(JsonObject.class);
            return constructor.newInstance(dataTypeConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化数据格式" + dataTypeValue + "出错！", e);
        }
    }

}
