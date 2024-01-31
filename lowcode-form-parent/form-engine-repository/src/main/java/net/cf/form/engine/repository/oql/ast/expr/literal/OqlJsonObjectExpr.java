package net.cf.form.engine.repository.oql.ast.expr.literal;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
public class OqlJsonObjectExpr extends OqlExprImpl {

    private Map<String, QqlExpr> items = new HashMap<>();

    public OqlJsonObjectExpr() {
    }

    public OqlJsonObjectExpr(Map<String, QqlExpr> items) {
        this.items = items;
    }

    @Override
    public OqlJsonObjectExpr clone() {
        Map<String, QqlExpr> x = new HashMap<>();
        for (Map.Entry<String, QqlExpr> entry : this.items.entrySet()) {
            x.put(entry.getKey(), entry.getValue().clone());
        }

        return new OqlJsonObjectExpr(x);
    }

    public Map<String, QqlExpr> getItems() {
        return this.items;
    }

    public void setItems(Map<String, QqlExpr> items) {
        this.items = items;
        this.addChildren(items.values());
    }

    public void putItem(String key, QqlExpr item) {
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
    public List<QqlExpr> getChildren() {
        List<QqlExpr> children = new ArrayList<>();
        children.addAll(this.items.values());
        return children;
    }

}
