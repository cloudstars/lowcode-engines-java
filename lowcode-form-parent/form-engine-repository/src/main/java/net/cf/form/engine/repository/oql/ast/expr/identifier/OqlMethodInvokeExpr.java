package net.cf.form.engine.repository.oql.ast.expr.identifier;

import net.cf.form.engine.repository.oql.visitor.OqlAstVisitor;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.OqlExprImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法调用表达式
 *
 * @author clouds
 */
@Deprecated
public class OqlMethodInvokeExpr extends OqlExprImpl {

    protected final List<QqlExpr> arguments = new ArrayList();

    protected String methodName;

    public OqlMethodInvokeExpr() {
    }

    public OqlMethodInvokeExpr(String methodName) {
        this.methodName = methodName;
    }

    public OqlMethodInvokeExpr(String methodName, QqlExpr... args) {
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

    public List<QqlExpr> getArguments() {
        return this.arguments;
    }

    public void setArgument(int i, QqlExpr arg) {
        this.arguments.set(i, arg);
        this.addChild(arg);
    }

    public void addArgument(int i, QqlExpr arg) {
        this.arguments.add(i, arg);
        this.addChild(arg);
    }

    public void addArgument(QqlExpr arg) {
        this.arguments.add(arg);
        this.addChild(arg);
    }

    @Override
    protected void accept0(OqlAstVisitor visitor) {
        if (visitor.visit(this)) {
            for (QqlExpr arg : this.arguments) {
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
        for (QqlExpr arg : this.arguments) {
            x.addArgument(arg.clone());
        }

        return x;
    }
}

