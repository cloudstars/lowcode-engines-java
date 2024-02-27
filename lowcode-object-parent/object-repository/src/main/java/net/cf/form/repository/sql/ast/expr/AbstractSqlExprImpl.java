package net.cf.form.repository.sql.ast.expr;

import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;

/**
 * SQL 表达式抽象实现类
 *
 * @author clouds
 */
public abstract class AbstractSqlExprImpl extends AbstractSqlObjectImpl implements SqlExpr {

    public AbstractSqlExprImpl() {
    }

    @Override
    public SqlExpr cloneMe() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

}
