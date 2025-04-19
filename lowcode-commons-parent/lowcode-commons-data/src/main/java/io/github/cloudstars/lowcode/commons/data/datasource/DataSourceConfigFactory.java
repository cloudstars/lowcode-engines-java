package io.github.cloudstars.lowcode.commons.data.datasource;

import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 数据源配置工厂
 *
 * @author clouds
 */
public class DataSourceConfigFactory {

    private DataSourceConfigFactory() {
    }

    /**
     * 根据数据源的Json配置实例化一个数据源配置
     *
     * @param dataTypeConfig
     * @return
     */
    public static XDataSourceConfig newInstance(JsonObject dataTypeConfig) {
        Object typeValue = dataTypeConfig.get(XConfig.ATTR_TYPE);
        if (typeValue == null) {
            throw new RuntimeException("数据源配置类型[type]不存在！");
        }

        Class<? extends XDataSourceConfig> configClass = DataSourceConfigClassFactory.get(typeValue.toString());
        try {
            Constructor<? extends XDataSourceConfig> constructor = configClass.getConstructor(JsonObject.class);
            return constructor.newInstance(dataTypeConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化数据源配置类型[" + typeValue + "]出错！", e);
        }
    }

}
