package net.cf.form.repository.sql.ast.expr.literal;


import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class SqlJsonArrayExpr extends AbstractSqlExprImpl implements SqlValuableExpr {

    private List<SqlExpr> items = new ArrayList();

    public SqlJsonArrayExpr() {
    }

    public SqlJsonArrayExpr(List<SqlExpr> items) {
        this.items = items;
    }

    public List<SqlExpr> getItems() {
        return this.items;
    }

    public void setItems(List<SqlExpr> items) {
        this.items = items;
        this.addChildren(items);
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.items != null) {
                this.nullSafeAcceptChildren(visitor, this.items);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlJsonArrayExpr cloneMe() {
        List<SqlExpr> x = new ArrayList<>();
        for (SqlExpr item : this.items) {
            x.add(item.cloneMe());
        }

        return new SqlJsonArrayExpr(x);
    }

    @Override
    public List<Object> getValue() {
        List<Object> value = new ArrayList<>();
        for (SqlExpr item : this.items) {
            if (item instanceof SqlValuableExpr) {
                value.add(((SqlValuableExpr) item).getValue());
            }
        }

        return value;
    }

    @Override
    public List<SqlExpr> getChildren() {
        return this.items;
    }

}
