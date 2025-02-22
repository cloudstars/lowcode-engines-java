package net.cf.form.repository.ast.util;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.SQLStatement;
import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlStatement;

import java.util.List;

/**
 * 用于测试 SQL 工具类
 *
 * @author clouds
 */
public class SqlTestUtils {

    /**
     * 判断 CodeFriend 的 SQL 语句的结构是否与 Druid 的 SQL 语句的结构一致
     *
     * @param sqls
     * @param druidSqls
     * @return
     */
    public static boolean equals(List<SqlStatement> sqls, List<SQLStatement> druidSqls) {
        assert (sqls != null && druidSqls != null);

        if (sqls.size() != druidSqls.size()) {
            return false;
        }

        for (int i = 0, l = sqls.size(); i < l; i++) {
            if (!SqlTestUtils.equals(sqls.get(i), druidSqls.get(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断 CodeFriend 的 SQL 语句的结构是否与 Druid 的 SQL 语句的结构一致
     *
     * @param sql
     * @param druidSql
     * @return
     */
    public static boolean equals(SqlStatement sql, SQLStatement druidSql) {
        SqlTypesAstVisitor sqlVisitor = new SqlTypesAstVisitor();
        sql.accept(sqlVisitor);
        List<SqlObject> sqlNodes = sqlVisitor.getNodes();

        DruidSQLTypesASTVisitor druidSqlVisitor = new DruidSQLTypesASTVisitor();
        druidSql.accept(druidSqlVisitor);
        List<SQLObject> druidSqlNodes = druidSqlVisitor.getNodes();

        return SqlTestUtils.isTypesEquals(sqlNodes, druidSqlNodes);
    }

    /**
     * 判断 CodeFriend 的 SQL 表达示的结构是否与 Druid 的 SQL 语句的结构一致
     *
     * @param expr
     * @param druidExpr
     * @return
     */
    public static boolean equals(SqlExpr expr, SQLExpr druidExpr) {
        SqlTypesAstVisitor sqlVisitor = new SqlTypesAstVisitor();
        expr.accept(sqlVisitor);
        List<SqlObject> sqlNodes = sqlVisitor.getNodes();

        DruidSQLTypesASTVisitor druidSqlVisitor = new DruidSQLTypesASTVisitor();
        druidExpr.accept(druidSqlVisitor);
        List<SQLObject> druidSqlNodes = druidSqlVisitor.getNodes();

        return SqlTestUtils.isTypesEquals(sqlNodes, druidSqlNodes);
    }


    /**
     * 判断两个列表长度以及每一个元素的类型是否匹配
     *
     * @param sqlNodes
     * @param druidSqlNodes
     * @return
     */
    private static boolean isTypesEquals(List<SqlObject> sqlNodes, List<SQLObject> druidSqlNodes) {
        assert (sqlNodes != null && druidSqlNodes != null);

        if (sqlNodes.size() != druidSqlNodes.size()) {
            return false;
        }

        for (int i = 0, l = sqlNodes.size(); i < l; i++) {
            String currSqlNodeClzName = sqlNodes.get(i).getClass().getSimpleName();
            String currDruidSqlNodeClzName = druidSqlNodes.get(i).getClass().getSimpleName();
            if ("SqlDecimalExpr".equals(currSqlNodeClzName) && "SQLNumberExpr".equals(currDruidSqlNodeClzName)) {
                // 属于特殊情况
                continue;
            }
            if (!currSqlNodeClzName.equalsIgnoreCase(currDruidSqlNodeClzName)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 忽略空白字符后是否相等
     *
     * @param sql
     * @param expected
     * @return
     */
    public static boolean equalsIgnoreWhiteSpace(String sql, String expected) {
        String s1 = sql.replaceAll("\\s", "");
        String s2 = expected.replaceAll("\\s", "");
        return s1.equalsIgnoreCase(s2);
    }
}
