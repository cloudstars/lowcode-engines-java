package net.cf.form.engine.repository.sql.ast.expr.literal;

import net.cf.form.engine.repository.sql.visitor.SqlAstVisitor;
import net.cf.form.engine.repository.sql.ast.expr.SqlValuableExpr;

/**
 * 字符串表达式
 *
 * @author clouds
 */
public class SqlCharExpr extends SqlTextLiteralExpr implements SqlValuableExpr {

    public SqlCharExpr(String text) {
        super(text);
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public String getValue() {
        return this.text;
    }

    @Override
    public SqlCharExpr _clone() {
        return new SqlCharExpr(this.text);
    }
}
