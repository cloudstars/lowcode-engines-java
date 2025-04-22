package io.github.cloudstars.lowcode.commons.predicate.type;

import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 表达式断言配置
 *
 * @author clouds
 */
@PredicateConfigClass(name = "EXPRESSION")
public class ExpressionPredicateConfig extends AbstractPredicateConfig {

    /**
     * 表达式
     */
    private String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public ExpressionPredicateConfig() {
    }

    public ExpressionPredicateConfig(JsonObject configJson) {
        super(configJson);

        this.expression = (String) configJson.get(XConfig.ATTR_EXPRESSION);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(XConfig.ATTR_EXPRESSION, this.expression);

        return configJson;
    }

}
