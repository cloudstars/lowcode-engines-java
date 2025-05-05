package io.github.cloudstars.lowcode.object.core.editor.field;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 字段配置Java类工厂
 *
 * @author clouds
 */
public class ObjectFieldConfigClassFactory {

    /**
     * key是字段类型名称，值是字段配置Java类的映射表
     */
    private static final Map<String, Class<? extends AbstractObjectFieldConfig>> configClassMap = new HashMap<>();

    private ObjectFieldConfigClassFactory() {
    }

    /**
     * 注册一种字段配置Java类
     *
     * @param configClass 字段配置Java类
     */
    public static void register(Class<? extends AbstractObjectFieldConfig> configClass) {
        String typeName = configClass.getName();
        if (configClassMap.containsKey(typeName)) {
            throw new RuntimeException("字段配置类型[" + typeName + "]已存在，注册失败！");
        }

        ObjectFieldConfigClass[] annos = configClass.getAnnotationsByType(ObjectFieldConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("字段配置类型[" + typeName + "]必须添加注解@NodeConfigClass，注册失败！");
        }

        try {
            configClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("字段配置类型[" + typeName + "]必须有一个带JsonObject参数的public构造函数！");
        }

        configClassMap.put(annos[0].name().toUpperCase(), configClass);
    }

    /**
     * 根据字段类型的名称获取字段配置Java的类
     *
     * @param typeName 字段类型
     * @return 字段配置的Java类
     */
    public static Class<? extends AbstractObjectFieldConfig> get(String typeName) {
        Class<? extends AbstractObjectFieldConfig> configClass = configClassMap.get(typeName.toUpperCase());
        if (configClass == null) {
            throw new RuntimeException("字段配置类型[" + typeName + "]不存在！");
        }

        return configClass;
    }

}
