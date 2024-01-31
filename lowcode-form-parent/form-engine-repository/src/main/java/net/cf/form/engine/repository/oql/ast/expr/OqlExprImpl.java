package net.cf.form.engine.repository.oql.ast.expr;

import net.cf.form.engine.repository.oql.ast.OqlObjectImpl;

/**
 * OQL 表达式实现抽象类
 *
 * @author clouds
 */
@Deprecated
public abstract class OqlExprImpl extends OqlObjectImpl implements QqlExpr {

    @Override
    public QqlExpr clone() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }
}
