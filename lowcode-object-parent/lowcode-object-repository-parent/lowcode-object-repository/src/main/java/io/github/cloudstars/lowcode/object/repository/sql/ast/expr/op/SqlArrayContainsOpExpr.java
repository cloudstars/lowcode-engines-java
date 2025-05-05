package io.github.cloudstars.lowcode.object.repository.sql.ast.expr.op;

import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.SqlExpr;

public class SqlArrayContainsOpExpr extends AbstractNotableBinaryOpExpr {

    private SqlContainsOption option = SqlContainsOption.ALL;

    public SqlArrayContainsOpExpr() {
    }

    public SqlArrayContainsOpExpr(SqlExpr left, SqlBinaryOperator operator, SqlExpr right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public SqlListExpr getRight() {
        return (SqlListExpr) right;
    }

    public SqlContainsOption getOption() {
        return option;
    }

    public void setOption(SqlContainsOption option) {
        this.option = option;
    }

    @Override
    public SqlArrayContainsOpExpr cloneMe() {
        SqlArrayContainsOpExpr x = new SqlArrayContainsOpExpr();
        x.setOption(option);
        super.cloneTo(x);
        return x;
    }
}
