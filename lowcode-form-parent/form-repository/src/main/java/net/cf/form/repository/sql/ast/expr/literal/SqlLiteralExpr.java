package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.ast.expr.SqlExpr;

/**
 * 字面量表达式
 *
 * @author clouds
 *
 */
public interface SqlLiteralExpr extends SqlExpr {

    SqlLiteralExpr _clone();
}
