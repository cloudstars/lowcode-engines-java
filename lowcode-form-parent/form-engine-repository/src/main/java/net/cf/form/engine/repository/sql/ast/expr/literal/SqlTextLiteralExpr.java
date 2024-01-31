package net.cf.form.engine.repository.sql.ast.expr.literal;

import net.cf.form.engine.repository.sql.ast.expr.SqlExprImpl;

/**
 * 文本型的字面量表达式
 *
 * @author clouds
 */
public abstract class SqlTextLiteralExpr extends SqlExprImpl implements SqlLiteralExpr {

    protected String text;

    public SqlTextLiteralExpr(){
    }

    public SqlTextLiteralExpr(String text){
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public abstract SqlTextLiteralExpr _clone();
}
