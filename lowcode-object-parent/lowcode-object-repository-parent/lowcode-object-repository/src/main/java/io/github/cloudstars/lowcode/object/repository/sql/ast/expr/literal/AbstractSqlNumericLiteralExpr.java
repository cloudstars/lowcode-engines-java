package io.github.cloudstars.lowcode.object.repository.sql.ast.expr.literal;

import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.AbstractSqlExprImpl;

/**
 * 数字型时字面量表达式
 *
 * @author clouds 
 */
public abstract class AbstractSqlNumericLiteralExpr extends AbstractSqlExprImpl implements SqlLiteralExpr {

    protected Number number;

    public AbstractSqlNumericLiteralExpr() {
    }

    public AbstractSqlNumericLiteralExpr(Number number) {
        this.number = number;
    }

    /**
     * 获取数值
     *
     * @return
     */
    public Number getNumber() {
        return this.number;
    }

    /**
     * 设置数值
     *
     * @param number
     */
    public void setNumber(Number number) {
        this.number = number;
    }

    /**
     * 克隆
     *
     * @return
     */
    public abstract AbstractSqlNumericLiteralExpr cloneMe();
}

