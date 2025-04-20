package io.github.cloudstars.lowcode.commons.data.datasource;

import io.github.cloudstars.lowcode.commons.data.predicate.AbstractPredicateConfig;

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
    private AbstractPredicateConfig expression;

    public AbstractPredicateConfig getExpression() {
        return expression;
    }

    public void setExpression(AbstractPredicateConfig expression) {
        this.expression = expression;
    }
}
