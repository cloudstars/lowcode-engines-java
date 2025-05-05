package io.github.cloudstars.lowcode.object.repository.sql.ast.statement;

import io.github.cloudstars.lowcode.object.repository.sql.ast.SqlObject;
import io.github.cloudstars.lowcode.object.repository.sql.ast.SqlReplaceable;
import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.SqlExpr;
import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.identifier.SqlName;

import java.util.ArrayList;
import java.util.List;

public class SqlInsertInto extends AbstractSqlStatementImpl implements SqlReplaceable {

    protected SqlExprTableSource tableSource;

    /**
     * 自动生成的主键
     */
    protected String autoGenColumn;

    protected final List<SqlExpr> columns = new ArrayList();

    protected final List<SqlInsertStatement.ValuesClause> valuesList = new ArrayList();

    /**
     * insert into ... select ... 语法
     */
    protected SqlSelect query;

    public SqlInsertInto() {
    }

    public SqlExprTableSource getTableSource() {
        return tableSource;
    }

    public void setTableSource(SqlExprTableSource tableSource) {
        this.tableSource = tableSource;
        this.addChild(tableSource);
    }

    public SqlName getTableName() {
        return (SqlName) this.tableSource.getExpr();
    }

    public List<SqlExpr> getColumns() {
        return columns;
    }

    public void addColumns(List<SqlExpr> columns) {
        for (SqlExpr column : columns) {
            this.addColumn(column);
        }
    }

    public void addColumn(SqlExpr column) {
        this.columns.add(column);
        this.addChild(column);
    }

    public void addColumn(int index, SqlExpr column) {
        this.columns.add(index, column);
        this.addChild(column);
    }

    public String getAutoGenColumn() {
        return autoGenColumn;
    }

    public void setAutoGenColumn(String autoGenColumn) {
        this.autoGenColumn = autoGenColumn;
    }


    public List<SqlInsertStatement.ValuesClause> getValuesList() {
        return valuesList;
    }

    public SqlInsertStatement.ValuesClause getValues() {
        return this.valuesList.isEmpty() ? null : this.valuesList.get(0);
    }

    public void addValues(SqlInsertStatement.ValuesClause values) {
        this.valuesList.add(values);
        this.addChild(values);
    }

    public void addValuesList(List<SqlInsertStatement.ValuesClause> valuesList) {
        this.valuesList.addAll(valuesList);
    }

    public SqlSelect getQuery() {
        return query;
    }

    public void setQuery(SqlSelect query) {
        this.query = query;
    }

    @Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList();
        children.add(this.tableSource);
        children.addAll(this.columns);
        children.addAll(this.valuesList);

        return children;
    }

    @Override
    public SqlInsertInto cloneMe() {
        SqlInsertInto x = new SqlInsertInto();
        if (this.tableSource != null) {
            x.setTableSource(this.getTableSource().cloneMe());
        }

        for (SqlExpr field : this.columns) {
            x.columns.add(field.cloneMe());
        }
        for (SqlInsertStatement.ValuesClause valuesItem : this.valuesList) {
            x.valuesList.add(valuesItem.cloneMe());
        }

        return x;
    }

    @Override
    public boolean replace(SqlExpr source, SqlExpr target) {
        return false;
    }
}
