package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 数据格式工厂
 *
 * @author clouds
 */
public class DataTypeFactory {

    private DataTypeFactory() {
    }

    /**
     * 根据数据格式的名称获取数据格式定义
     *
     * @param valueTypeConfig
     * @return
     */
    public static DataTypeConfig newInstance(JsonObject valueTypeConfig) {
        Object dataTypeValue = valueTypeConfig.get("dataType");
        if (dataTypeValue == null) {
            throw new RuntimeException("数据格式" + dataTypeValue + "不存在！");
        }

        Class<? extends DataTypeConfig> dataTypeClass = DataTypeClassFactory.get(dataTypeValue.toString());
        try {
            Constructor<? extends DataTypeConfig> constructor = dataTypeClass.getConstructor(JsonObject.class);
            return constructor.newInstance(valueTypeConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化数据格式" + dataTypeValue + "出错！", e);
        }
    }

}
