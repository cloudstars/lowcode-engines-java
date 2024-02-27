package net.cf.object.engine.oql.visitor;

import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;
import net.cf.object.engine.oql.ast.*;

/**
 * 语法树访问器
 *
 * @author clouds
 */
public interface OqlAstVisitor extends SqlAstVisitor {

    /**
     * 是否允许访问 OqlJsonArrayExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(SqlJsonArrayExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlJsonArrayExpr 的儿子节点
     *
     * @param x
     */
    default void endVisit(SqlJsonArrayExpr x) {
    }

    /**
     * 是否允许访问 OqlJsonObjectExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(SqlJsonObjectExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlJsonObjectExpr
     *
     * @param x
     */
    default void endVisit(SqlJsonObjectExpr x) {
    }

    /**
     * 是否允许访问 OqlObjectExpandExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlObjectExpandExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlObjectExpandExpr
     *
     * @param x
     */
    default void endVisit(OqlObjectExpandExpr x) {
    }


    /**
     * 是否允许访问 OqlSelect 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlSelectStatement x) {
        return true;
    }

    /**
     * 结束访问 OqlSelect
     *
     * @param x
     */
    default void endVisit(OqlSelectStatement x) {
    }

    /**
     * 是否允许访问 OqlSelect 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlSelect x) {
        return true;
    }

    /**
     * 结束访问 OqlSelect
     *
     * @param x
     */
    default void endVisit(OqlSelect x) {
    }

    /**
     * 是否允许访问 OqlExprObjectSource 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlExprObjectSource x) {
        return true;
    }

    /**
     * 结束访问 OqlExprObjectSource
     *
     * @param x
     */
    default void endVisit(OqlExprObjectSource x) {
    }

    /**
     * 结束访问 OqlInsertStatement
     *
     * @param x
     */
    default void endVisit(OqlInsertStatement x) {
    }

    /**
     * 是否允许访问 OqlInsert 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlInsertInto x) {
        return true;
    }

    /**
     * 结束访问 OqlInsert
     *
     * @param x
     */
    default void endVisit(OqlInsertInto x) {
    }

    /**
     * 是否允许访问 OqlUpdateStatement 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlUpdateStatement x) {
        return true;
    }

    /**
     * 结束访问 OqlUpdateStatement
     *
     * @param x
     */
    default void endVisit(OqlUpdateStatement x) {
    }

    /**
     * 是否允许访问 OqlDeleteStatement 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlDeleteStatement x) {
        return true;
    }

    /**
     * 结束访问 OqlDeleteStatement
     *
     * @param x
     */
    default void endVisit(OqlDeleteStatement x) {
    }

}