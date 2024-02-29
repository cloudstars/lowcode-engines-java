package net.cf.form.repository.mysql.data;

import net.cf.form.repository.mysql.data.select.SelectSqlAstVisitor;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;

public final class SqlStatementUtils {

    private SqlStatementUtils() {
    }

    /**
     * 将OQL查询转为SQL查询
     *
     * @param stmt
     * @return
     */
    public static String toSql(SqlSelectStatement stmt) {
        StringBuilder builder = new StringBuilder();
        SelectSqlAstVisitor visitor = new SelectSqlAstVisitor(builder);
        stmt.accept(visitor);
        return builder.toString();
    }
}
