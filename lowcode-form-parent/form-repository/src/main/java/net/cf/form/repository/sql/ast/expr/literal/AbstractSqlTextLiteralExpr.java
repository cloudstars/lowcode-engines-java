package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;

/**
 * 文本型的字面量表达式
 *
 * @author clouds
 */
public abstract class AbstractSqlTextLiteralExpr extends AbstractSqlExprImpl implements SqlLiteralExpr {

    protected String text;

    public AbstractSqlTextLiteralExpr(){
    }

    public AbstractSqlTextLiteralExpr(String text){
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public abstract AbstractSqlTextLiteralExpr _clone();
}
