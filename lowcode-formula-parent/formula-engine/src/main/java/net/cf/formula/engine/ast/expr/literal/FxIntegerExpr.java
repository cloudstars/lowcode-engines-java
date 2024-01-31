package net.cf.formula.engine.ast.expr.literal;

import net.cf.formula.engine.ast.expr.FxValuableExpr;
import net.cf.formula.engine.visitor.FxAstVisitor;

public class FxIntegerExpr extends FxNumericLiteralExpr implements FxValuableExpr {

    public FxIntegerExpr(String value) {
        super(Integer.parseInt(value));
    }

    public FxIntegerExpr(int value) {
        super(value);
    }

    public FxIntegerExpr() {
    }

    @Override
    protected void accept0(FxAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public Integer getValue() {
        return this.number.intValue();
    }

    @Override
    public FxIntegerExpr clone() {
        return new FxIntegerExpr(this.number.intValue());
    }
}
