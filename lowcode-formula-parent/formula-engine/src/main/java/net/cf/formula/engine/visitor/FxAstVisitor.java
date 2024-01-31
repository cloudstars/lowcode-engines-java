package net.cf.formula.engine.visitor;

import net.cf.formula.engine.ast.FxCommentHint;
import net.cf.formula.engine.ast.FxObject;
import net.cf.formula.engine.ast.expr.FxNotExpr;
import net.cf.formula.engine.ast.expr.identifier.FxIdentifierExpr;
import net.cf.formula.engine.ast.expr.identifier.FxMethodInvokeExpr;
import net.cf.formula.engine.ast.expr.identifier.FxPropertyExpr;
import net.cf.formula.engine.ast.expr.literal.*;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOpExpr;
import net.cf.formula.engine.ast.expr.operation.FxUnaryOpExpr;

/**
 * 语法树访问器
 *
 * @author clouds
 */
public interface FxAstVisitor {

    /**
     * 访问一个FxObject前
     *
     * @param x
     */
    default void preVisit(FxObject x) {
    }

    /**
     * 访问一个FxObject后
     *
     * @param x
     */
    default void postVisit(FxObject x) {
    }


    /**
     * 是否允许访问 FxCommentHint
     *
     * @param x
     * @return
     */
    default boolean visit(FxCommentHint x) {
        return true;
    }

    /**
     * 结束访问 FxCommentHint
     *
     * @param x
     */
    default void endVisit(FxCommentHint x) {
    }

    /**
     * 是否允许访问 FxNotExpr
     *
     * @param x
     * @return
     */
    default boolean visit(FxNotExpr x) {
        return true;
    }

    /**
     * 结束访问 FxNotExpr
     *
     * @param x
     */
    default void endVisit(FxNotExpr x) {
    }


    /**
     * 是否允许访问 FxCharExpr
     *
     * @param x
     * @return
     */
    default boolean visit(FxCharExpr x) {
        return true;
    }


    /**
     * 是否允许访问 FxBooleanExpr
     *
     * @param x
     * @return
     */
    default boolean visit(FxBooleanExpr x) {
        return true;
    }

    /**
     * 结束访问 FxBooleanExpr
     *
     * @param x
     */
    default void endVisit(FxBooleanExpr x) {
    }

    /**
     * 结束访问 FxCharExpr
     *
     * @param x
     */
    default void endVisit(FxCharExpr x) {
    }

    /**
     * 是否允许访问 FxIntegerExpr
     *
     * @param x
     * @return
     */
    default boolean visit(FxIntegerExpr x) {
        return true;
    }

    /**
     * 结束访问 FxIntegerExpr
     *
     * @param x
     */
    default void endVisit(FxIntegerExpr x) {
    }

    /**
     * 是否允许访问 FxNumberExpr
     *
     * @param x
     * @return
     */
    default boolean visit(FxNumberExpr x) {
        return true;
    }

    /**
     * 结束访问 FxNumberExpr
     *
     * @param x
     */
    default void endVisit(FxNumberExpr x) {
    }

    /**
     * 是否允许访问 FxNullExpr
     *
     * @param x
     * @return
     */
    default boolean visit(FxNullExpr x) {
        return true;
    }

    /**
     * 结束访问 FxNullExpr
     *
     * @param x
     */
    default void endVisit(FxNullExpr x) {
    }

    /**
     * 是否允许访问 FxJsonArrayExpr
     *
     * @param x
     * @return
     */
    default boolean visit(FxJsonArrayExpr x) {
        return true;
    }

    /**
     * 结束访问 FxJsonArrayExpr
     *
     * @param x
     */
    default void endVisit(FxJsonArrayExpr x) {
    }

    /**
     * 是否允许访问 FxJsonObjectExpr
     *
     * @param x
     * @return
     */
    default boolean visit(FxJsonObjectExpr x) {
        return true;
    }

    /**
     * 结束访问 FxJsonObjectExpr
     *
     * @param x
     */
    default void endVisit(FxJsonObjectExpr x) {
    }



    /**
     * 是否允许访问 FxIdentifierExpr
     *
     * @param x
     * @return
     */
    default boolean visit(FxIdentifierExpr x) {
        return true;
    }

    /**
     * 结束访问 FxIdentifierExpr
     *
     * @param x
     */
    default void endVisit(FxIdentifierExpr x) {
    }

    /**
     * 是否允许访问 FxPropertyExpr
     *
     * @param x
     * @return
     */
    default boolean visit(FxPropertyExpr x) {
        return true;
    }

    /**
     * 结束访问 FxPropertyExpr
     *
     * @param x
     */
    default void endVisit(FxPropertyExpr x) {
    }

    /**
     * 是否允许访问 FxBinaryOpExpr
     *
     * @param x
     * @return
     */
    default boolean visit(FxUnaryOpExpr x) {
        return true;
    }

    /**
     * 结束访问 FxBinaryOpExpr
     *
     * @param x
     */
    default void endVisit(FxUnaryOpExpr x) {
    }

    /**
     * 是否允许访问 FxBinaryOpExpr
     *
     * @param x
     * @return
     */
    default boolean visit(FxBinaryOpExpr x) {
        return true;
    }

    /**
     * 结束访问 FxBinaryOpExpr
     *
     * @param x
     */
    default void endVisit(FxBinaryOpExpr x) {
    }

    /**
     * 是否允许访问 FxMethodInvokeExpr
     *
     * @param x
     * @return
     */
    default boolean visit(FxMethodInvokeExpr x) {
        return true;
    }

    /**
     * 结束访问 FxMethodInvokeExpr
     *
     * @param x
     */
    default void endVisit(FxMethodInvokeExpr x) {
    }
}