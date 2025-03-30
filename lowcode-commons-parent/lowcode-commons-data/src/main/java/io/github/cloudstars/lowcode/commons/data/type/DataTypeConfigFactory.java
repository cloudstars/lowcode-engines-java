package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 数据格式工厂
 *
 * @author clouds
 */
public class DataTypeConfigFactory {

    private DataTypeConfigFactory() {
    }

    /**
     * 根据数据格式的Json配置实例化一个数据格式配置
     *
     * @param dataTypeConfig
     * @return
     */
    public static DataTypeConfig newInstance(JsonObject dataTypeConfig) {
        Object dataTypeValue = dataTypeConfig.get("dataType");
        if (dataTypeValue == null) {
            throw new RuntimeException("数据格式[type]不存在！");
        }

        Class<? extends DataTypeConfig> dataTypeClass = DataTypeConfigClassFactory.get(dataTypeValue.toString());
        try {
            Constructor<? extends DataTypeConfig> constructor = dataTypeClass.getConstructor(JsonObject.class);
            return constructor.newInstance(dataTypeConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化数据格式" + dataTypeValue + "出错！", e);
        }
    }

}
