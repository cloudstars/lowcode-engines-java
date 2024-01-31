package net.cf.form.engine.repository.oql.ast.statement;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.OqlObjectImpl;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;

import java.util.Arrays;
import java.util.List;


/**
 * OQL 对象数据源抽象实现类
 *
 * @author clouds
 */
@Deprecated
public abstract class OqlObjectSourceImpl extends OqlObjectImpl implements OqlObjectSource {

    protected QqlExpr flashback;

    protected String alias;


    public OqlObjectSourceImpl() {
    }

    public OqlObjectSourceImpl(String alias) {
        this.alias = alias;
    }

    public OqlObjectSourceImpl(QqlExpr flashback, String alias) {
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
    public QqlExpr getFlashback() {
        return this.flashback;
    }

    @Override
    public void setFlashback(QqlExpr flashback) {
        this.flashback = flashback;
        this.addChild(flashback);
    }

    @Override
    public OqlObjectSource clone() {
        throw new UnsupportedOperationException(this.getClass().getName());
    }

    @Override
    public List<QqlExpr> getChildren() {
        return Arrays.asList(this.flashback);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this) && this.flashback != null) {
            this.flashback.accept(visitor);
        }

        visitor.endVisit(this);
    }
}
