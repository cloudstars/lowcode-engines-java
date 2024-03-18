package net.cf.form.repository.sql.ast.expr.op;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.util.SqlUtils;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
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
            SqlExpr itemX = item.cloneMe();
            itemX.setParent(this);
            x.items.add(itemX);
        }

        return x;
    }

    @Override
    public List<? extends SqlObject> getChildren() {
        return this.items;
    }

    public List<SqlExpr> getItems() {
        return this.items;
    }

    public void addItem(SqlExpr item) {
        this.addItem(this.items.size(), item);
    }

    public void addItem(int index, SqlExpr item) {
        if (item instanceof SqlBinaryOpExpr) {
            // 待加入的表达式也是两元表达式
            SqlBinaryOpExpr binaryOpExpr = (SqlBinaryOpExpr) item;
            // 待轵入的两元表达式的操作符与当前分组的操作符相同
            if (binaryOpExpr.getOperator() == this.operator) {
                this.addItem(binaryOpExpr.getLeft());
                this.addItem(binaryOpExpr.getRight());
                return;
            }
        } else if (item instanceof SqlBinaryOpExprGroup) {
            // 待加入的表达式也是分组
            SqlBinaryOpExprGroup group = (SqlBinaryOpExprGroup) item;
            if (group.operator == this.operator) {
                // 待加入的分组与当前分组的操作符相同
                List<SqlExpr> groupItems = group.getItems();
                for (SqlExpr groupItem : groupItems) {
                    this.addItem(groupItem);
                }
                return;
            }
        }

        this.items.add(index, item);
        if (item != null) {
            item.setParent(this);
        }
    }

    public SqlBinaryOperator getOperator() {
        return this.operator;
    }

    @Override
    public boolean replace(SqlExpr expr, SqlExpr target) {
        boolean replaced = false;
        for (int i = 0; i < this.items.size(); ++i) {
            if (this.items.get(i) == expr) {// TODO 这里没有实现equals，所以==比较有问题
                if (target == null) {
                    this.items.remove(i);
                } else if (target instanceof SqlBinaryOpExpr && ((SqlBinaryOpExpr) target).getOperator() == this.operator) {
                    this.items.remove(i);
                    List<SqlExpr> list = SqlBinaryOpExpr.split(target, this.operator);
                    for (int j = 0; j < list.size(); ++j) {
                        SqlExpr o = list.get(j);
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
