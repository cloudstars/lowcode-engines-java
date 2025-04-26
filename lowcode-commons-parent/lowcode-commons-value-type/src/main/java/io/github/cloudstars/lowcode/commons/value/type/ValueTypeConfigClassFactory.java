package io.github.cloudstars.lowcode.commons.value.type;


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
    private static final Map<String, Class<? extends XValueTypeConfig>> valueTypeConfigClassMap = new HashMap<>();

    private ValueTypeConfigClassFactory() {
    }

    /**
     * 注册一种数据格式Java类
     *
     * @param valueTypeConfigClass 数据格式配置Java类
     */
    public static void register(Class<? extends XValueTypeConfig> valueTypeConfigClass) {
        String typeName = valueTypeConfigClass.getName();
        ValueTypeConfigClass[] annos = valueTypeConfigClass.getAnnotationsByType(ValueTypeConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("数据格式类型" + typeName + "必须添加注解@ValueTypeConfigClass，注册失败！");
        }

        try {
            valueTypeConfigClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("数据格式类型" + typeName + "必须有一个带JsonObject参数的public构造函数！");
        }

        valueTypeConfigClassMap.put(annos[0].name(), valueTypeConfigClass);
    }

    /**
     * 根据数据格式的名称获取数据格式的类型
     *
     * @param typeName 数据格式类型的名称
     * @return 数据格式配置的类型
     */
    public static Class<? extends XValueTypeConfig> get(String typeName) {
        Class<? extends XValueTypeConfig> dataTypeClass = valueTypeConfigClassMap.get(typeName);
        if (dataTypeClass == null) {
            throw new RuntimeException("数据格式类型" + typeName + "不存在！");
        }

        return dataTypeClass;
    }

}
