package net.cf.form.engine.repository.sql.ast.expr.identifier;

/**
 * 聚合函数的属性
 *
 * @author clouds
 */
public enum SqlAggregateOption {

    DISTINCT,
    ALL,
    UNIQUE,
    DEDUPLICATION;

    SqlAggregateOption() {
    }
}
