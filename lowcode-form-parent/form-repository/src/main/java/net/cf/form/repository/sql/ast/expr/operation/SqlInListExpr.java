//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.cf.form.repository.sql.ast.expr.operation;

import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.AbstractSqlExprImpl;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.util.SqlExprUtils;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class SqlInListExpr extends AbstractSqlExprImpl implements SqlReplaceable {
    private boolean not;
    private SqlExpr expr;
    private List<SqlExpr> targetList = new ArrayList();

    public SqlInListExpr() {
    }

    public SqlInListExpr(SqlExpr expr) {
        this.setExpr(expr);
    }

    public SqlInListExpr(String expr, String... values) {
        this.setExpr(SqlExprUtils.toSqlExpr(expr));
        String[] var3 = values;
        int var4 = values.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String value = var3[var5];
            this.targetList.add(new SqlCharExpr(value));
        }

    }

    public SqlInListExpr(SqlExpr expr, boolean not) {
        this.setExpr(expr);
        this.not = not;
    }

    public SqlInListExpr clone() {
        SqlInListExpr x = new SqlInListExpr();
        x.not = this.not;
        if (this.expr != null) {
            x.setExpr(this.expr.cloneMe());
        }

        Iterator var2 = this.targetList.iterator();
        while(var2.hasNext()) {
            SqlExpr e = (SqlExpr)var2.next();
            SqlExpr e2 = e.cloneMe();
            e2.setParent(x);
            x.targetList.add(e2);
        }

        return x;
    }

    public boolean isNot() {
        return this.not;
    }

    public void setNot(boolean not) {
        this.not = not;
    }

    public SqlExpr getExpr() {
        return this.expr;
    }

    public void setExpr(SqlExpr expr) {
        if (expr != null) {
            expr.setParent(this);
        }

        this.expr = expr;
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
            if (this.expr != null) {
                this.expr.accept(visitor);
            }

            if (this.targetList != null) {
                Iterator var2 = this.targetList.iterator();
                while(var2.hasNext()) {
                    SqlExpr item = (SqlExpr)var2.next();
                    if (item != null) {
                        item.accept(visitor);
                    }
                }
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public List<SqlExpr> getChildren() {
        List<SqlExpr> children = new ArrayList();
        if (this.expr != null) {
            children.add(this.expr);
        }

        children.addAll(this.targetList);
        return children;
    }

    public boolean replace(SqlExpr expr, SqlExpr target) {
        if (this.expr == expr) {
            this.setExpr(target);
            return true;
        } else {
            for(int i = this.targetList.size() - 1; i >= 0; --i) {
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
