package net.cf.form.engine.oql.ast.expr.operation;

import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.literal.OqlJsonArrayExpr;

public class OqlArrayContainsOpExpr extends OqlBinaryOpExpr {

    private OqlArrayContainsOption option = OqlArrayContainsOption.ALL;

    public OqlArrayContainsOpExpr() {
    }

    public OqlArrayContainsOpExpr(OqlExpr expr, OqlJsonArrayExpr values) {
        super(expr, OqlBinaryOperator.Contains, values);
    }

    public OqlJsonArrayExpr getValues() {
        return (OqlJsonArrayExpr) this.getRight();
    }

    public void setValues(OqlJsonArrayExpr values) {
        this.setRight(values);
    }

    public OqlArrayContainsOption getOption() {
        return option;
    }

    public void setOption(OqlArrayContainsOption option) {
        this.option = option;
    }

    @Override
    public OqlArrayContainsOpExpr clone() {
        OqlArrayContainsOpExpr x = new OqlArrayContainsOpExpr();
        cloneTarget(x);
        return x;
    }
}

