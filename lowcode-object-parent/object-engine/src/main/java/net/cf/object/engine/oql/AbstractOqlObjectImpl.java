package net.cf.object.engine.oql;

import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;
import net.cf.object.engine.oql.visitor.OqlAstOutputVisitor;
import net.cf.object.engine.oql.visitor.OqlAstVisitor;

/**
 * OQL AST 节点实现类
 *
 * @author clouds
 */
public abstract class AbstractOqlObjectImpl<V extends OqlAstVisitor> extends AbstractSqlObjectImpl<OqlAstVisitor> {

    public AbstractOqlObjectImpl() {
        super();
    }

    @Override
    public final void accept(OqlAstVisitor visitor) {
        if (visitor == null) {
            throw new IllegalArgumentException("visitor is null.");
        } else {
            visitor.preVisit(this);
            this.accept0(visitor);
            visitor.postVisit(this);
        }
    }

    /**
     * 接受一个visitor
     *
     * @param visitor
     */
    protected abstract void accept0(OqlAstVisitor visitor);

    @Override
    protected OqlAstVisitor createAstOutputVisitor(Appendable appendable) {
        return new OqlAstOutputVisitor(appendable);
    }

}
