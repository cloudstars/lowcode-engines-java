
package io.github.cloudstars.lowcode.dynamic.value;

import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 表达式值配置
 *
 * @author clouds 
 */
@DynamicValueConfigClass(name = "EXPRESSION")
public class ExpressionDynamicValueConfig extends AbstractDynamicValueConfig {

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

    public ExpressionDynamicValueConfig() {
    }

    public ExpressionDynamicValueConfig(JsonObject configJson) {
        super(configJson);

        this.expression = (String) configJson.get(GlobalAttrNames.ATTR_EXPRESSION);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put(GlobalAttrNames.ATTR_EXPRESSION, this.expression);

        return configJson;
    }
}
