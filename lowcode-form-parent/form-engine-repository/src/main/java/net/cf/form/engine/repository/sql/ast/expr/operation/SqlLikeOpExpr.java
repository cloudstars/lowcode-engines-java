package net.cf.form.engine.repository.sql.ast.expr.operation;

import net.cf.form.engine.repository.sql.ast.expr.SqlExpr;

public class SqlLikeOpExpr extends SqlBinaryOpExpr {

    /**
     * 转义字符
     */
    private String escape;

    public SqlLikeOpExpr() {
    }

    public SqlLikeOpExpr(SqlExpr left, SqlExpr right) {
        super(left, SqlBinaryOperator.Like, right);
    }

    public String getEscape() {
        return escape;
    }

    public void setEscape(String escape) {
        this.escape = escape;
    }

    @Override
    public SqlLikeOpExpr _clone() {
        SqlLikeOpExpr x = new SqlLikeOpExpr();
        super._cloneTo(x);
        x.setEscape(this.escape);
        return x;
    }
}
