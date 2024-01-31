package net.cf.form.engine.repository.oql.ast.expr.operation;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.OqlReplaceable;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class OqlBinaryOpExprGroup extends OqlExprImpl implements OqlReplaceable {

    private final OqlBinaryOperator operator;

    private final List<QqlExpr> items = new ArrayList();

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
    public QqlExpr clone() {
        OqlBinaryOpExprGroup x = new OqlBinaryOpExprGroup(this.operator);
        for (QqlExpr item : items) {
            QqlExpr item2 = item.clone();
            item2.setParent(this);
            x.items.add(item2);
        }

        return x;
    }

    @Override
    public List getChildren() {
        return this.items;
    }

    public void add(QqlExpr item) {
        this.add(this.items.size(), item);
    }

    public void add(int index, QqlExpr item) {
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
                for (QqlExpr item2 : group.items) {
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

    public List<QqlExpr> getItems() {
        return this.items;
    }

    public OqlBinaryOperator getOperator() {
        return this.operator;
    }

    @Override
    public boolean replace(QqlExpr expr, QqlExpr target) {
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

