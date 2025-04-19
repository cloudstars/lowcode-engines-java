package io.github.cloudstars.lowcode.commons.data.expression;

import io.github.cloudstars.lowcode.commons.data.defaultvalue.AbstractDefaultValueConfig;

/**
 * 文本表达式配置
 *
 * @author clouds
 */
@ExpressionConfigClass(name = "STATIC")
public class TextExpressionConfig extends AbstractDefaultValueConfig {

    /**
     * 静态文本
     */
    private String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
