package io.github.cloudstars.lowcode.object.form.editor.convert;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 模型字段配置到组件配置转换器Java类工厂
 *
 * @author clouds
 */
public class ObjectFieldConfig2ComponentConfigConvertorClassFactory {

    /**
     * key是默认值名称，值是模型字段配置到组件配置转换器Java类的映射表
     */
    private static final Map<String, Class<? extends XObjectFieldConfig2ComponentConfigConvertor>> convertorClassMap = new HashMap<>();

    private ObjectFieldConfig2ComponentConfigConvertorClassFactory() {
    }

    /**
     * 注册一种转换器Java类
     *
     * @param convertorClass 转换器Java类
     */
    public static void register(Class<? extends XObjectFieldConfig2ComponentConfigConvertor> convertorClass) {
        String convertorName = convertorClass.getName();
        ObjectFieldConfig2ComponentConfigConvertorClass[] annotations = convertorClass.getAnnotationsByType(ObjectFieldConfig2ComponentConfigConvertorClass.class);
        if (annotations.length == 0) {
            throw new RuntimeException("转换器类型[" + convertorName + "]必须添加注解@ObjectFieldConfig2ComponentConfigConvertorClass，注册失败！");
        }

        try {
            convertorClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("转换器类型[" + convertorName + "]必须有一个带JsonObject参数的public构造函数！");
        }

        convertorClassMap.put(annotations[0].type().toUpperCase(), convertorClass);
    }

    /**
     * 根据转换器的名称获取转换器的Java类
     *
     * @param typeName 转换器的名称
     * @return 转换器的Java类
     */
    public static Class<? extends XObjectFieldConfig2ComponentConfigConvertor> get(String typeName) {
        Class<? extends XObjectFieldConfig2ComponentConfigConvertor> typeClass = convertorClassMap.get(typeName.toUpperCase());
        if (typeClass == null) {
            throw new RuntimeException("转换器类型[" + typeName + "]不存在！");
        }

        return typeClass;
    }

}
