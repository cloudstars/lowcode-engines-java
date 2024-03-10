package net.cf.form.repository.sql.ast.expr.op;

import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;

/**
 * 可以带 not 关键字的表达式
 *
 * @author clouds
 */
public abstract class AbstractNotableExpr extends AbstractSqlExprImpl {

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
}
