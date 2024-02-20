package net.cf.form.engine.oql.ast.expr.literal;

import net.cf.form.engine.oql.ast.expr.AbstractOqlExprImpl;
import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class OqlJsonArrayExpr extends AbstractOqlExprImpl {

    private List<OqlExpr> items = new ArrayList();

    public OqlJsonArrayExpr() {
    }

    public OqlJsonArrayExpr(List<OqlExpr> items) {
        this.items = items;
    }

    @Override
    public OqlJsonArrayExpr clone() {
        List<OqlExpr> x = new ArrayList<>();
        for (OqlExpr item : this.items) {
            x.add(item.clone());
        }

        return new OqlJsonArrayExpr(x);
    }

    public List<OqlExpr> getItems() {
        return this.items;
    }

    public void setItems(List<OqlExpr> items) {
        this.items = items;
        this.addChildren(items);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.items != null) {
                this.acceptChildren(visitor, this.items);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public List<OqlExpr> getChildren() {
        return this.items;
    }

}
