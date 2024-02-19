package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlName;
import net.cf.form.repository.sql.ast.expr.identifier.SqlPropertyExpr;
import net.cf.form.repository.sql.util.SqlUtils;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * 表达式形式的表
 *
 * @author clouds
 */
public class SqlExprTableSource extends AbstractSqlTableSourceImpl implements SqlReplaceable {

    protected SqlExpr expr;

    public SqlExprTableSource() {
    }

    public SqlExprTableSource(String tableName) {
        this(new SqlIdentifierExpr(tableName), null);
    }

    public SqlExprTableSource(SqlExpr expr) {
        this(expr, null);
    }

    public SqlExprTableSource(SqlExpr expr, String alias) {
        this.setExpr(expr);
        this.setAlias(alias);
    }

    public SqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(SqlExpr x) {
        if (x != null) {
            x.setParent(this);
        }

        this.expr = x;
    }

    public void setExpr(String name) {
        this.setExpr(new SqlIdentifierExpr(name));
    }

    public SqlName getName() {
        return this.expr instanceof SqlName ? (SqlName) this.expr : null;
    }

    public String getTableName(boolean normalize) {
        String tableName = this.getTableName();
        return normalize ? SqlUtils.normalize(tableName) : tableName;
    }

    public String getTableName() {
        if (this.expr == null) {
            return null;
        } else if (this.expr instanceof SqlIdentifierExpr) {
            return ((SqlIdentifierExpr) this.expr).getName();
        } else {
            return this.expr instanceof SqlPropertyExpr ? ((SqlPropertyExpr) this.expr).getSimpleName() : null;
        }
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.expr != null) {
                this.expr.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlExprTableSource _clone() {
        SqlExprTableSource x = new SqlExprTableSource();
        x.alias = this.alias;
        if (this.expr != null) {
            x.setExpr(this.expr._clone());
        }

        return x;
    }

    @Override
    public boolean replace(SqlExpr source, SqlExpr target) {
        if (source == this.expr && target instanceof SqlIdentifierExpr) {
            this.setExpr(target);
            return true;
        } else {
            return false;
        }
    }
}
