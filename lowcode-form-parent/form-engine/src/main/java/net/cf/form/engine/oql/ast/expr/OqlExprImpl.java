package net.cf.form.engine.oql.ast.expr;

import net.cf.form.engine.oql.ast.OqlObjectImpl;

/**
 * OQL 表达式实现抽象类
 *
 * @author clouds
 */
public abstract class OqlExprImpl extends OqlObjectImpl implements OqlExpr {

    @Override
    public OqlExpr clone() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }
}
