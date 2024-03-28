package net.cf.object.engine.sqlbuilder.insert;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlInsertInto;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.sqlbuilder.AbstractSqlStatementBuilder;

/**
 * 插入SQL语句构建器
 *
 * @author clouds
 */
public class SqlInsertStatementBuilder extends AbstractSqlStatementBuilder<OqlInsertStatement, SqlInsertStatement> {

    private final SqlInsertInto insertInto = new SqlInsertInto();

    public SqlInsertStatementBuilder() {
    }

    /**
     * 设置自动生成的字段名称
     *
     * @param autoGenName
     * @return
     */
    public SqlInsertStatementBuilder setAutoGenName(String autoGenName) {
        this.insertInto.setAutoGenColumn(autoGenName);
        return this;
    }

    public SqlInsertStatementBuilder tableSource(SqlExprTableSource tableSource) {
        this.insertInto.setTableSource(tableSource);
        return this;
    }

    public SqlInsertStatementBuilder appendColumn(SqlExpr column) {
        this.insertInto.addColumn(column);
        return this;
    }


    public SqlInsertStatementBuilder appendInsertValues(SqlInsertStatement.ValuesClause insertValues) {
        this.insertInto.addValues(insertValues);
        return this;
    }

    @Override
    public SqlInsertStatement build() {
        SqlInsertStatement stmt = new SqlInsertStatement(this.insertInto);
        return stmt;
    }
}
