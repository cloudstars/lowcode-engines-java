package io.github.cloudstars.lowcode.dynamic.value;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认值配置Java类工厂
 *
 * @author clouds
 */
public class DynamicValueConfigClassFactory {

    /**
     * key是默认值名称，值是默认值配置Java类的映射表
     */
    private static final Map<String, Class<? extends XDynamicValueConfig>> configClassMap = new HashMap<>();

    private DynamicValueConfigClassFactory() {
    }

    /**
     * 注册一种默认值Java类
     *
     * @param configClass 默认值配置Java类
     */
    public static void register(Class<? extends XDynamicValueConfig> configClass) {
        String dataTypeName = configClass.getName();
        DynamicValueConfigClass[] annos = configClass.getAnnotationsByType(DynamicValueConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("动态值配置类型[" + dataTypeName + "]必须添加注解@DynamicValueConfigClass，注册失败！");
        }

        try {
            configClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("Value值配置类型[" + dataTypeName + "]必须有一个带JsonObject参数的public构造函数！");
        }

        configClassMap.put(annos[0].name().toUpperCase(), configClass);
    }

    /**
     * 根据动态值的名称获取动态值的Java类
     *
     * @param typeName 动态值的名称
     * @return 动态值配置的Java类
     */
    public static Class<? extends XDynamicValueConfig> get(String typeName) {
        Class<? extends XDynamicValueConfig> typeClass = configClassMap.get(typeName.toUpperCase());
        if (typeClass == null) {
            throw new RuntimeException("动态值配置类型[" + typeName + "]不存在！");
        }

        return typeClass;
    }

}
