package io.github.cloudstars.lowcode.commons.value;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认值配置Java类工厂
 *
 * @author clouds
 */
public class ValueConfigClassFactory {

    /**
     * key是默认值名称，值是默认值配置Java类的映射表
     */
    private static final Map<String, Class<? extends XValueConfig>> configClassMap = new HashMap<>();

    private ValueConfigClassFactory() {
    }

    /**
     * 注册一种默认值Java类
     *
     * @param configClass 默认值配置Java类
     */
    public static void register(Class<? extends XValueConfig> configClass) {
        String dataTypeName = configClass.getName();
        ValueConfigClass[] annos = configClass.getAnnotationsByType(ValueConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("Value值配置类型[" + dataTypeName + "]必须添加注解@ValueConfigClass，注册失败！");
        }

        try {
            configClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("Value值配置类型[" + dataTypeName + "]必须有一个带JsonObject参数的public构造函数！");
        }

        configClassMap.put(annos[0].name().toUpperCase(), configClass);
    }

    /**
     * 根据默认值的名称获取默认值的Java类
     *
     * @param typeName 默认值的名称
     * @return 默认值配置的Java类
     */
    public static Class<? extends XValueConfig> get(String typeName) {
        Class<? extends XValueConfig> typeClass = configClassMap.get(typeName.toUpperCase());
        if (typeClass == null) {
            throw new RuntimeException("Value值配置类型[" + typeName + "]不存在！");
        }

        return typeClass;
    }

}
