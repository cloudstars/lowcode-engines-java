package io.github.cloudstars.lowcode.object.repository.sql.ast.expr.literal;

import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.AbstractSqlExprImpl;
import io.github.cloudstars.lowcode.object.repository.sql.visitor.SqlAstVisitor;

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

