package net.cf.form.engine.repository.oql.ast.expr;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.OqlReplaceable;

import java.util.ArrayList;
import java.util.List;

/**
 * 以小括号括起来的列表值，如(1, 2, 3)，(,,,)
 *
 * @author clouds
 */
@Deprecated
public class OqlListExpr extends OqlExprImpl implements OqlReplaceable {

    private final List<QqlExpr> items = new ArrayList<>();

    public OqlListExpr() {
    }

    public OqlListExpr(List<QqlExpr> items) {
        for (QqlExpr item : items) {
            this.items.add(item);
            this.addChild(item);
        }
    }

    public OqlListExpr(QqlExpr... items) {
        for (QqlExpr item : items) {
            this.items.add(item);
            this.addChild(item);
        }
    }

    public List<QqlExpr> getItems() {
        return this.items;
    }

    public void addItem(QqlExpr item) {
        this.items.add(item);
        this.addChild(item);
    }

    public void addItem(int index, QqlExpr item) {
        this.items.add(index, item);
        this.addChild(item);
    }

    public void addItems(List<QqlExpr> items) {
        for (QqlExpr item : items) {
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
        for (QqlExpr item : this.items) {
            x.addItem(item.clone());
        }

        return x;
    }

    @Override
    public List getChildren() {
        return this.items;
    }

    @Override
    public boolean replace(QqlExpr source, QqlExpr target) {
        for (int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i) == source && target instanceof QqlExpr) {
                target.setParent(this);
                this.items.set(i, target);
                return true;
            }
        }

        return false;
    }
}
