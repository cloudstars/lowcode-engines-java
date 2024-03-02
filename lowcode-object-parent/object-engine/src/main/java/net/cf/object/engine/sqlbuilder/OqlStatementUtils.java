package net.cf.object.engine.sqlbuilder;

import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.sqlbuilder.insert.InsertOqlAstVisitor;
import net.cf.object.engine.sqlbuilder.insert.InsertSqlStatementBuilder;
import net.cf.object.engine.sqlbuilder.select.SelectOqlAstVisitor;
import net.cf.object.engine.sqlbuilder.select.SelectSqlStatementBuilder;

public final class OqlStatementUtils {

    private OqlStatementUtils() {
    }

    /**
     * 将OQL查询转为SQL查询
     *
     * @param stmt
     * @return
     */
    public static SqlSelectStatement toSqlSelect(OqlSelectStatement stmt) {
        SelectSqlStatementBuilder builder = new SelectSqlStatementBuilder();
        SelectOqlAstVisitor visitor = new SelectOqlAstVisitor(builder);
        stmt.accept(visitor);
        return builder.build();
    }

    /**
     * 将OQL插入转为SQL插入
     *
     * @param stmt
     * @return
     */
    public static SqlInsertStatement toSqlInsert(OqlInsertStatement stmt) {
        InsertSqlStatementBuilder builder = new InsertSqlStatementBuilder();
        InsertOqlAstVisitor visitor = new InsertOqlAstVisitor(builder);
        stmt.accept(visitor);
        return builder.build();
    }
}
