package io.github.cloudstars.lowcode.form.commons.field;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 字段配置Java类工厂
 *
 * @author clouds
 */
public class FieldConfigClassFactory {

    /**
     * key是字段类型名称，值是字段配置Java类的映射表
     */
    private static final Map<String, Class<? extends AbstractFieldConfig>> fieldConfigClassMap = new HashMap<>();

    private FieldConfigClassFactory() {
    }

    /**
     * 注册一种字段配置Java类
     *
     * @param nodeConfigClass 字段配置Java类
     */
    public static void register(Class<? extends AbstractFieldConfig> nodeConfigClass) {
        String nodeType = nodeConfigClass.getName();
        if (fieldConfigClassMap.containsKey(nodeType)) {
            throw new RuntimeException("字段类型" + nodeType + "已存在，注册失败！");
        }

        FieldConfigClass[] annos = nodeConfigClass.getAnnotationsByType(FieldConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("字段类型" + nodeType + "必须添加注解@NodeConfigClass，注册失败！");
        }

        try {
            nodeConfigClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("字段类型" + nodeType + "必须有一个带JsonObject参数的public构造函数！");
        }

        fieldConfigClassMap.put(annos[0].type(), nodeConfigClass);
    }

    /**
     * 根据字段类型的名称获取字段配置Java的类
     *
     * @param nodeType 字段类型
     * @return 字段配置的Java类
     */
    public static Class<? extends AbstractFieldConfig> get(String nodeType) {
        Class<? extends AbstractFieldConfig> nodeConfigClass = fieldConfigClassMap.get(nodeType);
        if (nodeConfigClass == null) {
            throw new RuntimeException("字段类型" + nodeType + "不存在！");
        }

        return nodeConfigClass;
    }

}
