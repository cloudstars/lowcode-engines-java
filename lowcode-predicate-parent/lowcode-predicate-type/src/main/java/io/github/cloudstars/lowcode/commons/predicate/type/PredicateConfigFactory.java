package io.github.cloudstars.lowcode.commons.predicate.type;

import io.github.cloudstars.lowcode.commons.config.XTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.lang.reflect.Constructor;

/**
 * 断言配置工厂
 *
 * @author clouds
 */
public class PredicateConfigFactory {

    private PredicateConfigFactory() {
    }

    /**
     * 根据断言的Json配置实例化一个断言配置
     *
     * @param dataTypeConfig
     * @return
     */
    public static XPredicateConfig newInstance(JsonObject dataTypeConfig) {
        Object typeValue = dataTypeConfig.get(XTypedConfig.ATTR);
        if (typeValue == null) {
            throw new RuntimeException("断言配置类型[type]不存在！");
        }

        Class<? extends XPredicateConfig> configClass = PredicateConfigClassFactory.get(typeValue.toString());
        try {
            Constructor<? extends XPredicateConfig> constructor = configClass.getConstructor(JsonObject.class);
            return constructor.newInstance(dataTypeConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化断言配置类型[" + typeValue + "]出错！", e);
        }
    }

}
