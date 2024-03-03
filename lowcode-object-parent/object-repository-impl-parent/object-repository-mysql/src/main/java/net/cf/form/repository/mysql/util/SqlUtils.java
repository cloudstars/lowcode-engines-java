package net.cf.form.repository.mysql.util;

import net.cf.form.repository.sql.ast.statement.SqlStatement;
import net.cf.form.repository.sql.visitor.SqlAstOutputVisitor;

/**
 * SQL工具类
 *
 * @author clouds
 */
public final class SqlUtils {

    private SqlUtils() {
    }

    /**
     * 将SQL语句转为SQL文本
     *
     * @param stmt
     * @return
     */
    public static String toSqlText(SqlStatement stmt) {
        StringBuilder builder = new StringBuilder();
        SqlAstOutputVisitor visitor = new SqlAstOutputVisitor(builder);
        stmt.accept(visitor);
        return builder.toString();
    }
}
