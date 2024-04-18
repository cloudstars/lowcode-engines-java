//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.cf.form.repository.sql.ast.expr.op;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * SQL contains 表达式
 *
 * @author clouds
 */
public final class SqlContainsOpExpr extends SqlBinaryOpExpr {

    public SqlContainsOpExpr() {

    }

    public SqlContainsOpExpr(SqlExpr left, SqlExpr right) {
        super(left, SqlBinaryOperator.CONTAINS, right);
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.left != null) {
                this.left.accept(visitor);
            }

            if (this.right != null) {
                this.right.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlContainsOpExpr cloneMe() {
        SqlContainsOpExpr x = new SqlContainsOpExpr();
        cloneT(x);
        return x;
    }

}
