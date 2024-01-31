package net.cf.form.engine.repository.oql.ast.expr.literal;

import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;

/**
 * 文本型的字面量表达式
 *
 * @author clouds
 */
@Deprecated
public abstract class OqlTextLiteralExpr extends OqlExprImpl implements QqlLiteralExpr {

    protected String text;

    public OqlTextLiteralExpr(){
    }

    public OqlTextLiteralExpr(String text){
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public abstract OqlTextLiteralExpr clone();
}
