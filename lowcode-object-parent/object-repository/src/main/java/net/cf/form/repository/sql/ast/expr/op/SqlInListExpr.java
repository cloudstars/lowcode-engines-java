//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.cf.form.repository.sql.ast.expr.op;

import net.cf.form.repository.sql.ast.SqlObject;
import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.util.SqlExprUtils;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * SQL in 表达式
 *
 * @author clouds
 */
public final class SqlInListExpr extends AbstractCanNotExpr implements SqlReplaceable {

    /**
     * 左边的表达式
     */
    private SqlExpr left;

    /**
     * 右边的值列表
     */
    private List<SqlExpr> targetList = new ArrayList();

    public SqlInListExpr() {
    }

    public SqlInListExpr(SqlExpr left) {
        this.setLeft(left);
    }

    public SqlInListExpr(String left, String... values) {
        this.setLeft(SqlExprUtils.toSqlExpr(left));
        int l = values.length;
        for (int i = 0; i < l; i++) {
            this.targetList.add(new SqlCharExpr(values[i]));
        }
    }

    public SqlInListExpr(SqlExpr left, boolean not) {
        this.setLeft(left);
        this.not = not;
    }

    @Override
    public SqlInListExpr cloneMe() {
        SqlInListExpr x = new SqlInListExpr();
        x.not = this.not;
        if (this.left != null) {
            x.setLeft(this.left.cloneMe());
        }

        Iterator var2 = this.targetList.iterator();
        while (var2.hasNext()) {
            SqlExpr e = (SqlExpr) var2.next();
            SqlExpr e2 = e.cloneMe();
            e2.setParent(x);
            x.targetList.add(e2);
        }

        return x;
    }

    public SqlExpr getLeft() {
        return this.left;
    }

    public void setLeft(SqlExpr left) {
        if (left != null) {
            left.setParent(this);
        }

        this.left = left;
    }

    public void addTarget(SqlExpr x) {
        x.setParent(this);
        this.targetList.add(x);
    }

    public void addTarget(int index, SqlExpr x) {
        x.setParent(this);
        this.targetList.add(index, x);
    }

    public List<SqlExpr> getTargetList() {
        return this.targetList;
    }

    public void setTargetList(List<SqlExpr> targetList) {
        this.targetList = targetList;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            if (this.left != null) {
                this.left.accept(visitor);
            }

            if (this.targetList != null) {
                Iterator var2 = this.targetList.iterator();
                while (var2.hasNext()) {
                    SqlExpr item = (SqlExpr) var2.next();
                    if (item != null) {
                        item.accept(visitor);
                    }
                }
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

        children.addAll(this.targetList);
        return children;
    }

    @Override
    public boolean replace(SqlExpr expr, SqlExpr target) {
        if (this.left == expr) {
            this.setLeft(target);
            return true;
        } else {
            for (int i = this.targetList.size() - 1; i >= 0; --i) {
                if (this.targetList.get(i) == expr) {
                    if (target == null) {
                        this.targetList.remove(i);
                    } else {
                        this.targetList.set(i, target);
                        target.setParent(this);
                    }

                    return true;
                }
            }

            return false;
        }
    }
}
