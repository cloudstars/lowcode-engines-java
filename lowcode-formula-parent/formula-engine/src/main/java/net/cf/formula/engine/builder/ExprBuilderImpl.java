package net.cf.formula.engine.builder;

import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOpExpr;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOperator;

/**
 * 表达式构建器实现
 *
 * 目前看这个类的作用不大，不如直接new
 *
 * @author clouds
 */
public class ExprBuilderImpl implements ExprBuilder {

    private FxExpr expr;

    @Override
    public ExprBuilderImpl or(FxExpr expr) {
        if (this.expr == null) {
            this.expr = expr;
        } else {
            this.expr = new FxBinaryOpExpr(this.expr, FxBinaryOperator.OR, expr);
        }

        return this;
    }

    @Override
    public ExprBuilderImpl and(FxExpr expr) {
        if (this.expr == null) {
            this.expr = expr;
        } else {
            this.expr = new FxBinaryOpExpr(this.expr, FxBinaryOperator.AND, expr);
        }

        return this;
    }

    @Override
    public FxExpr builer() {
        return this.expr;
    }
}
