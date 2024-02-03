package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;
import net.cf.form.repository.sql.ast.SqlObject;

import java.util.ArrayList;
import java.util.List;

public class SqlUpdateStatement extends SqlStatementImpl implements SqlStatement {

    protected SqlTableSource tableSource;

    protected final List<SqlUpdateSetItem> items = new ArrayList<>();

    protected SqlExpr where;

    public SqlUpdateStatement() {
    }

    public SqlTableSource getTableSource() {
        return tableSource;
    }

    public void setTableSource(SqlTableSource tableSource) {
        this.tableSource = tableSource;
        this.addChild(tableSource);
    }

    public List<SqlUpdateSetItem> getItems() {
        return items;
    }

    public void addSetItems(List<SqlUpdateSetItem> setItems) {
        for (SqlUpdateSetItem setItem : setItems) {
            this.addSetItem(setItem);
        }
    }

    public void addSetItem(SqlUpdateSetItem setItem) {
        this.items.add(setItem);
        this.addChild(setItem);
    }

    public SqlExpr getWhere() {
        return this.where;
    }

    public void setWhere(SqlExpr where) {
        if (where != null) {
            where.setParent(this);
        }

        this.where = where;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChild(visitor, this.tableSource);
            this.nullSafeAcceptChild(visitor, this.items);
            this.nullSafeAcceptChild(visitor, this.where);
        }

        visitor.endVisit(this);
    }


    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList();
        if (this.tableSource != null) {
            children.add(this.tableSource);
        }

        children.addAll(this.items);
        if (this.where != null) {
            children.add(this.where);
        }

        return children;
    }

    @Override
    public SqlUpdateStatement _clone() {
        SqlUpdateStatement statement = new SqlUpdateStatement();
        statement.setTableSource(this.tableSource._clone());
        for (SqlUpdateSetItem setItem : this.items) {
            statement.addSetItem(setItem);
        }
        if (this.where != null) {
            statement.setWhere(this.where._clone());
        }

        return statement;
    }
}
