package io.github.cloudstars.lowcode.commons.data.type;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据格式配置Java类工厂
 *
 * @author clouds
 */
public class DataTypeConfigClassFactory {

    /**
     * key是数据格式类型名称，值是数据格式类型配置Java类的映射表
     */
    private static final Map<String, Class<? extends DataTypeConfig>> dataTypeConfigClassMap = new HashMap<>();

    private DataTypeConfigClassFactory() {
    }

    /**
     * 注册一种数据格式Java类
     *
     * @param dataTypeConfigClass 数据格式配置Java类
     */
    public static void register(Class<? extends DataTypeConfig> dataTypeConfigClass) {
        String dataTypeName = dataTypeConfigClass.getName();
        if (dataTypeConfigClassMap.containsKey(dataTypeName)) {
            throw new RuntimeException("数据格式类型" + dataTypeName + "已存在，注册失败！");
        }

        DataTypeConfigClass annos[] = dataTypeConfigClass.getAnnotationsByType(DataTypeConfigClass.class);
        if (annos.length <= 0) {
            throw new RuntimeException("数据格式类型" + dataTypeName + "必须添加注解@DataTypeConfigClass，注册失败！");
        }

        try {
            dataTypeConfigClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("数据格式类型" + dataTypeName + "必须有一个带JsonObject参数的public构造函数！");
        }

        dataTypeConfigClassMap.put(annos[0].name(), dataTypeConfigClass);
    }

    /**
     * 根据数据格式类型的名称获取数据格式的Java类
     *
     * @param dataTypeName 数据格式类型的名称
     * @return 数据格式配置的Java类
     */
    public static Class<? extends DataTypeConfig> get(String dataTypeName) {
        Class<? extends DataTypeConfig> dataTypeClass = dataTypeConfigClassMap.get(dataTypeName);
        if (dataTypeClass == null) {
            throw new RuntimeException("数据格式类型" + dataTypeName + "不存在！");
        }

        return dataTypeClass;
    }

}
