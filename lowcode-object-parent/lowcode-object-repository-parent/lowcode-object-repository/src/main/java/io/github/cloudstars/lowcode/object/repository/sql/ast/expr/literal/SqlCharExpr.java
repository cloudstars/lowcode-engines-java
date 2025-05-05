package io.github.cloudstars.lowcode.object.repository.sql.ast.expr.literal;

import io.github.cloudstars.lowcode.object.repository.sql.visitor.SqlAstVisitor;

/**
 * 字符串表达式
 *
 * @author clouds
 */
public class SqlCharExpr extends AbstractSqlTextLiteralExpr implements SqlValuableExpr {

    public SqlCharExpr(String text) {
        super(text);
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public String getValue() {
        return this.text;
    }

    @Override
    public SqlCharExpr cloneMe() {
        return new SqlCharExpr(this.text);
    }
}
