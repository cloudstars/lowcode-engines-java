package net.cf.form.repository.sql.ast.expr.operation;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.SqlExprImpl;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.Collections;
import java.util.List;


/**
 * 非表达式
 *
 * @author clouds
 */
public final class SqlNotExpr extends SqlExprImpl {

    public SqlExpr expr;

    public SqlNotExpr() {
    }

    public SqlNotExpr(SqlExpr expr) {
        this.expr = expr;
        this.addChild(expr);
    }

    public SqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(SqlExpr expr) {
        this.expr = expr;
        this.addChild(expr);
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this) && this.expr != null) {
            this.expr.accept(visitor);
        }

        visitor.endVisit(this);
    }

    @Override
    public List getChildren() {
        return Collections.singletonList(this.expr);
    }

    @Override
    public SqlNotExpr _clone() {
        SqlNotExpr x = new SqlNotExpr();
        if (this.expr != null) {
            x.setExpr(this.expr._clone());
        }

        return x;
    }

}