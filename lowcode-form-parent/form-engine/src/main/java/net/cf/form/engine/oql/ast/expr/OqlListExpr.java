package net.cf.form.engine.oql.ast.expr;

import net.cf.form.engine.oql.ast.OqlReplaceable;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 以小括号括起来的列表值，如(1, 2, 3)，(,,,)
 *
 * @author clouds
 */
public class OqlListExpr extends AbstractOqlExprImpl implements OqlReplaceable {

    private final List<OqlExpr> items = new ArrayList<>();

    public OqlListExpr() {
    }

    public OqlListExpr(List<OqlExpr> items) {
        for (OqlExpr item : items) {
            this.items.add(item);
            this.addChild(item);
        }
    }

    public OqlListExpr(OqlExpr... items) {
        for (OqlExpr item : items) {
            this.items.add(item);
            this.addChild(item);
        }
    }

    public List<OqlExpr> getItems() {
        return this.items;
    }

    public void addItem(OqlExpr item) {
        this.items.add(item);
        this.addChild(item);
    }

    public void addItem(int index, OqlExpr item) {
        this.items.add(index, item);
        this.addChild(item);
    }

    public void addItems(List<OqlExpr> items) {
        for (OqlExpr item : items) {
            this.addItem(item);
        }
    }


    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.acceptChildren(visitor, this.items);
        }

        visitor.endVisit(this);
    }

    @Override
    public OqlListExpr clone() {
        OqlListExpr x = new OqlListExpr();
        for (OqlExpr item : this.items) {
            x.addItem(item.clone());
        }

        return x;
    }

    @Override
    public List getChildren() {
        return this.items;
    }

    @Override
    public boolean replace(OqlExpr source, OqlExpr target) {
        for (int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i) == source && target instanceof OqlExpr) {
                target.setParent(this);
                this.items.set(i, target);
                return true;
            }
        }

        return false;
    }
}
