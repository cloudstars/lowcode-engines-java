package net.cf.formula.engine.ast.expr.literal;

import net.cf.formula.engine.ast.expr.FxExprImpl;

/**
 * 数字型时字面量表达式
 */
public abstract class FxNumericLiteralExpr extends FxExprImpl implements FxLiteralExpr {

    protected Number number;


    public FxNumericLiteralExpr() {
    }

    public FxNumericLiteralExpr(Number number) {
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
    public abstract FxNumericLiteralExpr clone();
}

