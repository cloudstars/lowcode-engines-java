package io.github.cloudstars.lowcode.commons.data.expression;

import io.github.cloudstars.lowcode.commons.data.defaultvalue.AbstractDefaultValueConfig;
import io.github.cloudstars.lowcode.commons.data.expression.ExpressionConfigClass;
import io.github.cloudstars.lowcode.commons.data.expression.json.JsonExpression;

/**
 * JSON表达式配置
 *
 * @author clouds
 */
@ExpressionConfigClass(name = "STATIC")
public class JsonExpressionConfig extends AbstractDefaultValueConfig {

    /**
     * JSON表达式
     */
    private JsonExpression expression;

    public JsonExpression getExpression() {
        return expression;
    }

    public void setExpression(JsonExpression expression) {
        this.expression = expression;
    }

}
