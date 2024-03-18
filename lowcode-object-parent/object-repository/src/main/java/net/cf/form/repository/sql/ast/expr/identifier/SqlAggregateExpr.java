package net.cf.form.repository.sql.ast.expr.identifier;

import net.cf.form.repository.sql.ast.SqlReplaceable;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.visitor.SqlAstVisitor;

/**
 *聚合函数表达式
 *
 * @author clouds
 */
public class SqlAggregateExpr extends SqlMethodInvokeExpr implements SqlReplaceable {

    /**
     * 聚合属性
     */
    protected SqlAggregateOption option;

    /**
     * 是否在 group 中
     */
    protected boolean withinGroup = false;

    public SqlAggregateExpr(String methodName) {
        super(methodName);
    }

    public SqlAggregateOption getOption() {
        return option;
    }

    public void setOption(SqlAggregateOption option) {
        this.option = option;
    }

    public boolean isWithinGroup() {
        return withinGroup;
    }

    public void setWithinGroup(boolean withinGroup) {
        this.withinGroup = withinGroup;
    }

    @Override
    protected void accept0(SqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            for (SqlExpr arg : this.arguments) {
                if (arg != null) {
                    arg.accept(visitor);
                }
            }
        }

        visitor.endVisit(this);    }

    @Override
    public SqlMethodInvokeExpr cloneMe() {
        SqlMethodInvokeExpr x = new SqlMethodInvokeExpr();
        x.setMethodName(this.getMethodName());
        for (SqlExpr argument : this.arguments) {
            x.addArgument(argument);
        }

        return x;
    }

    @Override
    public boolean replace(SqlExpr source, SqlExpr target) {
        if (target == null) {
            return false;
        } else {
            for (int i = 0; i < this.arguments.size(); ++i) {
                if (this.arguments.get(i) == source) {
                    this.arguments.set(i, target);
                    target.setParent(this);
                    return true;
                }
            }

            return false;
        }
    }
}
