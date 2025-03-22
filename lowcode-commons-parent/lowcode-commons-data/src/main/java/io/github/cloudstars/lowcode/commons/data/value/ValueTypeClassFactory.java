package io.github.cloudstars.lowcode.commons.data.value;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据格式工厂
 *
 * @author clouds
 */
public class ValueTypeClassFactory {

    private static final Map<String, Class<? extends ValueTypeConfig>> dataTypeClassMap = new HashMap<>();

    private ValueTypeClassFactory() {
    }

    /**
     * 注册一种数据格式
     *
     * @param dataTypeClass 数据格式类
     */
    public static void register(Class<? extends ValueTypeConfig> dataTypeClass) {
        String dataTypeClassName = dataTypeClass.getName();
        if (dataTypeClassMap.containsKey(dataTypeClassName)) {
            throw new RuntimeException("数据格式类型" + dataTypeClassName + "已存在，注册失败！");
        }

        DataTypeConfigClass annos[] = dataTypeClass.getAnnotationsByType(DataTypeConfigClass.class);
        if (annos.length <= 0) {
            throw new RuntimeException("数据格式类型" + dataTypeClassName + "必须添加注解@DataTypeConfigClass，注册失败！");
        }

        try {
            dataTypeClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("数据格式类型" + dataTypeClassName + "必须有一个带JsonObject参数的public构造函数！");
        }

        dataTypeClassMap.put(annos[0].name(), dataTypeClass);
    }

    /**
     * 根据数据格式的名称获取数据格式定义
     *
     * @param dataTypeName 数据格式类型
     * @return 数据格式类
     */
    public static Class<? extends ValueTypeConfig> get(String dataTypeName) {
        Class<? extends ValueTypeConfig> dataTypeClass = dataTypeClassMap.get(dataTypeName);
        if (dataTypeClass == null) {
            throw new RuntimeException("数据格式类型" + dataTypeName + "不存在！");
        }

        return dataTypeClass;
    }

}
