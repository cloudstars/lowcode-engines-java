package io.github.cloudstars.lowcode.formula.commons.ast.expr.literal;


import io.github.cloudstars.lowcode.formula.commons.ast.expr.AbstractFxExprImpl;
import io.github.cloudstars.lowcode.formula.commons.ast.expr.FxValuableExpr;
import io.github.cloudstars.lowcode.formula.commons.visitor.FxAstVisitor;

public final class FxNullExpr extends AbstractFxExprImpl implements FxLiteralExpr, FxValuableExpr {

    public FxNullExpr() {
    }

    @Override
    public void accept(FxAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public FxNullExpr clone() {
        return new FxNullExpr();
    }
}

