package net.cf.object.engine.sqlbuilder.insert;

import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.sqlbuilder.AbstractSqlStatementBuilder;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlInsertInto;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;

/**
 * 插入SQL语句构建器
 *
 * @author clouds
 */
public class InsertSqlStatementBuilder extends AbstractSqlStatementBuilder<OqlInsertStatement, SqlInsertStatement> {

    private SqlInsertInto insertInto = new SqlInsertInto();

    private SqlInsertStatement.ValuesClause lastInsertValues = null;

    public InsertSqlStatementBuilder() {
    }

    public InsertSqlStatementBuilder tableSource(SqlExprTableSource tableSource) {
        insertInto.setTableSource(tableSource);
        return this;
    }

    public InsertSqlStatementBuilder appendColumn(SqlExpr column) {
        insertInto.addColumn(column);
        return this;
    }

    public InsertSqlStatementBuilder appendInsertValues(SqlInsertStatement.ValuesClause insertValues) {
        insertInto.addValues(insertValues);
        this.lastInsertValues = insertValues;
        return this;
    }

    public InsertSqlStatementBuilder appendInsertValuesItem(SqlExpr value) {
        if (this.lastInsertValues == null) {
            SqlInsertStatement.ValuesClause insertValues = new SqlInsertStatement.ValuesClause();
            this.appendInsertValues(insertValues);
            this.lastInsertValues = insertValues;
        }

        this.lastInsertValues.addValue(value);
        return this;
    }

    @Override
    public SqlInsertStatement build() {
        SqlInsertStatement stmt = new SqlInsertStatement(this.insertInto);
        return stmt;
    }
}
