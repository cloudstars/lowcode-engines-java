package net.cf.object.engine.sqlbuilder.update;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlUpdateSetItem;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.sqlbuilder.AbstractSqlStatementBuilder;

/**
 * 更新SQL语句构建器
 *
 * @author clouds
 */
public class SqlUpdateStatementBuilder extends AbstractSqlStatementBuilder<OqlUpdateStatement, SqlUpdateStatement> {

    private final SqlUpdateStatement stmt = new SqlUpdateStatement();

    public SqlUpdateStatementBuilder() {
    }

    public SqlUpdateStatementBuilder tableSource(SqlExprTableSource tableSource) {
        this.stmt.setTableSource(tableSource);
        return this;
    }

    public SqlUpdateStatementBuilder appendSetItem(SqlUpdateSetItem setItem) {
        this.stmt.addSetItem(setItem);
        return this;
    }

    public SqlUpdateStatementBuilder where(SqlExpr where) {
        this.stmt.setWhere(where);
        return this;
    }

    @Override
    public SqlUpdateStatement build() {
        return this.stmt;
    }
}
