package net.cf.form.engine.repository.oql.ast.expr.literal;

import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;

/**
 * 字面量表达式
 *
 * @author clouds
 *
 */
@Deprecated
public interface QqlLiteralExpr extends QqlExpr {

    QqlLiteralExpr clone();
}
