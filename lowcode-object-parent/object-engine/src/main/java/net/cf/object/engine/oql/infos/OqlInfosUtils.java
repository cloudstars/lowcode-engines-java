package net.cf.object.engine.oql.infos;

import net.cf.form.repository.sql.ast.statement.SqlStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.form.repository.sql.parser.SqlStatementParser;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;

import java.util.List;
import java.util.Map;

public final class OqlInfosUtils {

    private OqlInfosUtils() {
    }


    /**
     * 解析OQL查询语句并返回OQL查询指令信息
     *
     * @param stmt
     * @param isBatch
     * @return
     */
    public static OqlSelectInfos parseOqlSelectInfos(OqlSelectStatement stmt, boolean isBatch) {
        OqlSelectInfosParser parser = new OqlSelectInfosParser(stmt, isBatch);
        return parser.parse();
    }

    /**
     * 解析OQL查询语句并返回OQL查询指令信息
     *
     * @param stmt
     * @param paramMap
     * @param isQueryList
     * @return
     */
    public static OqlSelectInfos parseOqlSelectInfos(OqlSelectStatement stmt, Map<String, Object> paramMap, boolean isQueryList) {
        OqlSelectInfosParser parser = new OqlSelectInfosParser(stmt, paramMap, isQueryList);
        return parser.parse();
    }

    /**
     * 解析OQL插入语句并返回OQL插入指令信息
     *
     * @param stmt
     * @param paramMap
     * @return
     */
    public static OqlInsertInfos parseOqlInsertInfos(OqlInsertStatement stmt, Map<String, Object> paramMap) {
        OqlInsertInfosParser parser = new OqlInsertInfosParser(stmt, paramMap);
        return parser.parse();
    }

    /**
     * 解析OQL插入语句并返回OQL插入指令信息
     *
     * @param stmt
     * @param paramMaps
     * @return
     */
    public static OqlInsertInfos parseOqlInsertInfos(OqlInsertStatement stmt, List<Map<String, Object>> paramMaps) {
        OqlInsertInfosParser parser = new OqlInsertInfosParser(stmt, paramMaps);
        return parser.parse();
    }

    /**
     * 解析OQL插入语句并返回OQL更新指令信息
     *
     * @param stmt
     * @param paramMap
     * @return
     */
    public static OqlUpdateInfos parseOqlUpdateInfos(OqlUpdateStatement stmt, Map<String, Object> paramMap) {
        OqlUpdateInfosParser parser = new OqlUpdateInfosParser(stmt, paramMap);
        return parser.parse();
    }

    /**
     * 解析OQL插入语句并返回OQL更新指令信息
     *
     * @param stmt
     * @param paramMaps
     * @return
     */
    public static OqlUpdateInfos parseOqlUpdateInfos(OqlUpdateStatement stmt, List<Map<String, Object>> paramMaps) {
        OqlUpdateInfosParser parser = new OqlUpdateInfosParser(stmt, paramMaps);
        return parser.parse();
    }

    /**
     * 解析OQL插入语句并返回OQL删除指令信息
     *
     * @param stmt
     * @param paramMap
     * @return
     */
    public static OqlDeleteInfos parseOqlDeleteInfos(OqlDeleteStatement stmt, Map<String, Object> paramMap) {
        OqlDeleteInfosParser parser = new OqlDeleteInfosParser(stmt, paramMap);
        return parser.parse();
    }

    /**
     * 解析OQL插入语句并返回OQL删除指令信息
     *
     * @param stmt
     * @param paramMaps
     * @return
     */
    public static OqlDeleteInfos parseOqlDeleteInfos(OqlDeleteStatement stmt, List<Map<String, Object>> paramMaps) {
        OqlDeleteInfosParser parser = new OqlDeleteInfosParser(stmt, paramMaps);
        return parser.parse();
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

}
