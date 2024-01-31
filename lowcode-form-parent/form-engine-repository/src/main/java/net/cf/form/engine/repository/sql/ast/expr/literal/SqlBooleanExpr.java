package net.cf.form.engine.repository.sql.ast.expr.literal;

import net.cf.form.engine.repository.sql.visitor.SqlAstVisitor;
import net.cf.form.engine.repository.sql.ast.expr.SqlExprImpl;
import net.cf.form.engine.repository.sql.ast.expr.SqlValuableExpr;

public final class SqlBooleanExpr extends SqlExprImpl implements SqlLiteralExpr, SqlValuableExpr {

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
    protected void accept0(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public SqlBooleanExpr _clone() {
        return new SqlBooleanExpr(this.value);
    }

}
