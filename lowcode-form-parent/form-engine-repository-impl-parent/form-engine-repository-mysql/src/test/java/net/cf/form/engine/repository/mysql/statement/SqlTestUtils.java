package net.cf.form.engine.repository.mysql.statement;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.mysql.statement.delete.DeleteSqlAstVisitor;
import net.cf.form.engine.repository.mysql.statement.insert.InsertSqlAstVisitor;
import net.cf.form.engine.repository.mysql.statement.select.SelectSqlAstVisitor;
import net.cf.form.engine.repository.mysql.statement.update.UpdateSqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateStatement;
import net.cf.form.engine.repository.oql.util.OqlUtils;
import net.cf.form.engine.repository.sql_bak.DeleteSqlInfo;
import net.cf.form.engine.repository.sql_bak.InsertSqlInfo;
import net.cf.form.engine.repository.sql_bak.SelectSqlInfo;
import net.cf.form.engine.repository.sql_bak.UpdateSqlInfo;

/**
 * SQL测试工具类
 *
 * @author clouds
 */
public final class SqlTestUtils {

    /**
     * 比较两个SQL是否相等
     *
     * @param sql1
     * @param sql2
     * @return
     */
    public static boolean equals(String sql1, String sql2) {
        if (sql1 != null) {
            if (sql2 != null) {
                String s1 = sql1.replaceAll("[\\s\\r\\n\\t;]+", "");
                String s2 = sql2.replaceAll("[\\s\\r\\n\\t;]+", "");
                return s1.equalsIgnoreCase(s2);
            } else {
                return false;
            }
        } else {
            return sql2 == null;
        }
    }


    /**
     * 解析 select oql 语句
     *
     * @param oql
     * @return
     */
    public static SelectSqlInfo parseSelectOql(String oql, DataObjectResolver resolver) {
        return parseSelectOql(oql, resolver, false);
    }

    /**
     * 解析 select oql 语句
     *
     * @param oql
     * @return
     */
    public static SelectSqlInfo parseSelectOql(String oql, DataObjectResolver resolver, boolean parameterized) {
        OqlSelectStatement statement = OqlUtils.parseSingleSelectStatement(oql);
        SelectSqlAstVisitor visitor = new SelectSqlAstVisitor(resolver, parameterized);
        statement.accept(visitor);
        return visitor.getSqlInfo();
    }

    /**
     * 解析 insert oql 语句
     *
     * @param oql
     * @return
     */
    public static InsertSqlInfo parseInsertOql(String oql, DataObjectResolver resolver) {
        return parseInsertOql(oql, resolver, false);
    }

    /**
     * 解析 insert oql 语句
     *
     * @param oql
     * @return
     */
    public static InsertSqlInfo parseInsertOql(String oql, DataObjectResolver resolver, boolean parameterized) {
        OqlInsertStatement stmt = OqlUtils.parseSingleInsertStatement(oql);
        InsertSqlAstVisitor visitor = new InsertSqlAstVisitor(resolver, parameterized);
        stmt.accept(visitor);
        return visitor.getSqlInfo();
    }

    /**
     * 解析 update oql 语句
     *
     * @param oql
     * @return
     */
    public static UpdateSqlInfo parseUpdateOql(String oql, DataObjectResolver resolver) {
        return parseUpdateOql(oql, resolver, false);
    }

    /**
     * 解析 update oql 语句
     *
     * @param oql
     * @return
     */
    public static UpdateSqlInfo parseUpdateOql(String oql, DataObjectResolver resolver, boolean parameterized) {
        OqlUpdateStatement stmt = OqlUtils.parseSingleUpdateStatement(oql);
        UpdateSqlAstVisitor visitor = new UpdateSqlAstVisitor(resolver, parameterized);
        stmt.accept(visitor);
        return visitor.getSqlInfo();
    }

    /**
     * 解析 delete oql 语句
     *
     * @param oql
     * @return
     */
    public static DeleteSqlInfo parseDeleteOql(String oql, DataObjectResolver resolver) {
        return parseDeleteOql(oql, resolver, false);
    }

    /**
     * 解析 delete oql 语句
     *
     * @param oql
     * @return
     */
    public static DeleteSqlInfo parseDeleteOql(String oql, DataObjectResolver resolver, boolean parameterized) {
        OqlDeleteStatement stmt = OqlUtils.parseSingleDeleteStatement(oql);
        DeleteSqlAstVisitor visitor = new DeleteSqlAstVisitor(resolver, parameterized);
        stmt.accept(visitor);
        return visitor.getSqlInfo();
    }

}
