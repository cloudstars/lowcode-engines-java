package net.cf.form.engine.repository.sql.ast.statement;

import net.cf.form.engine.repository.sql.ast.SqlObject;
import net.cf.form.engine.repository.sql.ast.SqlReplaceable;
import net.cf.form.engine.repository.sql.ast.expr.SqlExpr;
import net.cf.form.engine.repository.sql.ast.expr.identifier.SqlName;

import java.util.ArrayList;
import java.util.List;

public class SqlInsertInto extends SqlStatementImpl implements SqlReplaceable {

    protected SqlExprTableSource tableSource;

    protected final List<SqlExpr> columns = new ArrayList();

    protected final List<SqlInsertStatement.ValuesClause> valuesList = new ArrayList();

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

    @Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList();
        children.add(this.tableSource);
        children.addAll(this.columns);
        children.addAll(this.valuesList);

        return children;
    }

    @Override
    public SqlInsertInto _clone() {
        SqlInsertInto x = new SqlInsertInto();
        if (this.tableSource != null) {
            x.setTableSource(this.getTableSource()._clone());
        }

        for (SqlExpr field : this.columns) {
            x.columns.add(field._clone());
        }
        for (SqlInsertStatement.ValuesClause valuesItem : this.valuesList) {
            x.valuesList.add(valuesItem._clone());
        }

        return x;
    }

    @Override
    public boolean replace(SqlExpr source, SqlExpr target) {
        return false;
    }
}
