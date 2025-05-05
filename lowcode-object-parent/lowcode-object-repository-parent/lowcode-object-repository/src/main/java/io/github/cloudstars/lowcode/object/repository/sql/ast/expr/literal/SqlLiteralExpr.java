package io.github.cloudstars.lowcode.object.repository.sql.ast.expr.literal;

import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.SqlExpr;

/**
 * 字面量表达式
 *
 * @author clouds
 *
 */
public interface SqlLiteralExpr extends SqlExpr {

    @Override
    SqlLiteralExpr cloneMe();
}
