package net.cf.form.repository.sql.ast.statement;

import net.cf.form.repository.sql.ast.AbstractSqlObjectImpl;
import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

public class SqlSelectGroupByClause extends AbstractSqlObjectImpl implements SqlReplaceable {

    // 分组字段
    private final List<SqlExpr> items = new ArrayList();

    // having 条件
    private SqlExpr having;

    private boolean distinct;

    public SqlSelectGroupByClause() {
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            this.nullSafeAcceptChildren(visitor, this.items);
            if (this.having != null) {
                this.having.accept(visitor);
            }
        }

        visitor.endVisit(this);
    }

    public boolean isDistinct() {
        return this.distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public SqlExpr getHaving() {
        return this.having;
    }

    public void setHaving(SqlExpr having) {
        this.having = having;
        this.addChild(having);
    }

    /*
    public void addHaving(SqlExpr condition) {
        if (condition != null) {
            if (this.having == null) {
                this.having = condition;
            } else {
                this.having = SqlBinaryOpExpr.and(this.having, condition);
            }

        }
    }*/

    public List<SqlExpr> getItems() {
        return this.items;
    }

    public boolean containsItem(SqlExpr item) {
        return this.items.contains(item);
    }

    public void addItem(SqlExpr expr) {
        if (expr != null) {
            expr.setParent(this);
            this.items.add(expr);
        }
    }

    public void addItem(int index, SqlExpr expr) {
        if (expr != null) {
            expr.setParent(this);
            this.items.add(index, expr);
        }
    }

    @Override
    public SqlSelectGroupByClause cloneMe() {
        SqlSelectGroupByClause x = new SqlSelectGroupByClause();
        for (SqlExpr item : this.items) {
            SqlExpr item2 = item.cloneMe();
            item2.setParent(x);
            x.items.add(item2);
        }

        if (this.having != null) {
            x.setHaving(this.having.cloneMe());
        }

        x.distinct = this.distinct;

        return x;
    }

    @Override
    public boolean replace(SqlExpr expr, SqlExpr target) {
        if (expr == this.having) {
            this.setHaving(target);
            return true;
        } else {
            for (int i = this.items.size() - 1; i >= 0; --i) {
                if (this.items.get(i) == expr) {
                    if (target instanceof SqlIntegerExpr) {
                        this.items.remove(i);
                    } else {
                        this.items.set(i, target);
                    }

                    return true;
                }
            }

            return false;
        }
    }

}

