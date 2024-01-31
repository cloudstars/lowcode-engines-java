package net.cf.form.engine.repository.oql.ast.expr.operation;

import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlJsonArrayExpr;

@Deprecated
public class OqlArrayContainsOpExpr extends OqlBinaryOpExpr {

    private OqlArrayContainsOption option = OqlArrayContainsOption.ALL;

    public OqlArrayContainsOpExpr() {
    }

    public OqlArrayContainsOpExpr(QqlExpr expr, OqlJsonArrayExpr values) {
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

