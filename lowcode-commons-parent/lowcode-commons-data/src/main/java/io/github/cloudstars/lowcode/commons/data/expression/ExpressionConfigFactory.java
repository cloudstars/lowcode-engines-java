package io.github.cloudstars.lowcode.commons.data.expression;

import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 表达式配置工厂
 *
 * @author clouds
 */
public class ExpressionConfigFactory {

    private ExpressionConfigFactory() {
    }

    /**
     * 根据表达式的Json配置实例化一个表达式配置
     *
     * @param dataTypeConfig
     * @return
     */
    public static XExpressionConfig newInstance(JsonObject dataTypeConfig) {
        Object typeValue = dataTypeConfig.get(XConfig.ATTR_TYPE);
        if (typeValue == null) {
            throw new RuntimeException("表达式配置类型[type]不存在！");
        }

        Class<? extends XExpressionConfig> configClass = ExpressionConfigClassFactory.get(typeValue.toString());
        try {
            Constructor<? extends XExpressionConfig> constructor = configClass.getConstructor(JsonObject.class);
            return constructor.newInstance(dataTypeConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化表达式配置类型[" + typeValue + "]出错！", e);
        }
    }

}
