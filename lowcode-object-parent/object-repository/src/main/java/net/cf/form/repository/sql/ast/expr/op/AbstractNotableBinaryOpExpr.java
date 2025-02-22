package net.cf.form.repository.sql.ast.expr.op;

/**
 * 可以带 not 关键字的表达式
 *
 * @author clouds
 */
public abstract class AbstractNotableBinaryOpExpr extends SqlBinaryOpExpr {

    /**
     * 是否有not关键字
     */
    public boolean not;

    public boolean isNot() {
        return this.not;
    }

    public void setNot(boolean not) {
        this.not = not;
    }

    protected void cloneTo(AbstractNotableBinaryOpExpr x) {
        super.cloneTo(x);
        x.not = this.not;
    }
}
