package net.cf.object.engine.sqlbuilder;

import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.sqlbuilder.delete.OqlDeleteAstVisitor;
import net.cf.object.engine.sqlbuilder.delete.SqlDeleteStatementBuilder;
import net.cf.object.engine.sqlbuilder.insert.OqlInsertAstVisitor;
import net.cf.object.engine.sqlbuilder.insert.SqlInsertStatementBuilder;
import net.cf.object.engine.sqlbuilder.select.OqlSelectAstVisitor;
import net.cf.object.engine.sqlbuilder.select.SqlSelectStatementBuilder;
import net.cf.object.engine.sqlbuilder.update.OqlUpdateAstVisitor;
import net.cf.object.engine.sqlbuilder.update.SqlUpdateStatementBuilder;

public final class OqlStatementUtils {

    private OqlStatementUtils() {
    }

    /**
     * 将OQL查询转为SQL查询
     *
     * @param stmt
     * @return
     */
    public static SqlSelectStatement toSqlSelect(final OqlSelectStatement stmt) {
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        OqlSelectAstVisitor visitor = new OqlSelectAstVisitor(builder);
        stmt.accept(visitor);
        return builder.build();
    }

    /**
     * 将OQL插入转为SQL插入
     *
     * @param stmt
     * @return
     */
    public static SqlInsertStatement toSqlInsert(final OqlInsertStatement stmt) {
        SqlInsertStatementBuilder builder = new SqlInsertStatementBuilder();
        OqlInsertAstVisitor visitor = new OqlInsertAstVisitor(builder);
        stmt.accept(visitor);
        return builder.build();
    }

    /**
     * 将OQL更新转为SQL更新
     *
     * @param stmt
     * @return
     */
    public static SqlUpdateStatement toSqlUpdate(final OqlUpdateStatement stmt) {
        SqlUpdateStatementBuilder builder = new SqlUpdateStatementBuilder();
        OqlUpdateAstVisitor visitor = new OqlUpdateAstVisitor(builder);
        stmt.accept(visitor);
        return builder.build();
    }

    /**
     * 将OQL更新转为SQL删除
     *
     * @param stmt
     * @return
     */
    public static SqlDeleteStatement toSqlDelete(final OqlDeleteStatement stmt) {
        SqlDeleteStatementBuilder builder = new SqlDeleteStatementBuilder();
        OqlDeleteAstVisitor visitor = new OqlDeleteAstVisitor(builder);
        stmt.accept(visitor);
        return builder.build();
    }

    /**
     * 根据标识符的字段名重新生成列句的SQL标识符表达式
     *
     * @param x
     * @return
     */
    public static SqlIdentifierExpr toSqlIdentifierExpr(final SqlIdentifierExpr x, XObject object) {
        SqlIdentifierExpr cloneExpr = x.cloneMe();
        String fieldName = cloneExpr.getName();
        cloneExpr.setName(object.getField(fieldName).getColumnName());
        return cloneExpr;
    }
}
