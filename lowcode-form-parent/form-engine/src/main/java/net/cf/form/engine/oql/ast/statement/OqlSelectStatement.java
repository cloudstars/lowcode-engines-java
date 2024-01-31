package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.List;

/**
 * OQL查询语句
 *
 * @author clouds
 */
public class OqlSelectStatement extends OqlStatementImpl {

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
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.acceptChild(visitor, this.select);
        }

        visitor.endVisit(this);
    }

    @Override
    public OqlStatement clone() {
        return null;
    }
}
