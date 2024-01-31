package net.cf.form.engine.repository.oql.ast.expr.literal;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;
import net.cf.form.engine.repository.oql.ast.expr.QqlValuableExpr;

@Deprecated
public final class OqlUndefinedExpr extends OqlExprImpl implements QqlLiteralExpr, QqlValuableExpr {

    public OqlUndefinedExpr() {
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public Object getValue() {
        return "";
    }

    @Override
    public OqlUndefinedExpr clone() {
        return new OqlUndefinedExpr();
    }
}

