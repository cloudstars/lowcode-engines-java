package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.OqlObjectImpl;
import net.cf.form.engine.oql.ast.OqlReplaceable;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public final class OqlSelectOrderBy extends OqlObjectImpl implements OqlReplaceable {

    protected final List<OqlSelectOrderByItem> items = new ArrayList();

    public OqlSelectOrderBy() {
    }

    public OqlSelectOrderBy(OqlExpr expr) {
        OqlSelectOrderByItem item = new OqlSelectOrderByItem(expr);
        this.addItem(item);
    }

    public OqlSelectOrderBy(OqlExpr expr, boolean ascending) {
        OqlSelectOrderByItem item = new OqlSelectOrderByItem(expr, ascending);
        this.addItem(item);
    }

    public void addItem(OqlSelectOrderByItem item) {
        this.items.add(item);
        this.addChild(item);
    }

    public void addItem(OqlExpr expr) {
        this.addItem(new OqlSelectOrderByItem(expr));
    }

    public void addItem(OqlExpr expr, boolean ascending) {
        this.addItem(new OqlSelectOrderByItem(expr, ascending));
    }

    public List<OqlSelectOrderByItem> getItems() {
        return this.items;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.acceptChildren(visitor, this.items);
        }

        visitor.endVisit(this);
    }

    @Override
    public boolean replace(OqlExpr source, OqlExpr target) {
        for (OqlSelectOrderByItem item : this.items) {
            if (item.replace(source, target)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public OqlSelectOrderBy clone() {
        OqlSelectOrderBy x = new OqlSelectOrderBy();
        for (OqlSelectOrderByItem item : this.items) {
            x.addItem(item.clone());
        }

        return x;
    }
}
