package net.cf.formula.engine.ast.expr.literal;

import net.cf.formula.engine.ast.expr.AbstractFxExprImpl;
import net.cf.formula.engine.ast.expr.FxValuableExpr;
import net.cf.formula.engine.visitor.FxAstVisitor;

public final class FxNullExpr extends AbstractFxExprImpl implements FxLiteralExpr, FxValuableExpr {

    public FxNullExpr() {
    }

    @Override
    protected void accept0(FxAstVisitor visitor) {
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

