package io.github.cloudstars.lowcode.commons.datasource;

import io.github.cloudstars.lowcode.commons.datasource.config.XDataSourceConfig;

import java.lang.reflect.Constructor;

/**
 * 数据源加载器工厂
 *
 * @author clouds
 */
public class DataSourceLoaderFactory {

    private DataSourceLoaderFactory() {
    }


    /**
     * 根据数据源加载器实例化一个数据源加载器实现类
     *
     * @param dataSourceConfig 数据源加载器
     * @return 数据源加载器
     */
    public static XDataSourceLoader newInstance(XDataSourceConfig dataSourceConfig) {
        Class<? extends XDataSourceConfig> dataSourceConfigClass = dataSourceConfig.getClass();
        Class<? extends XDataSourceLoader> dataSourceLoaderClass = DataSourceLoaderClassFactory.get(dataSourceConfigClass);
        try {
            Constructor<? extends XDataSourceLoader> constructor = dataSourceLoaderClass.getConstructor(dataSourceConfigClass);
            return constructor.newInstance(dataSourceConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化数据源加载器[" + dataSourceLoaderClass.getName() + "]出错！", e);
        }
    }

}
