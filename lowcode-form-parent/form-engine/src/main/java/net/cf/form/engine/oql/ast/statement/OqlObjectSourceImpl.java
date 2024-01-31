package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.OqlObjectImpl;
import net.cf.form.engine.oql.ast.expr.OqlExpr;

import java.util.Arrays;
import java.util.List;


/**
 * OQL 对象数据源抽象实现类
 *
 * @author clouds
 */
public abstract class OqlObjectSourceImpl extends OqlObjectImpl implements OqlObjectSource {

    protected OqlExpr flashback;

    protected String alias;


    public OqlObjectSourceImpl() {
    }

    public OqlObjectSourceImpl(String alias) {
        this.alias = alias;
    }

    public OqlObjectSourceImpl(OqlExpr flashback, String alias) {
        this.flashback = flashback;
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
    public OqlExpr getFlashback() {
        return this.flashback;
    }

    @Override
    public void setFlashback(OqlExpr flashback) {
        this.flashback = flashback;
        this.addChild(flashback);
    }

    @Override
    public OqlObjectSource clone() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    @Override
    public List<OqlExpr> getChildren() {
        return Arrays.asList(this.flashback);
    }
}
