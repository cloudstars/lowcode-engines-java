package net.cf.object.engine.sqlbuilder.select;

import net.cf.form.repository.sql.ast.statement.SqlSelect;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.sqlbuilder.AbstractSqlStatementBuilder;

/**
 * 查询SQL语句构建器
 *
 * @author clouds
 */
public class SelectSqlStatementBuilder extends AbstractSqlStatementBuilder<OqlSelectStatement, SqlSelectStatement> {

    private final SqlSelect select = new SqlSelect();

    public SelectSqlStatementBuilder() {
    }

    /**
     * 添加查询字段
     *
     * @param selectItem
     * @return
     */
    public SelectSqlStatementBuilder appendSelectItem(SqlSelectItem selectItem) {
        this.select.addSelectItem(selectItem);
        return this;
    }

    /**
     * 设置表
     *
     * @param tableSource
     */
    public void setTableSource(SqlTableSource tableSource) {
        this.select.setFrom(tableSource);
    }

    @Override
    public SqlSelectStatement build() {
        return new SqlSelectStatement(this.select);
    }
}
