package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlValuableExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

public final class SqlNullExpr extends AbstractSqlExprImpl implements SqlLiteralExpr, SqlValuableExpr {

    public SqlNullExpr() {
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public SqlNullExpr _clone() {
        return new SqlNullExpr();
    }
}

