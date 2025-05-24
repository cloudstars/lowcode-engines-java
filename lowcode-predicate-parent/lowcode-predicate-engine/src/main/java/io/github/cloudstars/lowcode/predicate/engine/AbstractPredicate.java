package io.github.cloudstars.lowcode.predicate.engine;

import io.github.cloudstars.lowcode.predicate.type.XPredicateConfig;

/**
 * 抽像的断言
 *
 * @author clouds
 * @param <T> 断言配置类型
 */
public abstract class AbstractPredicate<T extends XPredicateConfig> implements XPredicate<T> {

    /**
     * 断言配置
     */
    private T config;

    public AbstractPredicate(T config) {
        this.config = config;
    }

    @Override
    public T getConfig() {
        return this.config;
    }

    protected boolean parseResult(Object result) {
        if (result == null) {
            return false;
        }

        if (result instanceof Boolean) {
            return (Boolean) result;
        }

        if (result instanceof String) {
            return "true".equalsIgnoreCase(result.toString());
        }

        if (result instanceof Integer) {
            return (Integer) result != 0;
        }

        return false;
    }
}
