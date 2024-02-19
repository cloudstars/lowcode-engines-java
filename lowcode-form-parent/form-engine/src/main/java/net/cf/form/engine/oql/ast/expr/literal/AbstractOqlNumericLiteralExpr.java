package net.cf.form.engine.oql.ast.expr.literal;

import net.cf.form.engine.oql.ast.expr.AbstractOqlExprImpl;

/**
 * 数字型时字面量表达式
 *
 * @author clouds
 */
public abstract class AbstractOqlNumericLiteralExpr extends AbstractOqlExprImpl implements OqlLiteralExpr {

    protected Number number;


    public AbstractOqlNumericLiteralExpr() {
    }

    public AbstractOqlNumericLiteralExpr(Number number) {
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
    public abstract AbstractOqlNumericLiteralExpr clone();
}

