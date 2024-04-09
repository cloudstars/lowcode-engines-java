package net.cf.form.repository.sql.ast.expr.op;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * SQL like 表达式
 *
 * @author clouds
 */
public class SqlLikeOpExpr extends AbstractNotableExpr {

    /**
     * 转义字符
     */
    private String escape;

    private SqlBinaryOpExpr binaryOpExpr;

    public SqlLikeOpExpr() {
        binaryOpExpr = new SqlBinaryOpExpr(null, SqlBinaryOperator.LIKE, null);
    }

    public SqlLikeOpExpr(SqlExpr left, SqlExpr right) {
        this.binaryOpExpr = new SqlBinaryOpExpr(left, SqlBinaryOperator.LIKE, right);
    }

    public SqlExpr getLeft() {
        return this.binaryOpExpr.getLeft();
    }

    public void setLeft(SqlExpr left) {
        this.binaryOpExpr.setLeft(left);
    }

    public SqlExpr getRight() {
        return this.binaryOpExpr.getRight();
    }

    public void setRight(SqlExpr right) {
        this.binaryOpExpr.setRight(right);
    }

    public String getEscape() {
        return escape;
    }

    public void setEscape(String escape) {
        this.escape = escape;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChildren(visitor, binaryOpExpr);
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlLikeOpExpr cloneMe() {
        SqlLikeOpExpr x = new SqlLikeOpExpr();
        x.setNot(this.isNot());
        x.binaryOpExpr = this.binaryOpExpr.cloneMe();
        x.setEscape(this.escape);
        return x;
    }
}
