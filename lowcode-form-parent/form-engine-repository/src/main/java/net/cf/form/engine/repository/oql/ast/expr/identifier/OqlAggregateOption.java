package net.cf.form.engine.repository.oql.ast.expr.identifier;

/**
 * 聚合函数的属性
 *
 * @author clouds
 */
@Deprecated
public enum OqlAggregateOption {

    DISTINCT,
    ALL,
    UNIQUE,
    DEDUPLICATION;

    OqlAggregateOption() {
    }
}
