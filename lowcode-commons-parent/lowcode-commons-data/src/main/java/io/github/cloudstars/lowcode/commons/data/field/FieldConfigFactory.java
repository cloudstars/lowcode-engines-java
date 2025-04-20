package io.github.cloudstars.lowcode.commons.data.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 字段配置工厂
 *
 * @author clouds
 */
public class FieldConfigFactory {

    private FieldConfigFactory() {
    }

    /**
     * 根据字段的Json配置实例化一个字段配置
     *
     * @param fieldConfig
     * @return
     */
    public static AbstractFieldConfig newInstance(JsonObject fieldConfig) {
        Object typeValue = fieldConfig.get("type");
        if (typeValue == null) {
            throw new RuntimeException("字段类型[type]不存在！");
        }

        String type = typeValue.toString();
        Class<? extends AbstractFieldConfig> fieldConfigClass = FieldConfigClassFactory.get(type);
        try {
            Constructor<? extends AbstractFieldConfig> constructor = fieldConfigClass.getConstructor(JsonObject.class);
            return constructor.newInstance(fieldConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化字段配置，类型为[" + type + "]出错！", e);
        }
    }

}
