package io.github.cloudstars.lowcode.commons.predicate;

import io.github.cloudstars.lowcode.predicate.type.XPredicateConfig;

/**
 * 断言
 *
 * @author clouds
 */
public interface XPredicate<T extends XPredicateConfig> {

    /**
     * 获取断言的配置
     *
     * @return 断言配置
     */
    T getConfig();

    /**
     * 是否为真
     *
     * @return
     */
    boolean test();

}
