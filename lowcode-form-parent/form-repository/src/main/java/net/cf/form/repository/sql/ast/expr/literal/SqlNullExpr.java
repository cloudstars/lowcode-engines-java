package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;
import net.cf.form.repository.sql.ast.expr.SqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlValuableExpr;

public final class SqlNullExpr extends SqlExprImpl implements SqlLiteralExpr, SqlValuableExpr {

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

