package net.cf.object.fx.parser.ast.expr.literal;

import net.cf.object.fx.parser.ast.expr.AbstractFxExprImpl;
import net.cf.object.fx.parser.ast.expr.FxExpr;
import net.cf.object.fx.parser.ast.visitor.FxAstVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FxJsonObjectExpr extends AbstractFxExprImpl {

    private Map<String, FxExpr> items = new HashMap<>();

    public FxJsonObjectExpr() {
    }

    public FxJsonObjectExpr(Map<String, FxExpr> items) {
        this.items = items;
    }

    @Override
    public FxJsonObjectExpr clone() {
        Map<String, FxExpr> x = new HashMap<>();
        for (Map.Entry<String, FxExpr> entry : this.items.entrySet()) {
            x.put(entry.getKey(), entry.getValue().clone());
        }

        return new FxJsonObjectExpr(x);
    }

    public Map<String, FxExpr> getItems() {
        return this.items;
    }

    public void setItems(Map<String, FxExpr> items) {
        this.items = items;
        if (items != null) {
            for (Map.Entry<String, FxExpr> entry : this.items.entrySet()) {
                entry.getValue().setParent(this);
            }
        }
    }

    public void putItem(String key, FxExpr item) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        this.items.put(key, item);

        if (item != null) {
            item.setParent(this);
        }
    }

    @Override
    public void accept(FxAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.items != null) {
                this.acceptChild(visitor, this.getChildren());
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public List<FxExpr> getChildren() {
        List<FxExpr> children = new ArrayList<>();
        children.addAll(this.items.values());
        return children;
    }

}
