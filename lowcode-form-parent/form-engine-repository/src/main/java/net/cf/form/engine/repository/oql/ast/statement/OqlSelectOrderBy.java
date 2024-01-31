package net.cf.form.engine.repository.oql.ast.statement;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.OqlObjectImpl;
import net.cf.form.engine.repository.oql.ast.OqlReplaceable;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public final class OqlSelectOrderBy extends OqlObjectImpl implements OqlReplaceable {

    protected final List<OqlSelectOrderByItem> items = new ArrayList();

    public OqlSelectOrderBy() {
    }

    public OqlSelectOrderBy(QqlExpr expr) {
        OqlSelectOrderByItem item = new OqlSelectOrderByItem(expr);
        this.addItem(item);
    }

    public OqlSelectOrderBy(QqlExpr expr, boolean ascending) {
        OqlSelectOrderByItem item = new OqlSelectOrderByItem(expr, ascending);
        this.addItem(item);
    }

    public void addItem(OqlSelectOrderByItem item) {
        this.items.add(item);
        this.addChild(item);
    }

    public void addItem(QqlExpr expr) {
        this.addItem(new OqlSelectOrderByItem(expr));
    }

    public void addItem(QqlExpr expr, boolean ascending) {
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
    public boolean replace(QqlExpr source, QqlExpr target) {
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
