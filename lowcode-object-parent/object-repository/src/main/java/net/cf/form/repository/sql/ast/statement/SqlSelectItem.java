package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * SQL 查询的字段
 *
 * @author clouds
 */
public class SqlSelectItem extends AbstractSqlExprImpl implements SqlReplaceable {

    /**
     * 字段表达式
     */
    protected SqlExpr expr;

    /**
     * 别名
     */
    protected String alias;

    public SqlSelectItem() {
    }

    public SqlSelectItem(SqlExpr expr) {
        this(expr, null);
    }
    public SqlSelectItem(SqlExpr expr, String alias) {
        this.expr = expr;
        this.alias = alias;
        this.setExpr(expr);
    }

    public SqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(SqlExpr expr) {
        this.expr = expr;
        this.addChild(expr);
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    protected void accept0(SqlAstVisitor v) {
        if (v.visit(this) && this.expr != null) {
            this.expr.accept(v);
        }

        v.endVisit(this);
    }

    @Override
    public SqlSelectItem cloneMe() {
        SqlSelectItem x = new SqlSelectItem();
        x.alias = this.alias;
        if (this.expr != null) {
            x.setExpr(this.expr.cloneMe());
        }

        return x;
    }

    public boolean replace(SqlExpr expr, SqlExpr target) {
        if (this.expr == expr) {
            this.setExpr(target);
            return true;
        } else {
            return false;
        }
    }
}

