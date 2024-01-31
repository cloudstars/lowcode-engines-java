package net.cf.form.engine.repository.sql.ast.statement;

import net.cf.form.engine.repository.sql.visitor.SqlAstVisitor;
import net.cf.form.engine.repository.sql.ast.SqlReplaceable;
import net.cf.form.engine.repository.sql.ast.expr.SqlExpr;
import net.cf.form.engine.repository.sql.ast.expr.SqlExprImpl;

/**
 * SQL 查询的字段
 *
 * @author clouds
 */
public class SqlSelectItem extends SqlExprImpl implements SqlReplaceable {

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
    public SqlSelectItem _clone() {
        SqlSelectItem x = new SqlSelectItem();
        x.alias = this.alias;
        if (this.expr != null) {
            x.setExpr(this.expr._clone());
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

