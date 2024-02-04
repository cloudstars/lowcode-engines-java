package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.ast.expr.SqlExprImpl;

/**
 * 数字型时字面量表达式
 */
public abstract class SqlNumericLiteralExpr extends SqlExprImpl implements SqlLiteralExpr {

    protected Number number;


    public SqlNumericLiteralExpr() {
    }

    public SqlNumericLiteralExpr(Number number) {
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
    public abstract SqlNumericLiteralExpr _clone();
}

