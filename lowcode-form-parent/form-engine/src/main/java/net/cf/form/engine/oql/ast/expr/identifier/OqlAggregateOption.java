package net.cf.form.engine.oql.ast.expr.identifier;

/**
 * 聚合函数的属性
 *
 * @author clouds
 */
public enum OqlAggregateOption {

    DISTINCT,
    ALL,
    UNIQUE,
    DEDUPLICATION;

    OqlAggregateOption() {
    }
}
