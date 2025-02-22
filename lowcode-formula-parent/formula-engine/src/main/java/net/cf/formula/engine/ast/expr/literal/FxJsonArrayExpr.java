package net.cf.formula.engine.ast.expr.literal;

import net.cf.formula.engine.ast.expr.AbstractFxExprImpl;
import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.visitor.FxAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class FxJsonArrayExpr extends AbstractFxExprImpl {

    private List<FxExpr> items = new ArrayList();

    public FxJsonArrayExpr() {
    }

    public FxJsonArrayExpr(List<FxExpr> items) {
        this.items = items;
    }

    @Override
    public FxJsonArrayExpr clone() {
        List<FxExpr> x = new ArrayList<>();
        for (FxExpr item : this.items) {
            x.add(item.clone());
        }

        return new FxJsonArrayExpr(x);
    }

    public List<FxExpr> getItems() {
        return this.items;
    }

    public void setItems(List<FxExpr> items) {
        this.items = items;
        if (items != null) {
            for (FxExpr item : items) {
                item.setParent(this);
            }
        }
    }

    @Override
    protected void accept0(FxAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.items != null) {
                this.acceptChild(visitor, this.items);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public List<FxExpr> getChildren() {
        return this.items;
    }

}
