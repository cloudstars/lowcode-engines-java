//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.cf.form.engine.repository.sql.ast.expr;

import net.cf.form.engine.repository.sql.ast.SqlReplaceable;
import net.cf.form.engine.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SqlListExpr extends SqlExprImpl implements SqlReplaceable {
    private final List<SqlExpr> items;

    public SqlListExpr() {
        this.items = new ArrayList();
    }

    public SqlListExpr(SqlExpr... items) {
        this.items = new ArrayList(items.length);
        SqlExpr[] var2 = items;
        int var3 = items.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            SqlExpr item = var2[var4];
            item.setParent(this);
            this.items.add(item);
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

    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            for(int i = 0; i < this.items.size(); ++i) {
                SqlExpr item = (SqlExpr)this.items.get(i);
                if (item != null) {
                    item.accept(visitor);
                }
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlListExpr _clone() {
        SqlListExpr x = new SqlListExpr();
        Iterator var2 = this.items.iterator();

        while(var2.hasNext()) {
            SqlExpr item = (SqlExpr)var2.next();
            SqlExpr item2 = item._clone();
            item2.setParent(x);
            x.items.add(item2);
        }

        return x;
    }

    @Override
    public List<SqlExpr> getChildren() {
        return this.items;
    }

    @Override
    public boolean replace(SqlExpr expr, SqlExpr target) {
        for(int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i) == expr) {
                target.setParent(this);
                this.items.set(i, target);
                return true;
            }
        }

        return false;
    }
}
