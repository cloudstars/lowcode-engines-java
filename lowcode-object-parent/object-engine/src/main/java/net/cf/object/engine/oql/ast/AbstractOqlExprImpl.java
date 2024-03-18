package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.oql.AbstractOqlObjectImpl;

/**
 * OQL 表达式抽象实现类
 *
 * @author clouds
 */
public abstract class AbstractOqlExprImpl extends AbstractOqlObjectImpl implements SqlExpr {

    public AbstractOqlExprImpl() {
    }

    @Override
    public SqlExpr cloneMe() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

}
