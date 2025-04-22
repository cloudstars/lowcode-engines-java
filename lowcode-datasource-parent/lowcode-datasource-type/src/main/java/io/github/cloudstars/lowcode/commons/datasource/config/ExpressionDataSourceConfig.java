package io.github.cloudstars.lowcode.commons.datasource.config;

import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 表达式数据源配置
 *
 * @author clouds
 */
@DataSourceConfigClass(name = "EXPRESSION")
public class ExpressionDataSourceConfig extends AbstractDataSourceConfig {

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

    public ExpressionDataSourceConfig() {
    }

    public ExpressionDataSourceConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = new JsonObject();
        configJson.put(XConfig.ATTR_EXPRESSION, this.expression);

        return super.toJson();
    }
}
