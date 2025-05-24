package io.github.cloudstars.lowcode.predicate.engine;

import io.github.cloudstars.lowcode.predicate.type.XPredicateConfig;

import java.lang.reflect.Constructor;

/**
 * 断言工厂
 *
 * @author clouds
 */
public class PredicateFactory {

    /**
     * 根据断言配置实例化一个断言实现类
     *
     * @param predicateConfig 断言配置
     * @return 断言
     */
    public static XPredicate newInstance(XPredicateConfig predicateConfig) {
        Class<? extends XPredicateConfig> predicateConfigClass = predicateConfig.getClass();
        Class<? extends XPredicate> predicateClass = PredicateClassFactory.get(predicateConfigClass);
        try {
            Constructor<? extends XPredicate> constructor = predicateClass.getConstructor(predicateConfigClass);
            return constructor.newInstance(predicateConfig);
        } catch (Exception e) {
            throw new RuntimeException("实例化断言[" + predicateClass.getName() + "]出错！", e);
        }
    }

}
