package net.cf.form.engine.repository.oql.ast.expr.literal;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;
import net.cf.form.engine.repository.oql.ast.expr.QqlValuableExpr;

@Deprecated
public final class OqlBooleanExpr extends OqlExprImpl implements QqlLiteralExpr, QqlValuableExpr {

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
