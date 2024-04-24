package net.cf.object.engine.util;

import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.form.repository.sql.parser.SqlStatementParser;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oqlnew.info.OqlSelectInfos;
import net.cf.object.engine.oqlnew.parser.OqlSelectInfosParser;

import java.util.List;

public final class OqlStatementUtils {

    private OqlStatementUtils() {
    }

    /**
     * 解析OQL查询语句并返回OQL查询指令信息
     *
     * @param stmt
     * @param isBatch
     * @return
     */
    public static OqlSelectInfos parseSingleSelectStatement(OqlSelectStatement stmt, boolean isBatch) {
        OqlSelectInfosParser parser = new OqlSelectInfosParser(stmt, isBatch);
        return parser.parse();
    }

    /**
     * 解析并返回唯一的一条插入 SQL 语句
     *
     * @param sql
     * @return
     */
    public static SqlInsertStatement parseSingleSelectStatement(String sql) {
        SqlStatementParser parser = new SqlStatementParser(sql);
        List<SqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof SqlInsertStatement);
        return (SqlInsertStatement) statements.get(0);
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
