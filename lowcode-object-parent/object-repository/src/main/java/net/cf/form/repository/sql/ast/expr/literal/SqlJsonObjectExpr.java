package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlJsonObjectExpr extends AbstractSqlExprImpl implements SqlValuableExpr {

    private Map<String, SqlValuableExpr> items = new HashMap<>();

    public SqlJsonObjectExpr() {
    }

    public SqlJsonObjectExpr(Map<String, SqlValuableExpr> items) {
        this.items = items;
    }

    public Map<String, SqlValuableExpr> getItems() {
        return this.items;
    }

    public void setItems(Map<String, SqlValuableExpr> items) {
        this.items = items;
        this.addChildren(items.values());
    }

    public void putItem(String key, SqlValuableExpr item) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        this.items.put(key, item);
        this.addChild(item);
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.items != null) {
                this.nullSafeAcceptChildren(visitor, this.getChildren());
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlJsonObjectExpr cloneMe() {
        Map<String, SqlValuableExpr> x = new HashMap<>();
        for (Map.Entry<String, SqlValuableExpr> entry : this.items.entrySet()) {
            x.put(entry.getKey(), entry.getValue().cloneMe());
        }

        return new SqlJsonObjectExpr(x);
    }

    @Override
    public Map<String, Object> getValue() {
        Map<String, Object> value = new HashMap<>();
        this.items.forEach((k, v) -> {
            value.put(k, v.getValue());
        });
        return value;
    }

    @Override
    public List<SqlExpr> getChildren() {
        List<SqlExpr> children = new ArrayList<>();
        children.addAll(this.items.values());
        return children;
    }

}
