package net.cf.form.engine.oql.ast;


import net.cf.form.engine.oql.AbstractOqlObjectImpl;

/**
 * OQL 语句实现抽象类
 *
 * @author clouds
 */
public abstract class AbstractOqlStatementImpl extends AbstractOqlObjectImpl implements OqlStatement {

    protected boolean afterSemi;

    public AbstractOqlStatementImpl() {
    }

    @Override
    public boolean isAfterSemi() {
        return this.afterSemi;
    }

    @Override
    public void setAfterSemi(boolean afterSemi) {
        this.afterSemi = afterSemi;
    }

    @Override
    public OqlStatement cloneMe() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

}
