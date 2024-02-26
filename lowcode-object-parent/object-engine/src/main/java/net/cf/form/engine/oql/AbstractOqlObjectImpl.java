package net.cf.form.engine.oql;

import net.cf.form.engine.oql.visitor.OqlAstVisitor;
import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 * OQL AST 节点实现类
 *
 * @author clouds
 */
public abstract class AbstractOqlObjectImpl extends AbstractSqlObjectImpl {

    public AbstractOqlObjectImpl() {
        super();
    }

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
    protected void accept0(SqlAstVisitor visitor) {
        this.accept(visitor);
    }

}
