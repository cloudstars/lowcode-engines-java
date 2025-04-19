package io.github.cloudstars.lowcode.commons.data.expression.json;

import io.github.cloudstars.lowcode.commons.data.expression.XExpressionConfig;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;

import java.util.List;

/**
 * Json逻辑表达式
 *
 * @author clouds
 */
public class LogicJsonExpression extends AbstractConfig implements JsonExpression {

    /**
     * 逻辑操作符
     */
    private LogicOperatorEnum operator;

    /**
     * 通过操作符加接的表达式
     */
    private List<JsonExpression> expressions;

    public LogicJsonExpression() {
    }

    public LogicJsonExpression(LogicOperatorEnum operator, List<JsonExpression> expressions) {
        this.operator = operator;
        this.expressions = expressions;
    }

    public LogicJsonExpression(JsonObject configJson) {
        String operatorString = (String) configJson.get(ATTR_OPERATOR);
        this.operator = LogicOperatorEnum.valueOf(operatorString);

    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_TYPE, JsonExpression.TYPE_LOGIC);
        configJson.put(ATTR_OPERATOR, this.operator);
        configJson.put(XExpressionConfig.ATTR_EXPRESSION, JsonUtils.parseArray(this.expressions));
        return configJson;
    }

}
