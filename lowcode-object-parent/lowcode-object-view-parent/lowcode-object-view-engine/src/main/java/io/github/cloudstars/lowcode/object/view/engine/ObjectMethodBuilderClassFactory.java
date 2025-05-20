package io.github.cloudstars.lowcode.object.view.engine;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型视图接口构建器工厂
 *
 * @author clouds
 */
public final class ObjectMethodBuilderClassFactory {

    /**
     * key是模型视图接口构建器类型名称，值是模型视图构建器类型的映射表
     */
    private static final Map<String, Class<? extends XObjectViewMethodBuilder>> builderClassMap = new HashMap<>();

    private ObjectMethodBuilderClassFactory() {
    }

    /**
     * 注册一种模型视图接口构建器类型
     *
     * @param builderClass 模型视图接口构建器类型
     */
    public static void register(Class<? extends XObjectViewMethodBuilder> builderClass) {
        String typeName = builderClass.getName();
        if (builderClassMap.containsKey(typeName)) {
            throw new RuntimeException("模型视图接口构建器类型[" + typeName + "]已存在，注册失败！");
        }

        ObjectViewMethodBuilderClass[] annotations = builderClass.getAnnotationsByType(ObjectViewMethodBuilderClass.class);
        if (annotations.length == 0) {
            throw new RuntimeException("模型视图接口构建器类型[" + typeName + "]必须添加注解@ObjectViewMethodBuilderClass，注册失败！");
        }

        try {
            builderClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("模型视图接口构建器类型" + typeName + "必须有一个带JsonObject参数的public构造函数！");
        }

        builderClassMap.put(annotations[0].type().toUpperCase(), builderClass);
    }

    /**
     * 根据模型视图接口构建器类型的名称获取模型视图接口构建器的类型
     *
     * @param typeName 模型视图接口构建器类型的名称
     * @return 模型视图接口构建器类型
     */
    public static Class<? extends XObjectViewMethodBuilder> get(String typeName) {
        Class<? extends XObjectViewMethodBuilder> builderClass = builderClassMap.get(typeName.toUpperCase());
        if (builderClass == null) {
            throw new RuntimeException("模型视图接口构建器类型[" + typeName + "]不存在！");
        }

        return builderClass;
    }

}
