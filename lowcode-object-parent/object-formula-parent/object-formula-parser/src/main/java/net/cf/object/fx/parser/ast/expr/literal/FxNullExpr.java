package net.cf.object.fx.parser.ast.expr.literal;


import net.cf.object.fx.parser.ast.expr.AbstractFxExprImpl;
import net.cf.object.fx.parser.ast.expr.FxValuableExpr;
import net.cf.object.fx.parser.ast.visitor.FxAstVisitor;

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

