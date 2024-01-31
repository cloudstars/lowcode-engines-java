package net.cf.form.engine.repository.oql.ast.expr.literal;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.QqlValuableExpr;

/**
 * 字符串表达式
 *
 * @author clouds
 */
@Deprecated
public class OqlCharExpr extends OqlTextLiteralExpr implements QqlValuableExpr {

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
