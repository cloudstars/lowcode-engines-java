package net.cf.object.engine.sqlbuilder.select;

import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelect;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.sqlbuilder.AbstractSqlStatementBuilder;

/**
 * SQL查询语句构建器
 *
 * @author clouds
 */
public class SqlSelectStatementBuilder extends AbstractSqlStatementBuilder<OqlSelectStatement, SqlSelectStatement> {

    private final SqlSelect select = new SqlSelect();

    public SqlSelectStatementBuilder() {
    }

    /**
     * 添加查询字段
     *
     * @param selectItem
     * @return
     */
    public SqlSelectStatementBuilder appendSelectItem(SqlSelectItem selectItem) {
        this.select.addSelectItem(selectItem);
        return this;
    }

    /**
     * 设置表源
     *
     * @param from
     */
    public void from(SqlTableSource from) {
        this.select.setFrom(from);
    }

    /**
     * 设置查询表件
     *
     * @param where
     */
    public void where(SqlExpr where) {
        this.select.setWhere(where);
    }

    /**
     * 设置限制数量
     *
     * @param limit
     */
    public void limit(SqlLimit limit) {
        this.select.setLimit(limit);
    }

    @Override
    public SqlSelectStatement build() {
        return new SqlSelectStatement(this.select);
    }
}
