package io.github.cloudstars.lowcode.commons.data.defaultvalue;

import io.github.cloudstars.lowcode.commons.data.expression.ExpressionConfigParser;
import io.github.cloudstars.lowcode.commons.data.expression.XExpressionConfig;
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
    private XExpressionConfig expression;

    /**
     * 表达式解析器
     */
    private ExpressionConfigParser exprParser = new ExpressionConfigParser();

    public XExpressionConfig getExpression() {
        return expression;
    }

    public void setExpression(XExpressionConfig expression) {
        this.expression = expression;
    }

    public ExpressionDefaultValueConfig() {
    }

    public ExpressionDefaultValueConfig(JsonObject configJson) {
        super(configJson);

        JsonObject exprConfigJson = (JsonObject) configJson.get(XExpressionConfig.ATTR_EXPRESSION);
        this.expression = this.exprParser.fromJson(exprConfigJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(XExpressionConfig.ATTR_EXPRESSION, this.expression.toJson());
        return configJson;
    }
}
