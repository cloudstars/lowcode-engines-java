package net.cf.form.repository.sql.ast.expr.op;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.util.SqlUtils;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 二元操作表达式组合，组合之后还是一个表达式
 *
 * @author clouds
 */
public class SqlBinaryOpExprGroup extends AbstractSqlExprImpl implements SqlReplaceable {

    /**
     * 二元操作符
     */
    private final SqlBinaryOperator operator;

    /**
     * 操作表达式列表
     */
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
    public SqlExpr cloneMe() {
        SqlBinaryOpExprGroup x = new SqlBinaryOpExprGroup(this.operator);
        for (SqlExpr item : this.items) {
            SqlExpr item2 = item.cloneMe();
            item2.setParent(this);
            x.items.add(item2);
        }

        return x;
    }

    @Override
    public List<? extends SqlObject> getChildren() {
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

    @Override
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
            SqlUtils.replaceInParent(this, this.items.get(0));
        }

        if (this.items.isEmpty()) {
            SqlUtils.replaceInParent(this, null);
        }

        return replaced;
    }
}
