package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

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
     * @param dataTypeConfig
     * @return
     */
    public static DataType newInstance(JsonObject dataTypeConfig) {
        Object dataTypeValue = dataTypeConfig.get("dataType");
        if (dataTypeValue == null) {
            throw new RuntimeException("数据格式" + dataTypeValue + "不存在！");
        }

        Class<? extends DataType> dataTypeClass = DataTypeClassFactory.get(dataTypeValue.toString());
        try {
            Constructor<? extends DataType> constructor = dataTypeClass.getConstructor(JsonObject.class);
            return constructor.newInstance(dataTypeConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化数据格式" + dataTypeValue + "出错！", e);
        }
    }

}
