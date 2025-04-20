package io.github.cloudstars.lowcode.commons.data.datasource;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置Java类工厂
 *
 * @author clouds
 */
public class DataSourceConfigClassFactory {

    /**
     * key是数据源名称，值是数据源配置Java类的映射表
     */
    private static final Map<String, Class<? extends XDataSourceConfig>> configClassMap = new HashMap<>();

    private DataSourceConfigClassFactory() {
    }

    /**
     * 注册一种数据源Java类
     *
     * @param configClass 数据源配置Java类
     */
    public static void register(Class<? extends XDataSourceConfig> configClass) {
        String typeName = configClass.getName();
        DataSourceConfigClass[] annos = configClass.getAnnotationsByType(DataSourceConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("数据源配置类型[" + typeName + "]必须添加注解@DataSourceConfigClass，注册失败！");
        }

        try {
            configClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("数据源配置类型[" + typeName + "]必须有一个带JsonObject参数的public构造函数！");
        }

        configClassMap.put(annos[0].name().toUpperCase(), configClass);
    }

    /**
     * 根据数据源的名称获取数据源的Java类
     *
     * @param typeName 数据源类型的名称
     * @return 数据源配置的Java类
     */
    public static Class<? extends XDataSourceConfig> get(String typeName) {
        Class<? extends XDataSourceConfig> configClass = configClassMap.get(typeName.toUpperCase());
        if (configClass == null) {
            throw new RuntimeException("数据源配置类型[" + typeName + "]不存在！");
        }

        return configClass;
    }

}
