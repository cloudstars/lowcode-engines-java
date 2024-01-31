package net.cf.form.engine.repository.oql.ast.expr.literal;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;
import net.cf.form.engine.repository.oql.ast.expr.QqlValuableExpr;

@Deprecated
public final class OqlNullExpr extends OqlExprImpl implements QqlLiteralExpr, QqlValuableExpr {

    public OqlNullExpr() {
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public OqlNullExpr clone() {
        return new OqlNullExpr();
    }
}

