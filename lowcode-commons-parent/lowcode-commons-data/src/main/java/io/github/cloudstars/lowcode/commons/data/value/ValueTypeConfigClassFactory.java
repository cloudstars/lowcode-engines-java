package io.github.cloudstars.lowcode.commons.data.value;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据格式配置Java类工厂
 *
 * @author clouds
 */
public class ValueTypeConfigClassFactory {

    /**
     * key是数据格式名称，值是数据格式配置Java类的映射表
     */
    private static final Map<String, Class<? extends ValueTypeConfig>> valueTypeConfigClassMap = new HashMap<>();

    private ValueTypeConfigClassFactory() {
    }

    /**
     * 注册一种数据格式Java类
     *
     * @param valueTypeConfigClass 数据格式配置Java类
     */
    public static void register(Class<? extends ValueTypeConfig> valueTypeConfigClass) {
        String dataTypeName = valueTypeConfigClass.getName();
        /*if (valueTypeConfigClassMap.containsKey(dataTypeName)) {
            throw new RuntimeException("数据格式" + dataTypeName + "已存在，注册失败！");
        }*/

        ValueTypeConfigClass[] annos = valueTypeConfigClass.getAnnotationsByType(ValueTypeConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("数据格式" + dataTypeName + "必须添加注解@ValueTypeConfigClass，注册失败！");
        }

        try {
            valueTypeConfigClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("数据格式" + dataTypeName + "必须有一个带JsonObject参数的public构造函数！");
        }

        valueTypeConfigClassMap.put(annos[0].name(), valueTypeConfigClass);
    }

    /**
     * 根据数据格式的名称获取数据格式的Java类
     *
     * @param valueTypeName 数据格式的名称
     * @return 数据格式配置的Java类
     */
    public static Class<? extends ValueTypeConfig> get(String valueTypeName) {
        Class<? extends ValueTypeConfig> dataTypeClass = valueTypeConfigClassMap.get(valueTypeName);
        if (dataTypeClass == null) {
            throw new RuntimeException("数据格式" + valueTypeName + "不存在！");
        }

        return dataTypeClass;
    }

}
