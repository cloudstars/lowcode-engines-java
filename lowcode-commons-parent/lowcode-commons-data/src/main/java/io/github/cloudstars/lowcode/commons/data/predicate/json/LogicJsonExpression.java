package io.github.cloudstars.lowcode.commons.data.predicate.json;

import io.github.cloudstars.lowcode.commons.data.predicate.XExpressionConfig;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * Json逻辑表达式
 *
 * @author clouds
 */
public class LogicJsonExpression extends AbstractTypedConfig implements JsonExpression {

    /**
     * 逻辑操作符
     */
    private LogicOperatorEnum operator;

    /**
     * 通过操作符加接的表达式
     */
    private List<JsonExpression> expressions;

    public LogicJsonExpression() {
        this.setType(JsonExpression.TYPE_LOGIC);
    }

    public LogicJsonExpression(LogicOperatorEnum operator, List<JsonExpression> expressions) {
        this();

        this.operator = operator;
        this.expressions = expressions;
    }

    public LogicJsonExpression(JsonObject configJson) {
        super(configJson);

        String operatorString = (String) configJson.get(ATTR_OPERATOR);
        this.operator = LogicOperatorEnum.valueOf(operatorString);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(ATTR_OPERATOR, this.operator);
        configJson.put(XExpressionConfig.ATTR, JsonConfigUtils.toJsonArray(this.expressions));
        return configJson;
    }

}
