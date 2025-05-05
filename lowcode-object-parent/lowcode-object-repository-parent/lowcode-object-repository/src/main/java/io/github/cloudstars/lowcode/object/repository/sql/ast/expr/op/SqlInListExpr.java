package io.github.cloudstars.lowcode.object.repository.sql.ast.expr.op;


import io.github.cloudstars.lowcode.object.repository.sql.ast.SqlObject;
import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.SqlExpr;
import io.github.cloudstars.lowcode.object.repository.sql.ast.expr.literal.SqlCharExpr;
import io.github.cloudstars.lowcode.object.repository.sql.util.SqlExprUtils;
import io.github.cloudstars.lowcode.object.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL in 表达式
 *
 * @author clouds
 */
public final class SqlInListExpr extends AbstractNotableBinaryOpExpr {

    public SqlInListExpr() {
        this(null);
    }

    public SqlInListExpr(SqlExpr left) {
        this.setLeft(left);
        this.operator = SqlBinaryOperator.IN;
        this.right = new SqlListExpr();
    }

    public SqlInListExpr(String left, String... values) {
        this.setLeft(SqlExprUtils.toSqlExpr(left));
        int l = values.length;
        for (int i = 0; i < l; i++) {
            ((SqlListExpr) this.right).addItem(new SqlCharExpr(values[i]));
        }
    }

    public SqlInListExpr(SqlExpr left, boolean not) {
        this.setLeft(left);
        this.not = not;
    }

    @Override
    public SqlInListExpr cloneMe() {
        SqlInListExpr x = new SqlInListExpr();
        this.cloneTo(x);
        return x;
    }

    public void addTarget(SqlExpr x) {
        x.setParent(this);
        ((SqlListExpr) this.right).addItem(x);
    }

    public List<SqlExpr> getTargetList() {
        return ((SqlListExpr) this.right).getItems();
    }

    public void setTargetList(List<SqlExpr> targetList) {
        for (SqlExpr e : targetList) {
            ((SqlListExpr) this.right).addItem(e);
        }
    }

    @Override
    public void accept(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.left != null) {
                this.left.accept(visitor);
            }

            if (this.right != null) {
                List<SqlExpr> targetList = this.getTargetList();
                this.nullSafeAcceptChildren(visitor, targetList);
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public List<SqlObject> getChildren() {
        List<SqlObject> children = new ArrayList();
        if (this.left != null) {
            children.add(this.left);
        }

        children.addAll(((SqlListExpr) this.right).getChildren());
        return children;
    }

    @Override
    public boolean replace(SqlExpr expr, SqlExpr target) {
        if (this.left == expr) {
            this.setLeft(target);
            return true;
        } else {
            List<SqlExpr> targetList = ((SqlListExpr) this.right).getItems();
            for (int i = targetList.size() - 1; i >= 0; --i) {
                if (targetList.get(i) == expr) {
                    if (target == null) {
                        targetList.remove(i);
                    } else {
                        targetList.set(i, target);
                        target.setParent(this);
                    }

                    return true;
                }
            }

            return false;
        }
    }
}
