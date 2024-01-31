package net.cf.form.engine.oql.ast.expr.identifier;

import net.cf.form.engine.oql.ast.OqlReplaceable;
import net.cf.form.engine.oql.ast.expr.OqlExpr;

/**
 *聚合函数表达式
 *
 * @author clouds
 */
public class OqlAggregateExpr extends OqlMethodInvokeExpr implements OqlReplaceable {

    /**
     * 聚合属性
     */
    protected OqlAggregateOption option;

    /**
     * 是否在 group 中
     */
    protected boolean withinGroup = false;

    public OqlAggregateExpr(String methodName) {
        super(methodName);
    }

    public OqlAggregateOption getOption() {
        return option;
    }

    public void setOption(OqlAggregateOption option) {
        this.option = option;
    }

    public boolean isWithinGroup() {
        return withinGroup;
    }

    public void setWithinGroup(boolean withinGroup) {
        this.withinGroup = withinGroup;
    }

    @Override
    public OqlMethodInvokeExpr clone() {
        OqlMethodInvokeExpr x = new OqlMethodInvokeExpr();
        x.setMethodName(this.getMethodName());
        for (OqlExpr argument : this.arguments) {
            x.addArgument(argument);
        }

        return x;
    }

    @Override
    public boolean replace(OqlExpr source, OqlExpr target) {
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
