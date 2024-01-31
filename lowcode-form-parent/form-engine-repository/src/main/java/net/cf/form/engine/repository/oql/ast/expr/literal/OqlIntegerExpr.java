package net.cf.form.engine.repository.oql.ast.expr.literal;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.QqlValuableExpr;

@Deprecated
public class OqlIntegerExpr extends OqlNumericLiteralExpr implements QqlValuableExpr {

    public OqlIntegerExpr(String value) {
        super(Integer.parseInt(value));
    }

    public OqlIntegerExpr(int value) {
        super(value);
    }

    public OqlIntegerExpr() {
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public Integer getValue() {
        return this.number.intValue();
    }

    @Override
    public OqlIntegerExpr clone() {
        return new OqlIntegerExpr(this.number.intValue());
    }
}
