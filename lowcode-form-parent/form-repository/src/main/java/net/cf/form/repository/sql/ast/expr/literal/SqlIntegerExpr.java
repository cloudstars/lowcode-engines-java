package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.visitor.SqlAstVisitor;
import net.cf.form.repository.sql.ast.expr.SqlValuableExpr;

public class SqlIntegerExpr extends SqlNumericLiteralExpr implements SqlValuableExpr {

    public SqlIntegerExpr(String value) {
        super(Integer.parseInt(value));
    }

    public SqlIntegerExpr(Number value) {
        super(value);
    }

    public SqlIntegerExpr() {
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public Integer getValue() {
        return this.number.intValue();
    }

    @Override
    public SqlIntegerExpr _clone() {
        return new SqlIntegerExpr(this.number.intValue());
    }
}
