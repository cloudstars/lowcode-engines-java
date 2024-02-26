package net.cf.form.repository.sql.util;

import net.cf.form.repository.sql.ast.statement.*;
import net.cf.form.repository.sql.parser.SqlStatementParser;

import java.util.List;

public final class SqlStatementUtils {

    private SqlStatementUtils() {
    }

    /**
     * 解析并返回唯一的一条插入 SQL 语句
     *
     * @param sql
     * @return
     */
    public static SqlInsertStatement parseSingleInsertStatement(String sql) {
        SqlStatementParser parser = new SqlStatementParser(sql);
        List<SqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof SqlInsertStatement);
        return (SqlInsertStatement) statements.get(0);
    }

    /**
     * 解析并返回唯一的一条更新 SQL 语句
     *
     * @param sql
     * @return
     */
    public static SqlUpdateStatement parseSingleUpdateStatement(String sql) {
        SqlStatementParser parser = new SqlStatementParser(sql);
        List<SqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof SqlUpdateStatement);
        return (SqlUpdateStatement) statements.get(0);
    }

    /**
     * 解析并返回唯一的一条删除 SQL 语句
     *
     * @param sql
     * @return
     */
    public static SqlDeleteStatement parseSingleDeleteStatement(String sql) {
        SqlStatementParser parser = new SqlStatementParser(sql);
        List<SqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof SqlDeleteStatement);
        return (SqlDeleteStatement) statements.get(0);
    }

    /**
     * 解析并返回唯一的一条查询 SQL 语句
     *
     * @param sql
     * @return
     */
    public static SqlSelectStatement parseSingleSelectStatement(String sql) {
        SqlStatementParser parser = new SqlStatementParser(sql);
        List<SqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof SqlSelectStatement);
        return (SqlSelectStatement) statements.get(0);
    }

}
