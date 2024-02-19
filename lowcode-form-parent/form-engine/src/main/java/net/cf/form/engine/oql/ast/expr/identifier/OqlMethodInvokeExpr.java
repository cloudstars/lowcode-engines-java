package net.cf.form.engine.oql.ast.expr.identifier;

import net.cf.form.engine.oql.ast.expr.OqlExpr;
import net.cf.form.engine.oql.ast.expr.AbstractOqlExprImpl;
import net.cf.form.engine.oql.visitor.OqlAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法调用表达式
 *
 * @author clouds
 */
public class OqlMethodInvokeExpr extends AbstractOqlExprImpl {

    protected final List<OqlExpr> arguments = new ArrayList();

    protected String methodName;

    public OqlMethodInvokeExpr() {
    }

    public OqlMethodInvokeExpr(String methodName) {
        this.methodName = methodName;
    }

    public OqlMethodInvokeExpr(String methodName, OqlExpr... args) {
        this.methodName = methodName;

        for (int i = 0; i < args.length; i++) {
            this.addArgument(args[i]);
        }
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<OqlExpr> getArguments() {
        return this.arguments;
    }

    public void setArgument(int i, OqlExpr arg) {
        this.arguments.set(i, arg);
        this.addChild(arg);
    }

    public void addArgument(int i, OqlExpr arg) {
        this.arguments.add(i, arg);
        this.addChild(arg);
    }

    public void addArgument(OqlExpr arg) {
        this.arguments.add(arg);
        this.addChild(arg);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            for (OqlExpr arg : this.arguments) {
                if (arg != null) {
                    arg.accept(visitor);
                }
            }
        }

        visitor.endVisit(this);
    }

    @Override
    public List getChildren() {
        return this.arguments;
    }

    @Override
    public OqlMethodInvokeExpr clone() {
        OqlMethodInvokeExpr x = new OqlMethodInvokeExpr();
        x.methodName = this.methodName;
        for (OqlExpr arg : this.arguments) {
            x.addArgument(arg.clone());
        }

        return x;
    }
}

