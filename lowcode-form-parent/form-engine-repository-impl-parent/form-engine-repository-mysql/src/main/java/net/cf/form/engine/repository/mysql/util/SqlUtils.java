package net.cf.form.engine.repository.mysql.util;

import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.mysql.statement.delete.DeleteSqlAstVisitor;
import net.cf.form.engine.repository.mysql.statement.insert.InsertSqlAstVisitor;
import net.cf.form.engine.repository.mysql.statement.select.SelectSqlAstVisitor;
import net.cf.form.engine.repository.mysql.statement.update.UpdateSqlAstVisitor;
import net.cf.form.engine.repository.sql_bak.DeleteSqlInfo;
import net.cf.form.engine.repository.sql_bak.InsertSqlInfo;
import net.cf.form.engine.repository.sql_bak.SelectSqlInfo;
import net.cf.form.engine.repository.sql_bak.UpdateSqlInfo;
import net.cf.form.engine.repository.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateStatement;

/**
 * SQL工具类
 *
 * @author clouds
 */
public final class SqlUtils {

    /**
     * 解析插入语句AST为mysql的查询SQL
     *
     * @param statement
     * @param resolver
     * @return
     */
    public static InsertSqlInfo parseInsertStatement(OqlInsertStatement statement, DataObjectResolver resolver) {
        return parseInsertStatement(statement, resolver, false);
    }

    /**
     * 解析插入语句AST为mysql的查询SQL
     *
     * @param statement
     * @param resolver
     * @return
     */
    public static InsertSqlInfo parseInsertStatement(OqlInsertStatement statement, DataObjectResolver resolver, boolean parameterized) {
        InsertSqlAstVisitor visitor = new InsertSqlAstVisitor(resolver, parameterized);
        statement.accept(visitor);
        return visitor.getSqlInfo();
    }

    /**
     *
     * 解析查询语句AST为mysql的查询SQL
     *
     * @param statement
     * @param resolver
     * @return
     */
    public static SelectSqlInfo parseSelectStatement(OqlSelectStatement statement, DataObjectResolver resolver) {
        return parseSelectStatement(statement, resolver, false);
    }

    /**
     *
     * 解析查询语句AST为mysql的查询SQL
     *
     * @param statement
     * @param resolver
     * @param parameterized
     * @return
     */
    public static SelectSqlInfo parseSelectStatement(OqlSelectStatement statement, DataObjectResolver resolver, boolean parameterized) {
        SelectSqlAstVisitor visitor = new SelectSqlAstVisitor(resolver, parameterized);
        statement.accept(visitor);
        return visitor.getSqlInfo();
    }

    /**
     * 解析插入语句AST为mysql的更新SQL
     *
     * @param statement
     * @param resolver
     * @return
     */
    public static UpdateSqlInfo parseUpdateStatement(OqlUpdateStatement statement, DataObjectResolver resolver) {
        return parseUpdateStatement(statement, resolver, false);
    }


    /**
     * 解析插入语句AST为mysql的更新SQL
     *
     * @param statement
     * @param resolver
     * @return
     */
    public static UpdateSqlInfo parseUpdateStatement(OqlUpdateStatement statement, DataObjectResolver resolver, boolean parameterized) {
        UpdateSqlAstVisitor visitor = new UpdateSqlAstVisitor(resolver, parameterized);
        statement.accept(visitor);
        return visitor.getSqlInfo();
    }

    /**
     * 解析插入语句AST为mysql的删除SQL
     *
     * @param statement
     * @param resolver
     * @return
     */
    public static DeleteSqlInfo parseDeleteStatement(OqlDeleteStatement statement, DataObjectResolver resolver) {
        return parseDeleteStatement(statement, resolver, false);
    }

    /**
     * 解析插入语句AST为mysql的删除SQL
     *
     * @param statement
     * @param resolver
     * @return
     */
    public static DeleteSqlInfo parseDeleteStatement(OqlDeleteStatement statement, DataObjectResolver resolver, boolean parameterized) {
        DeleteSqlAstVisitor visitor = new DeleteSqlAstVisitor(resolver, parameterized);
        statement.accept(visitor);
        return visitor.getSqlInfo();
    }
}
