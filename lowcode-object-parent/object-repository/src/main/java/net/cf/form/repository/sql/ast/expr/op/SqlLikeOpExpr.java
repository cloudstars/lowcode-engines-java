package net.cf.form.repository.sql.ast.expr.op;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * SQL like 表达式
 *
 * @author clouds
 */
public class SqlLikeOpExpr extends AbstractNotableBinaryOpExpr {

    /**
     * 转义字符
     */
    private String escape;

    public SqlLikeOpExpr() {
        this.operator = SqlBinaryOperator.LIKE;
    }

    public SqlLikeOpExpr(SqlExpr left, SqlExpr right) {
        this.left = left;
        this.operator = SqlBinaryOperator.LIKE;
        this.right = right;
    }

    public String getEscape() {
        return escape;
    }

    public void setEscape(String escape) {
        this.escape = escape;
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public SqlLikeOpExpr cloneMe() {
        SqlLikeOpExpr x = new SqlLikeOpExpr();
        super.cloneTo(x);
        x.setNot(this.isNot());
        x.setEscape(this.escape);
        return x;
    }
}
