package io.github.cloudstars.lowcode.component.form;

import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 表单项组件配置工厂
 *
 * @author clouds
 */
public class FormItemComponentConfigFactory {

    private FormItemComponentConfigFactory() {
    }

    /**
     * 根据表单项组件的Json配置实例化一个表单项组件配置
     *
     * @param configJson
     * @return
     */
    public static AbstractFormItemComponentConfig newInstance(JsonObject configJson) {
        Object typeValue = configJson.get(XTypedConfig.ATTR);
        if (typeValue == null) {
            throw new RuntimeException("表单项组件类型[type]不存在！");
        }

        String type = typeValue.toString();
        Class<? extends AbstractFormItemComponentConfig> nodeConfigClass = FormItemComponentConfigClassFactory.get(type);
        try {
            Constructor<? extends AbstractFormItemComponentConfig> constructor = nodeConfigClass.getConstructor(JsonObject.class);
            return constructor.newInstance(configJson);
        } catch (Exception e) {
            throw new RuntimeException("实例化表单项组件配置，类型为[" + type + "]出错！", e);
        }
    }

}
