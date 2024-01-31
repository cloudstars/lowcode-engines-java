package net.cf.form.engine.repository.oql.ast.statement;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.OqlObjectImpl;
import net.cf.form.engine.repository.oql.ast.OqlReplaceable;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlIntegerExpr;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class OqlSelectGroupBy extends OqlObjectImpl implements OqlReplaceable {

    // 分组字段
    private final List<QqlExpr> items = new ArrayList();

    // having 条件
    private QqlExpr having;

    private boolean distinct;

    public OqlSelectGroupBy() {
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.acceptChildren(visitor, this.items);
            if (this.having != null) {
                this.having.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    public boolean isDistinct() {
        return this.distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public QqlExpr getHaving() {
        return this.having;
    }

    public void setHaving(QqlExpr having) {
        this.having = having;
        this.addChild(having);
    }

    /*
    public void addHaving(OqlExpr condition) {
        if (condition != null) {
            if (this.having == null) {
                this.having = condition;
            } else {
                this.having = OqlBinaryOpExpr.and(this.having, condition);
            }

        }
    }*/

    public List<QqlExpr> getItems() {
        return this.items;
    }

    public boolean containsItem(QqlExpr item) {
        return this.items.contains(item);
    }

    public void addItem(QqlExpr expr) {
        if (expr != null) {
            expr.setParent(this);
            this.items.add(expr);
        }
    }

    public void addItem(int index, QqlExpr expr) {
        if (expr != null) {
            expr.setParent(this);
            this.items.add(index, expr);
        }
    }

    @Override
    public OqlSelectGroupBy clone() {
        OqlSelectGroupBy x = new OqlSelectGroupBy();
        for (QqlExpr item : this.items) {
            QqlExpr item2 = item.clone();
            item2.setParent(x);
            x.items.add(item2);
        }

        if (this.having != null) {
            x.setHaving(this.having.clone());
        }

        x.distinct = this.distinct;

        return x;
    }

    @Override
    public boolean replace(QqlExpr expr, QqlExpr target) {
        if (expr == this.having) {
            this.setHaving(target);
            return true;
        } else {
            for (int i = this.items.size() - 1; i >= 0; --i) {
                if (this.items.get(i) == expr) {
                    if (target instanceof OqlIntegerExpr) {
                        this.items.remove(i);
                    } else {
                        this.items.set(i, target);
                    }

                    return true;
                }
            }

            return false;
        }
    }

}

