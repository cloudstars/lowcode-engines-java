package io.github.cloudstars.lowcode.object.repository.sql.ast.statement;

import io.github.cloudstars.lowcode.object.repository.sql.ast.AbstractSqlObjectImpl;
import io.github.cloudstars.lowcode.object.repository.sql.ast.SqlReplaceable;
import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.SqlExpr;
import io.github.cloudstars.lowcode.object.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public final class SqlOrderBy extends AbstractSqlObjectImpl implements SqlReplaceable {

    protected final List<SqlSelectOrderByItem> items = new ArrayList();

    public SqlOrderBy() {
    }

    public SqlOrderBy(SqlExpr expr) {
        SqlSelectOrderByItem item = new SqlSelectOrderByItem(expr);
        this.addItem(item);
    }

    public SqlOrderBy(SqlExpr expr, boolean ascending) {
        SqlSelectOrderByItem item = new SqlSelectOrderByItem(expr, ascending);
        this.addItem(item);
    }

    public void addItem(SqlSelectOrderByItem item) {
        this.items.add(item);
        this.addChild(item);
    }

    public void addItem(SqlExpr expr) {
        this.addItem(new SqlSelectOrderByItem(expr));
    }

    public void addItem(SqlExpr expr, boolean ascending) {
        this.addItem(new SqlSelectOrderByItem(expr, ascending));
    }

    public List<SqlSelectOrderByItem> getItems() {
        return this.items;
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChildren(visitor, this.items);
        }

        visitor.endVisit(this);
    }

    @Override
    public boolean replace(SqlExpr source, SqlExpr target) {
        for (SqlSelectOrderByItem item : this.items) {
            if (item.replace(source, target)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public SqlOrderBy cloneMe() {
        SqlOrderBy x = new SqlOrderBy();
        for (SqlSelectOrderByItem item : this.items) {
            x.addItem(item.cloneMe());
        }

        return x;
    }
}
