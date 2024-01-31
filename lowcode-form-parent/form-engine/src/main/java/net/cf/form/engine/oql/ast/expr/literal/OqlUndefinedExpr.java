package net.cf.form.engine.oql.ast.expr.literal;

import net.cf.form.engine.oql.ast.expr.OqlExprImpl;
import net.cf.form.engine.oql.ast.expr.OqlValuableExpr;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

public final class OqlUndefinedExpr extends OqlExprImpl implements OqlLiteralExpr, OqlValuableExpr {

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

