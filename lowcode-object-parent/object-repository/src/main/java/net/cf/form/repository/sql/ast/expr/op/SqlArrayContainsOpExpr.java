package net.cf.form.repository.sql.ast.expr.op;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;

public class SqlArrayContainsOpExpr extends SqlBinaryOpExpr{

    private SqlContainsOption option = SqlContainsOption.ALL;
    public SqlArrayContainsOpExpr() {
    }

    public SqlArrayContainsOpExpr(SqlExpr left, SqlBinaryOperator operator, SqlExpr right) {
        super(left, operator, right);
    }

    public SqlJsonArrayExpr getRight(){
        return (SqlJsonArrayExpr) right;
    }

    public SqlContainsOption getOption(){
        return option;
    }

    public void setOption(SqlContainsOption option) {
        this.option = option;
    }
}
