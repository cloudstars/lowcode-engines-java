package io.github.cloudstars.lowcode.commons.data.defaultvalue;

import io.github.cloudstars.lowcode.commons.data.predicate.PredicateConfigParser;
import io.github.cloudstars.lowcode.commons.data.predicate.XPredicateConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 表达式默认值配置
 *
 * @author clouds 
 */
@DefaultValueConfigClass(name = "EXPRESSION")
public class ExpressionDefaultValueConfig extends AbstractDefaultValueConfig {

    /**
     * 表达式
     */
    private XPredicateConfig expression;

    /**
     * 表达式解析器
     */
    private PredicateConfigParser exprParser = new PredicateConfigParser();

    public XPredicateConfig getExpression() {
        return expression;
    }

    public void setExpression(XPredicateConfig expression) {
        this.expression = expression;
    }

    public ExpressionDefaultValueConfig() {
    }

    public ExpressionDefaultValueConfig(JsonObject configJson) {
        super(configJson);

        JsonObject exprConfigJson = (JsonObject) configJson.get(XPredicateConfig.ATTR);
        this.expression = this.exprParser.fromJson(exprConfigJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(XPredicateConfig.ATTR, this.expression.toJson());
        return configJson;
    }
}
