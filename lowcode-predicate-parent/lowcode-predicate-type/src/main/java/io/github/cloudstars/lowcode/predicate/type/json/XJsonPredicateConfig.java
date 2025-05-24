package io.github.cloudstars.lowcode.predicate.type.json;

import io.github.cloudstars.lowcode.commons.config.XConfig;

/**
 * JSON断言接口
 *
 * @author clouds
 */
public interface XJsonPredicateConfig extends XConfig {

    // 操作符的属性名称
    String ATTR_OPERATOR = "operator";

    // 逻辑断言类型
    String TYPE_LOGIC = "LOGIC";

    // 二元操作断言类型
    String TYPE_BINARY = "BINARY";

}
