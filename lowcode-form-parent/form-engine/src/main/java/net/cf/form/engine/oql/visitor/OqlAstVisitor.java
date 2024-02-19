package net.cf.form.engine.oql.visitor;

import net.cf.form.engine.oql.ast.OqlCommentHint;
import net.cf.form.engine.oql.ast.OqlObject;
import net.cf.form.engine.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.oql.ast.expr.identifier.*;
import net.cf.form.engine.oql.ast.expr.literal.*;
import net.cf.form.engine.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.oql.ast.expr.operation.OqlBinaryOpExprGroup;
import net.cf.form.engine.oql.ast.expr.operation.OqlNotExpr;
import net.cf.form.engine.oql.ast.statement.*;

/**
 * 语法树访问器
 *
 * @author clouds
 */
public interface OqlAstVisitor {

    /**
     * 访问一个OqlObject前
     *
     * @param x
     */
    default void preVisit(OqlObject x) {
    }

    /**
     * 访问一个OqlObject后
     *
     * @param x
     */
    default void postVisit(OqlObject x) {
    }


    /**
     * 是否允许访问 OqlCommentHint 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlCommentHint x) {
        return false;
    }

    /**
     * 结束访问 OqlCommentHint
     *
     * @param x
     */
    default void endVisit(OqlCommentHint x) {
    }

    /**
     * 是否允许访问 OqlNotExpr
     *
     * @param x
     * @return
     */
    default boolean visit(OqlNotExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlNotExpr
     *
     * @param x
     */
    default void endVisit(OqlNotExpr x) {
    }


    /**
     * 是否允许访问 OqlStringExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlCharExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlStringExpr
     *
     * @param x
     */
    default void endVisit(OqlCharExpr x) {
    }

    /**
     * 是否允许访问 OqlBooleanExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlBooleanExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlBooleanExpr
     *
     * @param x
     */
    default void endVisit(OqlBooleanExpr x) {
    }

    /**
     * 是否允许访问 OqlIntegerExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlIntegerExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlIntegerExpr
     *
     * @param x
     */
    default void endVisit(OqlIntegerExpr x) {
    }

    /**
     * 是否允许访问 OqlNumberExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlNumberExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlNumberExpr
     *
     * @param x
     */
    default void endVisit(OqlNumberExpr x) {
    }

    /**
     * 是否允许访问 OqlNullExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlNullExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlNullExpr
     *
     * @param x
     */
    default void endVisit(OqlNullExpr x) {
    }

    /**
     * 是否允许访问 OqlUndefinedExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlUndefinedExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlUndefinedExpr
     *
     * @param x
     */
    default void endVisit(OqlUndefinedExpr x) {
    }

    /**
     * 是否允许访问 OqlJsonArrayExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlJsonArrayExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlJsonArrayExpr 的儿子节点
     *
     * @param x
     */
    default void endVisit(OqlJsonArrayExpr x) {
    }

    /**
     * 是否允许访问 OqlJsonObjectExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlJsonObjectExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlJsonObjectExpr
     *
     * @param x
     */
    default void endVisit(OqlJsonObjectExpr x) {
    }

    /**
     * 是否允许访问 OqlListExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlListExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlListExpr
     *
     * @param x
     */
    default void endVisit(OqlListExpr x) {
    }

    /**
     * 是否允许访问 OqlIdentifierExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlIdentifierExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlIdentifierExpr
     *
     * @param x
     */
    default void endVisit(OqlIdentifierExpr x) {
    }

    /**
     * 是否允许访问 OqlPropertyExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlPropertyExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlPropertyExpr
     *
     * @param x
     */
    default void endVisit(OqlPropertyExpr x) {
    }

    /**
     * 是否允许访问 OqlVariantRefExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlVariantRefExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlVariantRefExpr
     *
     * @param x
     */
    default void endVisit(OqlVariantRefExpr x) {
    }

    /**
     * 是否允许访问 OqlBinaryOpExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlBinaryOpExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlBinaryOpExpr
     *
     * @param x
     */
    default void endVisit(OqlBinaryOpExpr x) {
    }


    /**
     * 是否允许访问 OqlBinaryOpExprGroup 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlBinaryOpExprGroup x) {
        return true;
    }

    /**
     * 结束访问 OqlBinaryOpExprGroup
     *
     * @param x
     */
    default void endVisit(OqlBinaryOpExprGroup x) {
    }

    /**
     * 是否允许访问 OqlMethodInvokeExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlMethodInvokeExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlMethodInvokeExpr
     *
     * @param x
     */
    default void endVisit(OqlMethodInvokeExpr x) {
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
     * 是否允许访问 OqlSelectItem 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlSelectItem x) {
        return true;
    }

    /**
     * 结束访问 OqlSelectItem
     *
     * @param x
     */
    default void endVisit(OqlSelectItem x) {
    }

    /**
     * 是否允许访问 OqlAllColumnExpr 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlAllFieldExpr x) {
        return true;
    }

    /**
     * 结束访问 OqlAllColumnExpr
     *
     * @param x
     */
    default void endVisit(OqlAllFieldExpr x) {
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
     * 是否允许访问 OqlWhereClause 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlWhereClause x) {
        return true;
    }

    /**
     * 结束访问 OqlObjectSource
     *
     * @param x
     */
    default void endVisit(OqlWhereClause x) {
    }

    /**
     * 是否允许访问 OqlSelectGroupBy 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlSelectGroupBy x) {
        return true;
    }

    /**
     * 结束访问 OqlSelectGroupBy
     *
     * @param x
     */
    default void endVisit(OqlSelectGroupBy x) {
    }

    /**
     * 是否允许访问 OqlSelectOrderBy 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlSelectOrderBy x) {
        return true;
    }

    /**
     * 结束访问 OqlSelectOrderBy
     *
     * @param x
     */
    default void endVisit(OqlSelectOrderBy x) {
    }

    /**
     * 是否允许访问 OqlSelectOrderByItem 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlSelectOrderByItem x) {
        return true;
    }

    /**
     * 结束访问 OqlSelectOrderBy
     *
     * @param x
     */
    default void endVisit(OqlSelectOrderByItem x) {
    }

    /**
     * 是否允许访问 OqlSelectLimit 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlSelectLimit x) {
        return true;
    }

    /**
     * 结束访问 OqlSelectLimit
     *
     * @param x
     */
    default void endVisit(OqlSelectLimit x) {
    }

    /**
     * 是否允许访问 OqlInsertStatement 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlInsertStatement x) {
        return true;
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
     * 是否允许访问 OqlInsertValues 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlInsertValues x) {
        return true;
    }

    /**
     * 结束访问 OqlInsertValues
     *
     * @param x
     */
    default void endVisit(OqlInsertValues x) {
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
     * 是否允许访问 OqlUpdateSetItem 的儿子节点
     *
     * @param x
     * @return
     */
    default boolean visit(OqlUpdateSetItem x) {
        return true;
    }

    /**
     * 结束访问 OqlUpdateSetItem
     *
     * @param x
     */
    default void endVisit(OqlUpdateSetItem x) {
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