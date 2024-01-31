package net.cf.form.engine.repository.oql.ast.statement;

import net.cf.form.engine.repository.oql.ast.OqlObjectImpl;


/**
 * OQL 语句实现抽象类
 *
 * @author clouds
 */
@Deprecated
public abstract class OqlStatementImpl extends OqlObjectImpl implements OqlStatement {
    protected boolean afterSemi;

    public OqlStatementImpl() {
    }

    @Override
    public boolean isAfterSemi() {
        return this.afterSemi;
    }

    @Override
    public void setAfterSemi(boolean afterSemi) {
        this.afterSemi = afterSemi;
    }

    public OqlStatement clone() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

}
