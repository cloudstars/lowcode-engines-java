package io.github.cloudstars.lowcode.formula.commons.ast.expr.identifier;

import io.github.cloudstars.lowcode.formula.commons.ast.expr.AbstractFxExprImpl;
import io.github.cloudstars.lowcode.formula.commons.ast.expr.FxExpr;
import io.github.cloudstars.lowcode.formula.commons.visitor.FxAstVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法调用表达式
 *
 * @author clouds
 */
public class FxMethodInvokeExpr extends AbstractFxExprImpl {

    protected final List<FxExpr> arguments = new ArrayList();

    protected String methodName;

    public FxMethodInvokeExpr() {
    }

    public FxMethodInvokeExpr(String methodName) {
        this.methodName = methodName;
    }

    public FxMethodInvokeExpr(String methodName, FxExpr... args) {
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

    public List<FxExpr> getArguments() {
        return this.arguments;
    }

    public void setArgument(int i, FxExpr arg) {
        if (arg != null) {
            arg.setParent(this);
        }

        this.arguments.set(i, arg);
    }

    public void addArgument(FxExpr arg) {
        if (arg != null) {
            arg.setParent(this);
        }

        this.arguments.add(arg);
    }

    @Override
    public void accept(FxAstVisitor visitor) {
        if (visitor.visit(this)) {
            for (FxExpr arg : this.arguments) {
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
    public FxMethodInvokeExpr clone() {
        FxMethodInvokeExpr x = new FxMethodInvokeExpr();
        x.methodName = this.methodName;
        for (FxExpr arg : this.arguments) {
            x.addArgument(arg.clone());
        }

        return x;
    }
}

