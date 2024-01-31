package net.cf.form.engine.repository.oql.ast.expr.literal;

import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;

/**
 * 数字型时字面量表达式
 */
@Deprecated
public abstract class OqlNumericLiteralExpr extends OqlExprImpl implements QqlLiteralExpr {

    protected Number number;


    public OqlNumericLiteralExpr() {
    }

    public OqlNumericLiteralExpr(Number number) {
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
    public abstract OqlNumericLiteralExpr clone();
}

