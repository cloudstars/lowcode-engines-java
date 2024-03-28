package net.cf.object.engine.oql.ast;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlName;
import net.cf.object.engine.oql.AbstractOqlObjectImpl;

/**
 * OQL 表达式抽象实现类
 *
 * @author clouds
 */
public abstract class AbstractOqlExprImpl extends AbstractOqlObjectImpl implements SqlExpr {

    public AbstractOqlExprImpl() {
    }

    /**
     * 获取表达式的归属
     *
     * @return
     */
    public abstract SqlName getOwner();

    @Override
    public SqlExpr cloneMe() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

}
