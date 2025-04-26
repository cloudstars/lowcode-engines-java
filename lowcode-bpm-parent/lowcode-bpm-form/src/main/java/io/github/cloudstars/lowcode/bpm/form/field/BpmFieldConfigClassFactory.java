package io.github.cloudstars.lowcode.bpm.form.field;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 字段配置Java类工厂
 *
 * @author clouds
 */
public class BpmFieldConfigClassFactory {

    /**
     * key是字段类型名称，值是字段配置Java类的映射表
     */
    private static final Map<String, Class<? extends AbstractBpmFieldConfig>> configClassMap = new HashMap<>();

    private BpmFieldConfigClassFactory() {
    }

    /**
     * 注册一种字段配置Java类
     *
     * @param configClass 字段配置Java类
     */
    public static void register(Class<? extends AbstractBpmFieldConfig> configClass) {
        String typeName = configClass.getName();
        if (configClassMap.containsKey(typeName)) {
            throw new RuntimeException("字段配置类型[" + typeName + "]已存在，注册失败！");
        }

        BpmFieldConfigClass[] annos = configClass.getAnnotationsByType(BpmFieldConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("BPM字段配置类型[" + typeName + "]必须添加注解@BpmFieldConfigClass，注册失败！");
        }

        try {
            configClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("BPM字段配置类型[" + typeName + "]必须有一个带JsonObject参数的public构造函数！");
        }

        configClassMap.put(annos[0].name().toUpperCase(), configClass);
    }

    /**
     * 根据字段类型的名称获取字段配置Java的类
     *
     * @param typeName 字段类型
     * @return 字段配置的Java类
     */
    public static Class<? extends AbstractBpmFieldConfig> get(String typeName) {
        Class<? extends AbstractBpmFieldConfig> configClass = configClassMap.get(typeName.toUpperCase());
        if (configClass == null) {
            throw new RuntimeException("BPM字段配置类型[" + typeName + "]不存在！");
        }

        return configClass;
    }

}
