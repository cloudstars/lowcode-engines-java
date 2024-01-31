package net.cf.form.engine.repository.mysql.statement;

import net.cf.form.engine.repository.sql_bak.AbstractSqlInfo;

/**
 * SQL构建器
 *
 * @author clouds
 */
public abstract class AbstractSqlBuilder<T extends AbstractSqlInfo> {

    /**
     * 解析后的SQL信息
     */
    protected T sqlInfo;

    /**
     * OQL表达式访问器
     */
    protected OqlExprAstVisitor exprAstVisitor;

    /**
     * 生成的目标可执行SQL语句
     */
    private String targetSql;

    public AbstractSqlBuilder(T sqlInfo) {
        this.sqlInfo = sqlInfo;
        this.exprAstVisitor = new OqlExprAstVisitor(sqlInfo.getObject());
    }

    public String getSql() {
        if (targetSql == null) {
            targetSql = this.buildSql();
        }

        return targetSql;
    }

    /**
     * 构建SQL
     *
     * @return
     */
    protected abstract String buildSql();

}
