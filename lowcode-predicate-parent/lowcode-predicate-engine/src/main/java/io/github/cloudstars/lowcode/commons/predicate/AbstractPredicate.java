package io.github.cloudstars.lowcode.commons.predicate;

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

}
