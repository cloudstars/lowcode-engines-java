package net.cf.form.engine.repository.oql.ast.expr.literal;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;

import java.util.ArrayList;
import java.util.List;

public class OqlJsonArrayExpr extends OqlExprImpl {

    private List<QqlExpr> items = new ArrayList();

    public OqlJsonArrayExpr() {
    }

    public OqlJsonArrayExpr(List<QqlExpr> items) {
        this.items = items;
    }

    @Override
    public OqlJsonArrayExpr clone() {
        List<QqlExpr> x = new ArrayList<>();
        for (QqlExpr item : this.items) {
            x.add(item.clone());
        }

        return new OqlJsonArrayExpr(x);
    }

    public List<QqlExpr> getItems() {
        return this.items;
    }

    public void setItems(List<QqlExpr> items) {
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
    public List<QqlExpr> getChildren() {
        return this.items;
    }

}
