package net.cf.form.repository.sql.ast.expr.operation;

/**
 * 数组包含的属性
 *
 * @author clouds
 */
public enum SqlArrayContainsOption {
    CONTAINS,
    CONTAINS_ANY,
    CONTAINS_ALL;

    SqlArrayContainsOption() {
    }
}
