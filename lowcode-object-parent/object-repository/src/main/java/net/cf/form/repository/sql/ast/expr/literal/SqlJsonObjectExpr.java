package net.cf.form.repository.sql.ast.expr.literal;

import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlJsonObjectExpr extends AbstractSqlExprImpl {

    private Map<String, SqlExpr> items = new HashMap<>();

    public SqlJsonObjectExpr() {
    }

    public SqlJsonObjectExpr(Map<String, SqlExpr> items) {
        this.items = items;
    }

    public Map<String, SqlExpr> getItems() {
        return this.items;
    }

    public void setItems(Map<String, SqlExpr> items) {
        this.items = items;
        this.addChildren(items.values());
    }

    public void putItem(String key, SqlExpr item) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }

        this.items.put(key, item);
        this.addChild(item);
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.items != null) {
                this.nullSafeAcceptChildren(visitor, this.getChildren());
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlExpr cloneMe() {
        Map<String, SqlExpr> x = new HashMap<>();
        for (Map.Entry<String, SqlExpr> entry : this.items.entrySet()) {
            x.put(entry.getKey(), entry.getValue().cloneMe());
        }

        return new SqlJsonObjectExpr(x);
    }

    @Override
    public List<SqlExpr> getChildren() {
        List<SqlExpr> children = new ArrayList<>();
        children.addAll(this.items.values());
        return children;
    }

}
