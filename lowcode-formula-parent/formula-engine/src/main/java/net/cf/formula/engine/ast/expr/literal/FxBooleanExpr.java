package net.cf.formula.engine.ast.expr.literal;

import net.cf.formula.engine.ast.expr.AbstractFxExprImpl;
import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.FxValuableExpr;
import net.cf.formula.engine.visitor.FxAstVisitor;

public final class FxBooleanExpr extends AbstractFxExprImpl implements FxExpr, FxLiteralExpr, FxValuableExpr {

    private boolean value;

    public FxBooleanExpr() {
    }

    public FxBooleanExpr(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    protected void accept0(FxAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public FxBooleanExpr clone() {
        return new FxBooleanExpr(this.value);
    }

}
