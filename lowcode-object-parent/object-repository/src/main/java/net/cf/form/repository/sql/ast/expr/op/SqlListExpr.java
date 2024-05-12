package net.cf.form.repository.sql.ast.expr.op;

import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SqlListExpr extends AbstractSqlExprImpl implements SqlReplaceable/*, SqlValuableExpr*/ {
    private final List<SqlExpr> items;

    public SqlListExpr() {
        this.items = new ArrayList();
    }

    public SqlListExpr(SqlExpr... items) {
        this.items = new ArrayList(items.length);
        for (SqlExpr item : items) {
            item.setParent(this);
            this.items.add(item);
        }
    }

    public SqlListExpr(List<SqlExpr> items) {
        this.items = items;
        for (SqlExpr item : items) {
            item.setParent(this);
        }
    }

    public List<SqlExpr> getItems() {
        return this.items;
    }

    public void addItem(SqlExpr item) {
        if (item != null) {
            item.setParent(this);
        }

        this.items.add(item);
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            for (int i = 0; i < this.items.size(); ++i) {
                SqlExpr item = (SqlExpr) this.items.get(i);
                if (item != null) {
                    item.accept(visitor);
                }
            }
        }

        visitor.endVisit(this);
    }

    /*@Override
    public List<Object> getValue() {
        List<Object> value = new ArrayList<>();
        for (SqlExpr item : this.items) {
            if (item instanceof SqlValuableExpr) {
                value.add(((SqlValuableExpr) item).getValue());
            }
        }

        return value;
    }*/

    @Override
    public List<SqlExpr> getChildren() {
        return this.items;
    }

    @Override
    public SqlListExpr cloneMe() {
        SqlListExpr x = new SqlListExpr();
        Iterator var2 = this.items.iterator();

        while (var2.hasNext()) {
            SqlExpr item = (SqlExpr) var2.next();
            SqlExpr item2 = item.cloneMe();
            item2.setParent(x);
            x.items.add(item2);
        }

        return x;
    }

    @Override
    public boolean replace(SqlExpr expr, SqlExpr target) {
        for (int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i) == expr) {
                target.setParent(this);
                this.items.set(i, target);
                return true;
            }
        }

        return false;
    }
}
