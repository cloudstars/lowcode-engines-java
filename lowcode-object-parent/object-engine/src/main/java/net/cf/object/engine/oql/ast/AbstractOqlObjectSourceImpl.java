package net.cf.object.engine.oql.ast;

import net.cf.object.engine.oql.AbstractOqlObjectImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;


/**
 * OQL 模型数据源抽象实现类
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
