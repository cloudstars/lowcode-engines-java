package net.cf.form.engine.oql.ast.expr.operation;

import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.OqlListExpr;

public class OqlInOpExpr extends OqlBinaryOpExpr {

    public OqlInOpExpr() {
    }

    public OqlInOpExpr(OqlExpr expr, OqlListExpr values) {
        super(expr, OqlBinaryOperator.IN, values);
    }

    public OqlListExpr getValues() {
        return (OqlListExpr) this.getRight();
    }

    public void setValues(OqlListExpr values) {
        this.setRight(values);
    }

    @Override
    public OqlInOpExpr clone() {
        OqlInOpExpr x = new OqlInOpExpr();
        cloneTarget(x);
        return x;
    }
}

