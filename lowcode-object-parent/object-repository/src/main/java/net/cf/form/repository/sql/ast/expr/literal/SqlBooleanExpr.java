package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

public final class SqlBooleanExpr extends AbstractSqlExprImpl implements SqlLiteralExpr, SqlValuableExpr {

    private boolean value;

    public SqlBooleanExpr() {
    }

    public SqlBooleanExpr(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return this.value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public SqlBooleanExpr cloneMe() {
        return new SqlBooleanExpr(this.value);
    }

}
