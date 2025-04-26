package io.github.cloudstars.lowcode.formula.commons.ast.expr.literal;


import io.github.cloudstars.lowcode.formula.commons.ast.expr.AbstractFxExprImpl;

/**
 * 文本型的字面量表达式
 *
 * @author clouds
 */
public abstract class AbstractFxTextLiteralExpr extends AbstractFxExprImpl implements FxLiteralExpr {

    protected String text;

    public AbstractFxTextLiteralExpr(){
    }

    public AbstractFxTextLiteralExpr(String text){
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public abstract AbstractFxTextLiteralExpr clone();
}
