package net.cf.form.repository.sql.visitor;

import net.cf.form.repository.sql.ast.SqlCommentHint;
import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.expr.SqlCaseExpr;
import net.cf.form.repository.sql.ast.expr.SqlListExpr;
import net.cf.form.repository.sql.ast.expr.identifier.*;
import net.cf.form.repository.sql.ast.expr.literal.*;
import net.cf.form.repository.sql.ast.expr.operation.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.operation.SqlBinaryOpExprGroup;
import net.cf.form.repository.sql.ast.expr.operation.SqlInListExpr;
import net.cf.form.repository.sql.ast.expr.operation.SqlNotExpr;
import net.cf.form.repository.sql.ast.statement.*;

/**
 * 语法树访问器
 *
 * @author clouds
 */
public interface SqlAstVisitor {

    /**
     * 访问一个SqlObject前
     *
     * @param x
     */
    default void preVisit(SqlObject x) {
    }

    /**
     * 访问一个SqlObject后
     *
     * @param x
     */
    default void postVisit(SqlObject x) {
    }


    /**
     * 开始访问 SqlCommentHint 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlCommentHint x) {
        return false;
    }

    /**
     * 结束访问 SqlCommentHint
     *
     * @param x
     */
    default void endVisit(SqlCommentHint x) {
    }

    /**
     * 是否允许访问 SqlNotExpr
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlNotExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlNotExpr
     *
     * @param x
     */
    default void endVisit(SqlNotExpr x) {
    }


    /**
     * 开始访问 SqlStringExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlCharExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlStringExpr
     *
     * @param x
     */
    default void endVisit(SqlCharExpr x) {
    }

    /**
     * 开始访问 SqlBooleanExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlBooleanExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlBooleanExpr
     *
     * @param x
     */
    default void endVisit(SqlBooleanExpr x) {
    }

    /**
     * 开始访问 SqlIntegerExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlIntegerExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlIntegerExpr
     *
     * @param x
     */
    default void endVisit(SqlIntegerExpr x) {
    }

    /**
     * 开始访问 SqlNumberExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlNumberExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlNumberExpr
     *
     * @param x
     */
    default void endVisit(SqlNumberExpr x) {
    }

    /**
     * 开始访问 SqlDecimalExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlDecimalExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlDecimalExpr
     *
     * @param x
     */
    default void endVisit(SqlDecimalExpr x) {
    }

    /**
     * 开始访问 SqlTimestampExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlDateTimeExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlTimestampExpr
     *
     * @param x
     */
    default void endVisit(SqlDateTimeExpr x) {
    }


    /**
     * 开始访问 SqlDateExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlDateExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlDateExpr
     *
     * @param x
     */
    default void endVisit(SqlDateExpr x) {
    }

    /**
     * 开始访问 SqlTimeExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlTimeExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlTimeExpr
     *
     * @param x
     */
    default void endVisit(SqlTimeExpr x) {
    }

    /**
     * 开始访问 SqlNullExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlNullExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlNullExpr
     *
     * @param x
     */
    default void endVisit(SqlNullExpr x) {
    }

    /**
     * 开始访问 SqlListExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlListExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlListExpr
     *
     * @param x
     */
    default void endVisit(SqlListExpr x) {
    }

    /**
     * 开始访问 SqlIdentifierExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlIdentifierExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlIdentifierExpr
     *
     * @param x
     */
    default void endVisit(SqlIdentifierExpr x) {
    }

    /**
     * 开始访问 SqlPropertyExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlPropertyExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlPropertyExpr
     *
     * @param x
     */
    default void endVisit(SqlPropertyExpr x) {
    }

    /**
     * 开始访问 SqlVariantRefExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlVariantRefExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlVariantRefExpr
     *
     * @param x
     */
    default void endVisit(SqlVariantRefExpr x) {
    }

    /**
     * 开始访问 SqlCaseExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlCaseExpr x) {
        return true;
    }


    /**
     * 结束访问 SqlCaseExpr
     *
     * @param x
     */
    default void endVisit(SqlCaseExpr x) {
    }

    /**
     * 开始访问 SqlCaseExpr.Item 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlCaseExpr.Item x) {
        return true;
    }

    /**
     * 结束访问 SqlCaseExpr.Item
     *
     * @param x
     */
    default void endVisit(SqlCaseExpr.Item x) {
    }

    /**
     * 开始访问 SqlBinaryOpExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlBinaryOpExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlBinaryOpExpr
     *
     * @param x
     */
    default void endVisit(SqlBinaryOpExpr x) {
    }

    /**
     * 开始访问 SqlInListExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlInListExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlInListExpr
     *
     * @param x
     */
    default void endVisit(SqlInListExpr x) {
    }


    /**
     * 开始访问 SqlBinaryOpExprGroup 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlBinaryOpExprGroup x) {
        return true;
    }

    /**
     * 结束访问 SqlBinaryOpExprGroup
     *
     * @param x
     */
    default void endVisit(SqlBinaryOpExprGroup x) {
    }

    /**
     * 开始访问 SqlMethodInvokeExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlMethodInvokeExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlMethodInvokeExpr
     *
     * @param x
     */
    default void endVisit(SqlMethodInvokeExpr x) {
    }

    /**
     * 开始访问 SqlSelect 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlSelectStatement x) {
        return true;
    }

    /**
     * 结束访问 SqlSelect
     *
     * @param x
     */
    default void endVisit(SqlSelectStatement x) {
    }

    /**
     * 开始访问 SqlSelect 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlSelect x) {
        return true;
    }

    /**
     * 结束访问 SqlSelect
     *
     * @param x
     */
    default void endVisit(SqlSelect x) {
    }

    /**
     * 开始访问 SqlSelectItem 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlSelectItem x) {
        return true;
    }

    /**
     * 结束访问 SqlSelectItem
     *
     * @param x
     */
    default void endVisit(SqlSelectItem x) {
    }

    /**
     * 开始访问 SqlAllColumnExpr 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlAllColumnExpr x) {
        return true;
    }

    /**
     * 结束访问 SqlAllColumnExpr
     *
     * @param x
     */
    default void endVisit(SqlAllColumnExpr x) {
    }

    /**
     * 开始访问 SqlExprTableSource 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlExprTableSource x) {
        return true;
    }

    /**
     * 结束访问 SqlExprTableSource
     *
     * @param x
     */
    default void endVisit(SqlExprTableSource x) {
    }

    /**
     * 开始访问 SqlJoinTableSource 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlJoinTableSource x) {
        return true;
    }

    /**
     * 结束访问 SqlJoinTableSource 节点
     *
     * @param x
     */
    default void endVisit(SqlJoinTableSource x) {
    }

    /**
     * 开始访问 SqlSubqueryTableSource 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlSubqueryTableSource x) {
        return true;
    }

    /**
     * 结束访问 SqlSubqueryTableSource 节点
     *
     * @param x
     */
    default void endVisit(SqlSubqueryTableSource x) {
    }

    /**
     * 开始访问 SqlSelectGroupBy 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlSelectGroupByClause x) {
        return true;
    }

    /**
     * 结束访问 SqlSelectGroupBy
     *
     * @param x
     */
    default void endVisit(SqlSelectGroupByClause x) {
    }

    /**
     * 开始访问 SqlSelectOrderBy 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlOrderBy x) {
        return true;
    }

    /**
     * 结束访问 SqlSelectOrderBy
     *
     * @param x
     */
    default void endVisit(SqlOrderBy x) {
    }

    /**
     * 开始访问 SqlSelectOrderByItem 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlSelectOrderByItem x) {
        return true;
    }

    /**
     * 结束访问 SqlSelectOrderBy
     *
     * @param x
     */
    default void endVisit(SqlSelectOrderByItem x) {
    }

    /**
     * 开始访问 SqlSelectLimit 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlLimit x) {
        return true;
    }

    /**
     * 结束访问 SqlSelectLimit
     *
     * @param x
     */
    default void endVisit(SqlLimit x) {
    }

    /**
     * 开始访问 SqlInsertStatement 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlInsertStatement x) {
        return true;
    }

    /**
     * 结束访问 SqlInsertStatement
     *
     * @param x
     */
    default void endVisit(SqlInsertStatement x) {
    }

    /**
     * 开始访问 SqlInsertValues 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlInsertStatement.ValuesClause x) {
        return true;
    }

    /**
     * 结束访问 SqlInsertValues
     *
     * @param x
     */
    default void endVisit(SqlInsertStatement.ValuesClause x) {
    }

    /**
     * 开始访问 SqlUpdateStatement 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlUpdateStatement x) {
        return true;
    }

    /**
     * 结束访问 SqlUpdateStatement
     *
     * @param x
     */
    default void endVisit(SqlUpdateStatement x) {
    }

    /**
     * 开始访问 SqlUpdateSetItem 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlUpdateSetItem x) {
        return true;
    }

    /**
     * 结束访问 SqlUpdateSetItem
     *
     * @param x
     */
    default void endVisit(SqlUpdateSetItem x) {
    }

    /**
     * 开始访问 SqlDeleteStatement 节点
     *
     * @param x
     * @return 是否允许访问当前节点的子节点
     */
    default boolean visit(SqlDeleteStatement x) {
        return true;
    }

    /**
     * 结束访问 SqlDeleteStatement
     *
     * @param x
     */
    default void endVisit(SqlDeleteStatement x) {
    }

}