package net.cf.formula.engine.ast.expr.literal;

import net.cf.formula.engine.ast.expr.FxExpr;

/**
 * 字面量表达式
 *
 * @author clouds
 *
 */
public interface FxLiteralExpr extends FxExpr {

    FxLiteralExpr clone();
}
