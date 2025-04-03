package net.cf.object.engine.oql.ast;

import net.cf.object.engine.oql.visitor.OqlAstVisitor;

import java.util.List;

/**
 * OQL查询语句
 *
 * @author clouds
 */
public class OqlSelectStatement extends AbstractOqlStatementImpl {

    protected OqlSelect select;

    public OqlSelectStatement() {
    }

    public OqlSelectStatement(OqlSelect select) {
        this.setSelect(select);
    }

    public OqlSelect getSelect() {
        return select;
    }

    public void setSelect(OqlSelect select) {
        this.select = select;
        this.addChild(select);
    }

    @Override
    public List getChildren() {
        return super.getChildren();
    }

    @Override
    public void accept(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChild(visitor, this.select);
        }

        visitor.endVisit(this);
    }

    @Override
    public OqlStatement cloneMe() {
        return null;
    }
}
