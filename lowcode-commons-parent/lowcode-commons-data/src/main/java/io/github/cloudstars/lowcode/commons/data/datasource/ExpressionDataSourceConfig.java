package io.github.cloudstars.lowcode.commons.data.datasource;

import io.github.cloudstars.lowcode.commons.data.predicate.AbstractExpressionConfig;

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
    private AbstractExpressionConfig expression;

    public AbstractExpressionConfig getExpression() {
        return expression;
    }

    public void setExpression(AbstractExpressionConfig expression) {
        this.expression = expression;
    }
}
