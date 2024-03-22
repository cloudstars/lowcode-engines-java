package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAggregateExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlMethodInvokeExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;

public enum ExprTypeEnum {
    /**
     * 表达式
     * 函数
     * 字段
     * 通用
     */
    EXPRESSION,
    METHOD,
    PARAM,
    COMMON,
    AGGR;


    public static ExprTypeEnum match(SqlExpr sqlExpr) {
        if (sqlExpr instanceof SqlAggregateExpr) {
            return AGGR;
        }
        if (sqlExpr instanceof SqlMethodInvokeExpr) {
            return METHOD;
        }
        if (sqlExpr instanceof SqlIdentifierExpr) {
            return PARAM;
        }
        if (sqlExpr instanceof SqlBinaryOpExpr) {
            return EXPRESSION;
        }
        return COMMON;
    }


    public boolean isMethod() {
        return this == AGGR || this == METHOD;
    }

    public boolean shouldGetOriginExpression() {
        return this != PARAM;
    }

}
