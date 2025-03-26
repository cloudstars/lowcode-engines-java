package io.github.cloudstars.lowcode.commons.data.valuetype;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 数据格式工厂
 *
 * @author clouds
 */
public class ValueTypeFactory {

    private ValueTypeFactory() {
    }

    /**
     * 根据数据格式的名称获取数据格式定义
     *
     * @param valueTypeConfig
     * @return
     */
    public static ValueTypeConfig newInstance(JsonObject valueTypeConfig) {
        Object dataTypeValue = valueTypeConfig.get("dataType");
        if (dataTypeValue == null) {
            throw new RuntimeException("数据格式" + dataTypeValue + "不存在！");
        }

        Class<? extends ValueTypeConfig> dataTypeClass = ValueTypeClassFactory.get(dataTypeValue.toString());
        try {
            Constructor<? extends ValueTypeConfig> constructor = dataTypeClass.getConstructor(JsonObject.class);
            return constructor.newInstance(valueTypeConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化数据格式" + dataTypeValue + "出错！", e);
        }
    }

}
