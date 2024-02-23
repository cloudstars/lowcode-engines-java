package net.cf.form.repository.sql.ast.expr.operation;

import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;
import net.cf.form.repository.sql.ast.expr.SqlExpr;

public class SqlArrayContainsOpExpr extends SqlBinaryOpExpr {

    private SqlArrayContainsOption option = SqlArrayContainsOption.CONTAINS_ALL;

    public SqlArrayContainsOpExpr() {
    }

    public SqlArrayContainsOpExpr(SqlExpr expr, SqlJsonArrayExpr values) {
        super(expr, SqlBinaryOperator.CONTAINS, values);
    }

    public SqlJsonArrayExpr getValues() {
        return (SqlJsonArrayExpr) this.getRight();
    }

    public void setValues(SqlJsonArrayExpr values) {
        this.setRight(values);
    }

    public SqlArrayContainsOption getOption() {
        return option;
    }

    public void setOption(SqlArrayContainsOption option) {
        this.option = option;
    }

    @Override
    public SqlArrayContainsOpExpr clone() {
        SqlArrayContainsOpExpr x = new SqlArrayContainsOpExpr();
        //cloneTarget(x);
        return x;
    }
}

