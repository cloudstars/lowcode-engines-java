package net.cf.object.fx.parser.ast.expr.literal;


import net.cf.object.fx.parser.ast.expr.AbstractFxExprImpl;
import net.cf.object.fx.parser.ast.expr.FxExpr;
import net.cf.object.fx.parser.ast.expr.FxValuableExpr;
import net.cf.object.fx.parser.ast.visitor.FxAstVisitor;

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

    public void accept(FxAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public FxBooleanExpr clone() {
        return new FxBooleanExpr(this.value);
    }

}
