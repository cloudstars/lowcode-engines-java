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
public class SqlInsertStatementBuilder extends AbstractSqlStatementBuilder<OqlInsertStatement, SqlInsertStatement> {

    private final SqlInsertInto insertInto = new SqlInsertInto();

    private SqlInsertStatement.ValuesClause lastInsertValues = null;

    public SqlInsertStatementBuilder() {
    }

    /**
     * 设置自动生成的主键
     *
     * @param autoGenColumn
     * @return
     */
    public SqlInsertStatementBuilder setAutoGenColumn(String autoGenColumn) {
        this.insertInto.setAutoGenColumn(autoGenColumn);
        return this;
    }

    public SqlInsertStatementBuilder tableSource(SqlExprTableSource tableSource) {
        insertInto.setTableSource(tableSource);
        return this;
    }

    public SqlInsertStatementBuilder appendColumn(SqlExpr column) {
        insertInto.addColumn(column);
        return this;
    }

    public SqlInsertStatementBuilder appendInsertValues(SqlInsertStatement.ValuesClause insertValues) {
        insertInto.addValues(insertValues);
        this.lastInsertValues = insertValues;
        return this;
    }

    @Override
    public SqlInsertStatement build() {
        SqlInsertStatement stmt = new SqlInsertStatement(this.insertInto);
        return stmt;
    }
}
