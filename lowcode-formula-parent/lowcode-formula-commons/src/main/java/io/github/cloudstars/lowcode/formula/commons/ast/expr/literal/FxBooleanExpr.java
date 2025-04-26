package io.github.cloudstars.lowcode.formula.commons.ast.expr.literal;

import io.github.cloudstars.lowcode.formula.commons.ast.expr.AbstractFxExprImpl;
import io.github.cloudstars.lowcode.formula.commons.ast.expr.FxExpr;
import io.github.cloudstars.lowcode.formula.commons.ast.expr.FxValuableExpr;
import io.github.cloudstars.lowcode.formula.commons.visitor.FxAstVisitor;

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
