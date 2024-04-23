package net.cf.object.engine.util;

import net.cf.form.repository.sql.ast.statement.*;
import net.cf.form.repository.sql.parser.SqlStatementParser;
import net.cf.object.engine.oql.stmt.OqlSelectStatementParser;
import net.cf.object.engine.oql.parser.XObjectResolver;
import net.cf.object.engine.oql.stmt.OqlSelectStatement;

import java.util.List;

public final class OqlStatementUtils {

    private OqlStatementUtils() {
    }

    /**
     * 解析并返回唯一的一条查询 OQL 语句
     *
     * @param resolver
     * @param oql
     * @return
     */
    public static OqlSelectStatement parseSingleSelectStatement(XObjectResolver resolver, String oql) {
        OqlSelectStatementParser parser = new OqlSelectStatementParser(resolver, oql);
        return parser.parse();
    }

    /**
     * 解析并返回唯一的一条查询 OQL 语句
     *
     * @param resolver
     * @param oql
     * @return
     */
    public static OqlSelectStatement parseSingleSelectStatement(XObjectResolver resolver, String oql, boolean isBatch) {
        OqlSelectStatementParser parser = new OqlSelectStatementParser(resolver, oql, isBatch);
        return parser.parse();
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

}
