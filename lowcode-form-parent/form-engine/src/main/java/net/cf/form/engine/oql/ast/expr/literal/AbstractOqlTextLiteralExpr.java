package net.cf.form.engine.oql.ast.expr.literal;

import net.cf.form.engine.oql.ast.expr.AbstractOqlExprImpl;

/**
 * 文本型的字面量表达式
 *
 * @author clouds
 */
public abstract class AbstractOqlTextLiteralExpr extends AbstractOqlExprImpl implements OqlLiteralExpr {

    protected String text;

    public AbstractOqlTextLiteralExpr(){
    }

    public AbstractOqlTextLiteralExpr(String text){
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public abstract AbstractOqlTextLiteralExpr clone();
}
