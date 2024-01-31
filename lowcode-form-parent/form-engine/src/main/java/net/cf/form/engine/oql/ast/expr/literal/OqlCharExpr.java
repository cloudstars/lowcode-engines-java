package net.cf.form.engine.oql.ast.expr.literal;

import net.cf.form.engine.oql.ast.expr.OqlValuableExpr;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

/**
 * 字符串表达式
 *
 * @author clouds
 */
public class OqlCharExpr extends OqlTextLiteralExpr implements OqlValuableExpr {

    public OqlCharExpr(String text) {
        super(text);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public String getValue() {
        return this.text;
    }

    @Override
    public OqlCharExpr clone() {
        return new OqlCharExpr(this.text);
    }
}
