package net.cf.form.engine.repository.oql.ast.expr.operation;

import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;

@Deprecated
public class OqlInOpExpr extends OqlBinaryOpExpr {

    public OqlInOpExpr() {
    }

    public OqlInOpExpr(QqlExpr expr, OqlListExpr values) {
        super(expr, OqlBinaryOperator.In, values);
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

