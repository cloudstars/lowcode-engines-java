package net.cf.object.fx.parser.ast.expr.literal;

import net.cf.object.fx.parser.ast.expr.FxExpr;

/**
 * 字面量表达式
 *
 * @author clouds
 *
 */
public interface FxLiteralExpr extends FxExpr {

    FxLiteralExpr clone();
}
