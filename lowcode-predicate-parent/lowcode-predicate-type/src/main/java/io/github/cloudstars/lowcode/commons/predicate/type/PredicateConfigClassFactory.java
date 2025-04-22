package io.github.cloudstars.lowcode.commons.predicate.type;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 断言配置Java类工厂
 *
 * @author clouds
 */
public class PredicateConfigClassFactory {

    /**
     * key是断言名称，值是断言配置Java类的映射表
     */
    private static final Map<String, Class<? extends XPredicateConfig>> configClassMap = new HashMap<>();

    private PredicateConfigClassFactory() {
    }

    /**
     * 注册一种断言Java类
     *
     * @param configClass 断言配置Java类
     */
    public static void register(Class<? extends XPredicateConfig> configClass) {
        String typeName = configClass.getName();
        PredicateConfigClass[] annos = configClass.getAnnotationsByType(PredicateConfigClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("断言配置类型[" + typeName + "]必须添加注解@ExpressionConfigClass，注册失败！");
        }

        try {
            configClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("断言配置类型[" + typeName + "]必须有一个带JsonObject参数的public构造函数！");
        }

        configClassMap.put(annos[0].name().toUpperCase(), configClass);
    }

    /**
     * 根据断言的名称获取断言的Java类
     *
     * @param typeName 断言类型的名称
     * @return 断言类型配置的Java类
     */
    public static Class<? extends XPredicateConfig> get(String typeName) {
        Class<? extends XPredicateConfig> typeClass = configClassMap.get(typeName.toUpperCase());
        if (typeClass == null) {
            throw new RuntimeException("断言配置类型[" + typeName + "]不存在！");
        }

        return typeClass;
    }

}
