package io.github.cloudstars.lowcode.object.repository.sql.ast.statement;


import io.github.cloudstars.lowcode.object.repository.sql.ast.AbstractSqlObjectImpl;
import io.github.cloudstars.lowcode.object.repository.sql.ast.SqlReplaceable;
import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.SqlExpr;
import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.literal.SqlIntegerExpr;
import io.github.cloudstars.lowcode.object.repository.sql.visitor.SqlAstVisitor;

public final class SqlSelectOrderByItem extends AbstractSqlObjectImpl implements SqlReplaceable {

    protected SqlExpr expr;

    protected boolean ascending = true;

    //protected SqlSelectItem resolvedSelectItem;

    public SqlSelectOrderByItem() {
    }

    public SqlSelectOrderByItem(SqlExpr expr) {
        this.setExpr(expr);
    }

    public SqlSelectOrderByItem(SqlExpr expr, boolean ascending) {
        this.setExpr(expr);
        this.ascending = ascending;
    }

    public SqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(SqlExpr expr) {
        this.expr = expr;
        this.addChild(expr);
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public SqlSelectOrderByItem cloneMe() {
        SqlSelectOrderByItem x = new SqlSelectOrderByItem();
        x.setExpr(this.expr.cloneMe());
        x.setAscending(this.isAscending());
        return x;
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        if (visitor.visit(this) && this.expr != null) {
            this.expr.accept(visitor);
        }

        visitor.endVisit(this);
    }

    @Override
    public boolean replace(SqlExpr source, SqlExpr target) {
        if (this.expr == source) {
            if (target instanceof SqlIntegerExpr && this.parent instanceof SqlOrderBy) {
                ((SqlOrderBy) this.parent).getItems().remove(this);
            }

            this.setExpr(target);
            return true;
        } else {
            return false;
        }
    }
}

