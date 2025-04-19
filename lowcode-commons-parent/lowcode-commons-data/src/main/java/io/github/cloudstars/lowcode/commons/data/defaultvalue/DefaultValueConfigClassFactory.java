package io.github.cloudstars.lowcode.commons.data.defaultvalue;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认值配置Java类工厂
 *
 * @author clouds
 */
public class DefaultValueConfigClassFactory {

    /**
     * key是默认值名称，值是默认值配置Java类的映射表
     */
    private static final Map<String, Class<? extends XDefaultValueConfig>> configClassMap = new HashMap<>();

    private DefaultValueConfigClassFactory() {
    }

    /**
     * 注册一种默认值Java类
     *
     * @param defaultValueConfigClass 默认值配置Java类
     */
    public static void register(Class<? extends XDefaultValueConfig> defaultValueConfigClass) {
        String dataTypeName = defaultValueConfigClass.getName();
        DefaultValueConfigClass[] annos = defaultValueConfigClass.getAnnotationsByType(DefaultValueConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("默认值类型[" + dataTypeName + "]必须添加注解@DefaultValueConfigClass，注册失败！");
        }

        try {
            defaultValueConfigClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("默认值类型[" + dataTypeName + "]必须有一个带JsonObject参数的public构造函数！");
        }

        configClassMap.put(annos[0].name(), defaultValueConfigClass);
    }

    /**
     * 根据默认值的名称获取默认值的Java类
     *
     * @param typeName 默认值的名称
     * @return 默认值配置的Java类
     */
    public static Class<? extends XDefaultValueConfig> get(String typeName) {
        Class<? extends XDefaultValueConfig> typeClass = configClassMap.get(typeName);
        if (typeClass == null) {
            throw new RuntimeException("默认值类型[" + typeName + "]不存在！");
        }

        return typeClass;
    }

}
