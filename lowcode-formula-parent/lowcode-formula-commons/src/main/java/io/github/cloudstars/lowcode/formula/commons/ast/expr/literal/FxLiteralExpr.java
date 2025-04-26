package io.github.cloudstars.lowcode.formula.commons.ast.expr.literal;


import io.github.cloudstars.lowcode.formula.commons.ast.expr.FxExpr;

/**
 * 字面量表达式
 *
 * @author clouds
 *
 */
public interface FxLiteralExpr extends FxExpr {

    FxLiteralExpr clone();
}
