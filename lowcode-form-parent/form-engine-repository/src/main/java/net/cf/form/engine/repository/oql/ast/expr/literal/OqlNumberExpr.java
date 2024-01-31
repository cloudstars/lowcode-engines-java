package net.cf.form.engine.repository.oql.ast.expr.literal;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.QqlValuableExpr;

/**
 * 数字类表达式
 *
 * @author clouds
 */
@Deprecated
public class OqlNumberExpr extends OqlNumericLiteralExpr implements QqlValuableExpr {

    public OqlNumberExpr() {
    }

    public OqlNumberExpr(Number number) {
        super(number);
    }

    @Override
    public Number getValue() {
        return this.getNumber();
    }

    @Override
    public Number getNumber() {
        return this.number;
    }

    @Override
    public void setNumber(Number number) {
        super.setNumber(number);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public OqlNumberExpr clone() {
        OqlNumberExpr x = new OqlNumberExpr();
        x.number = this.number;
        return x;
    }

}
