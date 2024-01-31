package net.cf.form.engine.oql.ast.statement;

import net.cf.form.engine.oql.ast.OqlObjectImpl;
import net.cf.form.engine.oql.ast.OqlReplaceable;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.literal.OqlIntegerExpr;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class OqlSelectGroupBy extends OqlObjectImpl implements OqlReplaceable {

    // 分组字段
    private final List<OqlExpr> items = new ArrayList();

    // having 条件
    private OqlExpr having;

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

    public OqlExpr getHaving() {
        return this.having;
    }

    public void setHaving(OqlExpr having) {
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

    public List<OqlExpr> getItems() {
        return this.items;
    }

    public boolean containsItem(OqlExpr item) {
        return this.items.contains(item);
    }

    public void addItem(OqlExpr expr) {
        if (expr != null) {
            expr.setParent(this);
            this.items.add(expr);
        }
    }

    public void addItem(int index, OqlExpr expr) {
        if (expr != null) {
            expr.setParent(this);
            this.items.add(index, expr);
        }
    }

    @Override
    public OqlSelectGroupBy clone() {
        OqlSelectGroupBy x = new OqlSelectGroupBy();
        for (OqlExpr item : this.items) {
            OqlExpr item2 = item.clone();
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
    public boolean replace(OqlExpr expr, OqlExpr target) {
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

