package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class SqlUpdateStatement extends AbstractSqlStatementImpl implements SqlStatement {

    protected SqlTableSource tableSource;

    protected final List<SqlUpdateSetItem> setItems = new ArrayList<>();

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

    public List<SqlUpdateSetItem> getSetItems() {
        return setItems;
    }

    public void addSetItems(List<SqlUpdateSetItem> setItems) {
        for (SqlUpdateSetItem setItem : setItems) {
            this.addSetItem(setItem);
        }
    }

    public void addSetItem(SqlUpdateSetItem setItem) {
        this.setItems.add(setItem);
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
            this.nullSafeAcceptChildren(visitor, this.tableSource);
            this.nullSafeAcceptChildren(visitor, this.setItems);
            this.nullSafeAcceptChildren(visitor, this.where);
        }

        visitor.endVisit(this);
    }


    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList();
        if (this.tableSource != null) {
            children.add(this.tableSource);
        }

        children.addAll(this.setItems);
        if (this.where != null) {
            children.add(this.where);
        }

        return children;
    }

    @Override
    public SqlUpdateStatement cloneMe() {
        SqlUpdateStatement statement = new SqlUpdateStatement();
        statement.setTableSource(this.tableSource.cloneMe());
        for (SqlUpdateSetItem setItem : this.setItems) {
            statement.addSetItem(setItem);
        }
        if (this.where != null) {
            statement.setWhere(this.where.cloneMe());
        }

        return statement;
    }
}
