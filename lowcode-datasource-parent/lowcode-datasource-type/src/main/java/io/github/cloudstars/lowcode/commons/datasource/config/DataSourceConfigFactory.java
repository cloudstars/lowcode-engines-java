package io.github.cloudstars.lowcode.commons.datasource.config;

import io.github.cloudstars.lowcode.commons.lang.config.XTypedConfig;
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
     * @param configJson
     * @return
     */
    public static XDataSourceConfig newInstance(JsonObject configJson) {
        Object typeValue = configJson.get(XTypedConfig.ATTR);
        if (typeValue == null) {
            throw new RuntimeException("数据源配置类型[type]不存在！");
        }

        Class<? extends XDataSourceConfig> configClass = DataSourceConfigClassFactory.get(typeValue.toString());
        try {
            Constructor<? extends XDataSourceConfig> constructor = configClass.getConstructor(JsonObject.class);
            return constructor.newInstance(configJson);
        } catch (Exception e) {
            throw new RuntimeException("实例化数据源配置类型[" + typeValue + "]出错！", e);
        }
    }

}
