package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.OqlObjectImpl;
import net.cf.form.engine.oql.ast.expr.OqlExpr;


/**
 * OQL 对象数据源抽象实现类
 *
 * @author clouds
 */
public abstract class OqlObjectSourceImpl extends OqlObjectImpl implements OqlObjectSource {

    protected String alias;


    public OqlObjectSourceImpl() {
    }

    public OqlObjectSourceImpl(String alias) {
        this.alias = alias;
    }

    public OqlObjectSourceImpl(OqlExpr flashback, String alias) {
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
    public OqlObjectSource _clone() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }
}
