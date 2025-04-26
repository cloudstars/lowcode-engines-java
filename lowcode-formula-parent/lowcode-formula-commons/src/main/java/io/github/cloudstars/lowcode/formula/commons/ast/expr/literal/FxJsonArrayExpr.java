package io.github.cloudstars.lowcode.formula.commons.ast.expr.literal;

import io.github.cloudstars.lowcode.formula.commons.ast.expr.AbstractFxExprImpl;
import io.github.cloudstars.lowcode.formula.commons.ast.expr.FxExpr;
import io.github.cloudstars.lowcode.formula.commons.visitor.FxAstVisitor;

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
    public void accept(FxAstVisitor visitor) {
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
