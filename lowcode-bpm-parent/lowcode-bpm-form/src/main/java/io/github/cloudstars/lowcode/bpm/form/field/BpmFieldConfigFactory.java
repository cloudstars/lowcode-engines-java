package io.github.cloudstars.lowcode.bpm.form.field;

import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 字段配置工厂
 *
 * @author clouds
 */
public class BpmFieldConfigFactory {

    private BpmFieldConfigFactory() {
    }

    /**
     * 根据字段的Json配置实例化一个字段配置
     *
     * @param fieldConfig
     * @return
     */
    public static AbstractBpmFieldConfig newInstance(JsonObject fieldConfig) {
        Object typeValue = fieldConfig.get(XTypedConfig.ATTR);
        if (typeValue == null) {
            throw new RuntimeException("BPM字段类型[type]不存在！");
        }

        String type = typeValue.toString();
        Class<? extends AbstractBpmFieldConfig> fieldConfigClass = BpmFieldConfigClassFactory.get(type);
        try {
            Constructor<? extends AbstractBpmFieldConfig> constructor = fieldConfigClass.getConstructor(JsonObject.class);
            return constructor.newInstance(fieldConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化BPM字段配置，类型为[" + type + "]出错！", e);
        }
    }

}
