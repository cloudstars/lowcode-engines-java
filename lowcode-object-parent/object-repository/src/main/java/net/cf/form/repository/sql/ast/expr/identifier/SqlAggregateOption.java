package net.cf.form.repository.sql.ast.expr.identifier;

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
