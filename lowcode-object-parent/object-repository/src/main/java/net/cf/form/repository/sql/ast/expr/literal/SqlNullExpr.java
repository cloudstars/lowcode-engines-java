package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

public final class SqlNullExpr extends AbstractSqlExprImpl implements SqlLiteralExpr, SqlValuableExpr {

    public SqlNullExpr() {
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public SqlNullExpr cloneMe() {
        return new SqlNullExpr();
    }
}

