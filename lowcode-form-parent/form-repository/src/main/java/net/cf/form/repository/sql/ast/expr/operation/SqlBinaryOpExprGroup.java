package net.cf.form.repository.sql.ast.expr.operation;

import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.SqlExprImpl;
import net.cf.form.repository.sql.util.SqlUtils;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.*;

public class SqlBinaryOpExprGroup extends SqlExprImpl implements SqlReplaceable {

    private final SqlBinaryOperator operator;
    private final List<SqlExpr> items = new ArrayList();

    public SqlBinaryOpExprGroup(SqlBinaryOperator operator) {
        this.operator = operator;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            for (SqlExpr item : this.items) {
                item.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public SqlExpr _clone() {
        SqlBinaryOpExprGroup x = new SqlBinaryOpExprGroup(this.operator);
        for (SqlExpr item : this.items) {
            SqlExpr item2 = item._clone();
            item2.setParent(this);
            x.items.add(item2);
        }

        return x;
    }

    @Override
    public List<SqlExpr> getChildren() {
        return this.items;
    }

    public void add(SqlExpr item) {
        this.add(this.items.size(), item);
    }

    public void add(int index, SqlExpr item) {
        if (item instanceof SqlBinaryOpExpr) {
            SqlBinaryOpExpr binaryOpExpr = (SqlBinaryOpExpr) item;
            if (binaryOpExpr.getOperator() == this.operator) {
                this.add(binaryOpExpr.getLeft());
                this.add(binaryOpExpr.getRight());
                return;
            }
        } else if (item instanceof SqlBinaryOpExprGroup) {
            SqlBinaryOpExprGroup group = (SqlBinaryOpExprGroup) item;
            if (group.operator == this.operator) {
                Iterator var4 = group.getItems().iterator();

                while (var4.hasNext()) {
                    SqlExpr sqlExpr = (SqlExpr) var4.next();
                    this.add(sqlExpr);
                }

                return;
            }
        }

        if (item != null) {
            item.setParent(this);
        }

        this.items.add(index, item);
    }

    public List<SqlExpr> getItems() {
        return this.items;
    }

    public SqlBinaryOperator getOperator() {
        return this.operator;
    }

    public boolean replace(SqlExpr expr, SqlExpr target) {
        boolean replaced = false;

        for (int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i) == expr) {
                if (target == null) {
                    this.items.remove(i);
                } else if (target instanceof SqlBinaryOpExpr && ((SqlBinaryOpExpr) target).getOperator() == this.operator) {
                    this.items.remove(i);
                    List<SqlExpr> list = SqlBinaryOpExpr.split(target, this.operator);

                    for (int j = 0; j < list.size(); ++j) {
                        SqlExpr o = (SqlExpr) list.get(j);
                        o.setParent(this);
                        this.items.add(i + j, o);
                    }
                } else {
                    target.setParent(this);
                    this.items.set(i, target);
                }

                replaced = true;
            }
        }

        if (this.items.size() == 1 && replaced) {
            SqlUtils.replaceInParent(this, (SqlExpr) this.items.get(0));
        }

        if (this.items.isEmpty()) {
            SqlUtils.replaceInParent(this, (SqlExpr) null);
        }

        return replaced;
    }
}
