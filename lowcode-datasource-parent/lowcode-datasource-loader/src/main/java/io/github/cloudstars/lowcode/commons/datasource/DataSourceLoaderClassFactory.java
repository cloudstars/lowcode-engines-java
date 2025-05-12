package io.github.cloudstars.lowcode.commons.datasource;

import io.github.cloudstars.lowcode.commons.datasource.config.XDataSourceConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据源加载器类工厂
 *
 * @author clouds
 */
public class DataSourceLoaderClassFactory {

    /**
     * key是数据源加载器配置，值是数据源加载器实现的Java类的映射表
     */
    private static final Map<Class<? extends XDataSourceConfig>, Class<? extends XDataSourceLoader>> DATA_SOURCE_LOADER_CALSS_MAP = new HashMap<>();


    private DataSourceLoaderClassFactory() {
    }

    /**
     * 注册一种数据源加载器类
     *
     * @param dataSourceLoaderClass 数据源加载器类
     */
    public static void register(Class<? extends XDataSourceLoader> dataSourceLoaderClass) {
        String typeName = dataSourceLoaderClass.getName();
        DataSourceLoaderClass[] annotations = dataSourceLoaderClass.getAnnotationsByType(DataSourceLoaderClass.class);
        if (annotations.length == 0) {
            throw new RuntimeException("数据源加载器" + typeName + "必须添加注解@DataSourceLoaderClass，注册失败！");
        }

        Class<? extends XDataSourceConfig> dataSourceConfigClass = annotations[0].dataSourceConfigClass();
        try {
            dataSourceLoaderClass.getConstructor(dataSourceConfigClass);
            DATA_SOURCE_LOADER_CALSS_MAP.put(dataSourceConfigClass, dataSourceLoaderClass);
        } catch (Exception e) {
            String dataSourceConfigClassName = dataSourceConfigClass.getName();
            throw new RuntimeException("数据源加载器" + typeName + "必须有一个带" + dataSourceConfigClassName + "参数的public构造函数！");
        }
    }


    /**
     * 根据数据源加载器的Java类获取数据源加载器的Java类
     *
     * @param dataSourceConfigClass 数据源加载器配置Java类
     * @return 数据源加载器Java类
     */
    public static Class<? extends XDataSourceLoader> get(Class<? extends XDataSourceConfig> dataSourceConfigClass) {
        Class<? extends XDataSourceLoader> dataSourceLoaderClass = DATA_SOURCE_LOADER_CALSS_MAP.get(dataSourceConfigClass);
        if (dataSourceLoaderClass == null) {
            throw new RuntimeException("数据源加载器[" + dataSourceConfigClass.getName() + "]不存在！");
        }

        return dataSourceLoaderClass;
    }
}
