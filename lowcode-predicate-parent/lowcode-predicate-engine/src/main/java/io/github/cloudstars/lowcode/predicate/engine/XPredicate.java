package io.github.cloudstars.lowcode.predicate.engine;

import io.github.cloudstars.lowcode.predicate.type.XPredicateConfig;

import java.util.Map;

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
     * @param paramMap 参数
     * @return Boolean值
     */
    boolean test(Map<String, Object> paramMap);

}
