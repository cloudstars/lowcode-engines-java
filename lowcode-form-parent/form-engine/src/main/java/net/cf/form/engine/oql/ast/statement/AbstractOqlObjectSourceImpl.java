package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.AbstractOqlObjectImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;


/**
 * OQL 对象数据源抽象实现类
 *
 * @author clouds
 */
public abstract class AbstractOqlObjectSourceImpl extends AbstractOqlObjectImpl implements OqlObjectSource {

    protected String alias;


    public AbstractOqlObjectSourceImpl() {
    }

    public AbstractOqlObjectSourceImpl(String alias) {
        this.alias = alias;
    }

    public AbstractOqlObjectSourceImpl(SqlExpr flashback, String alias) {
        this.alias = alias;
    }

    @Override
    public String getAlias() {
        return this.alias;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public OqlObjectSource cloneMe() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }
}
