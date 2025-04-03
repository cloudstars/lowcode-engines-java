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
public final class SqlContainsOpExpr extends AbstractNotableBinaryOpExpr {

    private SqlContainsOption containsOption;

    public SqlContainsOpExpr() {
    }

    public SqlContainsOpExpr(SqlExpr left, SqlExpr right) {
        this.left = left;
        this.operator = SqlBinaryOperator.CONTAINS;
        this.right = right;
    }

    public SqlContainsOption getContainsOption() {
        return containsOption;
    }

    public void setContainsOption(SqlContainsOption containsOption) {
        this.containsOption = containsOption;
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public SqlContainsOpExpr cloneMe() {
        SqlContainsOpExpr x = new SqlContainsOpExpr();
        super.cloneTo(x);
        return x;
    }

}
