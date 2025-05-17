package io.github.cloudstars.lowcode.component.form;


import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 表单项组件配置Java类工厂
 *
 * @author clouds
 */
public class FormItemComponentConfigClassFactory {

    /**
     * key是表单项组件类型名称，值是表单项组件配置Java类的映射表
     */
    private static final Map<String, Class<? extends AbstractFormItemComponentConfig>> configClassMap = new HashMap<>();

    private FormItemComponentConfigClassFactory() {
    }

    /**
     * 注册一种表单项组件配置Java类
     *
     * @param configClass 表单项组件配置Java类
     */
    public static void register(Class<? extends AbstractFormItemComponentConfig> configClass) {
        String typeName = configClass.getName();
        if (configClassMap.containsKey(typeName)) {
            throw new RuntimeException("表单项组件配置类型[" + typeName + "]已存在，注册失败！");
        }

        FormItemComponentClass[] annos = configClass.getAnnotationsByType(FormItemComponentClass.class);
        if (annos.length == 0) {
            throw new RuntimeException("表单项组件配置类型[" + typeName + "]必须添加注解@NodeConfigClass，注册失败！");
        }

        try {
            configClass.getConstructor(JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("表单项组件配置类型" + typeName + "必须有一个带JsonObject参数的public构造函数！");
        }

        configClassMap.put(annos[0].type().toUpperCase(), configClass);
    }

    /**
     * 根据表单项组件类型的名称获取表单项组件配置Java的类
     *
     * @param typeName 表单项组件类型的名称
     * @return 表单项组件配置的Java类
     */
    public static Class<? extends AbstractFormItemComponentConfig> get(String typeName) {
        Class<? extends AbstractFormItemComponentConfig> configClass = configClassMap.get(typeName.toUpperCase());
        if (configClass == null) {
            throw new RuntimeException("表单项组件配置类型[" + typeName + "]不存在！");
        }

        return configClass;
    }

}
