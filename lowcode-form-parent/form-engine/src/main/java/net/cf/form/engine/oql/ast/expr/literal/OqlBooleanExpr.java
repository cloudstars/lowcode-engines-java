package net.cf.form.engine.oql.ast.expr.literal;

import net.cf.form.engine.oql.ast.expr.AbstractOqlExprImpl;
import net.cf.form.engine.oql.ast.expr.OqlValuableExpr;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

public final class OqlBooleanExpr extends AbstractOqlExprImpl implements OqlLiteralExpr, OqlValuableExpr {

    private boolean value;

    public OqlBooleanExpr() {
    }

    public OqlBooleanExpr(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public OqlBooleanExpr clone() {
        return new OqlBooleanExpr(this.value);
    }

}
