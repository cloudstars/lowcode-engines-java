package net.cf.object.engine.util;

import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.parser.OqlStatementParser;
import net.cf.object.engine.oql.parser.XObjectResolver;

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
        OqlStatementParser parser = new OqlStatementParser(resolver, oql);
        List<OqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof OqlSelectStatement);
        return (OqlSelectStatement) statements.get(0);
    }

    /**
     * 解析并返回唯一的一条插入 OQL 语句
     *
     * @param resolver
     * @param oql
     * @return
     */
    public static OqlInsertStatement parseSingleInsertStatement(XObjectResolver resolver, String oql) {
        OqlStatementParser parser = new OqlStatementParser(resolver, oql);
        List<OqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof OqlInsertStatement);
        return (OqlInsertStatement) statements.get(0);
    }

    /**
     * 解析并返回唯一的一条更新 OQL 语句
     *
     * @param resolver
     * @param oql
     * @return
     */
    public static OqlUpdateStatement parseSingleUpdateStatement(XObjectResolver resolver, String oql) {
        OqlStatementParser parser = new OqlStatementParser(resolver, oql);
        List<OqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof OqlUpdateStatement);
        return (OqlUpdateStatement) statements.get(0);
    }

    /**
     * 解析并返回唯一的一条删除 OQL 语句
     *
     * @param resolver
     * @param oql
     * @return
     */
    public static OqlDeleteStatement parseSingleDeleteStatement(XObjectResolver resolver, String oql) {
        OqlStatementParser parser = new OqlStatementParser(resolver, oql);
        List<OqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof OqlDeleteStatement);
        return (OqlDeleteStatement) statements.get(0);
    }

}
