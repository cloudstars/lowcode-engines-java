package io.github.cloudstars.lowcode.commons.data.predicate;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 表达式配置Java类工厂
 *
 * @author clouds
 */
public class ExpressionConfigClassFactory {

    /**
     * key是表达式名称，值是表达式配置Java类的映射表
     */
    private static final Map<String, Class<? extends XExpressionConfig>> configClassMap = new HashMap<>();

    private ExpressionConfigClassFactory() {
    }

    /**
     * 注册一种表达式Java类
     *
     * @param configClass 表达式配置Java类
     */
    public static void register(Class<? extends XExpressionConfig> configClass) {
        String typeName = configClass.getName();
        ExpressionConfigClass[] annos = configClass.getAnnotationsByType(ExpressionConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("表达式配置类型[" + typeName + "]必须添加注解@ExpressionConfigClass，注册失败！");
        }

        try {
            configClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("表达式配置类型[" + typeName + "]必须有一个带JsonObject参数的public构造函数！");
        }

        configClassMap.put(annos[0].name().toUpperCase(), configClass);
    }

    /**
     * 根据表达式的名称获取表达式的Java类
     *
     * @param typeName 表达式类型的名称
     * @return 表达式类型配置的Java类
     */
    public static Class<? extends XExpressionConfig> get(String typeName) {
        Class<? extends XExpressionConfig> typeClass = configClassMap.get(typeName.toUpperCase());
        if (typeClass == null) {
            throw new RuntimeException("表达式配置类型[" + typeName + "]不存在！");
        }

        return typeClass;
    }

}
