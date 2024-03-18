package net.cf.object.engine.oql.util;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.parser.OqlStatementParser;
import net.cf.object.engine.oql.visitor.OqlAstOutputVisitor;

import java.util.List;

/**
 * OQL工具类
 *
 * @author clouds
 */
public class OqlUtils {

    /**
     * 创建一个输出访问器
     *
     * @param out
     * @return
     */
    public static OqlAstOutputVisitor createAstOutputVisitor(Appendable out) {
        return new OqlAstOutputVisitor(out);
    }

    /**
     * 将一个OQL模型转为字符串
     *
     * @param object
     * @return
     */
    public static String toOqlString(SqlObject object) {
        StringBuilder out = new StringBuilder();
        object.accept(new OqlAstOutputVisitor(out));
        return out.toString();
    }

    /**
     * 解析并返回唯一的一条插入 OQL 语句
     *
     * @param object
     * @param oql
     * @return
     */
    public static OqlInsertStatement parseSingleInsertStatement(XObject object, String oql) {
        OqlStatementParser parser = new OqlStatementParser(object, oql);
        List<OqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof OqlInsertStatement);
        return (OqlInsertStatement) statements.get(0);
    }

    /**
     * 解析并返回唯一的一条更新 OQL 语句
     *
     * @param object
     * @param oql
     * @return
     */
    public static OqlUpdateStatement parseSingleUpdateStatement(XObject object, String oql) {
        OqlStatementParser parser = new OqlStatementParser(object, oql);
        List<OqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof OqlUpdateStatement);
        return (OqlUpdateStatement) statements.get(0);
    }

    /**
     * 解析并返回唯一的一条删除 OQL 语句
     *
     * @param object
     * @param oql
     * @return
     */
    public static OqlDeleteStatement parseSingleDeleteStatement(XObject object, String oql) {
        OqlStatementParser parser = new OqlStatementParser(object, oql);
        List<OqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof OqlDeleteStatement);
        return (OqlDeleteStatement) statements.get(0);
    }

    /**
     * 解析并返回唯一的一条查询 OQL 语句
     *
     * @param object
     * @param oql
     * @return
     */
    public static OqlSelectStatement parseSingleSelectStatement(XObject object, String oql) {
        OqlStatementParser parser = new OqlStatementParser(object, oql);
        List<OqlStatement> statements = parser.parseStatementList();
        assert (statements.size() == 1 && statements.get(0) instanceof OqlSelectStatement);
        return (OqlSelectStatement) statements.get(0);
    }

}
