package net.cf.form.repository.sql.ast.expr.operation;

import net.cf.form.repository.sql.ast.expr.SqlExpr;

public class SqlLikeOpExpr extends SqlBinaryOpExpr {

    /**
     * 转义字符
     */
    private String escape;

    public SqlLikeOpExpr() {
    }

    public SqlLikeOpExpr(SqlExpr left, SqlExpr right) {
        super(left, SqlBinaryOperator.LIKE, right);
    }

    public String getEscape() {
        return escape;
    }

    public void setEscape(String escape) {
        this.escape = escape;
    }

    @Override
    public SqlLikeOpExpr cloneMe() {
        SqlLikeOpExpr x = new SqlLikeOpExpr();
        super.cloneT(x);
        x.setEscape(this.escape);
        return x;
    }
}
