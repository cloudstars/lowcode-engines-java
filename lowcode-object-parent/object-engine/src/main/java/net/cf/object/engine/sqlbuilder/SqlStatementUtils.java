package net.cf.object.engine.sqlbuilder;

import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.sqlbuilder.select.SelectOqlAstVisitor;
import net.cf.object.engine.sqlbuilder.select.SelectSqlStatementBuilder;

public final class SqlStatementUtils {

    private SqlStatementUtils() {
    }

    /**
     * 将OQL查询转为SQL查询
     *
     * @param stmt
     * @return
     */
    public static SqlSelectStatement fromOqlSelect(OqlSelectStatement stmt) {
        SelectSqlStatementBuilder builder = new SelectSqlStatementBuilder();
        SelectOqlAstVisitor visitor = new SelectOqlAstVisitor(builder);
        stmt.accept(visitor);
        return builder.build();
    }
}
