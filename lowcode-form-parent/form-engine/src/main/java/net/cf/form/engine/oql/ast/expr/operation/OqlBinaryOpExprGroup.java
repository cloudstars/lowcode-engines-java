package net.cf.form.engine.oql.ast.expr.operation;

import net.cf.form.engine.oql.ast.OqlReplaceable;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.AbstractOqlExprImpl;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class OqlBinaryOpExprGroup extends AbstractOqlExprImpl implements OqlReplaceable {

    private final OqlBinaryOperator operator;

    private final List<OqlExpr> items = new ArrayList();

    public OqlBinaryOpExprGroup(OqlBinaryOperator operator) {
        this.operator = operator;
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.acceptChildren(visitor, this.items);
        }

        visitor.endVisit(this);
    }

    @Override
    public OqlExpr clone() {
        OqlBinaryOpExprGroup x = new OqlBinaryOpExprGroup(this.operator);
        for (OqlExpr item : items) {
            OqlExpr item2 = item.clone();
            item2.setParent(this);
            x.items.add(item2);
        }

        return x;
    }

    @Override
    public List getChildren() {
        return this.items;
    }

    public void add(OqlExpr item) {
        this.add(this.items.size(), item);
    }

    public void add(int index, OqlExpr item) {
        if (item instanceof OqlBinaryOpExpr) {
            OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) item;
            if (binaryOpExpr.getOperator() == this.operator) {
                this.add(binaryOpExpr.getLeft());
                this.add(binaryOpExpr.getRight());
                return;
            }
        } else if (item instanceof OqlBinaryOpExprGroup) {
            OqlBinaryOpExprGroup group = (OqlBinaryOpExprGroup) item;
            if (group.operator == this.operator) {
                for (OqlExpr item2 : group.items) {
                    this.add(item2);
                }

                return;
            } else {

            }
        } else {
            this.items.add(index, item);
            this.addChild(item);
        }
    }

    public List<OqlExpr> getItems() {
        return this.items;
    }

    public OqlBinaryOperator getOperator() {
        return this.operator;
    }

    @Override
    public boolean replace(OqlExpr expr, OqlExpr target) {
        boolean replaced = false;

        /*for (int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i) == expr) {
                if (target == null) {
                    this.items.remove(i);
                } else if (target instanceof OqlBinaryOpExpr && ((OqlBinaryOpExpr) target).getOperator() == this.operator) {
                    this.items.remove(i);
                    List<OqlExpr> list = OqlBinaryOpExpr.split(target, this.operator);

                    for (int j = 0; j < list.size(); ++j) {
                        OqlExpr o = (OqlExpr) list.get(j);
                        o.setParent(this);
                        this.items.add(i + j, o);
                    }
                } else {
                    target.setParent(this);
                    this.items.set(i, target);
                }

                replaced = true;
            }
        }

        if (this.items.size() == 1 && replaced) {
            OqlUtils.replaceInParent(this, (OqlExpr) this.items.get(0));
        }

        if (this.items.size() == 0) {
            OqlUtils.replaceInParent(this, (OqlExpr) null);
        }*/

        return replaced;
    }

}

