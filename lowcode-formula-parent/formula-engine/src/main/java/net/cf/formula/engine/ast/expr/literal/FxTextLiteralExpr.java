package net.cf.formula.engine.ast.expr.literal;

import net.cf.formula.engine.ast.expr.FxExprImpl;

/**
 * 文本型的字面量表达式
 *
 * @author clouds
 */
public abstract class FxTextLiteralExpr extends FxExprImpl implements FxLiteralExpr {

    protected String text;

    public FxTextLiteralExpr(){
    }

    public FxTextLiteralExpr(String text){
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public abstract FxTextLiteralExpr clone();
}
