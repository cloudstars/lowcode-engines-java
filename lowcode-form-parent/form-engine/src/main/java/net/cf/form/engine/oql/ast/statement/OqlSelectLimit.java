package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.AbstractOqlObjectImpl;
import net.cf.form.engine.oql.ast.OqlReplaceable;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;


public final class OqlSelectLimit extends AbstractOqlObjectImpl implements OqlReplaceable {

    private int offset = -1;

    private int rowCount;

    public OqlSelectLimit() {
    }

    public OqlSelectLimit(int rowCount) {
        this.rowCount = rowCount;
    }

    public OqlSelectLimit(int offset, int rowCount) {
        this.offset = offset;
        this.rowCount = rowCount;
    }


    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }

    @Override
    public OqlSelectLimit clone() {
        OqlSelectLimit x = new OqlSelectLimit();
        x.setOffset(this.getOffset());
        x.setRowCount(this.getRowCount());
        return x;
    }

    @Override
    public boolean replace(OqlExpr source, OqlExpr target) {
        return false;
    }
}

