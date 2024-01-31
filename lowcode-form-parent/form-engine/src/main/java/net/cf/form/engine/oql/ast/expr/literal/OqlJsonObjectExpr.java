package net.cf.form.engine.oql.ast.expr.literal;

import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.OqlExprImpl;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OqlJsonObjectExpr extends OqlExprImpl {

    private Map<String, OqlExpr> items = new HashMap<>();

    public OqlJsonObjectExpr() {
    }

    public OqlJsonObjectExpr(Map<String, OqlExpr> items) {
        this.items = items;
    }

    @Override
    public OqlJsonObjectExpr clone() {
        Map<String, OqlExpr> x = new HashMap<>();
        for (Map.Entry<String, OqlExpr> entry : this.items.entrySet()) {
            x.put(entry.getKey(), entry.getValue().clone());
        }

        return new OqlJsonObjectExpr(x);
    }

    public Map<String, OqlExpr> getItems() {
        return this.items;
    }

    public void setItems(Map<String, OqlExpr> items) {
        this.items = items;
        this.addChildren(items.values());
    }

    public void putItem(String key, OqlExpr item) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        this.items.put(key, item);
        this.addChild(item);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.items != null) {
                this.acceptChildren(visitor, this.getChildren());
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public List<OqlExpr> getChildren() {
        List<OqlExpr> children = new ArrayList<>();
        children.addAll(this.items.values());
        return children;
    }

}
