package io.github.cloudstars.lowcode.formula.commons.ast.expr.literal;


import io.github.cloudstars.lowcode.formula.commons.ast.expr.AbstractFxExprImpl;

/**
 * 数字型时字面量表达式
 *
 * @author clouds
 */
public abstract class AbstractFxNumericLiteralExpr extends AbstractFxExprImpl implements FxLiteralExpr {

    protected Number number;


    public AbstractFxNumericLiteralExpr() {
    }

    public AbstractFxNumericLiteralExpr(Number number) {
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
    public abstract AbstractFxNumericLiteralExpr clone();
}

