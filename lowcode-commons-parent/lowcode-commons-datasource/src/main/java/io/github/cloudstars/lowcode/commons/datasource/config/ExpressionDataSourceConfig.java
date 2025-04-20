package io.github.cloudstars.lowcode.commons.datasource.config;

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

}
