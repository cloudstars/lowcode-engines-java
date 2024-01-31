package net.cf.form.engine.oql.ast.expr.literal;

import net.cf.form.engine.oql.ast.expr.OqlExpr;

/**
 * 字面量表达式
 *
 * @author clouds
 *
 */
public interface OqlLiteralExpr extends OqlExpr {

    OqlLiteralExpr clone();
}
