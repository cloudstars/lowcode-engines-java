package io.github.cloudstars.lowcode.commons.data.expression.json;

import io.github.cloudstars.lowcode.commons.lang.config.XConfig;

/**
 * JSON表达式接口
 *
 * @author clouds
 */
public interface JsonExpression extends XConfig {

    // 操作符的属性名称
    String ATTR_OPERATOR = "operator";

    // 逻辑表达式类型
    String TYPE_LOGIC = "LOGIC";

    // 二元操作表达式类型
    String TYPE_BINARY = "BINARY";

}
