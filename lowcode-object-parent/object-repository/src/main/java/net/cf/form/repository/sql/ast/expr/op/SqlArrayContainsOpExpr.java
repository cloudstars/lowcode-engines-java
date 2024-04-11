package net.cf.form.repository.sql.ast.expr.op;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;

public class SqlArrayContainsOpExpr extends SqlBinaryOpExpr{

    public SqlArrayContainsOpExpr() {
    }

    public SqlArrayContainsOpExpr(SqlExpr left, SqlBinaryOperator operator, SqlExpr right) {
        super(left, operator, right);
    }

    public SqlJsonArrayExpr getRight(){
        return (SqlJsonArrayExpr) right;
    }

}
